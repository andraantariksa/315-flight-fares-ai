package core

import com.squareup.moshi.JsonClass
import java.nio.file.Paths
import com.squareup.moshi.JsonReader
import com.squareup.moshi.Moshi
import model.Flight
import model.FlightData
import okio.buffer
import okio.source

@JsonClass(generateAdapter = true)
data class FlightInfo(
    val flights: List<Flight>,
    val price: Int
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
    departureAirport: String,
    destinationAirport: String,
    fuzzyTimeAndPrice: Float,
    minimumPrice: Int = -1
): MutableList<FlightInfo> {
    val reader = JsonReader.of(Paths.get("/home/andra/Downloads/output-20210601.json").toFile().source().buffer())
    val moshi = Moshi.Builder().build()
    val adapter = moshi.adapter(FlightData::class.java)
    val flightDatas = adapter.fromJson(reader)!!

    var flightOutput = mutableListOf<FlightInfo>()

    // Trivial case
    flightDatas.flightsFromAirports[departureAirport]?.get(destinationAirport)?.forEach {
        flightOutput.add(
            FlightInfo(
                mutableListOf(it),
                it.mAppsPrice.amount.toInt()
            )
        )
    }

    val s = searchF(flightDatas, departureAirport, departureAirport, destinationAirport)
    s?.forEach {
        var price = 0
        val flights = mutableListOf<Flight>()
        it.forEach { it2 ->
            flights.add(it2.second)
            price += it2.second.mAppsPrice.amount.toInt()
        }
        flightOutput.add(
            FlightInfo(
                flights,
                price
            )
        )
    }

    if (minimumPrice != -1) {
        flightOutput = flightOutput.filter { it.price >= minimumPrice }.toMutableList()
    }

    flightOutput.sortBy { it.price }

    return flightOutput
}