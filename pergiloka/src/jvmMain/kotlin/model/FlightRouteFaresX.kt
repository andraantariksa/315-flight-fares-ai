package model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FlightRouteFaresX(
    @Json(name = "adminFeeTotal")
    val adminFeeTotal: AdminFeeTotalX,
    @Json(name = "adultAirlineFare")
    val adultAirlineFare: AdultAirlineFare,
    @Json(name = "adultBaseFare")
    val adultBaseFare: AdultBaseFare,
    @Json(name = "baseFareTotal")
    val baseFareTotal: BaseFareTotal,
    @Json(name = "childBaseFare")
    val childBaseFare: ChildBaseFare,
    @Json(name = "compulsoryInsuranceTotal")
    val compulsoryInsuranceTotal: CompulsoryInsuranceTotal,
    @Json(name = "fuelSurchargeTotal")
    val fuelSurchargeTotal: FuelSurchargeTotal,
    @Json(name = "infantBaseFare")
    val infantBaseFare: InfantBaseFare,
    @Json(name = "pscTotal")
    val pscTotal: PscTotal,
    @Json(name = "totalAdditionalFare")
    val totalAdditionalFare: TotalAdditionalFare,
    @Json(name = "totalAirlineFare")
    val totalAirlineFare: TotalAirlineFare,
    @Json(name = "vatTotal")
    val vatTotal: VatTotal
)