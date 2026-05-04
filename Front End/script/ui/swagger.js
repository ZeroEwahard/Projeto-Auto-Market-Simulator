export function swagger() {
  const abaPost = document.getElementById("modal-post")
  const abaPatch = document.getElementById("modal-patch")
  const abaDelete = document.getElementById("modal-delete")

  if (abaPost || abaPatch || abaDelete) {
    new Map([
      ["modal-post", "post"],
      ["modal-patch", "patch"],
      ["modal-delete", "delete"],
    ]);
    [abaPost, abaPatch, abaDelete].forEach((el) => {
      if (!el) return
      el.addEventListener("click", () => {
        window.location.assign("http://localhost:8080/swagger-ui")
      })
    })
  } else {
    alert("Ops! acontenceu algum erro")
  }
}