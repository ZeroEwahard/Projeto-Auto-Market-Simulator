export const capitalizar = (s) =>
	s ? s[0].toUpperCase() + s.slice(1).toLowerCase() : ""
export const moeda = (v) => Number(v ?? 0).toLocaleString("pt-BR")