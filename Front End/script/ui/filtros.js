import { parsePreco, parseQuilometragem } from "../util/parse.js"

let condicaoSelecionada = null

function getDom() {
  return {
    container: document.getElementById("carros-destaques"),
    busca: document.getElementById("busca"),
    filtroTipo: document.getElementById("filtroTipo"),
    ordenarPor: document.getElementById("ordenarPor"),
  }
}

function obterCards() {
  const { container } = getDom()
  return container ? Array.from(container.querySelectorAll(".carro")) : []
}

function valorCard(card, chaveDataset, seletor, parser) {
  if (card.dataset && card.dataset[chaveDataset])
    return Number(card.dataset[chaveDataset])
  const el = card.querySelector(seletor)
  if (!el) return 0
  return parser
    ? parser(el.textContent)
    : Number(el.textContent.replace(/\D/g, "")) || 0
}

const comparadores = {
  "preco-asc": (a, b) => a.preco - b.preco,
  "preco-desc": (a, b) => b.preco - a.preco,
  "km-asc": (a, b) => a.km - b.km,
  "km-desc": (a, b) => b.km - a.km,
}

const mapaCondicao = {
  estoque: "estoque",
  novo: "novo",
  usado: "usado",
}

export function aplicarFiltrosEOrdenar() {
  const { busca, filtroTipo, ordenarPor, container } = getDom()
  const termo = busca?.value?.toLowerCase?.() || ""
  const tipo = filtroTipo?.value || "todos"
  const orden = ordenarPor?.value || "padrao"

  const cards = obterCards()
  let algumVisivel = false

  const filtrados = cards.filter((card) => {
    if (condicaoSelecionada && condicaoSelecionada !== "estoque") {
      if ((card.dataset.condicao || "").toLowerCase() !== condicaoSelecionada)
        return false
    }
    if (tipo !== "todos") {
      const dataTipo = (card.dataset.tipo || "").toLowerCase()
      const titulo = (
        card.querySelector(".card-title")?.textContent || ""
      ).toLowerCase()
      if (dataTipo) {
        const tipos = dataTipo
          .split(/[,;\s]+/)
          .map((s) => s.trim())
          .filter(Boolean)
        if (!tipos.includes(tipo)) return false
      } else {
        if (!titulo.includes(tipo)) return false
      }
    }
    if (termo) {
      const textoCard = (card.textContent || "").toLowerCase()
      if (!textoCard.includes(termo)) return false
    }
    return true
  })

  cards.forEach((c) => (c.style.display = "none"))
  filtrados.forEach((c) => {
    c.style.display = ""
    algumVisivel = true
  })

  const mensagem = document.getElementById("erroNaoEncontrado")
  if (mensagem) mensagem.style.display = algumVisivel ? "none" : "block"

  if (orden !== "padrao" && comparadores[orden]) {
    const ordenados = filtrados
      .map((card) => ({
        card,
        preco: valorCard(card, "preco", ".valor-carro", parsePreco),
        km: valorCard(card, "km", ".card-body", parseQuilometragem),
      }))
      .sort((a, b) => comparadores[orden](a, b))
      .map((obj) => obj.card)

    ordenados.forEach((card) => container.appendChild(card))
  }
}

function handlePorCimaClick(e) {
  const btn = e.target.closest("a.porCima")
  if (!btn) return
  e.preventDefault()

  const texto = btn.textContent.toLowerCase()
  condicaoSelecionada =
    Object.entries(mapaCondicao).find(([k]) => texto.includes(k))?.[1] ?? null

  document
    .querySelectorAll("a.porCima")
    .forEach((x) => x.classList.remove("active"))
  btn.classList.add("active")
  aplicarFiltrosEOrdenar()
}

export function attachEvents() {
  document.addEventListener("click", handlePorCimaClick)
  const { busca, filtroTipo, ordenarPor } = getDom()
  if (busca) busca.addEventListener("input", aplicarFiltrosEOrdenar)
  if (filtroTipo) filtroTipo.addEventListener("change", aplicarFiltrosEOrdenar)
  if (ordenarPor) ordenarPor.addEventListener("change", aplicarFiltrosEOrdenar)
}
