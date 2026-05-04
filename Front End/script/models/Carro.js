export default class Carro {
  constructor(props = {}) {
    Object.assign(this, {
      marca: "",
      modelo: "",
      ano: 0,
      km: 0,
      combustivel: "",
      transmissao: "",
      motor: "",
      potencia: 0,
      torque: "",
      consumo: "",
      tipo: "",
      condicao: "",
      imagem: "",
      preco: 0,
      ...props
    })
  }
}