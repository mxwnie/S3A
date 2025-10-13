const API = "http://localhost:6969/funcionarios";

document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("form-funcionario");
  if (!form) {
    console.error("Form #form-funcionario não encontrado.");
    return;
  }

  form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const fd = new FormData(form);
    const payload = {
      nome: fd.get("nome"),
      cpf: fd.get("cpf"),
      cargo: fd.get("cargo"),
      salario: parseFloat((fd.get("salario") || "0").replace(",", ".")),
      dataAdmissao: fd.get("dataAdmissao"), // yyyy-MM-dd
      telefone: fd.get("telefone"),
    };

    try {
      const resp = await fetch(API, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload),
      });

      if (!resp.ok) {
        const txt = await resp.text();
        throw new Error(`HTTP ${resp.status} - ${txt}`);
      }

      form.reset();
      await carregarTabela();
    } catch (err) {
      console.error("Falha no POST:", err);
      alert("Erro ao salvar. Veja o console.");
    }
  });

  carregarTabela();
});

async function carregarTabela() {
  const resp = await fetch(API);
  const dados = await resp.json();
  const tbody = document.querySelector("#tabela tbody");
  tbody.innerHTML = "";
  dados.forEach((f) => {
    const tr = document.createElement("tr");
    tr.innerHTML = `
      <td>${f.id ?? ""}</td>
      <td>${f.nome ?? ""}</td>
      <td>${f.cpf ?? ""}</td>
      <td>${f.cargo ?? ""}</td>
      <td>${Number(f.salario ?? 0).toFixed(2)}</td>
      <td>${f.dataAdmissao ?? ""}</td>
      <td>${f.telefone ?? ""}</td>
    `;
    tbody.appendChild(tr);
  });
}
