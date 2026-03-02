[JAVA_BADGE]: https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white
[SPRING_BADGE]: https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white
[POSTGRES_BADGE]: https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white
[DOCKER_BADGE]: https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white
[JWT_BADGE]: https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens
[SWAGGER_BADGE]: https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black

<h1 align="center" style="font-weight: bold;">Gestão de Vagas — Backend 💼</h1>

![java][JAVA_BADGE]
![spring][SPRING_BADGE]
![postgres][POSTGRES_BADGE]
![docker][DOCKER_BADGE]
![jwt][JWT_BADGE]
![swagger][SWAGGER_BADGE]

<details open="open">
<summary>Índice</summary>

- [📖 Sobre](#about)
- [🔗 Repositórios do Projeto](#repos)
- [🚀 Como executar](#started)
  - [Pré-requisitos](#prerequisites)
  - [Clonando](#cloning)
  - [Variáveis de Ambiente](#env)
  - [Iniciando](#starting)
- [📍 Endpoints da API](#routes)
  - [Empresa](#company-routes)
  - [Candidato](#candidate-routes)


</details>

<p align="center">
  <b>API RESTful para gerenciamento de vagas de emprego desenvolvida com Spring Boot, PostgreSQL e autenticação JWT. Empresas podem cadastrar e gerenciar vagas; candidatos podem se inscrever e filtrar oportunidades. Inclui documentação Swagger e monitoramento com Prometheus e Grafana.</b>
</p>

<h2 id="about">📖 Sobre</h2>

O **Gestão de Vagas** é uma API com dois módulos principais:

- **Empresa** — cadastro de empresa, autenticação e gerenciamento de vagas.
- **Candidato** — cadastro de candidato, autenticação, visualização de perfil, busca de vagas por filtro e inscrição em vagas.

A autenticação é feita via **JWT** com tokens distintos para empresa e candidato. O banco de dados é gerenciado automaticamente pelo **Spring Data JPA/Hibernate** (`ddl-auto=update`).

<h2 id="repos">🔗 Repositórios do Projeto</h2>

| Repositório | Descrição |
|-------------|-----------|
| **Backend** (este repo) | API REST com Spring Boot |
| [**Frontend**](https://github.com/seu-usuario/front-gestao-vagas) | Interface web com Spring Boot + Thymeleaf |

<h2 id="started">🚀 Como executar</h2>

<h3 id="prerequisites">Pré-requisitos</h3>

- [Java 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven](https://maven.apache.org/)
- [Docker](https://www.docker.com/) e [Docker Compose](https://docs.docker.com/compose/)
- [Git](https://git-scm.com/)

<h3 id="cloning">Clonando</h3>

```bash
git clone https://github.com/seu-usuario/gestao-vagas.git
cd gestao-vagas
```

<h3 id="env">Variáveis de Ambiente</h3>

As configurações estão em `src/main/resources/application.properties`:

```properties
# Banco de dados
spring.datasource.url=jdbc:postgresql://localhost:5432/gestao_vagas
spring.datasource.username=admin
spring.datasource.password=admin
spring.jpa.hibernate.ddl-auto=update

# JWT
security.token.secret=JAVAGAS_@123#
security.token.secret.candidate=JAVAGAS_@123#CANDIDATE
```

> ⚠️ **Atenção:** Altere os valores dos secrets JWT antes de usar em produção. O `token.sonar` presente no arquivo original pode ser removido — é um token de integração com SonarQube e não deve ser versionado.

<h3 id="starting">▶️ Iniciando</h3>

**1. Suba o banco de dados e as ferramentas de monitoramento com Docker Compose:**

```bash
docker-compose up -d
```

Isso irá subir os seguintes serviços:

| Serviço | Porta |
|---------|-------|
| PostgreSQL | 5432 |
| Prometheus | 9090 |
| Grafana | 3000 |

**2. Execute a aplicação:**

```bash
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

A documentação Swagger estará disponível em:
```
http://localhost:8080/swagger-ui/index.html
```

<h2 id="routes">📍 Endpoints da API</h2>

<h3 id="company-routes">🏢 Empresa</h3>

| Rota | Método | Autenticação | Descrição |
|------|--------|--------------|-----------|
| `/company/` | `POST` | ❌ | Cadastra uma nova empresa |
| `/company/auth` | `POST` | ❌ | Autentica a empresa e retorna JWT |
| `/company/job/` | `POST` | ✅ COMPANY | Cadastra uma nova vaga |
| `/company/job/` | `GET` | ✅ COMPANY | Lista todas as vagas da empresa |

**POST /company/auth — Requisição**
```json
{
  "username": "empresa_teste",
  "password": "senha123"
}
```

**POST /company/auth — Resposta**
```json
{
  "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "expires_in": 3600
}
```

**POST /company/job/ — Requisição**
```json
{
  "description": "Desenvolvedor Java Sênior",
  "benefits": "Plano de saúde, VR, VT",
  "level": "SENIOR"
}
```

<h3 id="candidate-routes">👤 Candidato</h3>

| Rota | Método | Autenticação | Descrição |
|------|--------|--------------|-----------|
| `/candidate/` | `POST` | ❌ | Cadastra um novo candidato |
| `/candidate/auth` | `POST` | ❌ | Autentica o candidato e retorna JWT |
| `/candidate/` | `GET` | ✅ CANDIDATE | Retorna o perfil do candidato |
| `/candidate/job` | `GET` | ✅ CANDIDATE | Busca vagas por filtro |
| `/candidate/job/apply` | `POST` | ✅ CANDIDATE | Candidata-se a uma vaga |

**GET /candidate/job?filter={filtro} — Resposta**
```json
[
  {
    "id": "uuid",
    "description": "Desenvolvedor Java Sênior",
    "benefits": "Plano de saúde, VR, VT",
    "level": "SENIOR",
    "companyId": "uuid"
  }
]
```

**POST /candidate/job/apply — Requisição**
```json
"3fa85f64-5717-4562-b3fc-2c963f66afa6"
```
