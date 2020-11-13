package phonebook

import java.io.File
import kotlin.math.min
import kotlin.math.sqrt

var names = mutableListOf<String>()
var numbers = mutableListOf<Long>()
var searchTime = 0L
var startTime = 0L
var bubbleSortingTime = 0L

fun main() {
    
    init()
    
    val findNames = File("files/find.txt").readLines()
    
    startTime = System.currentTimeMillis()
    println("Start searching (linear search)...")
    linearSearchAll(findNames)
    
    val originalNames = mutableListOf<String>()
    originalNames.addAll(names)
    val originalNumbers = mutableListOf<Long>()
    originalNumbers.addAll(numbers)
    
    startTime = System.currentTimeMillis()
    println("\nStart searching (bubble sort + jump search)...")
    jumpSearchAll(findNames)
    
    names = originalNames
    numbers = originalNumbers
    
    startTime = System.currentTimeMillis()
    println("\nStart searching (quick sort + binary search)...")
    binarySearchAll(findNames)
    
    startTime = System.currentTimeMillis()
    println("\nStart searching (hash table)...")
    hashSearchAll(findNames)

}

fun init() {
    val directory = File("files/directory.txt").readLines()
    
    for (line in directory) {
        val pair = line.split(" ")
        names.add(line.substring(pair[0].length + 1, line.length))
        numbers.add(pair[0].toLong())
    }
}

fun linearSearchAll(findNames: List<String>) {
    var count = 0
    
    for (findName in findNames) {
        if (linearSearch(findName) != null) count++
    }
    
    searchTime = System.currentTimeMillis() - startTime
    println("Found $count/${findNames.size} entries. Time taken: ${displayTime(searchTime)}.")
}

fun displayTime(timeInMill: Long): String {
    val minutes = timeInMill / 60000
    val seconds = timeInMill % 60000 / 1000
    val milliseconds = timeInMill % 60000 % 1000
    return "$minutes min. $seconds sec. $milliseconds ms"
}


fun linearSearch(findName: String): Long? {
    for (i in names.indices) {
        if (names[i] == findName) return numbers[i]
    }
    return null
}

fun bubbleSort(findNames: List<String>): Boolean {
    val bubbleSortStartTime = System.currentTimeMillis()
    for (i in names.indices) {
        for (j in 0 until names.lastIndex - i) {
            if (names[j] > names[j + 1]) {
                swapValue(j, j + 1)
            }
            bubbleSortingTime = System.currentTimeMillis() - bubbleSortStartTime
            if (bubbleSortingTime > 10 * searchTime) {
                linearSearchAll(findNames)
                return false
            }
        }
    }
    bubbleSortingTime = System.currentTimeMillis() - bubbleSortStartTime
    return true
}

fun jumpSearchAll(findNames: List<String>) {
    var count = 0
    
    val bubbleSortResult = bubbleSort(findNames)
    
    if (bubbleSortResult) {
        for (findName in findNames) {
            if (jumpSearch(findName) != null) count++
        }
    
        val totalTime = System.currentTimeMillis() - startTime
        val jumpSearchTime = totalTime - bubbleSortingTime
        println("Found $count/${findNames.size} entries. Time taken: ${displayTime(totalTime)}.")
        println("Sorting time: ${displayTime(bubbleSortingTime)}.")
        println("Searching time: ${displayTime(jumpSearchTime)}.")
    } else {
        println("Sorting time: ${displayTime(bubbleSortingTime)}. - STOPPED, moved to linear search")
        println("Searching time: ${displayTime(searchTime - bubbleSortingTime)}.")
    }
    
}

fun jumpSearch(findName: String): Long? {
    val blockSize = sqrt(names.size.toDouble()).toInt()
    val blockCount = names.size / blockSize + 1
    
    for (i in 0 until blockCount) {
        val lastPosition = min((i + 1) * blockSize - 1, names.lastIndex)
        if (names[lastPosition] >= findName) {
            for (j in lastPosition downTo i * blockSize) {
                if (names[j] == findName) return numbers[j]
            }
        }
    }
    
    return null
}

fun partition(left: Int, right: Int, pivot: Int): Int {
    val pivotValue = names[pivot]
    swapValue(pivot, right)
    
    var storeIndex = left
    for (i in left until right) {
        if (names[i] <= pivotValue) {
            swapValue(i, storeIndex)
            storeIndex++
        }
    }
    swapValue(right, storeIndex)
    
    return storeIndex
}

fun quickSort(left: Int, right: Int) {
    if (left >= right) return
    else {
        var pivotIndex = (left + right) / 2
        pivotIndex = partition(left, right, pivotIndex)
        quickSort(left, pivotIndex - 1)
        quickSort(pivotIndex + 1, right)
    }
}

fun binarySearchAll(findNames: List<String>) {
    
    quickSort(0, names.lastIndex)
    val quickSortTime = System.currentTimeMillis() - startTime
    
    var count = 0
    
    for (findName in findNames) {
        if (binarySearch(0, names.lastIndex, findName) != null) count++
    }
    
    val totalTime = System.currentTimeMillis() - startTime
    searchTime = totalTime - quickSortTime
    println("Found $count/${findNames.size} entries. Time taken: ${displayTime(totalTime)}.")
    println("Sorting time: ${displayTime(quickSortTime)}.")
    println("Searching time: ${displayTime(searchTime)}.")
}

fun binarySearch(left: Int, right: Int, findName: String): Long? {
    when {
        left > right -> return null
        left == right -> {
            return if (names[left] == findName) numbers[left] else null
        }
        else -> {
            val middle = (left + right) / 2
            val middleName = names[middle]
            if (middleName == findName) return numbers[middle]
    
            return if (middleName < findName) binarySearch(middle + 1, right, findName)
            else binarySearch(left, middle - 1, findName)
        }
    }
}

fun swapValue(i: Int, j: Int) {
    val tempName = names[j]
    val tempNumber = numbers[j]
    names[j] = names[i]
    numbers[j] = numbers[i]
    names[i] = tempName
    numbers[i] = tempNumber
}

fun hashSearchAll(findNames: List<String>) {
    val hashMap = hashMapOf<String, Long>()
    
    for (i in names.indices) {
        hashMap[names[i]] = numbers[i]
    }
    
    val creatingTime = System.currentTimeMillis() - startTime
    
    var count = 0
    
    for (findName in findNames) {
        if (hashSearch(findName, hashMap) != null) count++
    }
    
    val totalTime = System.currentTimeMillis() - startTime
    searchTime = totalTime - creatingTime
    println("Found $count/${findNames.size} entries. Time taken: ${displayTime(totalTime)}.")
    println("Creating time: ${displayTime(creatingTime)}.")
    println("Searching time: ${displayTime(searchTime)}.")
}

fun <K, V> hashSearch(findName: String, hashMap: HashMap<K, V>): V? {
    return if (hashMap.containsKey(findName)) hashMap[findName] else null
}