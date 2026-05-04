export function fichaTexto(carro = {}, nomeExibido = "") {
  nomeExibido || `${carro.marca || ""} ${carro.modelo || ""}`.trim();
  return `Marca: ${carro.marca || ""}
Modelo: ${carro.modelo || ""}
Ano: ${Number(carro.ano || 0)}
Km: ${Number(carro.km || 0)}
Combustível: ${carro.combustivel || ""}
Transmissão: ${carro.transmissao || ""}
Motor: ${carro.motor || ""}
Potência: ${Number(carro.potencia || 0)}cv
Torque: ${carro.torque || ""} kgfm
Consumo: média de ${carro.consumo || ""}
Preço: ${Number(carro.preco || 0).toLocaleString("pt-BR")} R$`
}
