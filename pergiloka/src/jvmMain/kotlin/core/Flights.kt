package core

import java.nio.file.Paths
import com.squareup.moshi.JsonReader
import com.squareup.moshi.Moshi
import io.ktor.html.*
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import model.Flight
import model.FlightData
import okio.buffer
import okio.source
import java.util.*

data class FlightInfo(
    val airports: List<String>,
    val flights: List<Flight>,
    val price: Int
)

data class AirportAndPrice(
    val airportFrom: String,
    val price: Int,
    val flight: Flight
)

fun tracePath(current_: String, d: Map<String, AirportAndPrice>): FlightInfo {
    val airports = mutableListOf<String>()
    val flights = mutableListOf<Flight>()
    var price = 0

    var current = current_
    while (d.containsKey(current)) {
        val currentFlight = d[current]!!
        current = currentFlight.airportFrom

        airports.add(current)
        flights.add(currentFlight.flight)
        price += currentFlight.price
    }
    return FlightInfo(airports, flights, price)
}

fun search(
    departureAirport: String,
    destinationAirport: String,
    minimumPrice: Int = -1
): MutableList<FlightInfo> {
    val reader = JsonReader.of(Paths.get("/home/andra/Downloads/output-20210601.json").toFile().source().buffer())
    val moshi = Moshi.Builder().build()
    val adapter = moshi.adapter(FlightData::class.java)
    val flightDatas = adapter.fromJson(reader)!!

    var flightOutput = mutableListOf<FlightInfo>()
    val MAX_TRANSIT = 3

    // Trivial case
//    flightDatas.flightsFromAirports[departureAirport]?.get(destinationAirport)?.forEach {
//        flightOutput.add(
//            FlightInfo(
//                mutableListOf(departureAirport, destinationAirport),
//                it.mAppsPrice.amount.toInt()
//            )
//        )
//    }

    val pathAirportAndPrice = mutableMapOf<String, AirportAndPrice>()
    val airportQueue = LinkedList<String>()
    airportQueue.add(departureAirport)
    while (airportQueue.size != 0 && airportQueue.size <= MAX_TRANSIT) {
        val polledAirport = airportQueue.poll()
        if (polledAirport == destinationAirport) {
            flightOutput.add(tracePath(destinationAirport, pathAirportAndPrice))
        }
        flightDatas.flightsFromAirports[polledAirport]!!.forEach {
            if (it.value.isNotEmpty() && !airportQueue.contains(it.key)) {
                val optimalFlightByPrice = it.value.minByOrNull { it.mAppsPrice.amount.toInt() }!!
                pathAirportAndPrice[it.key] =
                    AirportAndPrice(polledAirport, optimalFlightByPrice.mAppsPrice.amount.toInt(), optimalFlightByPrice)
                airportQueue.add(it.key)
            }
        }
    }

    if (minimumPrice != -1) {
        flightOutput = flightOutput.filter { it.price >= minimumPrice }.toMutableList()
    }

    flightOutput.sortBy { it.price }

    return flightOutput
}