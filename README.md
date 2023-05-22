# Nasa Telegram bot

[![NasabotCI](https://github.com/PorterNight/Nasa-TelegramBot/actions/workflows/nasabot.yml/badge.svg)](https://github.com/PorterNight/Nasa-TelegramBot/actions/workflows/nasabot.yml)

## General info
Telegram bot предоставляет две команды: 
* "/pod" - (picture of a day) фото дня выбранное случайным образом 
* "/mars" - фото с марсохода Curiosity выбранное случайным образом 

 Фото берутся с сайта NASA https://api.nasa.gov/ 

### Requirements
* Maven
* Docker

### Technologies
* Telebram BOT
* Docker
* CI/CD (Github Actions)
* Checkstyle

### Run
Проект собирается в Docker image, при запуске контейнера установите переменные среды:
* NASA_TGBOT_USERNAME - username of your telegram bot
* NASA_TGBOT_TOKEN - token of your telegram bot
* NASA_TOKEN - token from https://api.nasa.gov/, you can use demo-key: DEMO_KEY

