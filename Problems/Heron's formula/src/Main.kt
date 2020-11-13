import java.util.Scanner
import kotlin.math.sqrt

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val a = scanner.nextInt()
    val b = scanner.nextInt()
    val c = scanner.nextInt()
    
    val p = (a + b + c) / 2.0
    print(sqrt(p * (p - a) * (p - b) * (p - c)))
}