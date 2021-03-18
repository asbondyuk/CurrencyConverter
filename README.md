# CurrencyConverter

Использованные библиотеки:

GSON (vs Jackson, Moshi)
    Функционал базовый: записать/прочитать json, поэтому требований к библиотеке (расширения форматов/ размера библиотек/скорости работы) нет.
    Основной выбор между ходовыми gson/jackson/moshi (технические аспекты тут https://www.ericdecanini.com/2020/09/29/gson-vs-jackson-vs-moshi-the-best-android-json-parser/ не критичны), поэтому выбрал наиболее популярную (https://www.appbrain.com/stats/libraries/tag/json/json-parsing-libraries).

Retrofit (vs Volley)
    Также же выбирал из ходовых (стабильных/поддерживаемых): функционал базовый, картинки обрабатывать не требуется - выбрал что проще
    
    
Текущее состояние:    

**1. Загружать список валют с сайта ЦБ https://www.cbr-xml-daily.ru/daily_json.js**

    Есть: Retrofit + GSON
    
**и отображать его в виде списка**

    Есть: CurrenciesActivity(RecyclerView)

**2. Предоставлять возможность конвертировать указанную сумму в рублях в выбранную 
валюту**

    Есть: ConverterActivity

**3. Сохранять данные о курсах валют**

SharedPreferences


**и не перезагружать их при повороте экрана или 
перезапуске приложения.**
    
    Есть: при перезапуске приложения используется кэш SharedPreferences;
    
    Есть, но нужно переделывать: при повороте из-за реализации также используется SharedPreferences, вместо более корректного onSave/RestoreInstanceState - не удалось развязать CurrenciesActivity

**Добавить возможность перезагрузить список курсов вручную.**

    Есть CurrenciesActivity(buttonUpdate)


**4. Периодически обновлять курсы валют**

    Отсутствует: есть проблемы в понимании передачи состояния, развязать god-class currenciesActivity не удалось - как в текущем формате добавить Alarm Manager не понятно




   
