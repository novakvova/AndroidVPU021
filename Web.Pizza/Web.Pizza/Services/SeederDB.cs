using Data.Pizza;
using Data.Pizza.Entities;

namespace Web.Pizza.Services
{
    public static class SeederDB
    {
        public static void SeedData(this IApplicationBuilder app)
        {
            using(var scope = app.ApplicationServices
                .GetRequiredService<IServiceScopeFactory>().CreateScope())
            {
                var context = scope.ServiceProvider.GetRequiredService<AppEFContext>();
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
            }
        }
    }
}
