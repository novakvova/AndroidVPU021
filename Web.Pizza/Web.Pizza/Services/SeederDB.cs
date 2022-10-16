using Data.Pizza;
using Data.Pizza.Entities;
using Data.Pizza.Entities.Identity;
using Microsoft.AspNetCore.Identity;
using Microsoft.EntityFrameworkCore;
using Web.Pizza.Constants;

namespace Web.Pizza.Services
{
    public static class SeederDB
    {
        public static void SeedData(this IApplicationBuilder app)
        {
            using(var scope = app.ApplicationServices
                .GetRequiredService<IServiceScopeFactory>().CreateScope())
            {
                var userManager = scope.ServiceProvider
                    .GetRequiredService<UserManager<AppUser>>();
                var roleManager = scope.ServiceProvider
                    .GetRequiredService<RoleManager<AppRole>>();
                var context = scope.ServiceProvider.GetRequiredService<AppEFContext>();
                context.Database.Migrate();
                if(!context.Categories.Any())
                {
                    CategoryEntity best = new CategoryEntity()
                    {
                        Name = "Краща ціна",
                        Priority = 1,
                        Description = "Для козаків",
                        DateCreated = DateTime.SpecifyKind(DateTime.Now, DateTimeKind.Utc),
                        Image = "best.jpg"
                    };
                    context.Categories.Add(best);
                    context.SaveChanges();

                    CategoryEntity heroes = new CategoryEntity()
                    {
                        Name = "Герої",
                        Priority = 2,
                        Description = "Для героїв",
                        DateCreated = DateTime.SpecifyKind(DateTime.Now, DateTimeKind.Utc),
                        Image = "heroes.jpg"
                    };
                    context.Categories.Add(heroes);
                    context.SaveChanges();
                }
            
                if(!roleManager.Roles.Any())
                {
                    var result = roleManager.CreateAsync(new AppRole
                    {
                        Name=Roles.Admin
                    }).Result;
                    result = roleManager.CreateAsync(new AppRole
                    {
                        Name = Roles.User
                    }).Result;
                }
                if(!userManager.Users.Any())
                {
                    string email = "admin@gmail.com";
                    var user = new AppUser
                    {
                        Email = email,
                        UserName = email,
                        Photo = "amdin.jpg",
                        PhoneNumber = "097 23 45 212"
                    };
                    var result = userManager.CreateAsync(user, "123456").Result;
                    result = userManager.AddToRoleAsync(user, Roles.Admin).Result;
                }

            }
        }
    }
}
