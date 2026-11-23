// js/app.js
if (!localStorage.getItem('@sapz:token')) {
    window.location.href = 'index.html';
  }
  
  const user = JSON.parse(localStorage.getItem('@sapz:user') || '{}');
  document.getElementById('usuario-logado').textContent = `Conectado: ${user.nome || user.email || ''}`;
  
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
  
  // --- CARREGAR DADOS INICIAIS ---
  async function inicializar() {
    await Promise.all([
      carregarDashboard(),
      carregarEscolas(),
      carregarUnidadesCurriculares(),
      carregarSapzs(),
    ]);
  }
  
  async function carregarDashboard() {
    try {
      const [escolas, cursos, turmas, sapzs] = await Promise.all([
        apiFetch('/escolas'),
        apiFetch('/cursos'),
        apiFetch('/turmas'),
        apiFetch('/situacoes-aprendizagem'),
      ]);
      document.getElementById('stat-escolas').textContent = escolas.length;
      document.getElementById('stat-cursos').textContent = cursos.length;
      document.getElementById('stat-turmas').textContent = turmas.length;
      document.getElementById('stat-sapzs').textContent = sapzs.length;
    } catch (e) {
      console.error('Erro ao carregar dashboard:', e);
    }
  }
  
  // --- ESCOLAS ---
  async function carregarEscolas() {
    const escolas = await apiFetch('/escolas');
    const tbody = document.getElementById('tabela-escolas');
    tbody.innerHTML = escolas
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
    carregarDashboard();
  });
  
  // --- SITUAÇÕES DE APRENDIZAGEM ---
  async function carregarUnidadesCurriculares() {
    const ucs = await apiFetch('/unidades-curriculares');
    const select = document.getElementById('sapz-uc');
    select.innerHTML =
      '<option value="">Selecione...</option>' +
      ucs.map((uc) => `<option value="${uc.id}">${uc.nome} (${uc.cargaHoraria}h)</option>`).join('');
  }
  
  async function carregarSapzs() {
    const lista = await apiFetch('/situacoes-aprendizagem');
    const tbody = document.getElementById('tabela-sapz');
    tbody.innerHTML = lista
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
    carregarDashboard();
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