using FluentValidation;
using Web.Pizza.Models;

namespace Web.Pizza.Validators
{
    public class ValidationCategoryCreateVM : AbstractValidator<CategoryCreateItemVM>
    {
        public ValidationCategoryCreateVM()
        {
            RuleFor(c => c.Name)
                .NotEmpty()
                .WithMessage("Вкажіть назву категорії");
            RuleFor(c => c.ImageBase64)
                .NotEmpty()
                .WithMessage("Оберіть фото для категорії");
        }
    }
}
