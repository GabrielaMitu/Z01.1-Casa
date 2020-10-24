; Arquivo: LCDQuadrado.nasm
; Curso: Elementos de Sistemas
; Criado por: Rafael Corsi
; Data: 28/3/2018
;
; Desenhe uma linha no LCD

;leaw $16384, %A
;movw $-1, (%A)

leaw $0, %A
movw %A, %D
notw %D

leaw $16424, %A
movw %D, (%A) 

incw %A
movw %D, (%A) 
incw %A
movw %D, (%A) 
incw %A
movw %D, (%A) 
incw %A
movw %D, (%A) 
incw %A
movw %D, (%A) 
incw %A
movw %D, (%A) 
incw %A
movw %D, (%A) 
incw %A
movw %D, (%A) 
incw %A
movw %D, (%A) 
incw %A
movw %D, (%A) 
incw %A
movw %D, (%A) 
incw %A
movw %D, (%A) 
incw %A
movw %D, (%A) 
incw %A
movw %D, (%A) 
incw %A
movw %D, (%A) 
incw %A
movw %D, (%A) 
incw %A
movw %D, (%A) 
incw %A
movw %D, (%A) 
incw %A
movw %D, (%A) 