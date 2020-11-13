fun main() {
    val fileName = "files/words_with_numbers.txt"
    val lines = java.io.File(fileName).readLines()
    
    var count = 0
    for (line in lines) {
        if (line.all { it.isDigit() }) count++
    }
    
    print(count)
}