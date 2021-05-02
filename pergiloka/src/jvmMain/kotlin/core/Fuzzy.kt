package core

enum class FuzzyType {
    TravelTime,
    Price
}

fun fuzzy(x_: Float): Pair<FuzzyType, Float> {
    if (x_ < 0.5f) {
        val x = x_ * 2.0f
        return Pair(FuzzyType.TravelTime, x)
    } else {
        val x = (x_ - 0.5f) * 2.0f
        return Pair(FuzzyType.Price, x)
    }
}
