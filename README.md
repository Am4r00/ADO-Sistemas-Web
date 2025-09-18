# ADO Sistemas Web

CRUD simples de **Usuários** em **memória** usando **Spring Boot 3**, **Thymeleaf** e **Bean Validation**.  
O objetivo é atender à ADO: app funcionando por **páginas (web)** e por **API REST (HTTP)**, sem banco de dados.

---

## ✅ Funcionalidades

- Criar, listar, editar e excluir usuários (nome, CPF)
- Armazenamento **em memória** (dados somem ao reiniciar)
- **Camada de serviço** isolada
- **Páginas Thymeleaf** (index + dashboard)
- **API REST** paralela aos fluxos web
- Validação com `@Valid` e `@NotBlank`

---

## 🧱 Tecnologias

- Java 17
- Spring Boot 3 (Web, Thymeleaf, Validation)
- Maven

---

## 🚀 Como rodar

Requisitos: **Java 17** e **Maven**.

```bash
# clonar
git clone https://github.com/Am4r00/ADO-Sistemas-Web.git
cd ADO-Sistemas-Web

# rodar
mvn spring-boot:run
# ou
mvn clean package && java -jar target/*.jar
A aplicação sobe em: http://localhost:8080

Dica de dev (opcional): desative cache do Thymeleaf em src/main/resources/application.properties:

ini
Copiar código
spring.thymeleaf.cache=false
spring.application.name=AdoSistemas
🖥️ Páginas (Web)
GET / ou /index → formulário para cadastrar

POST /cadastrar → cria usuário e redireciona

GET /dashboard → lista usuários (tabela)

GET /users/{id}/edit → abre formulário preenchido (edição)

POST /users/{id}/update → atualiza

POST /users/{id}/delete → exclui

Templates em src/main/resources/templates/:

index.html

dashboard.html

📦 API REST
Base URL: http://localhost:8080/api/users

Método	Rota	Corpo (JSON)	Resposta
GET	/	—	200 OK Lista de usuários
GET	/{id}	—	200 OK Usuário | 404
POST	/	{ "name": "...", "cpf":"..." }	201 Created
PUT	/{id}	{ "name": "...", "cpf":"..." }	200 OK | 404
DELETE	/{id}	—	204 No Content | 404

Exemplos curl
bash
Copiar código
# Criar
curl -i -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"name":"Ana","cpf":"12345678900"}'

# Listar
curl -s http://localhost:8080/api/users

# Buscar por id
curl -i http://localhost:8080/api/users/{id}

# Atualizar
curl -i -X PUT http://localhost:8080/api/users/{id} \
  -H "Content-Type: application/json" \
  -d '{"name":"Ana Silva","cpf":"12345678900"}'

# Excluir
curl -i -X DELETE http://localhost:8080/api/users/{id}
🗂️ Estrutura (resumo)
bash
Copiar código
src/main/java/com/meuapp/ado/
├─ AdoSistemasApplication.java
├─ controller/
│  ├─ UserWebController.java       # páginas (Thymeleaf)
│  └─ UserApiController.java       # API REST
├─ dto/
│  └─ SignUpUser.java              # DTO com validação (name, cpf)
├─ entity/
│  └─ User.java                    # id (UUID), nome, cpf
└─ service/
   ├─ UserService.java             # contrato
   └─ UserServiceImpl.java         # implementação em memória
⚠️ Observações
Sem banco de dados: os dados ficam em uma List em memória e se perdem ao reiniciar.

Os IDs são gerados com UUID (string).

O DTO usa campos name e cpf; a entidade usa nome e cpf.
