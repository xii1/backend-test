# Backend Project App

Backend Project App is a REST API application implements authentication, authorization (uses JWT) features which written by Java using Spring Boot, PostgreSQL, Redis.

## Getting Started

These instructions will get you building and running the project on your local machine for development and testing purposes. See usage and supported commands for notes on how to use the application.

## Prerequisites

- Apache Maven v3+
- JDK 11+
- Docker

## Run for local development

- Start database server
```bash
./bin/start_db
```
- Start redis server
```bash
./bin/start_redis
```
- Build & run local dev
```bash
mvn spring-boot:run
```

## Run for production

- Build docker image 
```bash
./bin/build
```
- Run with [num_scale] (default: 2)
```bash
./bin/run [num_scale]
```
- Stop
```bash
./bin/stop
```

## List of APIs

### Register API: ```/auth/register```
- Method: ```POST```
- Content-Type: ```application/json```
- Request: ```{"firstName": string, "lastName": string, "email": string, "password": string}```
- Response: ```{"status": int, "message": string}```
```
curl -X POST http://localhost:8080/auth/register -H 'Content-Type: application/json' -d '{"firstName": "fTest", "lastName": "lTest", "email": "test@gmail.com", "password": "Test123"}'
curl -X POST -k https://localhost:8443/auth/register -H 'Content-Type: application/json' -d '{"firstName": "fTest", "lastName": "lTest", "email": "test@gmail.com", "password": "Test123"}'
```
### Login API: ```/auth/login```
- Method: ```POST```
- Content-Type: ```application/json```
- Request: ```{"email": string, "password": string}```
- Response: ```{"status": int, "message": string, "data": {"token": string, "user": {...}}}```
```
curl -X POST http://localhost:8080/auth/login -H 'Content-Type: application/json' -d '{"email": "test@gmail.com", "password": "Test123"}'
curl -X POST -k https://localhost:8443/auth/login -H 'Content-Type: application/json' -d '{"email": "test@gmail.com", "password": "Test123"}'
```
### Logout API: ```/auth/logout```
- Method: ```POST```
- Content-Type: ```application/json```
- Authorization Header: ```Bearer <jwt_token>```
- Response: ```{"status": int, "message": string}```
```
curl -X POST http://localhost:8080/auth/logout -H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QGdtYWlsLmNvbSIsInVzZXJJZCI6IjIwNzAxYTEwLWY0YWMtNDA1ZC04OGYyLTEzNTI2YWE2NmY2YiIsInJvbGVzIjoiQ1VTVE9NRVIiLCJpc3MiOiJ3ZWItc2VydmVyIiwiaWF0IjoxNjM2NjE3OTExLCJleHAiOjE2MzY3MDQzMTF9.XyrGiVYCBpH0MiwDNp0WXgf7AUGkGFH4PstGMSoyK_uemOR3qpb3kPKrAoYQwEF9XMA3kEsECk8CyuPfsao-YA'
curl -X POST -k https://localhost:8443/auth/logout -H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QGdtYWlsLmNvbSIsInVzZXJJZCI6IjIwNzAxYTEwLWY0YWMtNDA1ZC04OGYyLTEzNTI2YWE2NmY2YiIsInJvbGVzIjoiQ1VTVE9NRVIiLCJpc3MiOiJ3ZWItc2VydmVyIiwiaWF0IjoxNjM2NjE3OTExLCJleHAiOjE2MzY3MDQzMTF9.XyrGiVYCBpH0MiwDNp0WXgf7AUGkGFH4PstGMSoyK_uemOR3qpb3kPKrAoYQwEF9XMA3kEsECk8CyuPfsao-YA'
```
### APIs for authorization testing
- Method: ```GET```
- Authorization Header: ```Bearer <jwt_token>```
```
http://localhost:8080/admin
http://localhost:8080/customer
```

## Notes:
- Database: PostgreSQL (indexes email)
- Cache: Redis (cache all revoked JWT token when user logout)
- Load balancer: Nginx
- Code coverage: 67%
- Database scripts: resources/db/migration

## License
This project is licensed under the MIT License - see the LICENSE file for details
