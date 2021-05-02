package core

import com.squareup.moshi.JsonClass
import java.nio.file.Paths
import com.squareup.moshi.JsonReader
import com.squareup.moshi.Moshi
import customDateToString
import java.time.LocalDate
import model.Flight
import model.FlightData
import okio.buffer
import okio.source

@JsonClass(generateAdapter = true)
data class FlightInfo(
    val flights: List<Flight>,
    val price: Int,
    val travelTimeMinute: Int
)

fun mostOptimumFlight(flights: List<Flight>): Flight {
    return flights.minByOrNull { it.mAppsPrice.amount.toInt() }!!
}

const val MAX_TRANSIT = 2

fun searchF(
    flightDatas: FlightData,
    currentAirport: String,
    departureAirport: String,
    destinationAirport: String,
    path: MutableList<Pair<String, Flight>> = mutableListOf()
): MutableList<MutableList<Pair<String, Flight>>>? {
    if (currentAirport == destinationAirport) {
        return mutableListOf(path)
    }
    if (path.size == MAX_TRANSIT) {
        return null
    }

    var acc = mutableListOf<MutableList<Pair<String, Flight>>>()

    flightDatas.flightsFromAirports[currentAirport]?.forEach { nextAirport ->
        if (!path.any { it.first == nextAirport.key } && nextAirport.key != departureAirport && nextAirport.value.isNotEmpty()) {
            val pathCopied = path.toMutableList()
            pathCopied.add(Pair(nextAirport.key, mostOptimumFlight(nextAirport.value)))
            searchF(flightDatas, nextAirport.key, departureAirport, destinationAirport, pathCopied)?.let {
                acc = acc.plus(it) as MutableList<MutableList<Pair<String, Flight>>>
            }
        }
    }

    return acc
}

fun search(
    date: LocalDate,
    departureAirport: String,
    destinationAirport: String,
    fuzzyTimeAndPrice: Float,
    minimumPrice: Int = -1
): MutableList<FlightInfo> {
    val reader = JsonReader.of(Paths.get("/home/andra/Downloads/traveloka-scrap/output-${customDateToString(date)}.json").toFile().source().buffer())
    val moshi = Moshi.Builder().build()
    val adapter = moshi.adapter(FlightData::class.java)
    val flightDatas = adapter.fromJson(reader)!!

    var flightOutput = mutableListOf<FlightInfo>()

    val s = searchF(flightDatas, departureAirport, departureAirport, destinationAirport)
    s?.forEach {
        var price = 0
        var travelTimeMinute = 0
        val flights = mutableListOf<Flight>()
        it.forEach { it2 ->
            flights.add(it2.second)
            price += it2.second.mAppsPrice.amount.toInt()
            travelTimeMinute += it2.second.connectingFlightRoutes.fold(0) { acc, e ->
                val result: Int = e.segments.fold(0) { acc2, e2 -> acc2 + e2.durationMinute.toInt() }
                acc + result
            }
        }
        flightOutput.add(
            FlightInfo(
                flights,
                price,
                travelTimeMinute
            )
        )
    }

    if (minimumPrice != -1) {
        flightOutput = flightOutput.filter { it.price >= minimumPrice }.toMutableList()
    }

    val (fuzzyCat, percentage) = fuzzy(fuzzyTimeAndPrice)
    if (fuzzyCat == FuzzyType.Price) {
        flightOutput.sortBy { it.price }
    } else {
        flightOutput.sortBy { it.travelTimeMinute }
    }

    return flightOutput
}