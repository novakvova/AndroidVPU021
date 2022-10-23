using AutoMapper;
using Data.Pizza;
using Data.Pizza.Entities;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Web.Pizza.Helpers;
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
            Thread.Sleep(2000);
            var model = _appEFContext.Categories
                .Where(x=>!x.IsDelete)
                .OrderBy(c => c.Priority)
                .Select(x => _mapper.Map<CategoryItemViewModel>(x)).ToList();
            return Ok(model);
        }

        [HttpPost("create")]
        public async Task<IActionResult> Create([FromBody] CategoryCreateItemVM model)
        {
            try
            {
                var category = _mapper.Map<CategoryEntity>(model);
                category.Image = ImageWorker.SaveImage(model.ImageBase64);
                category.DateCreated = DateTime.SpecifyKind(DateTime.Now, DateTimeKind.Utc);
                _appEFContext.Categories.Add(category);
                _appEFContext.SaveChanges();
            }
            catch(Exception ex)
            {
                return BadRequest(new { error = ex.Message });
            }
            
            return Ok();
        }

        // PUT api/<controller>/5
        [HttpPut("update")]
        public async Task<IActionResult> Put([FromBody] CategoryUpdateeItemVM model)
        {
            var category = await _appEFContext.Categories.FindAsync(model.Id);
            if (category is null)
                return NotFound();
            else
            {
                category.Name = model.Name;
                category.Description = model.Description;
                category.Priority = model.Priority;

                ImageWorker.RemoveImage(category.Image);
                category.Image = ImageWorker.SaveImage(model.ImageBase64);

                _appEFContext.Update(category);
                _appEFContext.SaveChanges();
            }

            return Ok();
        }

        [HttpDelete("{id}")]
        public async Task<IActionResult> Delete(int id)
        {
            var category = await _appEFContext.Categories.FindAsync(id);
            if (category is null)
                return NotFound();
            else
            {
                category.IsDelete = true;
                _appEFContext.SaveChanges();
                return Ok();
            }
        }
    }
}
