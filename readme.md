# Task manager

---

## USE

Для использования запускаем `Main.main`  
Приложение поднимается на порту `8080`

## API

---

#### Добавление задачи

```http request
POST /api/tasks/add
```

```json
{
  "title": "Задача 3",
  "description": "Раз, два, три..."
}
```

---

#### Получение задачи

```http request
GET /api/tasks/task?id=1
```

где `id` это номер задачи.

---

#### Обновление задачи

```http request
POST /api/tasks/update
```

```json
{
  "id": 3,
  "title": "Задача 3",
  "description": "Раз, два, три..."
}
```

---

#### Удаление задачи

```http request
POST /api/tasks/delete?id=1
```

где `id` это номер задачи.

---

#### Получение списка основных задач

Это задачи на верхнем уровне связей

```http request
GET api/tasks
```

---

#### Получение списка всех доступных задач

```http request
GET api/tasks/all
```

---

### Задание связи задачи

```http request
POST /api/tasks/link
```

```json
{
  "parentId": 0,
  "childId": 1
}
```

---

## Не реализованно

* Удаление связей
* Удаление связей при удалении задачи
* Unit-тестирование
* Benchmark тесты

