# Nasa Telegram bot

[![NasabotCI](https://github.com/PorterNight/Nasa-Bot/actions/workflows/nasabot.yml/badge.svg)](https://github.com/PorterNight/Nasa-Bot/actions/workflows/nasabot.yml)

## General info
Telegram bot предоставляет две комманды: 
* "POD" - фото дня выбранное случайным образом 
* "Mars" - фото с марсохода Curiosity выбранное случайным образом 

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
Проект собирается в Docker image, для запуска установите переменные среды:
* NASA_TGBOT_USERNAME - username of your telegram bot
* NASA_TGBOT_TOKEN - token of your telegram bot
* NASA_TOKEN - token from https://api.nasa.gov/, you can use demo-key: DEMO_KEY

