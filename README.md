# 🚗 AutoMarket

O **AutoMarket** é uma aplicação web para visualização, gerenciamento e simulação de financiamento de veículos. O projeto contém um back-end em Java/Spring Boot e front-end em HTML/CSS/JavaScript/Bootstrap.
![Java](https://img.shields.io/badge/Java-21-ED8B00)
![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3-6DB33F)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-ES6%2B-F7DF1E?style=for-the-badge&logo=javascript&logoColor=000000)

## 📄 Sumário
- [Visão geral](#visao-geral)
- [GIF](#gif)
- [Preview](#preview)
- [Funcionalidades](#funcionalidades)
- [Tecnologias](#tecnologias)
- [Destaques Técnicos](#destaque-tecnicos)
- [Estrutura Principal](#estrutura-principal)
- [Como rodar (Back End)](#cr-backend)
- [Como rodar (Front End)](#cr-frontend)
- [Configurações e variáveis de ambiente](#variaveis-ambiente)
- [Endpoints Principais](#endpoints)
- [Observações](#observacoes)
- [Contribuição](#contribuicao)

<a id="visao-geral"></a>
## 👓 Visão Geral 
O objetivo do AutoMarket é fornecer um catálogo de veículos com filtros, ficha técnica por veículo e um simulador de financiamento (método Price). O sistema utiliza PostgreSQL no back-end e uma UI em JavaScript no front-end.

<a id="gif"></a>
## 🎥 GIF
<table>
 <tr>
  <td>
   <img src="/Front%20End/assets/GIF%20Config.gif"/>
  </td>

  <td>
   <img src="/Front%20End/assets/GIF%20Swagger.gif"/>
  </td>
 </tr>

 <tr>
  <td>
   <img src="/Front%20End/assets/GIF%20Filtro.gif"/>
  </td>
 
  <td>
   <img src="/Front%20End/assets/GIF%20Funções%20Principais.gif"/>
  </td>
 </tr>

 <tr>
  <td colspan="2" align="center">
   <img src="/Front%20End/assets/GIF%20Taxa.gif"/>
  </td>
 </tr>

</table>

<a id="preview"></a>
## 📸 Preview
<table>
 <tr>
  <td>
   <img src="/Front%20End/assets/Swagger.png"/>
  </td>

  <td>
   <img src="/Front%20End/assets/Filtros.png"/>
  </td>
 </tr>

 <tr>
  <td>
   <img src="/Front%20End/assets/Ficha-Técnica.png"/>
  </td>

  <td>
   <img src="/Front%20End/assets/Simulador.png"/>
  </td>
 </tr>
</table>

<a id="funcionalidades"></a>
## 🚀 Funcionalidades

### 🛒 Catálogo
- Listagem dinâmica de veículos
- Cards renderizados via JavaScript
- Exibição de preço, combustível, KM, condição e etc.

### 🔎 Sistema de Filtros
- Busca textual
- Faixa de preço
- Quilometragem
- Condição de veículos

### 📄 Ficha Técnica
- Alerta detalhado
- Informações completas de carros

### 💰 Simulador de Compra (Tabela Price)
- Modal fácil de entender
- Entrada personalizada
- Parcelamento em até 60x
- Cálculo automático no back-end
 
### ⚙️ CRUD Administração
- Integração com Swagger
- Cadastro, edição e remoção de veículos

<a id="tecnologias"></a>
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

<a id="destaque-tecnicos"></a>
## 🧠 Destaques Técnicos
- Arquitetura REST
- Consumo de API via Fetch API
- Renderização dinâmica no front-end
- Persistência com JPA/Hibernate
- Simulação financeira usando Tabela Price
- CRUD documentando com Swagger

<a id="estrutura-principal"></a>
## 📁 Estrutura Principal
```bash
AutoMarket/
├── Back End/
│   ├── src/
│   │   ├── main/ 
│   │   │  ├── java/ # código fonte
│   │   │  ├── resources/ # properties e assets
│   │   │  │   └── static/ # imagens
│   ├── pom.xml/
│   └── mvnw

├── Front End/
│   ├── css/
│   ├── script/
│   ├── bootstrap/
│   ├── assets/
│   └── index.html
```

<a id="cr-backend"></a>
## ▶️ Como rodar - Back End
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

<a id="cr-frontend"></a>
## ▶️ Como rodar - Front End
O front-end é servido como arquivos estáticos (HTML/CSS/JS) - ou seja, o serivdor entrega os artefatos prontos sem gerar HTML no servidor a cada requisição. Porém, o comportamento da interface é dinâmico: o JavaScript no cliente consome a API do back-end (variável `API_URL` em Front End/script/main/config.js) e gera/atualiza o conteúdo no navegador (Client-Side Rendering - CSR). Em resumo: arquivos estáticos + renderização dinâmica no cliente via API.

Para desenvolvimento abra `Front End/index.html` no navegador ou sirva a pasta com um servidor estático:
```
cd "Front End"
# Exemplo Python
python -m http.server 5500
# então abra http://localhost:5500/index.html
```
O arquivo `Front End/script/main/config.js` define a variável `API_URL` (padrão `http://localhost:8080`). Atualize quando o back-end estiver em outro host/porta.

<a id="variaveis-ambiente"></a>
## ⚙️ Configurações e variáveis de ambiente
* `DB_HOST` (opcional): host e porta do PostgreSQL (padrão: `localhost:5432`).
* `DB_USER`: usuário do banco.
* `DB_PASSWORD`: senha do banco.
* `Back End/src/main/resources/application.properties` contém configurações adicionais (como `carros.max` e `simulador.taxa-juros`).

<a id="endpoints"></a>
## 💡 Endpoints Principais:
- `POST /carros` - cadastra um novo carro. Exemplo:
```json
{
 "marca": "Fiat",
 "modelo": "Uno",
 "tipo": "Hatch",
 "ano": 2019,
 "preco": 45000.00,
 "condicao": "Só é permitido nesse campo apenas: USADO ou NOVO",
 "imagem": "nome_da_imagem.extensão do arquivo",
 "fichaTecnica": {
   "km": 50000,
   "combustivel": "Flex",
   "transmissao": "Manual",
   "motor": "1.0",
   "potencia": 75,
   "torque": 9.0,
   "consumo": 12.0
  }
}
```
- `GET /carros` - lista de todos os carros
- `PUT /carros/{id}` - atualiza um carro específico
```json
{
  "marca": "string",
  "modelo": "string",
  "tipo": "string",
  "ano": 0,
  "preco": 0.00,
  "condicao": "Só é permitido nesse campo apenas: USADO ou NOVO",
  "imagem": "nome_da_imagem.extensão do arquivo",
  "fichaTecnica": {
    "km": 0,
    "combustivel": "string",
    "transmissao": "string",
    "motor": "string",
    "potencia": 0,
    "torque": 0.1,
    "consumo": 0.1
  }
}
```
- `PATCH /carros/{id}` - atualização parcial das informações de um carro específico
```json
{
 "preco": 50000.00,
 "imagem": "nome_da_imagem.extensão do arquivo",
 "condicao": "Só é permitido nesse campo apenas: USADO ou NOVO",
 "fiTecnica": {
   "km": 0
  }
}
```
- `DELETE /carros/{id}` - remove um carro por ID
- `POST /compras` - simula financiamento Exemplo:
```json
{
 "carroId": 1,
 "entrada": 25000.00,
 "parcelas": 60
}
```
<a id="observacoes"></a>
## 👁️‍🗨️ Observações
- O `pom.xml` define `java.version=21`.
- `spring.jpa.hibernate.ddl-auto=update` cria/atualiza as tabelas automaticamente em desenvolvimento.
- Propriedades úteis: `carros.max` (limite de cadastro) e `simulador.taxa-juros`.

<a id="contribuicao"></a>
## 🤝 Contribuição
Abra issues ou envie pull requests com melhorias e correções.
