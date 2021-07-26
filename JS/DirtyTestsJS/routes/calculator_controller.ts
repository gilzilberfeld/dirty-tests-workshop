import express = require('express');


const router = express.Router();
const calc = new Calculator();

router.get('/', (req: express.Request, res: express.Response) => {
    res.send("respond with a resource");
});

router.post(CALCULATE, (req: express.Request, res: express.Response) => {
    let result = calc.calculate(req.body);
    res.send(result);
});

router.post(RESET, (req: express.Request, res: express.Response) => {
    let result = calc.reset();
    if (result === "E") {
        res.status(400);
    }
    res.send(result);
});

export default router;