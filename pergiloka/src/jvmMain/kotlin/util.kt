import java.time.LocalDate

fun customDateToString(time: LocalDate): String {
    return "${time.year}${time.monthValue.toString().padStart(2, '0')}${time.dayOfMonth}"
}