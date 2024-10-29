import java.io.BufferedReader
import java.io.InputStreamReader

class Hijo {

    // Método para verificar si un número es primo
    fun esPrimo(numero: Int): Boolean {
        if (numero <= 1) return false
        for (i in 2..numero / 2) {
            if (numero % i == 0) return false
        }
        return true
    }

    // Método para recibir el número, verificar si es primo, y devolver el resultado
    fun procesarNumero() {
        // Leer el número desde la entrada estándar (stdin)
        val input = BufferedReader(InputStreamReader(System.`in`))

        println("Esperando número del proceso padre...")

        val numero = input.readLine()?.toIntOrNull()

        if (numero != null) {
            println("Número recibido: $numero")
            // Verificar si el número es primo
            val resultado = if (esPrimo(numero)) "es primo" else "no es primo"

            // Enviar el resultado de vuelta al padre
            println(resultado)
        } else {
            println("Número inválido")
        }
    }
}

fun main() {
    // Crear una instancia del Hijo y procesar el número recibido
    val hijo = Hijo()
    hijo.procesarNumero()
}
