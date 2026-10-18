# Lab 3: интерфейсы и полиморфизм

## Запуск

Из папки `Lab 3`:

```
javac -d out $(find src -name "*.java")
java -cp out task01.Main
```

Вместо `task01` подставить `task02` … `task15`. Общие классы (`Course`, `Student`) лежат в `src/model`.

В папке `broken` лежат примеры, которые специально не компилируются (задания 6, 10, 12):

```
javac -d /tmp/broken broken/task10/Broken.java
```

## Ответы

### Задание 1. Один контракт, две реализации
Переменная `CourseFormatter formatter` по очереди ссылается на `CompactCourseFormatter` и `DetailedCourseFormatter`.

- **Компилятор** знает только статический тип `CourseFormatter`: у него есть метод `format(Course)`, который возвращает `String`. Он проверяет, что вызов корректен, и записывает в байткод `invokeinterface CourseFormatter.format`. Методы, которых нет в интерфейсе, вызвать через эту переменную нельзя.
- **Во время выполнения** JVM смотрит на класс объекта, на который указывает ссылка, и вызывает его реализацию `format`. Строка вызова одна, а результат разный.

### Задание 2. Dynamic dispatch на бумаге
`NotificationSender` имеет `send(Object)` и `send(String)`. `EmailSender` переопределяет `send(Object)` и добавляет перегрузку `send(Integer)`. `ConsoleSender` переопределяет `send(String)`.

Выбор идёт в два шага:
1. **Overload resolution (компиляция).** По типу ссылки и типам аргументов выбирается сигнатура. Рассматриваются только методы, видимые в типе ссылки.
2. **Override dispatch (выполнение).** Для уже выбранной сигнатуры берётся самая «нижняя» версия в классе объекта.

| Вызов | Тип ссылки | Сигнатура (шаг 1) | Результат (шаг 2) |
|---|---|---|---|
| `emailAsBase.send("hi")` | `NotificationSender` | `send(String)` | `NotificationSender.send(String)`: Email её не переопределил |
| `emailAsBase.send(textAsObject)` | `NotificationSender` | `send(Object)` | `EmailSender.send(Object)` |
| `emailAsBase.send(42)` | `NotificationSender` | `send(Object)`: `send(Integer)` не виден | `EmailSender.send(Object)` |
| `email.send(42)` | `EmailSender` | `send(Integer)` | `EmailSender.send(Integer)` |
| `consoleAsBase.send("hi")` | `NotificationSender` | `send(String)` | `ConsoleSender.send(String)` |
| `consoleAsBase.send(textAsObject)` | `NotificationSender` | `send(Object)`: важен статический тип аргумента, а не `"hi"` внутри | `NotificationSender.send(Object)` |

Программа сравнивает предсказание с фактом: `misses: 0`.
Вывод: аргументы влияют только на выбор перегрузки, причём по статическому типу. Объект-получатель влияет только на выбор переопределения.

### Задание 3. Полиморфный массив
`CoursePrinter` хранит `CourseFormatter[]` и в цикле вызывает `format`, без `if` и `instanceof`. Он импортирует только `CourseFormatter` и `Course`.
Третья реализация, `MarkdownCourseFormatter`, лежит в другом пакете (`task03`), четвёртая сделана лямбдой. `task01` и сам цикл не менялись, поменялась только строка, где собирается массив.

### Задание 4. Абстрактный базовый класс
`AbstractNotificationSender.send` — это шаблонный метод (Template Method): проверить, что адресат не пустой, обрезать пробелы, вызвать хук `supports`, затем `doSend`.

- `send` объявлен `final`, чтобы подкласс не мог переопределить его и пропустить проверку. Инвариант «ничего не уходит без проверки адресата» обеспечен для всех наследников.
- Точки расширения: абстрактный `doSend` (обязательный, сама доставка) и `protected supports` (необязательный хук, у `EmailSender` он требует `@`). Оба `protected`, поэтому клиент снаружи не может вызвать `doSend` в обход `send`.

### Задание 5. Интерфейс или абстрактный класс

