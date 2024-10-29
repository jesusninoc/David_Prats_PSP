import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {

    val input = BufferedReader(InputStreamReader(System.`in`))
    val numero = input.readLine()?.toIntOrNull()

    if (numero != null) {
        val resultado = if (esPrimo(numero)) "es primo" else "no es primo"
        println(resultado)
    } else {
        println("Número inválido")
    }
}

fun esPrimo(numero: Int): Boolean {
    if (numero <= 1) return false
    for (i in 2..Math.sqrt(numero.toDouble()).toInt()) {
        if (numero % i == 0) return false
    }
    return true
}
