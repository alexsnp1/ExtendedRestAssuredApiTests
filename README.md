<p align="center">
  <a href="https://reqres.in/" target="_blank">
    <img src="images/screen/reqresIn.png" width="800" alt="Reqres Logo">
  </a>
</p>

# Проект по автоматизации тестовых сценариев для API сервиса [Reqres.in](https://reqres.in/)

## Содержание
- [Технологический стек](#-технологический-стек)
- [API-тесты](#-api-тесты)
- [Запуск тестов в Jenkins](#-запуск-тестов-в-jenkins)
- [Allure-отчет](#-allure-отчет)
- [Уведомления в Telegram](#-уведомления-в-telegram)

## 💻 Технологический стек

<div align="center">
  <table>
    <tr>
      <!-- Первая строка -->
      <td align="center" width="110">
        <a href="https://www.jetbrains.com/idea/" target="_blank">
          <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/intellij/intellij-original.svg" width="48" height="48" alt="IntelliJ IDEA" />
        </a>
        <br>IDEA
      </td>
      <td align="center" width="110">
        <a href="https://www.java.com" target="_blank">
          <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" width="48" height="48" alt="Java" />
        </a>
        <br>Java
      </td>
      <td align="center" width="110">
        <a href="https://junit.org/junit5/" target="_blank">
          <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/junit/junit-original-wordmark.svg" width="48" height="48" alt="JUnit 5" />
        </a>
        <br>JUnit 5
      </td>
      <td align="center" width="110">
        <a href="https://gradle.org/" target="_blank">
          <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/gradle/gradle-original.svg" width="48" height="48" alt="Gradle" />
        </a>
        <br>Gradle
      </td>
      <td align="center" width="110">
        <a href="https://rest-assured.io/" target="_blank">
          <img src="images/screen/rest-assured.png" width="48" height="48" alt="Selenide" />
        </a>
        <br>Rest-Assured
      </td>
    </tr>
    <tr>
      <!-- Вторая строка -->
      <td align="center" width="110">
        <a href="https://telegram.org/" target="_blank">
          <img src="images/screen/telegram.svg" width="48" height="48" alt="Selenoid" />
        </a>
        <br>Telegram
      </td>
      <td align="center" width="110">
        <a href="https://docs.qameta.io/allure/" target="_blank">
          <img src="https://avatars.githubusercontent.com/u/5879127?s=200&v=4" width="48" height="48" alt="Allure" />
        </a>
        <br>Allure
      </td>
      <td align="center" width="110">
        <a href="https://www.jenkins.io/" target="_blank">
          <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/jenkins/jenkins-original.svg" width="48" height="48" alt="Jenkins" />
        </a>
        <br>Jenkins
      </td>
      <td align="center" width="110">
        <a href="https://github.com/" target="_blank">
          <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/github/github-original.svg" width="48" height="48" alt="GitHub" />
        </a>
        <br>GitHub
      </td>
    </tr>
  </table>
</div>

Автотесты для данного проекта написаны на <code>Java</code> с использованием библиотеки <code>REST-assured</code> для взаимодействия с REST api.

В качестве фреймворка для запуска тестов используется <code>Junit5</code>, а в качестве сборщика проекта - <code>Gradle</code>. Произведена настройка CI в <code>Jenkins</code>.

По результатам каждого тестового прогона создаётся <code>Allure</code> отчёт для визуализации результатов прогона.

После прогона тестов <code>Telegram</code> бот присылает сообщение с информацией о прошедшем прогоне

---

## 🌐 API-тесты

### Основные проверки
- ✅ Регистрация
- ✅ Получение информации о пользователе
- ✅ Действия с пользователем. Создание/изменение/удаление
- ✅ Работа со списком пользователей
- ✅ Негативные проверки

---

## [<img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/jenkins/jenkins-original.svg" width="40" height="40" alt="Jenkins"> Запуск тестов в Jenkins](https://jenkins.autotests.cloud/job/Project%20Reqres_alexsnp/)

### Локальный запуск
```bash
gradle clean req_test
```


### Удаленный запуск (Jenkins)
Для запуска тестов в Jenkins нужно нажать на кнопку Build With Parameters в соответствующей сборке

<p align="center">
<img src="images/screen/jenkins.png">
</p>



## [<img src="https://avatars.githubusercontent.com/u/5879127?s=200&v=4" width="40" height="40" alt="Allure"> Allure-отчет](https://jenkins.autotests.cloud/job/Project%20Reqres_alexsnp/4/allure/)

### Главная страница Allure-отчета
<img src="images/screen/allure_report.png" width="800" alt="Allure">

### Пример отчета о выполнении тестов
Содержит в себе:
- Шаги теста
- Request
- Response

  <img src="images/screen/allure_report1.png" width="800" alt="Allure">


## <img src="images/screen/telegram.svg" width="40" height="40" alt="Telegram"> Уведомления в Telegram

### После завершения сборки, бот, созданный в Telegram, автоматически обрабатывает и отправляет сообщение с результатом

<p align="center">
<img src="images/screen/tg_report.png" width="600" alt="Allure">
</p>