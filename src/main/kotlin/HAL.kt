import isel.leic.UsbPort

const val ALLMASK = 0xFF
/**
 * HAL - Hardware Abstraction Layer 5
 * Camada de abstração para acesso ao hardware através da porta USB
 */
object HAL {
    // Cache do último valor lido/escrito para evitar operações desnecessárias
    private var lastValue = 0x00

    /**
     * Inicializa o HAL e limpa todos os bits
     */
    fun init() {
        clrBits(ALLMASK)
        lastValue = 0x00
        UsbPort.write(lastValue)
    }

    /**
     * Verifica se um bit específico está ativo
     * @param mask A máscara do bit a verificar (deve ter apenas um bit ativo)
     * @return true se o bit estiver ativo, false se estiver inativo, ou null se a máscara for inválida
     */
    fun isBit(mask: Int): Boolean? {
        // Verifica se o mask é uma potência de 2 (tem apenas 1 bit ativado)
        if (mask == 0 || (mask and (mask - 1)) != 0) {
            return null  // Retorna null para máscara inválida
        }
        val currentValue = UsbPort.read()
        return (currentValue and mask) != 0
    }

    /**
     * Lê os bits definidos pela máscara
     * @param mask A máscara dos bits a serem lidos
     * @return O valor dos bits definidos pela máscara
     */
    fun readBits(mask: Int): Int {
        val currentValue = UsbPort.read()
        lastValue = currentValue  // Atualiza o cache
        return currentValue and mask
    }

    /**
     * Escreve os bits definidos pela máscara
     * @param mask A máscara dos bits a serem escritos
     * @param value O valor a ser escrito nos bits definidos pela máscara
     */
    fun writeBits(mask: Int, value: Int) {
        val currentValue = UsbPort.read()
        // Mantém os bits que não estão na máscara e atualiza os bits que estão
        val newValue = (currentValue and mask.inv()) or (value and mask)
        lastValue = newValue  // Atualiza o cache
        UsbPort.write(newValue)
    }

    /**
     * Ativa os bits definidos pela máscara
     * @param mask A máscara dos bits a serem ativados
     */
    fun setBits(mask: Int) {
        val currentValue = UsbPort.read()
        val newValue = currentValue or mask
        if (newValue != currentValue) {  // Só escreve se houver mudança
            lastValue = newValue  // Atualiza o cache
            UsbPort.write(newValue)
        }
    }


    /**
     * Desativa os bits definidos pela máscara
     * @param mask A máscara dos bits a serem desativados
     */
    fun clrBits(mask: Int) {
        val currentValue = UsbPort.read()
        val newValue = currentValue and mask.inv()
        if (newValue != currentValue) {  // Só escreve se houver mudança
            lastValue = newValue  // Atualiza o cache
            UsbPort.write(newValue)
        }
    }
}
fun shiftLeft(value: Int, shift: Int): Int {
    return value shl shift
}