fun identity(number: Int): Int {
    return number
}

fun half(number: Int): Int {
    return number / 2
}

fun zero(number: Int): Int {
    return 0
}

fun generate(functionName: String): (Int) -> Int {
    return when (functionName) {
        "identity" -> ::identity
        "half" -> ::half
        else -> ::zero
    }
}