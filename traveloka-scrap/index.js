const puppeteer = require('puppeteer');
const fs = require('fs');

(async () => {
  const browser = await puppeteer.launch({ headless: false });
  const page = await browser.newPage();

  const iatas = 'CGK,HLP,DPS'.split(','); //CGK,HLP,SUB,DPS,JOG,YIA,NRT,HKG,SIN,KUL
  const resps = [];
  const flightDatas = {};

  for (let dayOfMonth = 1; dayOfMonth <= 1; dayOfMonth++) {
    for (const iataDepart of iatas) {
      for (const iataArriv of iatas) {
        if (iataDepart === iataArriv) continue;
        await page.goto(`https://www.traveloka.com/en-id/flight/fullsearch?ap=${iataArriv}.${iataDepart}&dt=0${dayOfMonth}-06-2021.NA&ps=1.0.0&sc=ECONOMY`, {
          waitUntil: 'networkidle2',
          timeout: 50000,
        });
        const resp = await page.waitForResponse('https://www.traveloka.com/api/v2/flight/search/oneway');
        const respData = await resp.json();
        // TODO
        // Get all the necessary data
        console.log(respData.data.searchId);
      }
    }
  }

  await Promise.all(resps);
  await browser.close();

  fs.writeFileSync('output.json', JSON.stringify(flightDatas));
})();
