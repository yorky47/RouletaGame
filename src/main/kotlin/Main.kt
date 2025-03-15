import isel.leic.UsbPort
import isel.leic.utils.*

const val Bit = 5
fun main(args: Array<String>) {
    while (true){
    val l = UsbPort.read()
    println(l)
}
/*
    var valshift = shiftLeft(1, Bit)
            println("shiftLeft(1, $Bit): $valshift")
    testHALFunctions()
    while (true) {
        val portValue = UsbPort.read()  // read a byte from the USB port
        println(portValue) // Exemplo de uso do HAL
        val getKeyResult = KBD.getKey()
        println("getKey(): $getKeyResult")
        val waitKeyResult = KBD.waitKey(1000)
        println("waitKey(1000): $waitKeyResult")
      /*  HAL.writeBits(0xFF, portValue) // write the value to the USB port
        println("Bits 0x01 esta ativo: ${HAL.isBit(0x01)}")*/

        Time.sleep(500) // Pequena pausa para não sobrecarregar o loop
    }*/
}


