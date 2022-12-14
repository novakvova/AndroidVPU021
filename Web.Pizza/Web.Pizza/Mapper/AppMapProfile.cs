using AutoMapper;
using Data.Pizza.Entities;
using Web.Pizza.Models;

namespace Web.Pizza.Mapper
{
    public class AppMapProfile : Profile
    {
        public AppMapProfile()
        {
            CreateMap<CategoryEntity, CategoryItemViewModel>()
                .ForMember(x=>x.Image, opt=>opt.MapFrom(x=>$"/images/{x.Image}"));

            CreateMap<CategoryCreateItemVM, CategoryEntity>()
                .ForMember(x => x.Image, opt => opt.Ignore());
        }
    }
}
