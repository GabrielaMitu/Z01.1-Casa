; Arquivo: Factorial.nasm
; Curso: Elementos de Sistemas
; Criado por: Luciano Soares
; Data: 27/03/2017
; Calcula o fatorial do número em R0 e armazena o valor em R1.

leaw $0, %A
movw (%A), %D
leaw $2, %A
movw %D, (%A)

leaw $FACT, %A
jne %D
nop

leaw $1, %A
movw %A, (%A)
leaw $CABOREAL, %A
jmp
nop

FACT:
leaw $2, %A
movw (%A), %D
decw %D
movw %D, (%A)

leaw $CABO, %A
je %D
nop

MULT:
leaw $2, %A
movw (%A), %D
leaw $4, %A
subw %D, (%A), %D
leaw $MULTEND, %A
je %D
nop

leaw $3, %A
movw (%A), %D
leaw $0, %A
addw (%A), %D, %D
leaw $3, %A
movw %D, (%A)

leaw $4, %A
movw (%A), %D
incw %D
movw %D, (%A)

leaw $MULT, %A
jmp
nop

MULTEND:
leaw $3, %A
movw (%A), %D
movw $0, (%A)
leaw $0, %A
movw %D, (%A)
leaw $4, %A
movw $0, (%A)

leaw $FACT, %A
jmp
nop

CABO:
leaw $0, %A
movw (%A), %D
leaw $1, %A
movw %D, (%A)
CABOREAL:

