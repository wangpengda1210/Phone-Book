import java.util.Scanner
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val a = scanner.nextDouble()
    val b = scanner.nextDouble()
    val c = scanner.nextDouble()
    
    val x1 = (-b + sqrt(b.pow(2) - 4 * a * c)) / (2 * a)
    val x2 = (-b - sqrt(b.pow(2) - 4 * a * c)) / (2 * a)
    
    println(min(x1, x2))
    println(max(x1, x2))
}