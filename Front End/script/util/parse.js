export function parsePreco(text) {
  if (!text) return 0
  const normalizar = String(text).replace(/\./g, "").replace(",", ".")
  const nums = normalizar.match(/\d+(\.\d+)?/g)
  if (!nums) return 0
  return Number(nums.at(-1))
}

export function parseQuilometragem(text) {
  if (!text) return 0
  const normalizar = String(text).replace(/\./g, "").replace(",", ".")
  const nums = normalizar.match(/\d+(\.\d+)?/g)
  if (!nums) return 0
  return Number(nums[0])
}