| Понятие | Выбор | Мотивировка | Цена изменения |
|---|---|---|---|
| `Identifiable` | interface | Способность, которую имеют несвязанные типы (`record Tag`, `Enrollment`). Состояния нет, класс может реализовать несколько таких интерфейсов | Новый абстрактный метод ломает всех реализаторов. Новый `default` метод ничего не ломает |
| `CoursePolicy` | функциональный interface | Стратегия: много взаимозаменяемых правил, удобно писать лямбдой | Смена сигнатуры `allows` затрагивает все политики и лямбды. Новые политики добавляются бесплатно |
| `BaseEntity` | abstract class | Есть общее **состояние** (`id`, `createdAt`) и инвариант (`equals/hashCode` по id, `final`). Интерфейс поля хранить не может | Занимает единственный слот наследования. Любое изменение базы затрагивает все сущности. Если общего состояния нет, лучше `Identifiable` + `record` |
| `Formatter` | interface `Formatter<T>` | Одна операция, много реализаций, нужен generic-параметр | Как у `CoursePolicy`. Если реализация одна и замена не планируется, отдельный тип не нужен, хватит обычного метода или `Function<T, String>` |
| `ClockProvider` | отдельного типа нет | В JDK уже есть абстракция `java.time.Clock`, а для тестов `Clock.fixed(...)`. Своя обёртка только дублирует её | Нулевая: зависим от стабильного JDK API. Своя обёртка — лишний тип, который придётся поддерживать |

«Обычный class» уместен там, где реализация одна и полиморфизм не нужен, например `EnrollmentService`.

### Задание 6. Переопределение без случайной перегрузки
`broken/task06/Broken.java`: реальные ошибки компилятора с `@Override`:

```
send(String,Object) in SmsSender does not override or implement a method from a supertype     <- параметр Object вместо String
priority() in SmsSender cannot override priority() in Sender
  attempting to assign weaker access privileges; was protected                                  <- package-private < protected
copy() in SmsSender cannot override copy() in Sender
  return type long is not compatible with Object                                                <- примитив не ковариантен Object
equals(BadCourse) in BadCourse does not override or implement a method from a supertype       <- нужен equals(Object)
hashcode() in BadCourse does not override or implement a method from a supertype              <- нужен hashCode
```

Исправлено в `Sender`/`SmsSender`: та же сигнатура `send(String, String)`; доступ можно расширить (`protected` -> `public`), но не сузить; возвращаемый тип можно сузить ковариантно (`Object copy()` -> `SmsSender copy()`).

Случай «компилировалось, но вызывался не тот метод»: `BadCourse` **без** `@Override` компилируется без ошибок:
- `equals(BadCourse)` — это перегрузка. `List.contains` и `HashSet` вызывают `equals(Object)`, поэтому используется `Object.equals` (сравнение ссылок): `contains = false`;
- `hashcode()` с маленькой `c` — просто новый метод, поэтому `HashSet` хранит два «одинаковых» курса;
- `tostring()` — `println` печатает `task06.BadCourse@...`.

`GoodCourse` с `@Override` работает правильно. Аннотация переносит проверку с этапа выполнения на этап компиляции.

### Задание 7. Композиция вместо наследования
Через наследование пришлось бы писать `LoggingEmailSender`, `RetryingLoggingEmailSender`, `MetricsRetryingLoggingEmailSender`… и всё это ещё раз для `ConsoleSender`. Порядок поведения жёстко зашит в иерархию.
С декораторами `LoggingSender`, `MetricsSender`, `RetryingSender` каждый из них реализует `NotificationSender` и оборачивает любой `NotificationSender`. Комбинации собираются при создании объекта.

Тестовый `EmailSender(2)` падает 2 раза, на 3-й попытке отправка проходит.

| Порядок | Лог | Метрики | Смысл |
|---|---|---|---|
| A: `Logging(Metrics(Retry(Email)))` | 1 запись `ok` | `calls=1, failures=0` | бизнес-уровень: «уведомление доставлено», ретраи скрыты внутри |
| B: `Retry(Logging(Metrics(Email)))` | 3 попытки, 2 `failed` | `calls=3, failures=2` | инфраструктурный уровень: видно нестабильность SMTP |

Декоратор видит только то, что проходит через него. Внешний слой видит одну логическую операцию, внутренний — каждую попытку.

### Задание 8. Нарушение LSP
`LegacyReadOnlyCourseRepository extends LegacyCourseRepository` обещает метод `save` (его требует тип родителя), но бросает `UnsupportedOperationException`.
Клиент `legacyImport(LegacyCourseRepository)` корректен для родителя и падает на подтипе. Это нарушение LSP: подтип ослабил постусловие (отказывается выполнять обещанную операцию).

