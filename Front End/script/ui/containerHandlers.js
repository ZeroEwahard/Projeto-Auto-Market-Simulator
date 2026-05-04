import { exibaModal } from "./modal.js"
import { fichaTexto } from "./fichaTecnica.js"

export function attachContainerHandlers(getCarrosInstancias) {
  const container = document.getElementById("carros-destaques")
  container?.addEventListener("click", (e) => {
    const simularBtn = e.target.closest(".simular")
    if (simularBtn) {
      window.carroSelecionado = simularBtn.closest(".carro")
      exibaModal()
      return
    }

    const fichaBtn = e.target.closest(".btn-primary")
    if (!fichaBtn) return

    const card = fichaBtn.closest(".carro")
    if (!card) return

    const index = Number(card.dataset.index)
    const carroObj = getCarrosInstancias()[index]
    const nome = card.querySelector(".card-title")?.textContent?.trim() ?? ""

    if (!carroObj) {
      alert(
        "Ficha técnica não encontrada! Verifique se está conectado ao Back-End",
      )
      return
    }
    alert(fichaTexto(carroObj, nome))
  })
}
