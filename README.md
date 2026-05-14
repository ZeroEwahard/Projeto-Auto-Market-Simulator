# 🚗 AutoMarket

O **AutoMarket** é uma aplicação web completa para visualização, gerenciamento e simulação de financiamento de veículos. O projeto oferece um catálogo dinâmico de carros, busca avançada
e uma ferramenta integrada para calcular parcelamentos utilizando o Sistema de Amortização Francês (Tabela Price).

## 🚀 Funcionalidades

### 🛒 Catálogo e Exploração 
* **Vitrine de Veículos:** Os carros cadastrados são renderizados na página principal em formato de *cards*, exibindo imagem, marca, modelo, combustível, trasmissão, tipo, condição (novo/usado) e preço.
* **Filtros de Busca Avançados:** Encontre o carro ideal utilizando 4 tipos de filtros:
  * Quilometragem (KM)
  * Faixa de Preço
  * Condição (Novo ou Usado)
  * Pesquisa Geral (Nome, Marca, etc.)
* **Ficha Técnica:** Ao clicar em "Ver ficha", o usuário tem acesso a todos os detalhes do veículo selecionado (ano, motor, consumo, etc.).

### 💰 Simulador de Compra (Tabela Price)
* **Simulação Personalizada:** Ao clicar em "Simular Compra", um modal permite inserir o valor de **Entrada** e escolher a quantidade de **Parcelas** (até 60x).
* **Cálculo de Back-End:** O sistema processa o financiamento aplicando o método da Tabela Price com uma taxa de juros fixa de 2% ao mês.
* **Resumo Transparente:** O resultado é exibido em um alerta contendo: Preço do carro, Entrada, Quantidade de parcelas, Valor restante a financiar, Valor de cada parcela e o Total pago ao final.
* **Área Educacional:** Uma seção dedicada a explicar o que é e como funciona a fórmula do Sistema Price, garantindo transparência para o usuário.

### ⚙️ Administração e Usabilidade
* **Painel de Controle (CRUD):** Através do botão de configuração ⚙️, o usuário abre um modal que o redireciona para a interface do **Swagger**. Lá, é possível realizar todas as operações de CRUD (Criar, Ler, Atualizar e Deletar) dos veículos diretamente na API.
* **Scroll-to-Top Dinâmico:** Botão flutuante que permite ao usuário retornar instantaneamente ao topo da página, melhorando a navegação.

## 🛠️ Tecnologia Utilizadas
**Front-End:**
* HTML5
* CSS3
* JavaScript (Manipulação de DOM, consumo da API e redenrização dinâmica)
* Bootstrap (Estilização responsiva, Modais e Cards)

**Back-End & Banco de Dados:**
* Java (Spring Boot, Lógica de negócios, cálculo financeiro e endpoints)
* PostgreSQL (Armazenamento e persistência dos dados)
* Swagger (Documentação da API e interface do CRUD)
