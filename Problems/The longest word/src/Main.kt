fun main() {
    val fileName = "files/words_sequence.txt"
    val lines = java.io.File(fileName).readLines()
    var longest = 0
    for (line in lines) {
        if (line.length > longest) longest = line.length
    }
    
    print(longest)
}