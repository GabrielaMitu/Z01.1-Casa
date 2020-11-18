
package assembler;

import java.io.*;

import static assembler.AssemblerZ01.verbose;

/**
 * Faz a geração do código gerenciando os demais módulos
 */
public class Assemble {
    private String inputFile;              // arquivo de entrada nasm
    File hackFile = null;                  // arquivo de saída hack
    private PrintWriter outHACK = null;    // grava saida do código de máquina em Hack
    boolean debug;                         // flag que especifica se mensagens de debug são impressas
    private SymbolTable table;             // tabela de símbolos (variáveis e marcadores)

    /*
     * inicializa assembler
     * @param inFile
     * @param outFileHack
     * @param debug
     * @throws IOException
     */
    public Assemble(String inFile, String outFileHack, boolean debug) throws IOException {
        this.debug = debug;
        inputFile  = inFile;
        hackFile   = new File(outFileHack);                      // Cria arquivo de saída .hack
        outHACK    = new PrintWriter(new FileWriter(hackFile));  // Cria saída do print para
        // o arquivo hackfile
        table      = new SymbolTable();                          // Cria e inicializa a tabela de simbolos
    }

    /**
     * primeiro passo para a construção da tabela de símbolos de marcadores (labels)
     * varre o código em busca de novos Labels e Endereços de memórias (variáveis)
     * e atualiza a tabela de símbolos com os endereços (table).
     *
     * Dependencia : Parser, SymbolTable
     * @return
     */
    public SymbolTable fillSymbolTable() throws FileNotFoundException, IOException {

        // primeira passada pelo código deve buscar os labels
        // LOOP:
        // END:
        Parser parser = new Parser(inputFile);
        int romAddress = 0;
        while (parser.advance()){
            if (parser.commandType(parser.command()).equals( Parser.CommandType.L_COMMAND) ){
                String label = parser.label(parser.command());

                if (!(table.contains(label))){
                    table.addEntry(label,romAddress);
                    if (verbose){
                        System.out.println("adicionando label" + label + "`a tabela");
                    }
                }

                // deve verificar se tal label já existe na tabela,
                // se não, deve inserir. Caso contrário, ignorar.
            }else {
                romAddress++;
            }


        }
        parser.close();

        // a segunda passada pelo código deve buscar pelas variáveis
        // leaw $var1, %A
        // leaw $X, %A
        // para cada nova variável deve ser alocado um endereço,
        // começando no RAM[15] e seguindo em diante.
        parser = new Parser(inputFile);
        int ramAddress = 15;
        while (parser.advance()){
            if (parser.commandType(parser.command()) == Parser.CommandType.A_COMMAND) {
                String symbol = parser.symbol(parser.command());
                if (Character.isDigit(symbol.charAt(0))){
                    /* TODO: implementar */
                    if (!(table.contains(symbol))){
                        table.addEntry(symbol, ramAddress);
                        if (verbose){
                            System.out.println("adicionando simbolo"+symbol+" `a tabela");
                        }
                    }

                    // deve verificar se tal símbolo já existe na tabela,
                    // se não, deve inserir associando um endereço de
                    // memória RAM a ele.
                }
                ramAddress++;

            }
        }
        parser.close();
        return table;
    }
    /**
     * Segundo passo para a geração do código de máquina
     * Varre o código em busca de instruções do tipo A, C
     * gerando a linguagem de máquina a partir do parse das instruções.
     *
     * Dependencias : Parser, Code
     */
    public void generateMachineCode() throws FileNotFoundException, IOException{
        Parser parser = new Parser(inputFile);  // abre o arquivo e aponta para o começo
        String instruction  = "";
        String command;
        String symbol;
        String primeirosBits;
        /**
         * Aqui devemos varrer o código nasm linha a linha
         * e gerar a string 'instruction' para cada linha
         * de instrução válida do nasm
         * seguindo o instruction set
         */
        while (parser.advance()){
            command = parser.command();
            switch (parser.commandType(parser.command())){
                /* TODO: implementar */

                case C_COMMAND:
                    String[] mne=parser.instruction(parser.command());

                    primeirosBits = "10";
                    instruction= primeirosBits+ Code.comp(mne) +Code.dest(mne) + Code.jump(mne);
                    if (verbose){
                        System.out.println("convertendo " + command +" em "+ instruction);
                    }

                    break;
                case A_COMMAND:

                    primeirosBits = "00";
                    boolean numeric = true;
                    String simbolo = parser.symbol(parser.command());

                    try {
                        Double num = Double.parseDouble(simbolo);
                    } catch (NumberFormatException e) {
                        numeric = false;
                    }
                    if(numeric) {
                        instruction = primeirosBits + Code.toBinary(parser.symbol(parser.command()));
                    } else {
                        instruction = primeirosBits +  Code.toBinary((table.getAddress(simbolo).toString()));

                    }
                    if (verbose){
                        System.out.println("convertendo " + command +" em "+ instruction);
                    }


                    break;
                default:
                    continue;
            }
            // Escreve no arquivo .hack a instrução
            //if(outHACK!=null) {
            outHACK.println(instruction);
            //}
            instruction = null;
            command = null;
            symbol = null;
        }
    }

    /**
     * Fecha arquivo de escrita
     */
    public void close() throws IOException {
        if(outHACK!=null) {
            outHACK.close();
        }
    }

    /**
     * Remove o arquivo de .hack se algum erro for encontrado
     */
    public void delete() {
        try{
            if(hackFile!=null) {
                hackFile.delete();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}