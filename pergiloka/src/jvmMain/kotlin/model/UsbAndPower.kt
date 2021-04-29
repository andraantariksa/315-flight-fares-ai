package model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UsbAndPower(
    @Json(name = "available")
    val available: Boolean,
    @Json(name = "chance")
    val chance: String,
    @Json(name = "copyText")
    val copyText: String,
    @Json(name = "iconUrl")
    val iconUrl: String,
    @Json(name = "power")
    val power: Boolean,
    @Json(name = "usb")
    val usb: Boolean
)