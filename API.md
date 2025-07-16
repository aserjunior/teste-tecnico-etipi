# Documentação da API
Aqui será encontrado a documentação sobre as rotas do projeto.
# Rotas da API
## Rota para registrar o seu User
* Rota: `/api/v1/auth/register`
* Metodo: `POST`

### Body
Params:
- `name`: String,
- `cpf`: String,
- `email`: String,
- `password`: String

Exemplo:
```
  {
    "name": "Aser Junior",
    "cpf": "123456890",
    "email": "aser@email.com",
    "password": "1234"
}
```

Respostas:
  `200 OK SUCCESS`
  ```
    "token: "eyJhbGciOiJI..."
  ```
- `token`: Será usado para verificar a autenticidade do Usuario ao acessar o sistema e os endpoints.
  
## Rota para autenticar o Usuario
* Rota: `/api/v1/auth/authenticate`
* Metodo: `POST`

### Body
Params:
- `email`: String,
- `password` String

## Email e Password devem ser os mesmos colocados na hora da criação da conta !!

  Exemplo:
  ```
    {
    "email": "aser@email.com",
    "password": "1234"
    }
  ```

  Respostas:
    `200 OK SUCESSS`
    ```
      "token: "eyJhbGciOiJI..."
    ```

## Rota para pegar dados do usuario logado
* Rota: `/api/v1/users/current`
* Metodo: `GET`
* Usuario precisa ser autenticado.
* Apenas consegue receber dados dele mesmo

Respostas:
  `200 OK SUCESSS`
  ```
    {
    "id": 7,
    "cpf": "123456890",
    "name": "teste user",
    "email": "teste@user.com"
    }
  ```
  `403 FORBIDDEN`
  ```
    Empty Body
  ```

## Rota para pegar as reclamações
* Rota: `/api/v1/reclamacoes/current`
* Metodo: `GET`
* Usuario precisa ser autenticado
* Retornara uma lista com todas as reclamações do usuario

Respostas:
  `200 OK SUCESSS`
  ```
    [
      {
          "id": 3,
          "title": "TESTE 1",
          "cpf": "123456890",
          "description": "123123123123123\n",
          "clientId": 7,
          "createdAt": "2025-07-16T17:41:52.468927Z"
      },
      {
          "id": 4,
          "title": "hELOOOOO",
          "cpf": "123456890",
          "description": "Teste description",
          "clientId": 7,
          "createdAt": "2025-07-16T17:45:40.964008Z"
      }
    ]
  ```

`403 FORBIDDEN`
```
  Empty Body
```
## Rota para pegar reclamação pelo Id
* Rota: `/api/v1/reclamacoes/id`
* Metodo: `GET`
* Usuario precise ser autenticado
* Sera retornado o objeto

Respostas
  `200 OK SUCCESSS`
  ```
    {
        "id": 4,
        "title": "hELOOOOO",
        "cpf": "123456890",
        "description": "Teste description",
        "clientId": 7,
        "createdAt": "2025-07-16T17:45:40.964008Z"
    }
  ```
  `403 FORBIDDEN`
  ```
    Empty Body
  ```

## Rota para criar uma reclamação
* Rota: `api/v1/reclamacoes`
* Metodo: `POST`
* Usuario precisa ser autenticado
* Sera retornado o objeto criado

Body:
  - `title`: String,
  - `cpf`: String,
  - `description`: String;
  - `clientId`: Long;

clientId e cpf serão povoado automaticamente pelo sistema, utilizando o usuario atual logado.
Respostas:
  `201 CREATED`
  ```
    {
        "id": 4,
        "title": "hELOOOOO",
        "cpf": "123456890",
        "description": "Teste description",
        "clientId": 7,
        "createdAt": "2025-07-16T17:45:40.964008Z"
    }
  ```
  `403 FORBIDDEN`
  ```
    Empty Body
  ```
