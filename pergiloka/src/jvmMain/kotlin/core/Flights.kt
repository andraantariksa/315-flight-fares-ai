package core

import java.nio.file.Paths
import com.squareup.moshi.JsonReader
import com.squareup.moshi.Moshi
import kotlinx.datetime.LocalDate
import model.FlightData
import okio.buffer
import okio.source
import java.util.*

data class FlightInfo(
    val airports: List<String>,
    val price: Int
)

fun search(
    date: LocalDate,
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
    flightDatas.flightsFromAirports[departureAirport]?.get(destinationAirport)?.forEach {
        flightOutput.add(
            FlightInfo(
                mutableListOf(departureAirport, destinationAirport),
                it.mAppsPrice.amount.toInt()
            )
        )
    }

    val airportQueue = LinkedList<String>()
//    while () {
//
//    }

    if (minimumPrice != -1) {
        flightOutput = flightOutput.filter { it.price >= minimumPrice }.toMutableList()
    }

    flightOutput.sortBy { it.price }

    return flightOutput
}