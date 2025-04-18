# Screenmatch

Aplicação para consulta de informações sobre séries e episódios utilizando a API OMDB.

## Tecnologias Utilizadas

- **Linguagem**: Java
- **Framework**: Spring Boot
- **Gerenciador de Dependências**: Maven
- **Biblioteca**: Jackson (para manipulação de JSON)

## Estrutura do Projeto

- `src/main/java`: Código-fonte principal.
- `src/main/resources`: Arquivos de configuração e recursos.
- `src/test/java`: Testes automatizados.

## Pré-requisitos

- **Java** 17 ou superior
- **Maven** 3.8 ou superior

## Como Executar

1. Clone o repositório:
   ```bash
   git clone <URL_DO_REPOSITORIO>
   cd <NOME_DO_DIRETORIO>
   ```

2. Compile o projeto:
   ```bash
   mvn clean install
   ```

3. Execute a aplicação:
   ```bash
   mvn spring-boot:run
   ```

## Funcionalidades

- Consulta de séries e temporadas utilizando a API OMDB.
- Exibição de episódios e suas avaliações.
- Cálculo de estatísticas como média, melhor e pior avaliação por temporada.

## Configuração

- Arquivo de configuração principal: `src/main/resources/application.properties`
- Ajuste as propriedades conforme necessário (ex.: chave da API OMDB).

