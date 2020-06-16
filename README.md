test

- написать клиент-серверное приложение(с использованием сокетов), которое принимает запросы от клиента в json-e:
·         в теле json-а передаем - тип документа, имя файла и xml тело документа в base64
·         затем тело парсим с использованием библиотеки Xstream, преобразуем его в json и записываем в БД
·         в бд пусть будет одна таблица, например docs, поля doc_id, doc_body, sender, recipient, file_name
·         приложение должно уметь обрабатывать документы ORDER, DESADV
·         также необходимо логировать запросы(например при помощи log4j), скажем выводить session id, sender, recipient и file_name
·         в ответ клиенту сервер должен возвращать json, в теле code - 200, message - OK
---
Описание:

    Приложение сервера получает сообщение из сокета, сохраняет данные в бд. Можно получить обьекты со веб страницы http://localhost:8080 по ид записи в бд. (Нажать соннект, и ввести  uuid в поле)
---
Запуск:

- запустить docker-compose up из папки docker.  
- ServerApplication запускает сервер, короторыйы слушает порт.  
- ApplicationClient запускает клиент, которые генериует запросы к порту.
    