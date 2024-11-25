class TablaMultiplicar(private val numero: Int) : Thread() {
    override fun run() {
        println("Comenzar tabla del $numero")
        for (i in 1..10) {
            try {
                Thread.sleep(500)
                println("$numero x $i = ${numero * i}")
            } catch (ex: InterruptedException) {
                ex.printStackTrace()
            }
        }
        println("Ha acabado la tabla del $numero")
    }

}

fun main() {
    val tablas = mutableListOf<TablaMultiplicar>()
    for (numero in 1..10) {
        val tabla = TablaMultiplicar(numero)
        tablas.add(tabla)
        tabla.start()
    }
    for (tabla in tablas) {
        tabla.join()
    }
}
