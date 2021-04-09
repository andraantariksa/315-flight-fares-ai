const puppeteer = require('puppeteer');

(async () => {
  const browser = await puppeteer.launch({ headless: false });
  const page = await browser.newPage();
  page.on('response', response => {
    if (response.url().startsWith('https://www.traveloka.com/api/v2/flight/search/oneway')) {
        console.log('ok?');
        response.json().then(a => console.log(a)).catch(e => console.log(e));
    }
  });
  await page.goto('https://www.traveloka.com/en-id/flight/fullsearch?ap=JKTA.TYOA&dt=10-04-2021.NA&ps=1.0.0&sc=ECONOMY', {
    waitUntil: 'networkidle2',
    timeout: 50000,
  });
  await browser.close();
})();
