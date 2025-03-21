/*import isel.leic.UsbPort
import isel.leic.utils.*

const val Bit = 5
fun main(args: Array<String>) {
    HAL.writeBits(0x0F, 0x00)
    while (true) {
        val portValue = UsbPort.read()  // read a byte from the USB port
        println(portValue) // Exemplo de uso do HAL
       // HAL.writeBits(0xFF, portValue) // write the value to the USB port
      //  println("Bits 0x01 esta ativo: ${HAL.isBit(0x01)}")
        Time.sleep(500) // Pequena pausa para não sobrecarregar o loop
    }

}
*/

import isel.leic.UsbPort
import isel.leic.utils.*

fun main() {
    // Inicializa os módulos HAL e KBD
    HAL.init()
    KBD.init()

    println("=== Jogo da Roleta - Inicializado ===")

    var creditos = 0
    var apostaAtual: Char? = null
    var jogoRodando = false

    while (true) {
        // Ler tecla pressionada
        val key = KBD.getKey()
        if (key != KBD.NONE.toChar()) {
            println("Tecla pressionada: $key")

            when (key) {
                '*' -> { // Iniciar o jogo
                    if (creditos > 0) {
                        jogoRodando = true
                        println("Jogo iniciado! Faça sua aposta.")
                    } else {
                        println("Sem créditos!")
                    }
                }

                '#' -> { // Finalizar apostas
                    if (jogoRodando) {
                        println("Apostas encerradas. Sorteando resultado...")
                        val resultado = (0..9).random() // Simula um sorteio
                        println("Número sorteado: $resultado")
                        jogoRodando = false
                    }
                }

                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D' -> {
                    if (!jogoRodando) {
                        apostaAtual = key
                        println("Aposta registrada: $apostaAtual")
                        creditos--
                    }
                }

                else -> println("Tecla inválida.")
            }
        }

        // Verifica inserção de créditos no moedeiro
        val coinInserted = HAL.isBit(0x02) ?: false
        if (coinInserted) {
            creditos += 2  // Cada moeda adiciona 2 créditos
            println("Moeda inserida! Créditos: $creditos")
        }

        Time.sleep(200)  // Pequena pausa para evitar sobrecarga da CPU
    }
}



