export function exibaModal() {
  const modal = new bootstrap.Modal(document.getElementById("modalSimulacao"))
  modal.show()
}

function exibaConfig() {
  const modal = new bootstrap.Modal(document.getElementById("modalCrud"))
  modal.show()
}

export function abaConfig() {
  const config = document.getElementById("cruds")
  if (config) {
    config.addEventListener("click", () => {
      exibaConfig()
    })
  }
}
