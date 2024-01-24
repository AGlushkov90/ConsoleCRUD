# **ConsoleCRUD**
## **Описание**
Необходимо реализовать консольное CRUD приложение, которое имеет следующие сущности:

Writer (id, firstName, lastName, List<Post> posts)
Post (id, title, content, List<Label> labels)
Label(id, name)
Status (enum ACTIVE, DELETED)

Каждая сущность имеет поле Status. В момент удаления, мы не удаляем запись из файла, а меняем её статус на DELETED.

В качестве хранилища данных необходимо использовать текстовые файлы:
writers.json, posts.json, labels.json

Пользователь в консоли должен иметь возможность создания, получения, редактирования и удаления данных.
