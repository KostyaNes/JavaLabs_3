# microservices-spring

## Нобхідно для запуску додатку:
 - docker
 - docker-compose

## Запуск додатку:
```bash
docker-compose up --scale client=2
```

### Eureka Server URL: http://localhost:8761
### Service URL (instance 1): http://localhost:8081
### Service URL (instance 2): http://localhost:8082
### API-Gateway URL: http://localhost:8080
### Config Server: http://localhost:8888

## Для перевірки "fallback" або "retry"
```bash
docker stop <container_id/container_name>
docker start <container_id/container_name>
