# RideSure

A learning project inspired by how insurance systems work: a two-wheeler
insurance backend, built step by step to practise Java, Spring Boot,
Docker and Kubernetes.

## What it does
- Create a quote with an automatically calculated premium (3% of vehicle value)
- Activate or cancel a policy, with state rules enforced (QUOTED -> ACTIVE -> CANCELLED)
- Clear errors: 400 (validation), 404 (not found), 409 (invalid state change)

## Tech stack
Java 21, Spring Boot 4, Spring Data JPA, PostgreSQL, Flyway,
JUnit 5, Testcontainers, Docker

## Run everything with Docker
    docker compose up -d --build
The API is then available on http://localhost:8081

## Run locally without Docker for the app
1. `docker compose up -d policy-db`
2. `.\mvnw.cmd spring-boot:run`

## Run tests
`.\mvnw.cmd test` (needs Docker running)

## API
| Method | Path | Purpose |
|---|---|---|
| POST | /api/policies | Create a quote |
| GET | /api/policies/{id} | Get a policy |
| POST | /api/policies/{id}/activate | Activate a quoted policy |
| POST | /api/policies/{id}/cancel | Cancel a policy |

## Design decisions
- Flyway owns the schema; Hibernate only validates it
- Money uses BigDecimal / numeric, never double
- Business rules live inside the entity, not in controllers
- Database settings come from environment variables, with local defaults