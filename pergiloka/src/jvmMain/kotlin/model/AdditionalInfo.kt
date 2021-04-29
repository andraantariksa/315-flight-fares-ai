package model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AdditionalInfo(
    @Json(name = "isNcMu")
    val isNcMu: Boolean,
    @Json(name = "seatClassLabel")
    val seatClassLabel: String
)