; Arquivo: Pow.nasm
; Curso: Elementos de Sistemas
; Criado por: Luciano Soares
; Data: 27/03/2017

; Eleva ao quadrado o valor da RAM[1] e armazena o resultado na RAM[0].
; Só funciona com números positivos

LOOP:
leaw $1, %A
movw (%A), %D
leaw $END, %A
jle %D
nop
leaw $2, %A
subw %D, (%A), %D
leaw $END, %A
je %D
nop

leaw $1, %A
movw (%A), %D
leaw $0, %A
addw (%A), %D, %D
movw %D, (%A)
leaw $2, %A
movw (%A), %D
incw %D
movw %D, (%A)
leaw $LOOP, %A
jmp
nop

END: