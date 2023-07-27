# Nasa Telegram bot

[![NasabotCI](https://github.com/PorterNight/Nasa-TelegramBot/actions/workflows/nasabot.yml/badge.svg)](https://github.com/PorterNight/Nasa-TelegramBot/actions/workflows/nasabot.yml)

## General info
Telegram bot предоставляет две команды: 
* "/pod" - (picture of the day) фото дня выбранное случайным образом 
* "/mars" - фото с марсохода Curiosity выбранное случайным образом 

 Фото берутся с сайта NASA https://api.nasa.gov/ 

### Requirements
* Maven
* Docker

### Technologies
* Telegram BOT with WebHook
* Spring-boot
* Docker
* CI/CD (Github Actions)
* Checkstyle

### Run
Проект собирается в Docker image, при запуске контейнера установите переменные среды:
* NASA_TGBOT_USERNAME = username of your telegram bot
* NASA_TGBOT_TOKEN = token of your telegram bot
* NASA_TOKEN = token from https://api.nasa.gov/, you can use demo-key: DEMO_KEY

Для корректной работы webhook можно использовать ngrok для доступа http через внешний https.
Для установки webhook использовать команду: https://api.telegram.org/bot{NASA_TGBOT_TOKEN}/setWebhook?url={https://ngrok-free.app}.

Вместо https://ngrok-free.app подставить адрес предоставленный ngrok. 
 
