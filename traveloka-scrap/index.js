const puppeteer = require('puppeteer');
const fs = require('fs');

(async () => {
  const browser = await puppeteer.launch({ headless: false });
  const page = await browser.newPage();

  const iatas = 'CGK,HLP'.split(','); //,HLP,SUB,DPS,JOG,YIA,NRT,HKG,SIN,KUL
  const resps = [];
  const flightDatas = {};

  for (let dayOfMonth = 1; dayOfMonth <= 10; dayOfMonth++) {
    for (const iataDepart of iatas) {
      for (const iataArriv of iatas) {
        if (iataDepart === iataArriv) continue;
        page.on('response', response => {
          if (response.url().startsWith('https://www.traveloka.com/api/v2/flight/search/oneway')) {
              resps.push(new Promise(async (resolve, reject) => {
                const respData = await response.json();
                // TODO
                // Get all the necessary data
                console.log(respData.data.searchId);
                resolve();
              }))
          }
        });
        await page.goto(`https://www.traveloka.com/en-id/flight/fullsearch?ap=${iataArriv}.${iataDepart}&dt=${dayOfMonth}-06-2021.NA&ps=1.0.0&sc=ECONOMY`, {
          waitUntil: 'networkidle2',
          timeout: 50000,
        });
      }
    }
  }

  await Promise.all(resps);
  await browser.close();

  fs.writeFileSync('output.json', JSON.stringify(flightDatas));
})();
