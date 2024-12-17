# Gerenciador de Tarefas - API REST

Este repositório contém uma API REST desenvolvida com Spring Framework para gerenciamento de tarefas e usuários. A aplicação permite criar, listar, atualizar e excluir usuários e tarefas associadas a eles.

## Tecnologias Utilizadas

- **Java 23**
- **Spring Boot**
- **Jakarta Persistence API (JPA)**
- **Banco de dados relacional (MySQL)**
- **Maven**

## Funcionalidades

A API oferece os seguintes endpoints:

### Usuários

- **`GET /users`**: Lista todos os usuários.
- **`GET /users/{id}`**: Obtém detalhes de um usuário específico pelo ID.
- **`POST /users`**: Cria um novo usuário.
- **`PUT /users/{id}`**: Atualiza os dados de um usuário existente.
- **`DELETE /users/{id}`**: Exclui um usuário pelo ID.

### Exemplo de Entrada para adicionar um usuário 

```json
{
  "nome": "exemplo"
}
```

### Tarefas

- **`GET /tasks`**: Lista todas as tarefas.
- **`POST /tasks`**: Cria uma nova tarefa associada a um usuário.
- **`PUT /tasks/{id}`**: Atualiza os dados de uma tarefa existente.
- **`DELETE /tasks/{id}`**: Exclui uma tarefa pelo ID.

### Exemplo de Entrada para Atualizar uma Tarefa

```json
{
  "titulo": "Atualizar Documentação",
  "descricao": "Atualizar o README do projeto",
  "dataInicio": "2023-08-30T09:00:00",
  "dataFim": "2023-09-01T18:00:00",
  "status": "pendente",
  "prioridade": "alta",
  "idUsuario": "39ac346b-f7d5-4d87-a547-b08f2e7dc9be"
}
```

## Configuração

### Pré-requisitos

- Java 23 ou superior
- Maven instalado
- Banco de dados configurado (MySQL)

### Instalação

1. Clone o repositório:
   ```bash
   https://github.com/Aerttyz/GTP.git
   ```

2. Navegue até o diretório do projeto

3. Configure o arquivo `application.properties` no diretório `src/main/resources` com as credenciais do seu banco de dados.

4. Execute o projeto

5. A API estará disponível em: `http://localhost:8080`

## Erros e Tratamento

A API retorna mensagens claras em caso de erro, como:

- **404 Not Found**: Recurso não encontrado (usuário ou tarefa inexistente).
- **400 Bad Request**: Dados inválidos na requisição.
- **500 Internal Server Error**: Erro interno no servidor.

## Estrutura do Código

- **Controllers:** Contém a lógica de controle e os endpoints da API.
- **DTOs (Data Transfer Objects):** Utilizados para transferir dados entre o cliente e o servidor.
- **Models:** Representam as entidades do banco de dados.
- **Repositories:** Interfaces para acesso aos dados.
