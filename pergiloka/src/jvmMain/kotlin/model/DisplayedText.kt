package model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DisplayedText(
    @Json(name = "label")
    val label: String,
    @Json(name = "priceDetail")
    val priceDetail: String,
    @Json(name = "tooltip")
    val tooltip: String
)