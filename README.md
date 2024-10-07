# Circuit Breaker using Spring Boot 3 and Resilience4J

the slow service take 5 seconds after 3 executions
read timeout in 2 seconds so after 3ª execution it give us a timeout
later circuit is activated

## Endpoints
- `curl http://localhost:8080/service` this call by RestTemplate to another service by http Http://localhost:8080/slow
  
