# HotelBooking

Приложение для бронирования номера в выбранном отеле.
Приложение состоит их четырех экранов.<br>
<br>**Экран Отель**
<br>На экране отображена основная информация об отеле: фотографии, название, рейтинг и т.д., вся информация приходит с API. Отображение фотографий отеля выполнено с помощью recyclerView и CarouselLayoutManager.
По нажатию кнопки переходим к выбору номера.<br>
<br>**Экран Выбор номера**
<br>Список комнтат выполнен с помощью recyclerView. Отображение изображений комнат реализованы также как и на экране Отель. Информация о номерах приходит с API.<br>
<br>**Экран Бронирование**
<br>На экране отображается вся информация о выбранном номере, данные получаем из API. На экране необходимо заполнить поля с номером телефона, почты и информации о туристе.
Поле ввода телефона содержит маску, которая помогает вводить номер в правильном формате. Реализована логика по проверке валидности введенных значений по критериям правильного формата, а также в принципе
наличия данных в поле. Также есть логика проверки наличия данных в полях сведений о туристе. В случае ошибок будут отображаться соответствующие сообщения и подсвечивание/подскролл к нужным полям.
Отображение и динамическое добавление новых блоков полей о новом туристе(кнопка добавить туриста) реализовано с помощью recyclerView и diffUtil. Есть возможность скрывать и показывать поля ввода данных о туристах.<br>
<br>**Экран Подтверждения заказа**
<br>При успешном бронировании будет обображен экран с информацией о принятом заказе, при нажатии кнопки будет переход на главный.<br>

**Техническая часть**
+ Проект выполенен как single activity с фрагментами. Навигация с помощью Navigation component и SafeArgs.
+ Проект реализован на чистой архитектуре, использованым слои data, domain, presentation. Слои не связаны, модели в разных слоях используются разные.
+ В presentation слое проекта используется паттерн MVVM и LiveData.
+ DI реализован с помощью Koin.
+ Для получения данных с API используется Retrofit и Courutines.
+ Для повторяющихся view созданы отдельные стили.
+ На всех экранах, кроме экрана о подтверждении заказа, реализована модель LCE(Loadin, Content, Error) с помощью sealed interfaces. Т.е. при загрузке данных мы видем прогресс бар, при удачном получени видим контент, при ошибке - экран ошибки.
+ Добавлен splashScreen.

## Используемый стек

+ Kotlin
+ Clean Architecture
+ Koin
+ Retrofit
+ SOLID
+ MVVM (ViewModel, LiveData)  
+ RecyclerView & DiffUtil  
+ ViewBinding  
+ Navigation Component & SafeArgs 
+ Glide
+ Coroutines
+ SplashScreen
  

## Gif flow приложения

<img src="https://github.com/alexxk2/HotelBooking/blob/dev/app/src/main/res/drawable/flow.gif" width="340" height="699" />  <br>


