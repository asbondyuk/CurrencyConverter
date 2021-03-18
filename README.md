# CurrencyConverter

**Описание задачи**

Реализовать Android приложение со следующими функциями:
**1. Загружать список валют с сайта ЦБ https://www.cbr-xml-daily.ru/daily_json.js **

    Retrofit + GSON
    
**и отображать его в виде списка**

    CurrenciesActivity(RecyclerView)

**2. Предоставлять возможность конвертировать указанную сумму в рублях в выбранную 
валюту**

    ConverterActivity , но в случае горизонатльного отражения - не видно результат

3. **Сохранять данные о курсах валют**

SharedPreferences


**и не перезагружать их при повороте экрана или 
перезапуске приложения.**
    
    при перезапуске приложения используется кэш SharedPreferences
    
    при повороте экрана из-за рукожопости он же (кэш) на onCreate, вместо корректного onSave/RestoreInstanceState

**Добавить возможность перезагрузить список курсов вручную.**

    CurrenciesActivity(buttonUpdate)


**4. Периодически обновлять курсы валют**

    !запилить AlarmManager




Первая функция обязательна. Реализованные функции 2-4 будут увеличивать ваш шанс попасть на 
курс.

Приложение должно быть реализовано на языке java или kotlin. 
Можно пользоваться любыми 
библиотеками, но необходимо объяснить в сопроводительном письме к заданию, почему каждая 
из библиотек была вами использована и почему именно эта, а не ее аналог.

Исходный код выполненного задания должен быть размещен в git репозитории 
(например, на github.com или bitbucket.org).



GSON (vs Jackson, Moshi)
    Функционал базовый: записать/прочитать json, поэтому требований к библиотеке (расширения форматов/ размера библиотек/скорости работы) нет.
    Основной выбор между gson/jackson/moshi (технические аспекты тут https://www.ericdecanini.com/2020/09/29/gson-vs-jackson-vs-moshi-the-best-android-json-parser/),
    поэтому выбрал наиболее популярную (https://www.appbrain.com/stats/libraries/tag/json/json-parsing-libraries).

Retrofit (vs Volley)
    Опять же, что популярно/ходовое, со спецификациями + проще (картинки обрабатывать не требуется, нужен базовый функционал).
   
