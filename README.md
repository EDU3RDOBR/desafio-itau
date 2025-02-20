# Transação API - Sistema de Transações Bancárias

Este projeto é uma API REST para registro, consulta e estatísticas de transações bancárias, desenvolvido em **Java 21** com **Spring Boot 3.4.2**.

Ele permite:
- **Registrar transações financeiras**
- **Excluir todas as transações**
- **Obter estatísticas das transações**
- **Consultar a documentação da API via Swagger**
- **Monitorar a saúde da aplicação com Actuator**

---

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.4.2**
- **Spring Web**
- **Spring Boot Actuator**
- **SpringDoc OpenAPI (Swagger)**
- **Lombok**
- **Gradle**
- **Docker** (opcional)
- **JUnit** (para testes)

---

## Como Rodar a Aplicação

### 1. Pré-requisitos

Antes de começar, instale:
- **[Java 21](https://adoptium.net/)** (ou outra distribuição OpenJDK 21)
- **[Docker](https://www.docker.com/get-started)** (caso queira rodar via container)

Verifique se o Java está instalado:
```
java -version
```
Saída esperada (exemplo):
```
openjdk version "21.0.2" 2024-01-16
OpenJDK Runtime Environment ...
OpenJDK 64-Bit Server VM ...
```

---

### 2. Clonar o Repositório

```
git clone https://github.com/EDU3RDOBR/desafio-itau.git
cd desafio-itau
```

---

### 3. Rodar a Aplicação

#### Método 1: Usando Gradle (Sem Docker)

```
./gradlew clean build
./gradlew bootRun
```

A API estará disponível em:
```
http://localhost:8080
```

#### Método 2: Usando Docker

1. **Gerar o JAR** (opcional, se quiser garantir o build):
   ```
   ./gradlew clean build
   ```
2. **Criar a imagem Docker**:
   ```
   docker build -t transacao-api .
   ```
3. **Rodar o container**:
   ```
   docker run -p 8080:8080 transacao-api
   ```

A API estará disponível em:
```
http://localhost:8080
```

---

## Endpoints da API

### Criar uma Transação
- **URL**: `POST /transacao`
- **Descrição**: Registra uma nova transação.

**Exemplo de Request Body**:
```json
{
  "valor": 150.75,
  "dataHora": "2025-02-19T15:30:00Z"
}
```

**Possíveis Respostas**:
- `201 Created` – Transação registrada com sucesso.
- `422 Unprocessable Entity` – Data/hora inválida ou valor negativo.
- `400 Bad Request` – Erro na requisição.
- `500 Internal Server Error` – Erro interno do servidor.

---

### Deletar Todas as Transações
- **URL**: `DELETE /transacao`
- **Descrição**: Remove todas as transações registradas.

**Possíveis Respostas**:
- `200 OK` – Todas as transações foram removidas.
- `400 Bad Request` – Erro na requisição.
- `500 Internal Server Error` – Erro interno do servidor.

---

### Buscar Estatísticas de Transações
- **URL**: `GET /estatistica`
- **Descrição**: Retorna estatísticas das transações de um intervalo (padrão 60 segundos).

**Parâmetros de Query**:
- `intervaloBusca` (opcional) – Define o intervalo de tempo, em segundos. (`defaultValue = 60`)

**Possíveis Respostas**:
- `200 OK` – Estatísticas calculadas com sucesso.
- `400 Bad Request` – Erro na requisição.
- `500 Internal Server Error` – Erro interno do servidor.

**Exemplo de Resposta**:
```json
{
  "quantidade": 3,
  "soma": 450.75,
  "media": 150.25,
  "min": 100.50,
  "max": 200.00
}
```

---

## Monitoramento e Documentação

### Swagger UI
Acesse a documentação interativa Swagger:
```
http://localhost:8080/swagger-ui/index.html
```

### Healthcheck (Spring Boot Actuator)
O Spring Boot Actuator disponibiliza um endpoint de verificação de saúde:
```
http://localhost:8080/actuator/health
```
Retornará algo como:
```json
{
  "status": "UP"
}
```
Caso a aplicação esteja rodando normalmente.

---

