package model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Flight(
    @Json(name = "additionalInfo")
    val additionalInfo: AdditionalInfo,
    @Json(name = "agentFareInfo")
    val agentFareInfo: AgentFareInfo,
    @Json(name = "airlineFareInfo")
    val airlineFareInfo: AirlineFareInfo,
    @Json(name = "bundleFareInfo")
    val bundleFareInfo: Any?,
    @Json(name = "connectingFlightRoutes")
    val connectingFlightRoutes: List<ConnectingFlightRoute>,
    @Json(name = "deltaPrice")
    val deltaPrice: Any?,
    @Json(name = "desktopPrice")
    val desktopPrice: DesktopPrice,
    @Json(name = "flexibleTicket")
    val flexibleTicket: Boolean,
    @Json(name = "flightId")
    val flightId: String,
    @Json(name = "flightMetadata")
    val flightMetadata: List<FlightMetadata>,
    @Json(name = "loyaltyPoint")
    val loyaltyPoint: String,
    @Json(name = "mAppsPrice")
    val mAppsPrice: MAppsPrice,
    @Json(name = "mobileAppDeal")
    val mobileAppDeal: Boolean,
    @Json(name = "score")
    val score: String,
    @Json(name = "totalNumStop")
    val totalNumStop: String,
    @Json(name = "tripDuration")
    val tripDuration: String
)