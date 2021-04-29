const puppeteer = require('puppeteer');
const fs = require('fs');

(async () => {
  const browser = await puppeteer.launch({ headless: false });
  const page = await browser.newPage();
  let airport = {};
  let airline = {};
  const flightDatas = {};
  const iatas = 'CGK,HLP,SUB,DPS,JOG,YIA,NRT,HKG,SIN,KUL,MAN,YQB,MUC,BNE,CTU'.split(','); //,HLP,SUB,DPS,JOG,YIA,NRT,HKG,SIN,KUL

  for (let dayOfMonth = 2; dayOfMonth <= 10; dayOfMonth++) {
    const flightDepartData = {};
    for (const iataDepart of iatas) {
      const flightArrivalData = {};
      for (const iataArriv of iatas) {
        if (iataDepart === iataArriv) continue;
        while (true) {
          try {
            await page.goto(`https://www.traveloka.com/en-id/flight/fullsearch?ap=${iataDepart}.${iataArriv}&dt=0${dayOfMonth}-06-2021.NA&ps=1.0.0&sc=ECONOMY`, {
              waitUntil: 'networkidle2',
              timeout: 50000,
            });
            break;
          } catch(e) {
            console.log(e);
          }
        }
        let resp;
        try {
          resp = await page.waitForResponse('https://www.traveloka.com/api/v2/flight/search/oneway', { timeout: 5000 });
        } catch (e) {
          continue;
        }
        const respData = await resp.json();
        
        // Filter search results
        airline = Object.assign(airline, respData.data.airlineDataMap);
        airport = Object.assign(airport, respData.data.airportDataMap);
        
        flightArrivalData[iataArriv] = respData.data.searchResults;

        console.log(respData.data.searchId);
      }
      flightDepartData[iataDepart] = flightArrivalData;
    }

    fs.writeFileSync(`output-${20210600+dayOfMonth}.json`, JSON.stringify({
      airline,
      airport,
      flightDatas: flightDepartData
    }));
    // flightDatas[20210600+dayOfMonth] = flightDepartData;
  }

  await browser.close();
})();
