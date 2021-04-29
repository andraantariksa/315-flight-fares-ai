package model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuickFilter(
    @Json(name = "actionContent")
    val actionContent: String?,
    @Json(name = "actionType")
    val actionType: String?,
    @Json(name = "descBgColor")
    val descBgColor: String,
    @Json(name = "descTextColor")
    val descTextColor: String,
    @Json(name = "displayedText")
    val displayedText: String,
    @Json(name = "iconUrl")
    val iconUrl: String,
    @Json(name = "quickFilterCopy")
    val quickFilterCopy: String
)