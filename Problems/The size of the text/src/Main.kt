fun main() {
    val fileName = "files/text.txt"
    val lines = java.io.File(fileName).readText()
    
    print(lines.split(" ").size)
}