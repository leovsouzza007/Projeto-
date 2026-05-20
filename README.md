🛒 Sistema Loja - Microsserviços com Spring Boot

Projeto acadêmico desenvolvido utilizando arquitetura baseada em microsserviços com Spring Boot, PostgreSQL, Spring Security e Swagger/OpenAPI.

📌 Tecnologias Utilizadas
Java 17
Spring Boot 3
Spring Data JPA
Spring Security
PostgreSQL
Swagger / OpenAPI
Maven
JUnit 5
Mockito
📁 Estrutura do Projeto
src/main/java/com/sistema/loja

├── catalogo
├── estoque
├── entrega
├── pagamento
├── pedido
├── seguranca
├── usuario
└── exception
🧱 Arquitetura do Sistema

O sistema foi dividido em módulos independentes seguindo conceitos de microsserviços.

Módulo	Responsabilidade
Catálogo	Gerenciamento de produtos
Estoque	Controle de estoque
Pedido	Gerenciamento de pedidos
Pagamento	Processamento de pagamentos
Entrega	Controle de entregas
Usuário	Cadastro/autenticação
Segurança	Configuração do Spring Security
⚙️ Configuração do PostgreSQL
1. Instalar

Instale:

PostgreSQL
pgAdmin 4
2. Criar banco de dados

No terminal SQL do PostgreSQL:

CREATE DATABASE loja;

Ou pelo terminal:

psql -U postgres -c "CREATE DATABASE loja;"
⚙️ Configuração do application.properties

Arquivo correto:

src/main/resources/application.properties

Configuração utilizada no projeto:

# BANCO DE DADOS - PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/loja
spring.datasource.username=postgres
spring.datasource.password=SUA_SENHA
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA / HIBERNATE
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# SWAGGER / OPENAPI
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.api-docs.path=/api-docs

# SPRING SECURITY
spring.security.user.name=admin
spring.security.user.password=admin
📦 Dependências Principais
PostgreSQL
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>
Spring Security
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
JWT
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.11.5</version>
</dependency>
▶️ Como Executar o Projeto
1. Clonar repositório
git clone URL_DO_REPOSITORIO
2. Entrar na pasta do projeto
cd nome-do-projeto
3. Instalar dependências
mvn clean install
4. Executar aplicação
mvn spring-boot:run
🌐 URLs do Sistema
Swagger UI
http://localhost:8080/swagger-ui.html

Interface gráfica da API.

OpenAPI JSON
http://localhost:8080/api-docs

Documentação JSON da API.

🔐 Login do Spring Security

Usuário padrão configurado:

Usuário: admin
Senha: admin
📌 Endpoints Principais
Produtos
GET     /api/v1/produtos
POST    /api/v1/produtos
GET     /api/v1/produtos/{id}
DELETE  /api/v1/produtos/{id}
Estoque
POST    /api/v1/estoques
PUT     /api/v1/estoques/entrada
PUT     /api/v1/estoques/saida
GET     /api/v1/estoques/{produtoId}
Pedidos
POST    /api/v1/pedidos
GET     /api/v1/pedidos
GET     /api/v1/pedidos/{id}
PUT     /api/v1/pedidos/{id}/status
DELETE  /api/v1/pedidos/{id}
Pagamentos
POST    /api/v1/pagamentos
PUT     /api/v1/pagamentos/{id}/aprovar
PUT     /api/v1/pagamentos/{id}/recusar
GET     /api/v1/pagamentos/{id}
Entregas
POST    /api/v1/entregas
PUT     /api/v1/entregas/{id}/enviar
PUT     /api/v1/entregas/{id}/finalizar
🧪 Testes Unitários

O projeto possui testes utilizando:

JUnit 5
Mockito

Módulos testados:

CatalogoService
EstoqueService
PedidoService
PagamentoService
EntregaService

Executar testes:

mvn test
🛠️ Problemas Corrigidos Durante o Projeto
✔️ Migração H2 → PostgreSQL

Foi removido o banco H2 e configurado PostgreSQL.

✔️ Configuração do Maven

O Maven não estava reconhecido no terminal do Windows.

Foi corrigido adicionando:

MAVEN_HOME

e:

%MAVEN_HOME%\bin

nas variáveis de ambiente.

✔️ Erro de Porta 8080

A aplicação ficou presa em background utilizando a porta 8080.

Correção:

netstat -ano | findstr :8080
taskkill /PID NUMERO_DO_PID /F
✔️ Erro no application.properties

O arquivo estava sendo editado incorretamente dentro da pasta:

target/classes

O correto é editar apenas:

src/main/resources/application.properties
✔️ Erros de Service e Controller

Foram corrigidos:

métodos inexistentes
nomes incorretos
conflitos de pacotes
constructor injection
erros de importação
inconsistência entre Service e Controller
📚 Boas Práticas Utilizadas
REST API
SOLID
DDD
Constructor Injection
Tratamento global de exceções
Separação por domínio
Uso de Enums
Services desacoplados
🚧 Funcionalidades Futuras

Ainda não implementado:

Dockerfile
docker-compose.yml
Spring Boot Actuator
JWT completo
Gateway/API Gateway
Deploy em nuvem
👨‍💻 Autores

Valdir Coutinho
Leonardo Vieira

Projeto acadêmico desenvolvido utilizando arquitetura moderna com Spring Boot e microsserviços.