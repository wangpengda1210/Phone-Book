import java.util.Scanner
import kotlin.math.PI
import kotlin.math.acos
import kotlin.math.pow
import kotlin.math.sqrt

fun main() {
    val scanner = Scanner(System.`in`)
    val x1 = scanner.nextDouble()
    val y1 = scanner.nextDouble()
    val x2 = scanner.nextDouble()
    val y2 = scanner.nextDouble()
    
    print((acos((x1 * x2 + y1 * y2) /
            (sqrt(x1.pow(2.0) + y1.pow(2.0)) *
                    sqrt(x2.pow(2.0) + y2.pow(2.0))))) * 180 / PI)
}