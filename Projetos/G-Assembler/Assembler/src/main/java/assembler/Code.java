/**
 * Curso: Elementos de Sistemas
 * Arquivo: Code.java
 */

package assembler;

/**
 * Traduz mnemônicos da linguagem assembly para códigos binários da arquitetura Z0.
 */
public class Code {

    /**
     * Retorna o código binário do(s) registrador(es) que vão receber o valor da instrução.
     * @param  mnemnonic vetor de mnemônicos "instrução" a ser analisada.
     * @return Opcode (String de 4 bits) com código em linguagem de máquina para a instrução.
     */
    public static String dest(String[] mnemnonic) {
          int tamanho = mnemnonic.length;

          if (mnemnonic[0] == "jmp" || mnemnonic[0] == "je" || mnemnonic[0] == "jne" || mnemnonic[0] == "jg" || mnemnonic[0] == "jge" || mnemnonic[0] == "jl" || mnemnonic[0] == "jle"){
              return "0000";
          }
          else if (tamanho <4){
              if (mnemnonic[tamanho-1] == "(%A)"){ return "0100";}
              else if (mnemnonic[tamanho-1] == "%A"){ return "0001";}
              else if (mnemnonic[tamanho-1] == "%D"){ return "0010";}
          }
          else if (tamanho == 4)
              if (mnemnonic[0] == "movw" && mnemnonic[3] == "(%A)")  {return "0110";}
              if (mnemnonic[0] == "addw" && mnemnonic[3] == "%D")  {return "0010";}
              else{
                  if (mnemnonic[tamanho-1] == "(%A)"){ return "0100";}
                  else if (mnemnonic[tamanho-1] == "%A"){ return "0001";}
                  else if (mnemnonic[tamanho-1] == "%D"){ return "0010";}}
            return "0000";
    }

    /**
     * Retorna o código binário do mnemônico para realizar uma operação de cálculo.
     * @param  mnemnonic vetor de mnemônicos "instrução" a ser analisada.
     * @return Opcode (String de 7 bits) com código em linguagem de máquina para a instrução.
     */
    public static String comp(String[] mnemnonic) {
        if (mnemnonic[0].equals("jmp")){ return "000001100";}
        else if (mnemnonic[0].equals("je")){ return "000001100"; }
        else if (mnemnonic[0].equals("jne")){ return "000001100";}
        else if (mnemnonic[0].equals("jg")){ return "000001100";}
        else if (mnemnonic[0].equals("jge")){ return "000001100";}
        else if (mnemnonic[0].equals("jl")){ return "000001100";}
        else if (mnemnonic[0].equals("jle")){ return "000001100";}

        else if (mnemnonic[0].equals("movw")){
            if (mnemnonic[1].equals("%A")){
                switch (mnemnonic[2]){
                    case "%D"  : return "000110000";
                    case "(%A)": return "000110000";
                    default    : return "000000000";
                }}
            else if (mnemnonic[1].equals("%D")){
                switch (mnemnonic[2]){
                    case "%A"  : return "000001100";
                    case "(%A)": return "000001100";
                    default    : return "000000000";
                }}
            else if (mnemnonic[1].equals("(%A)")){
                switch (mnemnonic[2]){
                    case "%A"  : return "001110000";
                    case "%D"  : return "001110000";
                    default    : return "000000000";
                }}
        }
//        SIMPLICANDO
        else if (mnemnonic[0].equals("addw")){
            if (mnemnonic[1].equals("%A")){
                switch (mnemnonic[2]){
                    case "%D"  : return "000000010";
                    default    : return "000000000";
                }}
            else if (mnemnonic[1].equals("(%A)")){
                switch (mnemnonic[2]){
                    case "%D"  : return "001000010";
                    default    : return "000000000";
                }}
            else if (mnemnonic[1].equals("$1")){
                switch (mnemnonic[2]){
                    case "(%A)"  : return "001110111";
                    default    : return "000000000";
                }}
        }

        else if (mnemnonic[0].equals("incw")){
            switch (mnemnonic[1]){
                case "(%A)"  : return "001110111";
                case "%A"  : return "000110111";
                case "%D"  : return "000011111";
                default    : return "000000000";
            }
        }
        else if (mnemnonic[0].equals("decw")){
            switch (mnemnonic[1]){
                case "%A"  : return "000110010";
                case "%D"  : return "000001110";
                default    : return "000000000";
            }
        }
        else if (mnemnonic[0].equals("notw")){
            switch (mnemnonic[1]){
                case "%A"  : return "000110001";
                case "%D"  : return "000001101";
                default    : return "000000000";
            }
        }
        else if (mnemnonic[0].equals("negw")){
            switch (mnemnonic[1]){
                case "%A"  : return "000110011";
                case "%D"  : return "000001111";
                default    : return "000000000";
            }
        }
        else if (mnemnonic[0].equals("andw")){
            switch (mnemnonic[1]){
                case "(%A)"  : return "001000000";
                case "%D"  : return "000000000";
//                default    : return "000000000";
            }
        }
        else if (mnemnonic[0].equals("orw")){
            switch (mnemnonic[1]){
                case "(%A)"  : return "001010101";
                case "%D"  : return "000010101";
                default    : return "000000000";
            }
        }
        else if (mnemnonic[0].equals("subw")){
            switch (mnemnonic[1]){
                case "(%A)"  : return "001110010";
                case "%D"  : return "001010011";
                default    : return "000000000";
            }
        }
        else if (mnemnonic[0].equals("rsubw")){return "001000111";}
//        else {
//            return "";
//        }
        return "";
    }

    /**
     * Retorna o código binário do mnemônico para realizar uma operação de jump (salto).
     * @param  mnemnonic vetor de mnemônicos "instrução" a ser analisada.
     * @return Opcode (String de 3 bits) com código em linguagem de máquina para a instrução.
     */
    public static String jump(String[] mnemnonic) {
        switch (mnemnonic[0]){
            case "jmp"  : return "111";
            case "jle"  : return "110";
            case "jne"  : return "101";
            case "jl"   : return "100";
            case "jge"  : return "011";
            case "je"   : return "010";
            case "jg"   : return "001";

            default    : return "000";
        }
    }

    /**
     * Retorna o código binário de um valor decimal armazenado numa String.
     * @param  symbol valor numérico decimal armazenado em uma String.
     * @return Valor em binário (String de 15 bits) representado com 0s e 1s.
     */
    public static String toBinary(String symbol) {
        int value = Integer.valueOf(symbol);
        String binary = Integer.toBinaryString(value);
        return String.format("%1$16s", binary).replace(" ", "0");
    }

}
