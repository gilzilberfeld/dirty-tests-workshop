using DirtyTestsWorkshop.Exercise;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace DirtyTestsWorkshop.Controllers
{
    [ApiController]
    [Route(Routing.ROOT)]
    public class CalculatorController : ControllerBase
    {
        readonly Calculator _calc;
        public CalculatorController(Calculator calc)
        {
            _calc = calc;
        }

        [HttpPost(Routing.CALCULATE)]
        public ActionResult<string> Calculate(CalculatorParams parameters)
        {
            return Ok(_calc.Calculate(parameters));
        }

        [HttpPost(Routing.RESET)]
        public ActionResult<string> Reset()
        {
            string result = _calc.Reset();
            if (result == "E")
                return BadRequest(result);
            return Ok(result);
        }
    }
}
