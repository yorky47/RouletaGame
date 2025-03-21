import isel.leic.utils.Time

// Escreve no LCD usando a interface a 4 bits.
object LCD {

    // Dimensao do display.
    private const val LINES = 2
    private const val COLS = 16

    // Define se a interface e Serie ou Paralela
    private const val SERIAL_INTERFACE = false
    private const val LOW_MASK = 0x0F
    private const val HIGH_MASK = 0xF0

    private const val FUNCTION_SET = 0b0000110000
    private const val DISPLAY_OFF = 0b0000111000
    private const val DISPLAY_CLEAR = 0b0000000001

    // Escreve um byte de comando/dados no LCD em paralelo
    private fun writeNibbleParallel(rs: Boolean, data: Int){





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
        writeNibble(rs, (data and HIGH_MASK) shr 4)
        writeNibble(rs,data and LOW_MASK)
    }

    // Escreve um comando no LCD
    private fun writeCMD(data: Int){
        writeByte(false, data)
    }

    // Escreve um dado no LCD
    private fun writeDATA(data: Int){
        writeByte(true, data)
    }

    // Envia a sequencia de iniciacao para comunicacao a 4 bits.
    fun init (){
        Time.sleep(15)

        writeCMD(FUNCTION_SET)

        Time.sleep(5)

        writeCMD(FUNCTION_SET)

        Time.sleep(1)

        writeCMD(FUNCTION_SET)

        writeCMD(DISPLAY_OFF)

        writeCMD(DISPLAY_CLEAR)

    }

    // Escreve um carater na posicao corrente.
    fun write (c: Char){
        writeDATA(c.code)
    }

    // Escreve uma string na posicao corrente.
    fun write(text: String){
        text.forEach {write(it)}
    }

    // Envia comando para posicionar cursor ('line ' : 0. . LINES -1 , 'column ' : 0. . COLS -1)
    fun cursor (line: Int, column: Int){

        var lineValue = 0x0
        var colValue = 0x4
        if (line in 0..LINES -1 && column in 0..COLS -1){
            if(line == 0) lineValue = 0x00 else lineValue = 0x40
            colValue = lineValue + column
        }

        writeCMD(0x80 + colValue)


    }

    // Envia comando para limpar o ecra e posicionar o cursor em (0,0)
    fun clear (){
        writeCMD(DISPLAY_CLEAR)
    }
}