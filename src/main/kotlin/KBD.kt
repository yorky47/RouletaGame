import isel.leic.UsbPort
import isel.leic.utils.*

// Ler teclas. Funcoes retornam ’0’..’9’, ’A’..’D’, ’#’, ’*’ ou NONE .
object KBD {

    private val keys = arrayOf(
        '1', '2', '3', 'A',
        '4', '5', '6', 'B',
        '7', '8', '9', 'C',
        '*', '0', '#', 'D'
    )


    const val NONE = 0

    // Inicia a classe
    fun init( ){
        UsbPort.write(NONE)
    }

    // Retorna de imediato a tecla premida ou NONE se nao ha tecla premida .
    fun getKey ( ) : Char{
        val currentValue = UsbPort.read()
        if (currentValue == 0 ){
        return '1'// read a byte from the USB port
        }
                if (currentValue != NONE && currentValue < keys.size && currentValue >= 0) {
                    return keys[currentValue]
                }
        return NONE.toChar()

    }

    // Retorna a tecla premida, caso ocorra antes do ’timeout’ (em milissegundos),
    // ou NONE caso contrario .
    fun waitKey (timeout : Long) : Char{
        val startTime = System.currentTimeMillis()
        while (System.currentTimeMillis() - startTime < timeout) {
            val key = getKey()
            if (key != NONE.toChar()) {
                return key
            }
            Time.sleep(10) // Pequena pausa para evitar sobrecarga do loop
        }
        return NONE.toChar()
    }
}