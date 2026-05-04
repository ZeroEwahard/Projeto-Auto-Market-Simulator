import { moeda, capitalizar } from "../util/formatadores.js"

export function exibiCarros(lista, container) {
  const URL = "http://localhost:8080/"
  container.innerHTML = ""

  lista.forEach((carro, index) => {
    const card = document.createElement("div")
    card.classList.add("carro", "card", "mb-3")

    card.dataset.index = index
    card.dataset.id = carro.id

    card.dataset.preco = carro.preco
    card.dataset.km = carro.km
    card.dataset.tipo = carro.tipo
    card.dataset.imagem = carro.imagem
    card.dataset.condicao = carro.condicao
    card.dataset.transmissao = carro.transmissao
    card.dataset.combustivel = carro.combustivel

    card.innerHTML = `
    <div class="container carros-destaques-fundo">
      <div class="row mt-4 carros-exibicao justify-content-center">
        <div class="col-md-6 mb-4 animar">
          <div class="card">
            <div class="position-relative overflow-hidden">  
                <img src="${URL + carro.imagem}" alt="${carro.marca} ${carro.modelo}" class="card-img-top img-fluid expandi img-carro foto">
            </div>
              
              <div class="card-body text-center">
              <h3 class="card-title fw-bold mb-0">${carro.marca}</h3>
              <h5 class="card-title fw-bold">${carro.modelo}</h5>
              <p class="mb-1"><strong>Tipo de Combustível: </strong> ${carro.combustivel}</p>
              <p class="mb-1"><strong>Transmissão: </strong> ${carro.transmissao}</p>
              <p class="mb-1"><strong>Tipo: </strong> ${carro.tipo}</p>
              <p class="mb-1"><strong>Condição do Carro: </strong> ${capitalizar(carro.condicao)}</p>
              <p class="valor-carro fw-bold mb-3">${moeda(carro.preco)} R$</p>
              <div class="d-flex gap-1 justify-content-center">
                <button class="btn btn-primary">Ver ficha</button>
                <button class="btn btn-dark simular" data-index="${index}">Simular Compra</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    `

    card.addEventListener("click", () => {
      window.carroSelecionado = card
      console.log("Carro selecionando:", lista)
    })

    container.appendChild(card)
  })
}
