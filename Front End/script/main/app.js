import { carregarCarros } from "../services/carrosService.js"
import { exibiCarros } from "../ui/renderCarros.js"
import { simulacaoCompra as simulacaoCompraService } from "../services/simulacaoService.js"
import {
  aplicarFiltrosEOrdenar,
  attachEvents as attachFiltros,
} from "../ui/filtros.js"
import { obterCarroId, validador } from "../util/validacao.js"
import { attachContainerHandlers } from "../ui/containerHandlers.js"
import { faixa } from "../ui/faixaDePreco.js"
import { swagger } from "../ui/swagger.js"
import { abaConfig, exibaModal } from "../ui/modal.js"
import { infoTaxas } from "../ui/tabelaPriceInfo.js"

let carrosInstancias = []

window.exibaModal = function (button) {
  window.carroSelecionado = button.closest(".carro")
  exibaModal()
}

async function init() {
  try {
    const container = document.getElementById("carros-destaques")

    carrosInstancias = await carregarCarros()
    exibiCarros(carrosInstancias, container)
    attachFiltros()
    attachContainerHandlers(() => carrosInstancias)
    aplicarFiltrosEOrdenar()
    faixa()
    abaConfig()
    swagger()
    infoTaxas()

    window.simulacaoCompra = async function () {
      try {
        if (!window.carroSelecionado) {
          alert("Selecione um carro antes de simular.")
          return
        }

        const card = window.carroSelecionado
        const carroId = obterCarroId(card, carrosInstancias)

        if (!Number.isFinite(carroId)) {
          console.error("carroId inválido:", carroId)
          alert("Erro ao identificar o carro selecionado")
          return
        }

        if (!validador()) {
          return
        }

        const entradaInput = document.getElementById("entradaInput")
        const parcelasInput = document.getElementById("parcelasInput")
        const entrada = entradaInput ? Number(entradaInput.value) : 0
        const parcelas = parcelasInput ? Number(parcelasInput.value) : 1

        const resultado = await simulacaoCompraService({
          carroId,
          entrada,
          parcelas,
        })

        alert(`\nSimulação de Compra\n
    Preço: R$ ${resultado.precoCarro.toLocaleString("pt-BR")}
    Entrada: R$ ${resultado.entrada.toLocaleString("pt-BR")}
    Parcelas: ${resultado.parcelas}
    Valor Restante: R$ ${resultado.valorFinanciamento.toLocaleString("pt-BR")}
    
    Valor das Parcelas: R$ ${resultado.valorParcela?.toLocaleString ? resultado.valorParcela.toLocaleString("pt-BR") : resultado.valorParcela}
    Valor Total Pago: R$ ${resultado.valorTotalPago?.toLocaleString ? resultado.valorTotalPago.toLocaleString("pt-BR") : resultado.valorTotalPago}`)
      } catch (erro) {
        console.error(erro)
        alert("Erro ao simular compra. Valores inesperados foram colocados!")
      }
    }

    const animacao = document.querySelectorAll(".animar")
    const observado = new IntersectionObserver(
      (entries) => {
        entries.forEach((entry) => {
          if (entry.isIntersecting) {
            entry.target.classList.add("ativo")
          } else {
            entry.target.classList.remove("ativo")
          }
        })
      },
      {
        threshold: 0.15,
      },
    )

    animacao.forEach((ani) => observado.observe(ani))

    window.voltaAoTopo = () => window.scroll({ top: 0, behavior: "smooth" })
    window.opcoes = () => window.scroll({ top: 1050, behavior: "smooth" })
    window.carrosInstancias = carrosInstancias
  } catch (err) {
    console.error(err)
  }
}

document.addEventListener("DOMContentLoaded", init)
