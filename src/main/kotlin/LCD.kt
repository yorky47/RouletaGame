// Escreve no LCD usando a interface a 4 bits.
object LCD {

    // Dimensao do display.
    private const val LINES = 2
    private const val COLS = 16

    // Define se a interface e Serie ou Paralela
    private const val SERIAL_INTERFACE = false

    // Escreve um byte de comando/dados no LCD em paralelo
    private fun writeNibbleParallel(rs: Boolean, data: Int) {}

    // Escreve um byte de comando/dados no LCD em serie
    private fun writeNibbleSerial(rs: Boolean, data: Int) {}

    // Escreve um nibble de comando/dados no LCD
    private fun writeNibble(rs: Boolean, data: Int) {}

    // Escreve um byte de comando/dados no LCD
    private fun writeByte(rs: Boolean, data: Int) {}

    // Escreve um comando no LCD
    private fun writeCMD(data: Int) {}
}








