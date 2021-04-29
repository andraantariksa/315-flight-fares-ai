package model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PromoLabel(
    @Json(name = "actionContent")
    val actionContent: String?,
    @Json(name = "actionType")
    val actionType: String?,
    @Json(name = "ancillaryType")
    val ancillaryType: Any?,
    @Json(name = "bgColor")
    val bgColor: String,
    @Json(name = "conditionList")
    val conditionList: List<Any>,
    @Json(name = "displayLocations")
    val displayLocations: List<String>,
    @Json(name = "displayedText")
    val displayedText: DisplayedText,
    @Json(name = "freebies")
    val freebies: List<Any>,
    @Json(name = "id")
    val id: String,
    @Json(name = "placeholder")
    val placeholder: String,
    @Json(name = "priority")
    val priority: String,
    @Json(name = "promoType")
    val promoType: String,
    @Json(name = "quickFilter")
    val quickFilter: QuickFilter,
    @Json(name = "textColor")
    val textColor: String,
    @Json(name = "timeLimitInHour")
    val timeLimitInHour: String,
    @Json(name = "timeLimitInMinutes")
    val timeLimitInMinutes: String,
    @Json(name = "timeLimitVisibility")
    val timeLimitVisibility: Boolean
)