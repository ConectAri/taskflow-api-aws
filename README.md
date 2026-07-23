# TaskFlow API

Projeto criado em 23 de julho de 2026

API REST para gerenciamento de tarefas, construida em Spring Boot,
aplicando principios SOLID e arquitetura em camadas, com deploy na AWS.

Sobre o nome da API: "TaskFlow" é a junção de Task (tarefa) + Flow (fluxo) — o nome sugere justamente o que a API faz: gerenciar o fluxo de uma tarefa, do momento em que ela é criada (TODO) até passar por andamento (IN_PROGRESS) e ser concluída (DONE).
É um nome comum no mundo de ferramentas de produtividade (dá aquele ar de Trello/Jira), curto, fácil de lembrar e de pronunciar em uma entrevista — e ainda comunica bem o propósito do projeto só pelo nome, o que é um detalhe que costuma chamar atenção positiva do recrutador.


## Arquitetura da aplicacao

![Arquitetura da aplicação](arquitetura_api.png)

## Onde os principios SOLID foram aplicados

| Principio | Onde foi aplicado |~~~~
|-----------|--------------------|
| SRP (Single Responsibility Principle) | `TaskController`, `TaskServiceImpl` e `TaskRepositoryImpl`, cada um com uma unica responsabilidade (camada web, regra de negocio e persistencia, respectivamente) |
| OCP (Open/Closed Principle) | Interface `TaskService`, que permite novas implementacoes sem alterar quem depende dela |
| LSP (Liskov Substitution Principle) | `TaskRepositoryImpl` implementando `TaskRepository`, podendo substituir a abstracao sem quebrar o comportamento esperado |
| ISP (Interface Segregation Principle) | Interfaces `TaskRepository` e `TaskService` separadas, cada uma com metodos coesos ao seu proposito |
| DIP (Dependency Inversion Principle) | `TaskController` dependendo da abstracao `TaskService`, e nao de uma implementacao concreta |

## Arquitetura AWS

![Arquitetura AWS](arquitetura_aws.png)

IAM -> Elastic Beanstalk (EC2) -> RDS (MySQL) | CloudWatch | S3

## Tecnologias

- Java 17
- Spring Boot 4.0.7
- Spring Data JPA
- MySQL
- H2
- Docker
- springdoc-openapi (Swagger)
- JUnit 5 + Mockito
- AWS (IAM, EB, RDS, CloudWatch, S3)

## Como rodar localmente

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

Acesse: http://localhost:8080/swagger-ui.html

## Endpoints

| Metodo | Endpoint |
|--------|----------|
| POST   | /api/tasks |
| GET    | /api/tasks |
| GET    | /api/tasks/{id} |
| PUT    | /api/tasks/{id} |
| DELETE | /api/tasks/{id} |

## Testando no Postman

Importe o arquivo `postman_collection.json` ou importe direto via
Swagger: Import > Link > `<url>/v3/api-docs`

## Evidencias

![Swagger UI - TaskFlow API](evidencias/evidencias-swagger.png)

## Deploy na AWS

(resumo dos passos de deploy)

## Autor

Ariane Moura Barboza
