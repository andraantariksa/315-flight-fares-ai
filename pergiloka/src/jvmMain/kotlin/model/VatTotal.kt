package model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VatTotal(
    @Json(name = "amount")
    val amount: String,
    @Json(name = "currency")
    val currency: String,
    @Json(name = "nullOrEmpty")
    val nullOrEmpty: Boolean
)