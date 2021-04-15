const puppeteer = require('puppeteer');
const fs = require('fs');

(async () => {
  const browser = await puppeteer.launch({ headless: false });
  const page = await browser.newPage();
  let airport = {};
  let airline = {};
  const flightDatas = {};
  const output = {};
  const iatas = 'CGK,HLP,DPS'.split(','); //,HLP,SUB,DPS,JOG,YIA,NRT,HKG,SIN,KUL
  const resps = [];

  for (let dayOfMonth = 1; dayOfMonth <= 1; dayOfMonth++) {
    const searchData = {};

    for (const iataDepart of iatas) {
      for (const iataArriv of iatas) {
        if (iataDepart === iataArriv) continue;
        await page.goto(`https://www.traveloka.com/en-id/flight/fullsearch?ap=${iataArriv}.${iataDepart}&dt=0${dayOfMonth}-06-2021.NA&ps=1.0.0&sc=ECONOMY`, {
          waitUntil: 'networkidle2',
          timeout: 50000,
        });
        const resp = await page.waitForResponse('https://www.traveloka.com/api/v2/flight/search/oneway');
        const respData = await resp.json();
        
        // Filter search results
        const searchResults = respData.data.searchResults;
        let filteredResults = [];
        for (const result of searchResults) {
          let finalResult = {};
          finalResult.totalNumStop = result.totalNumStop;
          finalResult.mAppsPrice = result.mAppsPrice;
          
          let filteredConnectingFlight = [];
          const connectingFlightRoutes = result.connectingFlightRoutes
          for (const connectingFlight of connectingFlightRoutes){
            let finalConnectingFlight = {};
            finalConnectingFlight.departureAirport = connectingFlight.departureAirport;
            finalConnectingFlight.arrivalAirport = connectingFlight.arrivalAirport;
            finalConnectingFlight.numDayOffset = connectingFlight.numDayOffset;
            finalConnectingFlight.totalNumStop = connectingFlight.totalNumStop;
            finalConnectingFlight.segments = connectingFlight.segments;
            
            filteredConnectingFlight.push(finalConnectingFlight);
          }
          
          finalResult.connectingFlightRoutes = connectingFlightRoutes;
          filteredResults.push(finalResult);
        }
        
        searchData[iataDepart+'to'+iataArriv] = filteredResults;
        airline = Object.assign(airline, respData.data.airlineDataMap);
        airport = Object.assign(airport, respData.data.airportDataMap);
        
        console.log(respData.data.searchId);
      }
    }

    flightDatas[20210600+dayOfMonth] = searchData;
  }

  output.airline = airline;
  output.airport = airport;
  output.flightDatas = flightDatas;

  await Promise.all(resps);
  await browser.close();



  fs.writeFileSync('output.json', JSON.stringify(output));
})();