package model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PurchaseableBaggageInformation(
    @Json(name = "title")
    val title: String,
    @Json(name = "totalFareAgentWithCurrency")
    val totalFareAgentWithCurrency: TotalFareAgentWithCurrency
)