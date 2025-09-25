# Acervo Digital API

Este projeto é uma API REST para um Acervo Digital, desenvolvida utilizando o framework Spring Boot.

## Pré-requisitos

Antes de começar, você precisará ter as seguintes ferramentas instaladas em sua máquina:
- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/downloads/) - Versão 21 ou superior.
- [Apache Maven](https://maven.apache.org/download.cgi) - Gerenciador de dependências e build.
- [Git](https://git-scm.com/downloads) (Opcional) - Para clonar o repositório.

## Configuração do Ambiente

Siga os passos abaixo para preparar o ambiente e executar o projeto localmente.

**1. Clone o Repositório**

```bash
git clone <URL_DO_SEU_REPOSITORIO_GIT>
cd acervodigital
```

**2. Configure as Variáveis de Ambiente**

Este projeto utiliza o arquivo `application.properties` para gerenciar variáveis sensíveis, como credenciais de banco de dados e segredos de token.

- Adicione as seguintes variáveis ao arquivo, substituindo os valores de exemplo pelos seus:

```application.properties
# Configuração do Banco de Dados
spring.datasource.url=jdbc:postgresql://localhost:5432/${BD_NAME}
spring.datasource.username=${BD_USER}
spring.datasource.password=${BD_PASSWORD}

# Segredo para geração de Token JWT
security.jwt.secret-key=${SECRET_KEY}
# Tempo de expiração do token JWT
security.jwt.expiration-time=900000
```

## Endpoints Principais da API

Aqui estão alguns dos endpoints disponíveis na API:

- **Autenticação:**
    - `POST /api/auth/login`: Realiza o login e retorna um token JWT.
- **Usuários:**
    - `POST /api/admin/users`: Registra um novo usuário. (requer autenticação)
- **Itens do Acervo:**
    - `GET /api/assets`: Lista todos os itens do acervo
    - `POST /api/admin/assets`: Adiciona um novo item ao acervo (requer autenticação).
- **Documentação:**
  - `http://localhost:8080/swagger-ui/index.html#/`