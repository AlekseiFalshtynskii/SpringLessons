## Примеры параметров команд
```
$ save-author '{lastName:Пехов,firstName:Алексей}'
$ save-author '{id:1,lastName:Бычкова,firstName:Наталья}'
$ find-by-id-author 1
$ find-all-author
$ count-author
$ delete-by-id-author 1
$ delete-all-author
```
```
$ save-genre '{name:Фантастика}'
$ save-genre '{id:1,name:Фэнтези}'
$ find-by-id-genre 1
$ find-all-genre
$ count-genre
$ delete-by-id-genre 1
$ delete-all-genre
```
```
$ save-book '{name:Киндрэт,description:Описание,authors:[{lastName:Пехов,firstName:Алексей},{lastName:Бычкова,firstName:Елена},{lastName:Турчанинова,firstName:Наталья}],genres:[{name:Фантастика},{name:Фэнтези}]}'
$ save-book '{id:1,name:Братья,description:Описание2,authors:[{id:2,lastName:Пехов2,firstName:Алексей2},{id:3,lastName:Бычкова2,firstName:Елена2},{id:4,lastName:Турчанинова2,firstName:Наталья2}],genres:[{id:2,name:Фантастика2},{id:3,name:Фэнтези2}]}'
$ find-by-id-book 1
$ find-all-book
$ count-book
$ delete-by-id-book 1
$ delete-all-book
```
```
$ save-comment '{message:"Крутая книга",bookId:1}'
$ save-comment '{id:1,message:"Не очень книга",bookId:1}'
$ find-by-id-comment 1
$ find-all-comment
$ count-comment
$ delete-by-id-comment 1
$ delete-all-comment
```
