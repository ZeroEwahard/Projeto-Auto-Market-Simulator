import { API_URL } from "../main/config.js"

export async function simulacaoCompra({ carroId, entrada, parcelas }) {
  if (!API_URL) {
    throw new Error("Api indefinido")
  }

  if (!carroId) {
    throw new Error("carroId obrigatório")
  }

  if (typeof entrada !== "number") {
    throw new Error("entrada inválida")
  }

  if (typeof parcelas !== "number") {
    throw new Error("parcelas inválidas")
  }

  const controller = new AbortController()

  const timeout = setTimeout(() => {
    controller.abort()
  }, 10000)

  try {
    const resp = await fetch(`${API_URL}/compras`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        carroId,
        entrada,
        parcelas,
      }),
      signal: controller.signal,
    })

    clearTimeout(timeout)

    const text = await resp.text()

    let data = {
      mensagem() {
        return undefined;
      }
    }

    try {
      data = text ? JSON.parse(text) : {}
    } catch {
      throw new Error("Resposta inválida da API")
    }

    if (!resp.ok) {
      throw new Error(
          data?.mensagem ||
          `Erro ${resp.status}`
      )
    }

    return data
  } catch (err) {
    if (err.name === "AbortError") {
      throw new Error("Tempo de resposta excedido")
    }

    throw err
  }
}
