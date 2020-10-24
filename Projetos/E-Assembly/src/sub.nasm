; Arquivo: Abs.nasm
; Curso: Elementos de Sistemas
; Criado por: Luciano Soares
; Data: 27/03/2017

; faz uma subtracao binaria do valor de :
;  RAM[1] - RAM[0] gravando em RAM[2].


  leaw $R0,%A            
  movw (%A),%D
  leaw $R1,%A
  subw (%A), %D, %D
  leaw $R2, %A
  movw %D, (%A)








