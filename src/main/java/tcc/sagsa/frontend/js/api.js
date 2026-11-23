// js/api.js
const API_BASE_URL = 'http://localhost:8080';

async function apiFetch(endpoint, options = {}) {
  const token = localStorage.getItem('@sapz:token');

  const headers = {
    'Content-Type': 'application/json',
    ...(token ? { Authorization: `Bearer ${token}` } : {}),
    ...options.headers,
  };

  const response = await fetch(`${API_BASE_URL}${endpoint}`, {
    ...options,
    headers,
  });

  if (response.status === 401) {
    localStorage.removeItem('@sapz:token');
    localStorage.removeItem('@sapz:user');
    window.location.href = 'index.html';
    return null;
  }

  if (!response.ok) {
    const errorText = await response.text();
    throw new Error(errorText || `Erro HTTP: ${response.status}`);
  }

  if (response.status === 204) return null;
  return response.json();
}