Исправление: разделить контракт.
```
CourseReader            findByCode, findAll
   ^          ^
CourseRepository     ReadOnlyCourseCatalog
  + save
   ^
InMemoryCourseRepository
```
`printCatalog(CourseReader)` принимает обе реализации. `importCourses(CourseRepository)` read-only каталог не примет: ошибка теперь на этапе компиляции, а не во время выполнения.

### Задание 9. Политика допуска как стратегия
`EnrollmentPolicy.violation(Student, Course)` возвращает `Optional<String>` с причиной отказа. Реализации: `AgePolicy`, `PrerequisitesPolicy`, `CapacityPolicy` и составная `AllPolicies`.
`EnrollmentService` получает одну политику через конструктор и знает только интерфейс.
Новые политики (`BlockedStudentsPolicy` и лямбда «курс не длиннее 38 ч») добавлены без единого изменения в `EnrollmentService`. Это принцип открытости/закрытости (OCP).

### Задание 10. Конфликт default-методов
`broken/task10`: `class Report implements Printable, Loggable {}`:
```
error: types Printable and Loggable are incompatible;
  class Report inherits unrelated defaults for describe() from types Printable and Loggable
```
Исправление: явный `@Override describe()`. Внутри можно вызвать нужную версию через `Printable.super.describe()` / `Loggable.super.describe()` или объединить обе (`Report`), либо выбрать одну (`PrintOnlyReport`).
Почему нужен явный выбор: интерфейсы не связаны между собой, у них нет общего «более конкретного» метода, и любое автоматическое правило было бы произвольным. Если позже кто-то добавит или изменит default в одном интерфейсе, поведение класса молча поменялось бы. Java требует, чтобы решение принял автор класса.
Правила: метод класса важнее default-метода интерфейса; более конкретный интерфейс (подынтерфейс) важнее родительского; во всех остальных случаях — ошибка.

### Задание 11. Равенство в иерархии
`Point.equals` использует `instanceof Point`, `ColoredPoint.equals` использует `instanceof ColoredPoint` и сравнивает цвет:
`p.equals(cp) = true`, `cp.equals(p) = false`. Симметричность нарушена, и `HashSet`/`List.contains` отвечают по-разному в зависимости от того, с какой стороны сравнивать.

1. **Strict class equality** (`getClass() != o.getClass()`): симметрично, оба `false`. Цена: даже пустой подкласс (`new StrictPoint(1,2){}`) не равен базовому, что нарушает LSP для кода, который ожидает `StrictPoint`.
2. **Композиция**: `record PointValue(x, y)` и `record ColoredPointValue(PointValue point, String color)`. `ColoredPointValue` не является точкой, поэтому сравнивать их напрямую бессмысленно, а сравнение координат пишется явно: `cpv.point().equals(pv)`.

Почему final value class проще: при `final` (а `record` всегда `final`) нет подклассов, поэтому `equals` сравнивает объекты одного типа по одному набору полей. Рефлексивность, симметричность, транзитивность и согласованность с `hashCode` выполняются автоматически.
В расширяемой иерархии подкласс добавляет поле, и нельзя одновременно сохранить контракт `equals` и подстановку подтипа (Bloch, Effective Java, item 10).

### Задание 12. Sealed-иерархия результата
`sealed interface EnrollmentResult permits Accepted, Rejected, WaitListed, PendingApproval`, варианты — `record`. `describe` и `isFinal` используют `switch` без `default`.
Сначала было три варианта, потом добавлен `PendingApproval`. Компилятор (воспроизведено в `broken/task12`):
```
Broken.java:14: error: the switch expression does not cover all possible input values   <- describe
Broken.java:22: error: the switch expression does not cover all possible input values   <- isFinal
Broken.java:30: error: class is not allowed to extend sealed class: EnrollmentResult
                (as it is not listed in its 'permits' clause)                             <- Deferred без permits
```
Компилятор потребовал изменить **каждый** `switch`, который обрабатывает результат, и `permits`. С `default` ошибок не было бы, и новый вариант молча попал бы в неправильную ветку.

### Задание 13. Удаление type code
`LegacyNotifier` хранит `String notificationType` и делает `switch` в `send`. Каждый новый тип заставляет менять этот класс, а опечатка в строке обнаруживается только во время выполнения.
После рефакторинга: `NotificationChannel` и реализации `EmailChannel`, `SmsChannel`, `ConsoleChannel`. Строка конфигурации разбирается **один раз** на границе системы (`Channels.fromConfig`), дальше в коде живёт только объект.

