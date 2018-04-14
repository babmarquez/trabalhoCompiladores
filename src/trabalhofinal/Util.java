/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinal;

/**
 *
 * @author Ana
 */

public class Util {
    private String[] texto;
    private int linhaAtu;
    private int countPosition;
    
    public void setTexto(String texto){
        this.texto = texto.split("\n");
        linhaAtu = 1;
        countPosition = this.texto[0].length();
    }
    
    public int getLinhaAtu(int position){
        while(countPosition < position){
            countPosition+= texto[linhaAtu].length();
            linhaAtu++;
        } 
        
        return linhaAtu;
    }
    
    public boolean isSimboloInvalido(int lastState){        
        return ((lastState == 0) || (lastState == 4) || (lastState == 18));
    }
    
    
    //Retorna as classes de acordo com as constantes de id
    public String getClasse(int id){
        if ((id >= 6) && (id <= 19)){
            return "símbolo especial";
        }else if (id > 19){
            return "palavra reservada";
        }else if (id == 2){
            return "identificador";
        }else if (id == 3){
            return "constante inteira";
        }else if (id == 4){
            return "constante real";
        }else if (id == 5){
            return "constante caractere";
        }        
                
        return "";
    }
    
    //Adiciona espaços à direita para identar o retorna da compilação
    public static String preencheDireita(String valor, int tamanho, char c) {
	String result = "";
	while ((valor.length() + result.length()) < tamanho) {
            result = c + result;
	}
	return valor + result;
    }
}
