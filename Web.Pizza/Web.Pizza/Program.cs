using Data.Pizza;
using Data.Pizza.Entities.Identity;
using FluentValidation;
using FluentValidation.AspNetCore;
using Microsoft.AspNetCore.Identity;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.FileProviders;
using System;
using Web.Pizza.Mapper;
using Web.Pizza.Models;
using Web.Pizza.Services;
using Web.Pizza.Validators;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddDbContext<AppEFContext>(options =>
    options.UseNpgsql(builder.Configuration.GetConnectionString("DefaultConnection")));

builder.Services.AddIdentity<AppUser, AppRole>(options =>
{
    options.Password.RequireDigit = false;
    options.Password.RequiredLength = 5;
    options.Password.RequireNonAlphanumeric = false;
    options.Password.RequireUppercase = false;
    options.Password.RequireLowercase= false;

}).AddEntityFrameworkStores<AppEFContext>().AddDefaultTokenProviders();
// Add services to the container.

builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

builder.Services.AddAutoMapper(typeof(AppMapProfile));

#pragma warning disable CS0618 // Type or member is obsolete
builder.Services.AddFluentValidation(x => x.RegisterValidatorsFromAssemblyContaining<Program>());
#pragma warning restore CS0618 // Type or member is obsolete

builder.Services.AddCors();

var app = builder.Build();

app.UseCors(p =>
    p.AllowAnyMethod().AllowAnyOrigin().AllowAnyHeader());

// Configure the HTTP request pipeline.
//if (app.Environment.IsDevelopment())
//{
    app.UseSwagger();
    app.UseSwaggerUI();
//}


app.UseAuthorization();

var dir = Path.Combine(Directory.GetCurrentDirectory(), "images");
if(!Directory.Exists(dir))
{
    Directory.CreateDirectory(dir);
}
app.UseStaticFiles(new StaticFileOptions
{
    FileProvider=new PhysicalFileProvider(dir),
    RequestPath="/images"
});

app.SeedData();

app.MapControllers();

app.Run();
