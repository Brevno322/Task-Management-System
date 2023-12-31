# Task-Management-System

Task-Management-System представляет собой REST API, разработанное с использованием Spring Boot, Spring Security, Hibernate и MySQL. Это система управления задачами, которая позволяет пользователям создавать, управлять и отслеживать задачи и проекты.

## Особенности

- **Аутентификация и авторизация:** Используя Spring Security, Task-Management-System обеспечивает безопасность данных и функциональности API, контролируя доступ к ресурсам на основе введеных логина и пароля и полученного JWT токена.
- **Управление задачами и проектами:**
- Пользователи могут управлять своими задачами: создавать новые,
редактировать существующие, просматривать и удалять, менять статус и
назначать исполнителей задачи.
Пользователи могут просматривать задачи других пользователей, а
исполнители задачи могут менять статус своих задач.
К задачам можно оставлять комментарии.
- **REST API:** Весь функционал системы доступен через RESTful интерфейс, что обеспечивает простоту интеграции с другими приложениями и сервисами.
- **Хранение данных:** Используя MySQL в качестве базы данных, Task-Management-System обеспечивает надежное хранение информации о задачах и проектах.

## Технологии

Task-Management-System разработан с использованием следующих технологий и инструментов:

- **Spring Boot:** Фреймворк для создания автономных, готовых к использованию приложений на основе Spring.
- **Spring Security:** Обеспечивает аутентификацию и авторизацию в приложении.
- **Hibernate:** Фреймворк для отображения объектно-ориентированной модели данных на реляционную базу данных.
- **MySQL:** Реляционная база данных для хранения информации о задачах и проектах.

## Установка и запуск

Для установки и запуска Task-Management-System требуется выполнить следующие шаги:

1. Клонировать репозиторий на локальную машину.
2. Настроить подключение к базе данных MySQL в файле настроек.
3. Собрать проект с использованием Maven или Gradle.
4. Запустить приложение.
