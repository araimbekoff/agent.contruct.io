Создай Maven multi-module проект с такими требованиями:

1. Java 11.
2. Spring Boot версии 2.7.x (не новее 3.x).
3. Структура:
   construct/
     ├── core-lib/                  (JAR-библиотека, общий модуль)
     ├── agent-orchestrator/  (микросервис)
     ├── agent-conversation/  (микросервис)
     ├── agent-intention/        (микросервис)
     ├── agent-catalog/       (микросервис)
     ├── agent-order/         (микросервис)
     └── agent-order-status/  (микросервис)

4. Общий parent pom.xml в папке construct:
   - groupId: io.construct
   - packaging: pom
   - version: 0.0.1-SNAPSHOT
   - dependencyManagement включает Spring Boot 2.7.x
   - modules перечисляют все подпроекты

5. Модуль core-lib:
   - packaging: jar
   - groupId: io.construct
   - artifactId: core-lib
   - содержит простой @Configuration класс
   - собирается в JAR и может быть подключён как зависимость

6. Каждый сервис:
   - packaging: jar
   - groupId: io.construct.agent
   - artifactId = имя сервиса (например agent-orchestrator)
   - зависит от io.construct:core-lib:0.0.1-SNAPSHOT
   - содержит класс Application с аннотацией @SpringBootApplication
   - по умолчанию запускается на случайном порту (server.port=0 в application.yml)

7. Установи проект так, чтобы после `mvn clean install`:
   - сначала собирался core-lib
   - потом все сервисы автоматически подтягивали core-lib как зависимость
   - при изменении кода в core-lib при следующем запуске mvn clean install все сервисы пересобираются с учётом новых артефактов core-lib
   - можно было запустить любой сервис отдельно командой mvn spring-boot:run в его папке

Сгенерируй все необходимые команды и pom.xml файлы.
