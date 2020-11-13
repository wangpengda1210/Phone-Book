import java.util.Scanner

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    print((scanner.nextDouble() * 10).toInt() % 10)
}