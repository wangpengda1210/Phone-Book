val lambda: (Long, Long) -> Long = { left, right ->
    var result = 1L
    for (i in left..right) result *= i
    result
}