using AutoMapper;
using Data.Pizza;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Web.Pizza.Models;

namespace Web.Pizza.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class CategoriesController : ControllerBase
    {
        private readonly IMapper _mapper;
        private readonly AppEFContext _appEFContext;

        public CategoriesController(IMapper mapper, AppEFContext appEFContext)
        {
            _mapper = mapper;
            _appEFContext = appEFContext;
        }
        [HttpGet("list")]
        public async Task<IActionResult> List()
        {
            var model = _appEFContext.Categories.OrderBy(c => c.Priority).
                Select(x => _mapper.Map<CategoryItemViewModel>(x)).ToList();
            return Ok(model);
        }
    }
}
