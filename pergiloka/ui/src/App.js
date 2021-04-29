import React, {useState} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {
    Form,
    Button
} from 'react-bootstrap';

const airportIatas = 'CGK,HLP,SUB,DPS,JOG,YIA,NRT,HKG,SIN,KUL'.split(',');

export default () => {
    const [selectedDepartureAirport, setSelectedDepartureAirport] = useState(0);
    const [selectedArrivalAirport, setSelectedArrivalAirport] = useState(0);

    return (
        <div>
            <div className="row">
                <div className="col-md-4">
                    <Form.Group>
                        <Form.Label>Departure Airport</Form.Label>
                        <Form.Control
                            as="select"
                            onChange={(e) => {
                                setSelectedDepartureAirport(e.target.value);
                            }}
                            value={selectedDepartureAirport}
                        >
                            {airportIatas.map((val, idx) =>
                                <option value={idx}>{val}</option>
                            )}
                        </Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Arrival Airport</Form.Label>
                        <Form.Control
                            as="select"
                            onChange={(e) => {
                                setSelectedArrivalAirport(e.target.value);
                            }}
                            value={selectedArrivalAirport}
                        >
                            {airportIatas.map((val, idx) =>
                                <option value={idx}>{val}</option>
                            )}
                        </Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Departure Date</Form.Label>
                        <Form.Control type="date" placeholder="Departure Date"/>
                    </Form.Group>
                    <Button>Search Flight</Button>
                </div>
            </div>
            <div>

            </div>
        </div>
    );
};