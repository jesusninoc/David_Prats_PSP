import java.rmi.registry.LocateRegistry

fun main(){
    val registro=LocateRegistry.getRegistry(6000)
    val sumaremota=registro.lookup("Suma") as SumaR
    println(sumaremota.suma(1,7))

}