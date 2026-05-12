export function infoTaxas() {
  const inforTax = document.getElementById("taxas")
  inforTax.addEventListener("click", () => {
    alert(`
📌 SISTEMA PRICE (TABELA PRICE)
As taxas deste simulador utilizam o Sistema Francês de Amortização, conhecido como Tabela Price.
Esse método é amplamente usado em:
• financiamentos
• empréstimos
• compras parceladas

📖 COMO FUNCIONA
• As parcelas permanecem fixas.
• No início do contrato, os juros são maiores.
• Com o passar do tempo, a amortização aumenta.
• O saldo devedor reduz gradualmente.

📐 FÓRMULA
PMT = PV × [ i(1+i)^n ] / [ (1+i)^n − 1 ]

📚 LEGENDA
PMT → valor da parcela
PV  → valor financiado
i = 2%  → taxa de juros
n  → número de parcelas
  `)

    setTimeout(() => {
      window.scrollTo({ top: document.body.scrollHeight, behavior: "instant" })
    }, 0)
  })

  history.scrollRestoration = "manual"
  window.addEventListener("load", () => {
    window.scrollTo({
      top: 0,
      behavior: "instant",
    })
  })
}