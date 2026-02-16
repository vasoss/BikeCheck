# BikeCheck — Bicycle Finance Tracker (Backend MVP)

Backend-часть системы для учета стоимости велосипеда и вложений в апгрейды.  
Изначально разработан как основа для Telegram-бота (интеграционный слой не включен в публичный репозиторий).

---

## О проекте

BikeCheck позволяет пользователю:

- создавать велосипед (stock или custom сборка)
- добавлять и удалять компоненты
- выполнять апгрейды
- фиксировать покупки и продажи деталей
- отслеживать текущую стоимость велосипеда
- отслеживать суммарные вложения

Проект реализует разделение между конфигурацией велосипеда и финансовой историей операций.

---

## Доменная модель

Система разделяет две независимые метрики.

### 1. Текущая стоимость (`price`)

Отражает актуальную конфигурацию велосипеда.

Изменяется при:
- установке компонентов
- удалении компонентов
- апгрейде

Редактирование конфигурации не создает финансовых записей.

---

### 2. Вложения (`invest`)

Отражает реальные денежные потоки пользователя.

Формируется только через операции:
- BUY — покупка компонента
- SELL — продажа компонента

Редактирование не влияет на историю операций.

---

## Модель данных

### User
- id (Telegram ID используется как primary key)
- name
- bikesCount

Связь: 1 → N с Bike

---

### Bike
- id
- name
- price (текущая стоимость)
- invest (вложения)
- user

Связи:
- 1 → N с Component
- 1 → N с Finance

---

### Component
- id
- name
- type
- price
- bike

Ограничение: один компонент одного типа на велосипед.

---

### Finance
- id
- type (BUY / SELL)
- moneyAmount
- description
- bike

Используется для фиксации финансовых операций.

---

## Архитектура

Проект построен по слоистой архитектуре:

```
Controller → Service → Repository → Database
```

- Controller — REST endpoints
- Service — бизнес-логика
- Repository — Spring Data JPA
- Entity — JPA модели

Бизнес-логика полностью отделена от Telegram-интерфейса.

---

## Технологии

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven

---

## Основные REST endpoints

### Пользователи

```
POST   /api/users
GET    /api/users
GET    /api/users/{id}
```

### Велосипеды

```
POST   /api/users/{userId}/bikes/create/custom
POST   /api/users/{userId}/bikes/create/stock
GET    /api/users/{userId}/bikes
GET    /api/users/{userId}/bikes/{id}
DELETE /api/users/{userId}/bikes/{id}
```

### Апгрейд компонентов

```
POST   /api/users/{userId}/bikes/{bikeId}/upgrade/install
DELETE /api/users/{userId}/bikes/{bikeId}/upgrade/uninstall
```

---

## Статус проекта

MVP.  
Используется как backend для Telegram-бота учета велосипедов и апгрейдов.  
Разработка велась параллельно с изучением Spring Boot.
