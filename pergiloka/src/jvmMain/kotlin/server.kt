import com.hypercubetools.ktor.moshi.moshi
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import core.FlightInfo
import core.search
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.html.respondHtml
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.html.*
import model.Flight
import java.util.*

fun HTML.index() {
    head {
        title("Pergiloka")
    }
    body {
        div {
            id = "root"
        }
        script(src = "/static/js.js") {}
    }
}

@JsonClass(generateAdapter = true)
data class FlightResult(
    val flightInfos: List<FlightInfo>
)

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        install(CORS) {
            anyHost()
        }
        install(ContentNegotiation) {
            moshi()
        }
        routing {
            get("/") {
                call.respondHtml(HttpStatusCode.OK, HTML::index)
            }
            post("/flight") {
                lateinit var departureDate: LocalDate
                lateinit var airportDeparture: String
                lateinit var airportArrival: String
                var fuzzyChoice: Float = 0.0f

                val multipartData = call.receiveMultipart()
                multipartData.forEachPart { part ->
                    when (part) {
                        is PartData.FormItem -> {
                            when (part.name) {
                                "departureDate" -> {
                                    departureDate = LocalDate.parse(part.value)
                                }
                                "airportDeparture" -> {
                                    airportDeparture = part.value
                                }
                                "airportArrival" -> {
                                    airportArrival = part.value
                                }
                                "fuzzyChoice" -> {
                                    fuzzyChoice = part.value.toFloat()
                                }
                            }
                        }
                    }
                }
                val moshi = Moshi.Builder().build()
                val jsonAdapter: JsonAdapter<FlightResult> = moshi.adapter<FlightResult>(FlightResult::class.java)

                val s = search(airportDeparture, airportArrival, fuzzyChoice)
                call.respond(jsonAdapter.toJson(FlightResult(s as List<FlightInfo>)))
            }
            static("/static") {
                resources()
            }
        }
    }.start(wait = true)
}