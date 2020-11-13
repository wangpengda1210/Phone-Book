import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val first = Math.max(scanner.nextInt(), scanner.nextInt())
    val second = Math.max(scanner.nextInt(), scanner.nextInt())
    print(Math.max(first, second))
}