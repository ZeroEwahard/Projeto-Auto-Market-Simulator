export function faixa() {
  const faixaPreco = document.getElementById("preco")
  const valor = document.getElementById("valor")

  if (faixaPreco && valor) {
    faixaPreco.addEventListener("input", () => {
      valor.textContent = `${Number(faixaPreco.value).toLocaleString("pt-BR")} R$`
      valor.style.transform = `scale(${1 + faixaPreco.value / 1000000})`
    })
    faixaPreco.addEventListener("mousedown", () => {
      valor.style.color = "#28a745"
    })
    faixaPreco.addEventListener("mouseup", () => {
      valor.style.color = "#007bff"
      valor.style.fontWeight = "bold"
    })
  }
}
