import kotlin.random.Random
import java.util.concurrent.atomic.AtomicInteger

fun getCarColor(car: String): String {
    return when (car) {
        "Auto 1" -> "\u001B[31m"
        "Auto 2" -> "\u001B[32m"
        "Auto 3" -> "\u001B[33m"
        "Auto 4" -> "\u001B[34m"
        "Auto 5" -> "\u001B[35m"
        else -> "\u001B[0m"
    }
}

fun resetColor(): String {
    return "\u001B[0m"
}

fun main() {
    val trackLength = 100
    val cars = listOf("Auto 1", "Auto 2", "Auto 3", "Auto 4", "Auto 5")
    val carPositions = mutableMapOf<String, Int>()
    val turnCounter = AtomicInteger(0)
    var raceFinished = false
    cars.forEach { carPositions[it] = 0 }
    val startTime = System.currentTimeMillis()
    
    val carThreads = cars.map { car ->
        Thread {
            while (!raceFinished) {
                synchronized(turnCounter) {
                    val currentPosition = carPositions[car]!!
                    val randomAdvance = Random.nextInt(1, 11)
                    val newPosition = currentPosition + randomAdvance
                    carPositions[car] = newPosition
                    println("${getCarColor(car)}$car${resetColor()} avanzó a $newPosition metros.")
                    if (newPosition >= trackLength && !raceFinished) {
                        raceFinished = true
                        val endTime = System.currentTimeMillis()
                        val timeElapsed = (endTime - startTime) / 1000
                        println("${getCarColor(car)}¡$car${resetColor()} ganó la carrera! Tiempo: $timeElapsed segundos.")
                    }
                    println("Posiciones actuales:")
                    cars.forEach { car ->
                        println("${getCarColor(car)}$car${resetColor()}: ${carPositions[car]} metros.")
                    }
                    println("=".repeat(30))
                }
                Thread.sleep(500)
            }
        }.apply { name = car }
    }
    println("¡La carrera comienza!")
    carThreads.forEach { it.start() }
    carThreads.forEach { it.join() }
    println("Carrera terminada.")
    println("Resultados finales:")
    carPositions.forEach { (car, position) ->
        println("${getCarColor(car)}$car${resetColor()}: $position metros.")
    }
    val endTime = System.currentTimeMillis()
    val totalTime = (endTime - startTime) / 1000
    println("La carrera duró $totalTime segundos.")
}
