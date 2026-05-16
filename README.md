# 🚗 AutoMarket

O **AutoMarket** é uma aplicação web para visualização, gerenciamento e simulação de financiamento de veículos. O projeto contém um back-end em Java/Spring Boot e um front-end estático em HTML/CSS/JavaScript e Bootstrap.

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

**Back-End & Banco de Dados:**
* Java 21
* Spring Boot 4
* Spring Data JPA
* PostgreSQL (Armazenamento e persistência dos dados)
* Swagger (Documentação da API e interface do CRUD)

## Estrutura principal
* Back End/: código Spring Boot (mvnw, pom.xml)
* Front End/: arquivos (index.html, script/, css/, assets/)

## Como rodar - Back End
Pré-requisitos: JDK 21 instalado e um banco PostgreSQL.
### Configurar variáveis de ambiente (exemplo PowerShell):
```
$env:DB_USER = "postgres"
$env:DB_PASSWORD = "sua_senha"
$env:DB_HOST = "localhost:5432" # opcional - padrão é localhost:5432
cd "Back End"
.\mvnw.cmd spring-boot:run
```
ou (Linux/macOS):
```
export DB_USER=postgres
export DB_PASSWORD=sua_senha
export DB_HOST=localhost:5432
cd "Back End"
./mvnw spring-boot:run
```
Build e execução do JAR:
```
cd "Back End"
./mvnw package
java -jar target/*.jar
```
Pontos úteis:
- Swagger UI: http://localhost:8080/swagger-ui
- OpenAPI: http://localhost:8080/v3/api-docs

## Como rodar - Front End
O front-end é servido como arquivos estáticos (HTML/CSS/JS) - ou seja, o serivdor entrega os artefatos prontos sem gerar HTML no servidor a cada requisição. Porém, o comportamento da interface é dinâmico: o JavaScript no cliente consome a API do back-end (variável `API_URL` em Front End/script/main/config.js) e gera/atualiza o conteúdo no navegador (Client-Side Rendering - CSR). Em resumo: arquivos estáticos + renderização dinâmica no cliente via API.

Para desenvolvimento abra `Front End/index.html` no navegador ou sirva a pasta com um servidor estático:
```
cd "Front End"
# Exemplo Python
python -m http.server 5500
# então abra http://localhost:5500/index.html
```
O arquivo `Front End/script/main/config.js` define a variável `API_URL` (padrão `http://localhost:8080`). Atualize quando o back-end estiver em outro host/porta.

## Configurações e variáveis de ambiente
* `DB_HOST` (opcional): host e porta do PostgreSQL (padrão: `localhost:5432`).
* `DB_USER`: usuário do banco.
* `DB_PASSWORD`: senha do banco.
* `Back End/src/main/resources/application.properties` contém configurações adicionais (como `carros.max` e `simulador.taxa-juros`).

## Endpoints principais:
- `GET /carros` - lista de todos os carros (padrão Spring Data `Page` em JSON).
- `POST /carros` - cadastra um novo carro. Exemplo:
```json
{
"marca": "Fiat",
"modelo": "Uno",
"tipo": "Hatch",
"ano": 2019,
"preco": 45000.00,
"condicao": "USADO",
"imagem": "nome_da_imagem.jpg",
"fichaTecnica": {
  "km": 50000,
  "combustivel": "Flex",
  "transmissao": "Manual",
  "motor": "1.0",
  "potencia": 75,
  "torque": 9.0,
  "consumo": 12.0,
 }
}
```

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
