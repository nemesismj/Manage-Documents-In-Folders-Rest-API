# RestAdminForDocumentAndFolder
Spring Boot Rest Api Admin Panel on Postman 


# Установка и запуск
1)Чтобы скачать с гитхаба проект запускаем команду:

git clone https://github.com/nemesismj/RestAdminForDocumentAndFolder.git

2)Открываем проект с IntellijIDEA

3)Необходимо запустить проект https://github.com/nemesismj/DocumentandFolders чтобы поднять MySQL базу со следующими учетными данными:

username root 

password root

//учетные данные вы можете поменять если не хотите создавать новый в application.properties

4)Прогнать скрипт schema.sql для того чтобы зарегать админа для реста

5)Запускаем проект

проект запускается на порту 8075

#REST

1)для логина используем POST запрос localhost:8075/auth/login 

тело:

![Github_logo](https://sun4.dataix-kz-akkol.userapi.com/c855436/v855436563/24a754/93UWDeTJVuw.jpg)

2)Копируем bearer token и доабвляем key в header

Key: Authorization Value: Bearer_'token'

![Github_logo](https://sun9-49.userapi.com/c855436/v855436563/24a75e/49HiJIv39Jg.jpg)

3)Вывод всех юзеров 

/admin/users  method Get

4)Вывод всех папок

/admin/folders method Get

5)Вывод всех документов 

/admin/documents method Get

6)Добавление юзера

/admin/users/new   Method Post

![Github_logo](https://sun3.dataix-kz-akkol.userapi.com/c855436/v855436799/248ed4/CMNBjUAURto.jpg)

7)Удаление юзера по имени

/admin/users/delete/{name}  Method Delete

8)Удаление документа по имени

/admin/documents/delete/{name}  Method Delete

9)Удаление папки по имени

/admin/folders/delete/{name}  Method Delete

10)Изменение параметров юзера 

/admin/users/edit/{name}  Method Put

![Github_logo](https://sun9-1.userapi.com/c855436/v855436563/24a77a/9J5Bdu2cMw4.jpg)

11)Изменение имени папки

/admin/folders/edit/{name} Method Put

![Github_logo](https://sun1.dataix-kz-akkol.userapi.com/c855436/v855436799/248eb7/j1FunUjM-oI.jpg)





