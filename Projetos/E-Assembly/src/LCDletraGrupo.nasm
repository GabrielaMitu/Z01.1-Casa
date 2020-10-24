; Arquivo: LCDletraGrupo.nasm
; Curso: Elementos de Sistemas
; Criado por: Rafael Corsi
; Data: 28/3/2018
;
; Escreva no LCD a letra do grupo de vocês
;  - Valide no hardawre
;  - Bata uma foto!

leaw $16384, %A
movw %A, %D
leaw $0, %A
movw %D, (%A)
leaw $18944, %A
movw %A, %D
leaw $1, %A
movw %D, (%A)

VERT:
leaw $0, %A
movw (%A), %D
movw %D, %A
movw -1, (%A)
movw %A, %D
leaw $20, %A
addw %D, %A, %D
leaw $0, %A
movw %D, (%A)
leaw $1, %A
subw (%A), %D, %D
leaw $PASS0, %A
jle %D
nop
leaw $VERT, %A
jmp
nop

PASS0:
leaw $16385, %A
movw %A, %D
leaw $2, %A
movw %D, (%A)

SUP:
leaw $2, %A
movw (%A), %D
leaw $0, %A
movw %D, (%A)
leaw $320, %A
addw %A, %D, %D
leaw $1, %A
movw %D, (%A)

LOOP1:
leaw $0, %A
movw (%A), %D
movw %D, %A
movw -1, (%A)
movw %A, %D
leaw $20, %A
addw %D, %A, %D
leaw $0, %A
movw %D, (%A)
leaw $1, %A
subw (%A), %D, %D
leaw $PASS1, %A
jle %D
nop
leaw $LOOP1, %A
jmp
nop

PASS1:
leaw $2, %A
movw (%A),%D
incw %D
movw %D,(%A)
leaw $16392, %A
subw %A, %D, %D
leaw $FIMSUP, %A
jle %D
nop
leaw $SUP, %A
jmp
nop

FIMSUP:
leaw $18944, %A
movw %A, %D
leaw $2, %A
movw %D, (%A)

INF:
leaw $2, %A
movw (%A), %D
leaw $0, %A
movw %D, (%A)
leaw $320, %A
addw %A, %D, %D
leaw $1, %A
movw %D, (%A)

LOOP2:
leaw $0, %A
movw (%A), %D
movw %D, %A
movw -1, (%A)
movw %A, %D
leaw $20, %A
addw %D, %A, %D
leaw $0, %A
movw %D, (%A)
leaw $1, %A
subw (%A), %D, %D
leaw $PASS2, %A
jle %D
nop
leaw $LOOP2, %A
jmp
nop

PASS2:
leaw $2, %A
movw (%A),%D
incw %D
movw %D,(%A)
leaw $18952, %A
subw %A, %D, %D
leaw $FIMINF, %A
jle %D
nop
leaw $INF, %A
jmp
nop

FIMINF:
leaw $17671, %A
movw %A, %D
leaw $0, %A
movw %D, (%A)
leaw $18951, %A
movw %A, %D
leaw $1, %A
movw %D, (%A)

