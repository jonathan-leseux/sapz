// js/app.js
if (!localStorage.getItem('@sapz:token')) {
  window.location.href = 'index.html';
}

const user = JSON.parse(localStorage.getItem('@sapz:user') || '{}');
const perfilNome = (user.perfil?.nome || user.perfilNome || 'ADMINISTRADOR').toUpperCase();
document.getElementById('usuario-logado').textContent = `Conectado: ${user.nome || user.email || ''} (${perfilNome})`;

// Oculta menus administrativos caso o perfil logado seja apenas INSTRUTOR
if (perfilNome === 'INSTRUTOR') {
  document.querySelectorAll('.admin-only').forEach((el) => (el.style.display = 'none'));
}

function logout() {
  localStorage.removeItem('@sapz:token');
  localStorage.removeItem('@sapz:user');
  window.location.href = 'index.html';
}

function mostrarSecao(idSecao, botao) {
  document.querySelectorAll('main section').forEach((sec) => sec.classList.add('hidden'));
  document.getElementById(idSecao).classList.remove('hidden');

  document.querySelectorAll('.nav-btn').forEach((btn) => btn.classList.remove('active'));
  botao.classList.add('active');
}

// --- CARREGAR TODOS OS DADOS ---
async function inicializar() {
  await Promise.all([
    carregarEscolas(),
    carregarCursos(),
    carregarUnidadesCurriculares(),
    carregarTurmas(),
    carregarSapzs(),
  ]);
}

// --- 1. ESCOLAS ---
async function carregarEscolas() {
  try {
    const escolas = await apiFetch('/escolas');
    document.getElementById('stat-escolas').textContent = escolas.length;

    document.getElementById('tabela-escolas').innerHTML = escolas
      .map(
        (e) => `
        <tr>
          <td>${e.codigoUnidade || '-'}</td>
          <td><strong>${e.nome}</strong></td>
          <td>${e.cidade} - ${e.estado}</td>
          <td>
            <button class="btn btn-primary" style="padding: 4px 10px; font-size: 0.8rem;"
              onclick='editarEscola(${JSON.stringify(e)})'>Editar</button>
          </td>
        </tr>`
      )
      .join('');

    const opcoesEscolas =
      '<option value="">Selecione a escola...</option>' +
      escolas.map((e) => `<option value="${e.id}">${e.nome}</option>`).join('');

    document.getElementById('curso-escola').innerHTML = opcoesEscolas;
    document.getElementById('turma-escola').innerHTML = opcoesEscolas;
  } catch (e) {
    console.error('Erro ao carregar escolas:', e);
  }
}

function editarEscola(escola) {
  document.getElementById('escola-id').value = escola.id;
  document.getElementById('escola-nome').value = escola.nome;
  document.getElementById('escola-codigo').value = escola.codigoUnidade || '';
  document.getElementById('escola-cidade').value = escola.cidade;
  document.getElementById('escola-estado').value = escola.estado;
}

document.getElementById('form-escola').addEventListener('submit', async (e) => {
  e.preventDefault();
  const id = document.getElementById('escola-id').value;
  const payload = {
    nome: document.getElementById('escola-nome').value,
    codigoUnidade: document.getElementById('escola-codigo').value,
    cidade: document.getElementById('escola-cidade').value,
    estado: document.getElementById('escola-estado').value.toUpperCase(),
    ativo: true,
  };

  if (id) {
    await apiFetch(`/escolas/${id}`, { method: 'PUT', body: JSON.stringify(payload) });
  } else {
    await apiFetch('/escolas', { method: 'POST', body: JSON.stringify(payload) });
  }

  e.target.reset();
  document.getElementById('escola-id').value = '';
  carregarEscolas();
});

// --- 2. CURSOS E UNIDADES CURRICULARES ---
async function carregarCursos() {
  try {
    const cursos = await apiFetch('/cursos');
    document.getElementById('stat-cursos').textContent = cursos.length;

    document.getElementById('tabela-cursos').innerHTML = cursos
      .map(
        (c) => `
        <tr>
          <td><strong>${c.nome}</strong></td>
          <td>${c.modalidade || '-'}</td>
          <td>${c.cargaHorariaTotal || c.cargaHoraria || '-'}h</td>
        </tr>`
      )
      .join('');

    const opcoesCursos =
      '<option value="">Selecione o curso...</option>' +
      cursos.map((c) => `<option value="${c.id}">${c.nome}</option>`).join('');

    document.getElementById('uc-curso').innerHTML = opcoesCursos;
    document.getElementById('turma-curso').innerHTML = opcoesCursos;
  } catch (e) {
    console.error('Erro ao carregar cursos:', e);
  }
}

