package model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ConnectingFlightRoute(
    @Json(name = "arrivalAirport")
    val arrivalAirport: String,
    @Json(name = "dateTimeStamp")
    val dateTimeStamp: String,
    @Json(name = "departureAirport")
    val departureAirport: String,
    @Json(name = "flightRefundInfo")
    val flightRefundInfo: FlightRefundInfo,
    @Json(name = "flightRescheduleInfo")
    val flightRescheduleInfo: FlightRescheduleInfo,
    @Json(name = "loyaltyPoint")
    val loyaltyPoint: String,
    @Json(name = "numDayOffset")
    val numDayOffset: String,
    @Json(name = "promoLabels")
    val promoLabels: List<PromoLabel>,
    @Json(name = "providerId")
    val providerId: String,
    @Json(name = "purchaseableBaggageInformation")
    val purchaseableBaggageInformation: List<List<PurchaseableBaggageInformation>>,
    @Json(name = "routeInventories")
    val routeInventories: List<RouteInventory>,
    @Json(name = "segments")
    val segments: List<Segment>,
    @Json(name = "totalNumStop")
    val totalNumStop: String
)