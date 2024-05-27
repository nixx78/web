# Проект для для демонстрации использования SSL (HTTPS) в Spring Boot Project 


## Команда для генерации keystore в формате: PKCS12
keytool -genkeypair -alias nixx  -keyalg RSA -keysize 2048 -dname "CN=NIKOLAS,OU=DEVELOPER,O=XYZ,C=LATVIA" -ext "SAN:c=DNS:localhost,IP:127.0.0.1" -validity 3650 -storepass 123456 -keypass 123456 -keystore nixx.p12 -storetype PKCS12

## Добавление сертификата в keystore
keytool -import -alias RestClient -file NIKOLAS.crt  -keystore client.p12 -storetype PKCS12


## Получение списка сертификатов
keytool -v -list -keystore nixx.p12
keytool -v -list -keystore client.p12

Endpoints для тестирования
GET  https://localhost:8080/info
POST https://localhost:8080/process