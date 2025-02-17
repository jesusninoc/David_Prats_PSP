import java.rmi.registry.LocateRegistry
import java.rmi.registry.Registry


fun main() {
    val objeto=SumaI()
    val register:Registry=LocateRegistry.createRegistry(6000)
    register.rebind("",objeto)
}
