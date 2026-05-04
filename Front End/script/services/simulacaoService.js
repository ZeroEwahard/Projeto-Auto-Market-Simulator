import { API_URL } from "../main/config.js"

export async function simulacaoCompra({ carroId, entrada, parcelas }) {
  if (!API_URL) {
    console.error("simulacaoService: API_URL está vazio/indefinido", {
      API_URL,
    })
    throw new Error("Api indefinido")
  }
  try {
    console.log("simulacaoService: enviando simulação", {
      API_URL,
      carroId,
      entrada,
      parcelas,
    })
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
    })

    if (!resp.ok) {
      const corpo = await resp.text().catch(() => "")
      throw new Error(`Erro na simulação: ${resp.status} ${corpo}`)
    }
    const json = await resp.json()
    console.log("simulacaoService: resposta", json)
    return json
  } catch (err) {
    console.error("simulacaoService: fetch erro", err)
    throw err
  }
}
