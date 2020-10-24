WHILE:
leaw $R1,%A            
movw (%A),%D
leaw $R0,%A
subw (%A), %D, %D
leaw $R3, %A
movw %D, (%A)

leaw $END,%D
jl
nop

leaw $R1,%A            
movw (%A),%D
leaw $R0,%A
subw (%A), %D, %D
leaw $R0, %A
movw %D, (%A)
leaw $R2, %A
movw %D, (%A)

leaw $WHILE, %A
jg
nop
END:
; leaw $R0, %A
; movw %A, %D
; lean %R2, %D