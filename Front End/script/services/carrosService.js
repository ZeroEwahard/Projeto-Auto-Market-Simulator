import { API_URL } from "../main/config.js"
import Carro from "../models/Carro.js"

export async function carregarCarros() {
  const resp = await fetch(`${API_URL}/carros`)
  if (!resp.ok) throw new Error(`Erro ao buscar carros: ${resp.status}`)

  const data = await resp.json()

  return data.content.map(
    (item) =>
      new Carro({
        id: item.id,
        marca: item.marca,
        modelo: item.modelo,
        ano: item.ano,
        km: item.fichaTecnica?.km ?? item.km,
        combustivel: item.fichaTecnica?.combustivel ?? item.combustivel,
        transmissao: item.fichaTecnica?.transmissao ?? item.transmissao,
        motor: item.fichaTecnica?.motor ?? item.motor,
        potencia: item.fichaTecnica?.potencia ?? item.potencia,
        torque: item.fichaTecnica?.torque ?? item.torque,
        consumo: item.fichaTecnica?.consumo ?? item.consumo,
        tipo: item.tipo,
        condicao: item.condicao?.condicao ?? item.condicao,
        imagem: item.imagem,
        preco: item.preco,
      }),
  )
}
