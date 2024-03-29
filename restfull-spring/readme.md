Spring REST Samples project
=======================
В данном проекте реализованы примеры использования Spring REST для CRUD операций.
В проекте используются следующие frameworks/технологии: 
 - Spring Boot
 - Spring Test
 - JUnit
 - Mockito
 - JAXB
 - JSON

# Документация
Официальная документация Spring по использованию Spring REST:  
http://docs.spring.io/spring/docs/4.0.0.RELEASE/spring-framework-reference/pdf/spring-framework-reference.pdf

# Основные принципы работы с REST
* Принципы формирования URL для запросов
- добавление данных POST: [host]/person
- получение данных GET: [host]/person/{id}
- получение данных в XML формате GET: [host]/person/{id}/xml
- обновление данных PUT: [host]/person/{id}
- удаление данных DELETE: [host]/person/{id}

* Использования HTTP Method для различных операций
	- добавление сущности: POST
	- получение информации о сущности: GET
	- обновление данных: POST
	- удаление данных: DELETE
* Передача результата выполнения операции- результат выполнение операции передается в HttpStatus, например: 200 - ОК, 404 - NOT FOUND.
* Передача данных в HTTP Headers
	- при совершении операции, результат пожно помещать в Http Headers. 
	Например, рассмотрим сценарий : "Добавление сущности".
1. Добавление сущности: Вызов метода POST на сервере- передача данных для добавления
2. Формирование объекта с уникальным идентификатором
3. Возвращение результата клиенту: 
			HttpStatus - 200
			HTTP header 'Location' http://localhost:8080/person/777
# Примеры

## Domain
	* Person - объект для передачи информации человеке

## Controller
	* PersonController - класс контролер, куда попадают запросы от клиентов. В методах, при помощи аннотаций, происходит маппинг URL и метода запроса на соответсвующий метод.
    * ControllerWithAsyncProcessing - примеры вызовов с асинхронной обработкой
	
## Тесты
	* PersonControllerMockTest - тест, в котором при помощи заглушек происходит тестирование контроллера.
	* PersonControllerTest - примеры вызовов с клиентской стороны сервера для выполнения различных оперций


#Swagger UL Path
http://localhost:8080/rest-spring/swagger-ui.html

#Получение/отправка данных при помощи Curl(Windows)
* Получение данных при помощи GET: curl http://localhost:8080/rest-spring/rest/person
* Отправка данных при помощи POST: curl -X POST -H "Content-Type: application/json" -d "{\"name\":\"Person.Name\",\"surname\":\"Person.Surname\",\"dateOfBirth\": \"1978-12-06\"}" http://localhost:8080/rest-spring/rest/person