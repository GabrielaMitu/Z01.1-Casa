; Arquivo: SWeLED.nasm
; Curso: Elementos de Sistemas
; Criado por: Rafael Corsi
; Data: 28/3/2018
;
; Faça os LEDs exibirem 
; LED = ON ON ON ON ON !SW3 !SW2 !SW1 0
; Mesma questão da prova
; 11111 0000 496

leaw $21185, %A 
movw (%A), %D    
leaw $R0, %A
movw (%A), %D    
leaw $14, %A
notw %D 
andw %D, %A, %D 
leaw $496, %A  
addw %A, %D, %D
leaw $21184, %A
movw %D, (%A) 
