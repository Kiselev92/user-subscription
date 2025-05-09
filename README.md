1. Зайти в папку init, запустить docker-compose.yml
2. Задать в Spring Configuration для UserSubscriptionApplication переменные окружения Edit Configuration -> Modify Options -> Environment variables
   Вставить DB_HOST=localhost;DB_NAME=user_subscription;DB_PASSWORD=postgres;DB_PORT=5432;DB_USER=postgres
3. Повторить в Spring Configuration переменные окружения для тестов UserSubscriptionApplicationTest Edit Configuration -> Modify Options -> Environment variables
   Вставить DB_HOST=localhost;DB_NAME=user_subscription;DB_PASSWORD=postgres;DB_PORT=5432;DB_USER=postgres
4. Запустить docker-compose.yml