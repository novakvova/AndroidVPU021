# Група ВПУ021 Adnroid and ASP.NET WebApi Core 6.0

Цей проект піцерія. Маємо категорії товарів та самі товари. 

## Запуск проекта

Виконайте наступні кроки

1. Установка Android Studio Dolphin | 2021.3.1 або вище - https://developer.android.com/studio

2. Установити dotnet ef .NET CLI 

```
dotnet tool install --global dotnet-ef
dotnet tool update --global dotnet-ef
dotnet ef --info 
``` 

3. Ставимо пакети і додаємо міграцію. Перейти в папку `Web.Pizza/Web.Pizza`:

```
dotnet ef migrations add "Init Database" -c AppEFContext -p ../Data.Pizza/Data.Pizza.csproj -s Web.Pizza.csproj

dotnet ef database update -c AppEFContext -p ../Data.Pizza/Data.Pizza.csproj -s Web.Pizza.csproj

```

4. Перейти в папку `Web.Pizza/Web.Pizza`. Установка ASP.NET core 6.0 або вище - https://dot.net

```
dotnet restore
dotnet build
dotnet watch run
```

5. Вікриваємо http://localhost:5000 у веб браузері.

6. Публікація проекта. Переходимо в папку Web.Pizza/Web.Pizza

```
dotnet publish
dotnet Web.Pizza.dll
dotnet Web.Pizza.dll --urls=https://localhost:1632
systemctl enable vpu021.novakvova.com
systemctl start vpu021.novakvova.com
systemctl status vpu021.novakvova.com
```

7. vpu021.novakvova.com.service

```
[Unit]
Description=vpu021.novakvova.com

[Service]
WorkingDirectory=/var/www/android/vpu021.novakvova.com
ExecStart=/usr/bin/dotnet Web.Pizza.dll --urls=https://localhost:1632
Restart=always
# Restart service after 10 seconds if the dotnet service crashes:
RestartSec=10
KillSignal=SIGINT
SyslogIdentifier=dotnet-example
User=root
Environment=ASPNETCORE_ENVIRONMENT=Production
Environment=DOTNET_PRINT_TELEMETRY_MESSAGE=false

[Install]
WantedBy=multi-user.target
```

8. Пnginx config

```
server {
server_name   vpu021.novakvova.com *.vpu021.novakvova.com;
location / {
        proxy_pass         https://localhost:1632;
        proxy_http_version 1.1;
        proxy_set_header   Upgrade $http_upgrade;
        proxy_set_header   Connection keep-alive;
        proxy_set_header   Host $host;
        proxy_cache_bypass $http_upgrade;
        proxy_set_header   X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header   X-Forwarded-Proto $scheme;
    }
}
```

8. Перезапускаємо nginx `systemctl restart nginx` `systemctl status nginx`

9. Додаємо ssl сетрифікат `certbot`



