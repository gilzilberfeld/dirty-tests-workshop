using DirtyTestsWorkshop.App;
using Newtonsoft.Json.Linq;

namespace DirtyTestsWorkshop.Controllers
{

/*
    [ApiController]
    [Route("/calc")]
    public class CalculatorController : ControllerBase
    {
        readonly Calculator calc;
        public CalculatorController()
        {
            calc = new Calculator();
        }

        [HttpPost("/press")]
        public ActionResult Press(String key)
        {
            Console.WriteLine("-------- received pressed " + key + " -------------------");
            calc.Press(key);
            return Ok();
        }

        [HttpGet("/display")]
        [Produces("application/json")]
        public ActionResult<ResultWrapper> GetDisplay()
        {
            ResultWrapper wrapper = new ResultWrapper();
            wrapper.setDisplay(calc.GetDisplay());
            Console.WriteLine("-------- getting display " + calc.GetDisplay() + " -------------------");
            return new OkObjectResult(wrapper);
        }

        [HttpPost("/sequence")]
        public ActionResult PressSequence([FromBody] string sequenceMessage)
        {
            Console.WriteLine("-------- received sequence " + sequenceMessage + " -------------------");
            JObject json = JObject.Parse(sequenceMessage);
            string sequence = (string)json["sequence"];
            calc.PressAll(sequence);
            return Ok();
        }

        [HttpPost("/reset")]
        public ActionResult Reset()
        {
            Console.WriteLine("-------- received reset -------------------");
            calc.Reset();
            return Ok();
        }


        [HttpGet("/")]
        public ActionResult<string> GetRoot()
        {
            return Ok("Working hard");
        }

    }
*/ 

}
