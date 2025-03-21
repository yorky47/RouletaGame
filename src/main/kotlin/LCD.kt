import isel.leic.utils.Time

// Escreve no LCD usando a interface a 4 bits.
object LCD {

    // Dimensao do display.
    private const val LINES = 2
    private const val COLS = 16

    // Define se a interface e Serie ou Paralela
    private const val SERIAL_INTERFACE = false

    // Escreve um byte de comando/dados no LCD em paralelo
    private fun writeNibbleParallel(rs: Boolean, data: Int){
        HAL.clrBits(ALLMASK)


    }

    // Escreve um byte de comando/dados no LCD em serie
    private fun writeNibbleSerial(rs: Boolean, data: Int){

    }

    // Escreve um nibble de comando/dados no LCD
    private fun writeNibble(rs: Boolean, data: Int){
    if(SERIAL_INTERFACE) writeNibbleSerial(rs, data)
    else writeNibbleParallel(rs, data)
    }

    // Escreve um byte de comando/dados no LCD
    private fun writeByte(rs: Boolean, data: Int){

    }

    // Escreve um comando no LCD
    private fun writeCMD(data: Int){
        writeByte(SERIAL_INTERFACE, data)
    }

    // Escreve um dado no LCD
    private fun writeDATA(data: Int){

    }

    // Envia a sequencia de iniciacao para comunicacao a 4 bits.
    fun init (){
        Time.sleep(15)

        writeCMD(0b0000110000)

        Time.sleep(5)

        writeCMD(0b0000110000)

        Time.sleep(1)

        writeCMD(0b0000110000)

        writeCMD(0b0000111000)

        writeCMD(0b0000000001)

    }

    // Escreve um carater na posicao corrente.
    fun write (c: Char){

    }

    // Escreve uma string na posicao corrente.
    fun write(text: String){

    }

    // Envia comando para posicionar cursor ('line ' : 0. . LINES -1 , 'column ' : 0. . COLS -1)
    fun cursor (line: Int, column: Int){

    }

    // Envia comando para limpar o ecra e posicionar o cursor em (0,0)
    fun clear (){

    }
}








