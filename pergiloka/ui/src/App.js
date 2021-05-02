import React, {useState} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-slider/dist/css/bootstrap-slider.css"
import {
    Form,
    Button,
    Container,
    Card
} from 'react-bootstrap';
import ReactBootstrapSlider from 'react-bootstrap-slider';

const airportIatas = 'CGK,HLP,SUB,DPS,JOG,YIA,NRT,HKG,SIN,KUL'.split(',');

export default () => {
    const [selectedDepartureAirport, setSelectedDepartureAirport] = useState(0);
    const [selectedArrivalAirport, setSelectedArrivalAirport] = useState(6);
    const [departureDate, setDepartureDate] = useState("2021-06-02");
    const [fuzzy, setFuzzy_] = useState(0.5);
    const [result, setResult] = useState(null);

    const setFuzzy = (value) => {
        console.log(value);
        setFuzzy_(value);
    };

    const doSearch = () => {
        console.log(selectedDepartureAirport, selectedArrivalAirport, departureDate);

        // Fetch here
        const data = {
            departureDate,
            airportDeparture: airportIatas[selectedDepartureAirport],
            airportArrival: airportIatas[selectedArrivalAirport],
        };
        const body = new FormData();
        body.set("departureDate", departureDate);
        body.set("airportDeparture", airportIatas[selectedDepartureAirport]);
        body.set("airportArrival", airportIatas[selectedArrivalAirport]);
        body.set("fuzzyChoice", fuzzy);
        const formData =
            fetch('http://localhost:8080/flight', {
                method: 'POST', // or 'PUT'
                body
                // headers: {
                //     'Content-Type': 'application/json',
                // },
                // body: JSON.stringify(data),
            })
                .then(result => result.json())
                .then(data => {
                    setResult(data);
                })
                .catch((error) => {
                    console.error('Error:', error);
                });
    };

    return (
        <Container>
            <Form.Group>
                <Container>
                    {'Time'}
                    <ReactBootstrapSlider
                        value={fuzzy}
                        change={(e) => setFuzzy(e.target.value)}
                        step={0.01}
                        max={1}
                        min={0}/>
                    {'Price'}
                </Container>
            </Form.Group>
            <Form.Group>
                <Form.Label>Departure Airport</Form.Label>
                <Form.Control
                    as="select"
                    onChange={(e) => {
                        setSelectedDepartureAirport(parseInt(e.target.value));
                    }}
                    value={selectedDepartureAirport}
                >
                    {airportIatas.map((val, idx) =>
                        <option value={idx} key={idx}>{val}</option>
                    )}
                </Form.Control>
            </Form.Group>
            <Form.Group>
                <Form.Label>Arrival Airport</Form.Label>
                <Form.Control
                    as="select"
                    onChange={(e) => {
                        setSelectedArrivalAirport(parseInt(e.target.value));
                    }}
                    value={selectedArrivalAirport}
                >
                    {airportIatas.map((val, idx) =>
                        <option value={idx} key={idx}>{val}</option>
                    )}
                </Form.Control>
            </Form.Group>
            <Form.Group>
                <Form.Label>Departure Date</Form.Label>
                <Form.Control
                    type="date"
                    placeholder="Departure Date"
                    value={departureDate}
                    onChange={(e) => setDepartureDate(e.target.value)}
                />
            </Form.Group>
            <Button
                disabled={selectedArrivalAirport === selectedDepartureAirport}
                onClick={doSearch}
            >
                Search Flight
            </Button>
            <Container>
                {
                    (result === null) ? null : result.flightInfos.map((v) =>
                        <Card>
                            <Card.Title>Rp. {v.price} - {v.travelTimeMinute} min est</Card.Title>
                            <Card.Body>
                                Route: {airportIatas[selectedDepartureAirport]}{v.flights.map((v2) => `-${v2.flightMetadata[v2.flightMetadata.length - 1].destinationAirport}`)}
                            </Card.Body>
                        </Card>
                    )
                }
            </Container>
        </Container>
    );
};