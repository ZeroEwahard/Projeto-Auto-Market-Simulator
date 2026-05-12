export function obterCarroId(card, carroInstancias) {
  if (!card || !card.dataset) {
    console.error("Card inválido ou sem dataset:", card)
    return null
  }

  if (card.dataset.id) {
    const idNum = Number(card.dataset.id)
    if (Number.isFinite(idNum)) {
      return idNum
    }
    console.error("data-id inválido:", card.dataset.id)
  }

  if (card.dataset.index) {
    const idx = Number(card.dataset.index)
    if (Number.isFinite(idx)) {
      const obj = carroInstancias?.[idx]
      if (obj?.id) {
        return obj.id
      }
      console.error("Index fora do range ou objeto sem id:", idx, obj)
    } else {
      console.error("data-index inválido:", card.dataset.index)
    }
  }
  console.error("Não foi possível determinar carroId", card.dataset)
  return null
}

export function validador() {
  const entradaInput = document.getElementById("entradaInput")
  const parcelasInput = document.getElementById("parcelasInput")
  const entrada = entradaInput ? Number(entradaInput.value) : 0
  const parcelas = parcelasInput ? Number(parcelasInput.value) : 1
  const valor = entradaInput ? entradaInput.value : ""
  const precoCarro = Number(window.carroSelecionado?.dataset?.preco)

  if (Number.isNaN(entrada) || entrada <= 0) {
    alert("Informe um valor de entrada válido")
    return false
  }

  if (!/^\d+(\.\d{1,2})?$/.test(valor)) {
    alert("O valor deve ter no máximo 2 casas decimais")
    return false
  }

  if (!Number.isInteger(parcelas) || parcelas <= 0) {
    alert("Informe um número de parcelas válido.")
    return false
  }

  if (parcelas > 60) {
    alert("Máximo de 60 parcelas")
    return false
  }

  if (entrada > precoCarro) {
    alert("O valor da entrada não pode ser maior que o preço do carro")
    return false
  }

  if (entrada !== precoCarro && parcelas === 1) {
    alert("Para pagamento à vista, o valor deve ser igual ao preço total do carro")
    return false
  }

  if (entrada === precoCarro) {
    if (parcelasInput) parcelasInput.value = 1
    alert("Pagamento à vista")
    return true
  }

  return true
}
