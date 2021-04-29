package model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Facilities(
    @Json(name = "baggage")
    val baggage: Baggage,
    @Json(name = "entertainment")
    val entertainment: Entertainment,
    @Json(name = "freeMeal")
    val freeMeal: FreeMeal,
    @Json(name = "order")
    val order: List<String>,
    @Json(name = "usbAndPower")
    val usbAndPower: UsbAndPower,
    @Json(name = "wifi")
    val wifi: Wifi
)