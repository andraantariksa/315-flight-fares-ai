import io.ktor.application.call
import io.ktor.html.respondHtml
import io.ktor.http.HttpStatusCode
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.html.*
import java.time.LocalDateTime
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

data class FlightSearchData(
    val dateFrom: Date,
    val airportDeparture: String,
    val airportArrival: String,
)

data class Flight(
    val datetime: LocalDateTime
)

data class FlightsResult(
    val flights: Array<Flight>
)

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        routing {
            get("/") {
                call.respondHtml(HttpStatusCode.OK, HTML::index)
            }
            post("/flight") {
                val searchData = call.receive<FlightSearchData>()
                call.respond("")
            }
            static("/static") {
                resources()
            }
        }
    }.start(wait = true)
}