package model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Segment(
    @Json(name = "aircraftInformation")
    val aircraftInformation: AircraftInformation,
    @Json(name = "aircraftType")
    val aircraftType: String?,
    @Json(name = "airlineCode")
    val airlineCode: String,
    @Json(name = "arrivalAirport")
    val arrivalAirport: String,
    @Json(name = "arrivalDate")
    val arrivalDate: ArrivalDate,
    @Json(name = "arrivalTime")
    val arrivalTime: ArrivalTime,
    @Json(name = "brandCode")
    val brandCode: String,
    @Json(name = "departureAirport")
    val departureAirport: String,
    @Json(name = "departureDate")
    val departureDate: DepartureDate,
    @Json(name = "departureTime")
    val departureTime: DepartureTime,
    @Json(name = "durationMinute")
    val durationMinute: String,
    @Json(name = "facilities")
    val facilities: Facilities,
    @Json(name = "fareBasisCode")
    val fareBasisCode: Any?,
    @Json(name = "flightLegInfoList")
    val flightLegInfoList: List<Any>,
    @Json(name = "flightNumber")
    val flightNumber: String,
    @Json(name = "freeBaggageInfo")
    val freeBaggageInfo: FreeBaggageInfo,
    @Json(name = "hasMeal")
    val hasMeal: Boolean,
    @Json(name = "mayReCheckIn")
    val mayReCheckIn: Boolean,
    @Json(name = "numTransit")
    val numTransit: String,
    @Json(name = "operatingAirlineCode")
    val operatingAirlineCode: String?,
    @Json(name = "routeNumDaysOffset")
    val routeNumDaysOffset: String,
    @Json(name = "segmentInventories")
    val segmentInventories: List<SegmentInventory>,
    @Json(name = "tzArrivalMinuteOffset")
    val tzArrivalMinuteOffset: String,
    @Json(name = "tzDepartureMinuteOffset")
    val tzDepartureMinuteOffset: String,
    @Json(name = "visaRequired")
    val visaRequired: Boolean
)