Граница оправданного полиморфизма: отдельный класс нужен, когда отличается **поведение** (валидация адреса, формат сообщения). Если отличаются только **данные**, хватит параметра.
`sms` и `sms-long` в старом коде — две ветки `switch`, но это один алгоритм с разным `maxLength`, поэтому `sms:20` и `sms:70` — один `SmsChannel`. Тема письма — тоже параметр `EmailChannel`, а не подкласс.

### Задание 14. Наследование взрывает комбинации
Две независимые оси, `DeliveryFormat` (`Online`, `Classroom`, `Hybrid`) и `PaymentPolicy` (`Free`, `Fixed`, `Subscription`), собираются в `CourseOffering(title, weeks, format, payment)`.
Вместо 3 × 3 = 9 подклассов получилось 3 + 3 = 6 классов. `Hybrid` сам собран композицией из `Online` и `Classroom`.
Добавлены `SelfPaced` и `Installments` (декоратор над любой оплатой): 2 новых класса дают 16 комбинаций, существующие классы не менялись, в `Main` добавились две строки. При наследовании понадобилось бы 4 × 4 = 16 классов, то есть 7 новых.

### Задание 15. Расширяемый движок правил CourseHub
Код в `src/task15`.

**Диаграмма зависимостей** (стрелка — «зависит от / реализует»):
```
                 Main ──────────────► RuleExplainer
                  │                        │ (exhaustive switch по Rule)
                  ▼                        ▼
          EnrollmentService ─────────► «sealed» Rule ──────────► EnrollmentRequest ──► model.Student, model.Course
                  │                     ▲  evaluate(req): Decision
                  │      ┌──────┬───────┼────────┬──────┬──────────────┐
                  │   AgeRule  Prereq  Capacity  AllOf  AnyOf   «non-sealed» CustomRule
                  │   (record) (record) (record)  │  ▲   │  ▲         ▲            ▲
                  │                               └──┘   └──┘   DeanApprovalRule  HoursLimitRule
                  │                         (содержат List<Rule>)   (открытые стратегии, любой пакет)
                  ▼
           «sealed» Decision ◄── Accepted | Rejected(reasons) | WaitListed(position)
```
`EnrollmentService` зависит только от `Rule` и `Decision`, а не от конкретных правил.

**Реализации `Rule`** (7 штук): `AgeRule`, `PrerequisiteRule`, `CapacityRule`, `AllOf`, `AnyOf`, `DeanApprovalRule`, `HoursLimitRule`.
Комбинирование: `Rule.allOf(...)` (отказ, если хотя бы одно правило отказало, с объединением причин; иначе лист ожидания, если он есть; иначе приём) и `Rule.anyOf(...)` (приём при первом `Accepted`).

**Dynamic dispatch.** В `Main` массив `Rule[]` из 8 правил обходится одним вызовом `rule.evaluate(request)`. JVM вызывает метод класса объекта. `AllOf`/`AnyOf` внутри тоже вызывают `evaluate` у детей, не зная их типов. Так дерево правил вычисляется рекурсивно через полиморфизм.

**Записка.**
- *Где наследование уместно:* наследование **типа** — `CustomRule extends Rule`, записи `implements Rule`/`Decision`. Наследование **реализации** здесь не используется ни разу: у правил нет общего состояния. Оно уместно, когда есть общий алгоритм с жёстким инвариантом, как `final send` в задании 4 (Template Method).
- *Где выбрана композиция:* `AllOf`/`AnyOf` содержат `List<Rule>`; сервис получает `Rule` через конструктор; итоговая политика — дерево объектов, собранное в `Main`. Новое сочетание правил не требует нового класса.
- *Какие гарантии даёт sealed-граница:*
  - `Decision` закрыт: только три исхода. Каждый `switch` по результату (`AllOf`, `AnyOf`, `Main.show`) проверяется компилятором на полноту без `default`. Новый исход заставит обновить все места обработки (как в задании 12).
  - `Rule` закрыт сверху, но открыт через `non-sealed CustomRule`. Основные правила известны полностью, и `RuleExplainer` перечисляет их исчерпывающим `switch`. Сторонние правила допускаются только через `CustomRule`, а `explain` обрабатывает их одной веткой через `name()`. Если в ядро добавят новый `permits`-тип, `RuleExplainer` перестанет компилироваться, пока его не учтут.
  - Сервис при этом не делает `instanceof` по правилам: проверка конкретных классов есть только в инструменте анализа дерева (`RuleExplainer`), и там она безопасна благодаря sealed.
