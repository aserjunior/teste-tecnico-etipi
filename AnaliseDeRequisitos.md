
# 📋 Análise de Requisitos - Sistema de Reclamações

## 🧑‍💻 Atores

- **Usuário**: Pode se registrar, fazer login, criar e visualizar suas reclamações.
- **Administrador** (opcional): Pode visualizar e gerenciar todas as reclamações.

---

## ✅ Requisitos Funcionais

| Código | Descrição |
|--------|-----------|
| **RF01** | O sistema deve permitir que o usuário se registre. |
| **RF02** | O sistema deve permitir que o usuário faça login com e-mail e senha. |
| **RF03** | Após o login, o usuário deve ser redirecionado para a home. |
| **RF04** | O sistema deve permitir que o usuário crie uma nova reclamação. |
| **RF05** | O sistema deve exibir uma lista com as reclamações do usuário. |
| **RF06** | O sistema deve permitir que o usuário visualize os detalhes de uma reclamação. |
| **RF07** | O sistema deve garantir que o usuário só possa ver suas próprias reclamações. |
| **RF08** | (Opcional) O administrador pode ver todas as reclamações. |

---

## 🚀 Requisitos Não Funcionais

| Código | Descrição |
|--------|-----------|
| **RNF01** | O sistema deve utilizar autenticação segura (ex: JWT). |
| **RNF02** | O banco de dados deve manter integridade e consistência dos dados. |
| **RNF03** | O sistema deve ser implementado com backend em Spring Boot e frontend em React. |

---

## 📌 Casos de Uso

| Caso de Uso         | Descrição |
|---------------------|-----------|
| **Registrar Usuário** | Usuário informa nome, e-mail, senha, etc., e cria uma conta. |
| **Login** | Usuário entra no sistema com e-mail e senha. |
| **Criar Reclamação** | Usuário descreve um problema e envia. |
| **Listar Reclamações** | Usuário vê suas reclamações na tela principal. |
| **Ver Detalhes** | Usuário clica em uma reclamação para ver mais detalhes. |

---

## 🔄 Fluxo Principal - Criar Reclamação

1. Usuário faz login.
2. É redirecionado para a home.
3. Clica em "Nova Reclamação".
4. Preenche o formulário.
5. Clica em "Enviar".
6. Reclamação é salva no banco de dados.
7. A reclamação aparece na lista principal.

---

## 🗃️ Modelo de Dados (Simplificado)

### Tabela: `usuarios`

| Campo     | Tipo      | Descrição                 |
|-----------|-----------|---------------------------|
| id        | LONG | Identificador do usuário  |
| nome      | STRING    | Nome do usuário           |
| email     | STRING    | E-mail do usuário         |
| senha     | STRING    | Senha criptografada       |

### Tabela: `reclamacoes`

| Campo           | Tipo      | Descrição                             |
|-----------------|-----------|----------------------------------------|
| id              | LONG | Identificador da reclamação           |
| titulo          | STRING    | Título da reclamação                  |
| descricao       | TEXT      | Descrição completa da reclamação      |
| data_criacao    | DATETIME  | Data e hora da criação                |
| usuario_id      | LONG | Chave estrangeira para `usuarios`    |

---

