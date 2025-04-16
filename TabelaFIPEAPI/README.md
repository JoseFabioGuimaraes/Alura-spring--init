# TabelaFIPEAPI

## Descrição do Projeto

O projeto **TabelaFIPEAPI** é uma aplicação Java desenvolvida com o framework Spring Boot que consome a API pública da Tabela FIPE para realizar consultas de preços de veículos (carros, motos e caminhões). A aplicação permite que o usuário interaja via terminal para buscar informações sobre marcas, modelos e anos de veículos, exibindo os dados de forma organizada.

## Funcionalidades

- Exibir um menu para o usuário escolher o tipo de veículo (carro, moto ou caminhão).
- Listar as marcas disponíveis para o tipo de veículo selecionado.
- Permitir a busca de modelos de veículos com base em uma marca escolhida.
- Filtrar modelos de veículos pelo nome.
- Listar os anos disponíveis para um modelo selecionado.
- Exibir informações detalhadas sobre o veículo, como valor, marca, modelo, ano e tipo de combustível.

## Estrutura do Código

### Classes Principais

1. **`TabelaFipeapiApplication`**  
   Classe principal que inicializa a aplicação Spring Boot e executa o método `exibeMenu()` da classe `Main`.

2. **`Main`**  
   Contém a lógica principal da aplicação, incluindo a interação com o usuário via terminal, consumo da API e exibição dos dados.

3. **`ConsomeAPI`**  
   Classe responsável por realizar requisições HTTP para a API da Tabela FIPE e retornar os dados em formato JSON.

4. **`ConverteDados`**  
   Implementa a interface `IConverteDados` e utiliza a biblioteca Jackson para converter os dados JSON em objetos Java.

5. **Modelos**
    - **`Dados`**: Representa informações genéricas como código e nome.
    - **`Modelos`**: Representa uma lista de modelos de veículos.
    - **`Veiculo`**: Representa os detalhes de um veículo, como valor, marca, modelo, ano e tipo de combustível.

## Como Executar

1. Certifique-se de ter o Java 17+ e o Maven instalados.
2. Clone o repositório.
3. No terminal, navegue até o diretório do projeto e execute:
   ```bash
   mvn spring-boot:run
   ```
4. Siga as instruções exibidas no terminal para interagir com a aplicação.

## Exemplo de Uso

1. Escolha o tipo de veículo (carro, moto ou caminhão).
2. Selecione uma marca pelo código exibido.
3. Filtre os modelos pelo nome ou escolha diretamente um modelo.
4. Consulte os anos disponíveis e visualize os detalhes do veículo.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Maven**
- **Jackson** (para manipulação de JSON)
- **HTTP Client** (para consumo da API)

## API Utilizada

A aplicação consome a API pública da Tabela FIPE disponibilizada em:  
[https://parallelum.com.br/fipe/api/v1](https://parallelum.com.br/fipe/api/v1)

## Estrutura do Projeto

```plaintext
TabelaFIPEAPI/
├── src/main/java/br/com/jfabiodev/TabelaFIPEAPI/
│   ├── main/
│   │   └── Main.java
│   ├── model/
│   │   ├── Dados.java
│   │   ├── Modelos.java
│   │   └── Veiculo.java
│   ├── service/
│   │   ├── ConsomeAPI.java
│   │   ├── ConverteDados.java
│   │   └── IConverteDados.java
│   └── TabelaFipeapiApplication.java
└── pom.xml
```

## Autor

Desenvolvido por **JoseFabioGuimaraes**.