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
`dotnet ef migrations add "Init Database" -c AppEFContext -p ../Data.Pizza/Data.Pizza.csproj -s Web.Pizza.csproj`

```

2. Установка ASP.NET core 6.0 або вище - https://dot.net

```
dotnet restore
dotnet build
dotnet watch run
```

3. Вікриваємо http://localhost:5000 у веб браузері.



