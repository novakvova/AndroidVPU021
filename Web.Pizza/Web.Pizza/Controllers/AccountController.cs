using Data.Pizza.Entities.Identity;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Web.Pizza.Models;
using Web.Pizza.Services;

namespace Web.Pizza.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class AccountController : ControllerBase
    {
        private readonly IJwtTokenService _jwtTokenService;
        private readonly UserManager<AppUser> _userManager;

        public AccountController(IJwtTokenService jwtTokenService, UserManager<AppUser> userManager)
        {
            _jwtTokenService = jwtTokenService;
            _userManager = userManager;
        }
        [HttpPost]
        [Route("login")]
        public async Task<IActionResult> Login([FromBody] LoginViewModel model)
        {
            try
            {
                var user = await _userManager.FindByEmailAsync(model.Email);
                if(await _userManager.CheckPasswordAsync(user, model.Password))
                {
                    string token = await _jwtTokenService.CreateToken(user);
                    return Ok( new { token });
                }
                else
                {
                    return BadRequest(new { error = "Invalid user login" });
                }
            }
            catch (Exception)
            {

                throw;
            }
        }
    }
}