document.getElementById('form-curso').addEventListener('submit', async (e) => {
  e.preventDefault();
  const payload = {
    nome: document.getElementById('curso-nome').value,
    modalidade: document.getElementById('curso-modalidade').value,
    cargaHorariaTotal: Number(document.getElementById('curso-ch').value),
    escolaId: Number(document.getElementById('curso-escola').value),
    ativo: true,
  };

  await apiFetch('/cursos', { method: 'POST', body: JSON.stringify(payload) });
  e.target.reset();
  carregarCursos();
});

document.getElementById('form-uc').addEventListener('submit', async (e) => {
  e.preventDefault();
  const payload = {
    nome: document.getElementById('uc-nome').value,
    cargaHoraria: Number(document.getElementById('uc-ch').value),
    cursoId: Number(document.getElementById('uc-curso').value),
  };

  await apiFetch('/unidades-curriculares', { method: 'POST', body: JSON.stringify(payload) });
  e.target.reset();
  carregarUnidadesCurriculares();
  alert('Unidade Curricular cadastrada com sucesso!');
});

// --- 3. TURMAS ---
async function carregarTurmas() {
  try {
    const turmas = await apiFetch('/turmas');
    document.getElementById('stat-turmas').textContent = turmas.length;

    document.getElementById('tabela-turmas').innerHTML = turmas
      .map(
        (t) => `
        <tr>
          <td><strong>${t.codigo || t.nome}</strong></td>
          <td>${t.turno || '-'}</td>
          <td>${t.dataInicio || '-'}</td>
          <td>${t.dataFim || '-'}</td>
        </tr>`
      )
      .join('');
  } catch (e) {
    console.error('Erro ao carregar turmas:', e);
  }
}

document.getElementById('form-turma').addEventListener('submit', async (e) => {
  e.preventDefault();
  const codigo = document.getElementById('turma-codigo').value;
  const payload = {
    codigo: codigo,
    nome: codigo,
    turno: document.getElementById('turma-turno').value,
    cursoId: Number(document.getElementById('turma-curso').value),
    escolaId: Number(document.getElementById('turma-escola').value),
    dataInicio: document.getElementById('turma-inicio').value,
    dataFim: document.getElementById('turma-fim').value,
    ativo: true,
  };

  await apiFetch('/turmas', { method: 'POST', body: JSON.stringify(payload) });
  e.target.reset();
  carregarTurmas();
});

// --- 4. SITUAÇÕES DE APRENDIZAGEM ---
async function carregarUnidadesCurriculares() {
  try {
    const ucs = await apiFetch('/unidades-curriculares');
    document.getElementById('sapz-uc').innerHTML =
      '<option value="">Selecione a UC...</option>' +
      ucs.map((uc) => `<option value="${uc.id}">${uc.nome} (${uc.cargaHoraria}h)</option>`).join('');
  } catch (e) {
    console.error('Erro ao carregar UCs:', e);
  }
}

async function carregarSapzs() {
  try {
    const lista = await apiFetch('/situacoes-aprendizagem');
    document.getElementById('stat-sapzs').textContent = lista.length;

    document.getElementById('tabela-sapz').innerHTML = lista
      .map(
        (sa) => `
        <tr>
          <td><strong>${sa.titulo}</strong></td>
          <td>${sa.tipoEstrategia}</td>
          <td>${sa.cargaHorariaSa}h</td>
          <td>
            <button class="btn btn-primary" style="background: #E30613; padding: 4px 10px; font-size: 0.8rem;"
              onclick="gerarPdf(${sa.id}, '${sa.titulo}')">Gerar PDF</button>
          </td>
        </tr>`
      )
      .join('');
  } catch (e) {
    console.error('Erro ao carregar SAPZs:', e);
  }
}

document.getElementById('form-sapz').addEventListener('submit', async (e) => {
  e.preventDefault();
  const payload = {
    titulo: document.getElementById('sapz-titulo').value,
    tipoEstrategia: document.getElementById('sapz-estrategia').value,
    unidadeCurricularId: Number(document.getElementById('sapz-uc').value),
    cargaHorariaSa: Number(document.getElementById('sapz-ch').value),
    contextualizacao: document.getElementById('sapz-contexto').value,
    desafio: document.getElementById('sapz-desafio').value,
    resultadosEsperados: document.getElementById('sapz-resultados').value,
    compartilhada: false,
  };

  await apiFetch('/situacoes-aprendizagem', { method: 'POST', body: JSON.stringify(payload) });
  e.target.reset();
  carregarSapzs();
});

async function gerarPdf(saId, titulo) {
  await apiFetch('/sapz-historico-pdf', {
    method: 'POST',
    body: JSON.stringify({
      situacaoAprendizagemId: saId,
      versao: '1.0',
      caminhoArquivoPdf: `/exports/sapz_${saId}_v1.pdf`,
      hashConteudo: btoa(`${saId}-${titulo}`).slice(0, 32),
    }),
  });
  alert(`Histórico PDF registrado com sucesso para: ${titulo}`);
}

inicializar();