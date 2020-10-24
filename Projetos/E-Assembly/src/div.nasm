; Arquivo: Div.nasm
; Curso: Elementos de Sistemas
; Criado por: Luciano Soares
; Data: 27/03/2017

; Divide R0 por R1 e armazena o resultado em R2.
; (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], respectively.)
; divisao para numeros inteiros positivos



leaw $2, %A
movw $0, (%A)

leaw $0, %A
movw (%A), %D ; D = RAM[0]

leaw $3, %A
movw %D, (%A) ; RAM[3] = RAM[0]

WHILE:
leaw $3, %A
movw (%A), %D

leaw $1, %A
subw %D, (%A), %D ; RAM[3] - RAM[1]
leaw $3, %A
movw %D, (%A)

leaw $END, %A
jl
nop

leaw $2, %A
movw (%A), %D
incw %D
movw %D, (%A) ; RAM[2] += 1

leaw $WHILE, %A
jmp
nop

END:

















































































