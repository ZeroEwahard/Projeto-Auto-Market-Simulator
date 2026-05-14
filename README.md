# 🚗 AutoMarket

O **AutoMarket** é uma aplicação web para visualização, gerenciamento e simulação de financiamento de veículos. O projeto contém um back-end em Java/Spring Boot e um front-end dinâmico em HTML/CSS/JavaScript e Bootstrap.

## 📄 Sumário
- Visão geral
- Tecnologias
- Estrutura do Projeto
- Como rodar (Back End)
- Como rodar (Front End)
- Configurações e variáveis de ambiente
- Endpoints principais
- Testes
- Observações

## Visão Geral
O objetivo do AutoMarket é fornecer um catálago de veículos com filtros, ficha técnica por veículo e um simulador de financiamento (método Price). O sistema utiliza PostgreSQL no back-end e uma UI em JavaScript no front-end.

## 🛠️ Tecnologia
**Front-End:**
* HTML
* CSS
* JavaScript (Manipulação de DOM, consumo da API e redenrização dinâmica)
* Bootstrap 5

* **Back-End & Banco de Dados:**
* Java 21
* Spring Boot 4
* Spring Data JPA
* PostgreSQL (Armazenamento e persistência dos dados)
* Swagger (Documentação da API e interface do CRUD)

## Estrutura principal
* Back End/: código Spring Boot (mvnw, pom.xml)
* Front End/: arquivos (index.html, script/, css/, assets/)

## Como rodar - Back End
Pré-requisitos:

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
