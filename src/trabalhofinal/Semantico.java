package trabalhofinal;

import trabalhofinal.pilha.PilhaLista;

public class Semantico implements Constants
{
    PilhaLista<TipoExpressao> pilha = new PilhaLista();
    
    public void executeAction(int action, Token token)	throws SemanticError
    {
        System.out.println("Ação #"+action+", Token: "+token);
        
        String result;
        
        switch (action){
            case 1: acao01();
                break;
            case 2: acao02();
                break;
            case 3: acao03();
                break;
            case 4: acao04();
                break;
            case 5: result = acao05(token);
                break;
            case 6: result = acao06(token);
                break;
            case 7: result = acao07(token);
                break;
            case 8: result = acao08(token);
                break;
            case 9: result = acao09(token);
                break;
            case 10: result = acao10(token);
                break;
            case 11: result = acao11(token);
                break;
            case 12: result = acao12(token);
                break;
            case 13: result = acao13(token);
                break;
            case 14: result = acao14(token);
                break;
            case 15: result = acao15(token);
                break;
            case 16: result = acao16(token);
                break;
                
            default: result = "";
                break;
        }        
    }	
    
    private void acao01(){
        TipoExpressao t1 = pilha.pop();
        TipoExpressao t2 = pilha.pop();
        if ((t1 == TipoExpressao.FLOAT64) || (t2 == TipoExpressao.FLOAT64))
            pilha.push(TipoExpressao.FLOAT64);
        else
            pilha.push(TipoExpressao.INT64);        
    }
    
    private void acao02(){
        pilha.pop();
        pilha.pop();
        pilha.push(TipoExpressao.FLOAT64);
    }
    
    private void acao03(){
        pilha.push(TipoExpressao.INT64);
    }
    
    private void acao04(){
        pilha.push(TipoExpressao.FLOAT64); 
    }
    
    private String acao05(Token token){
        return "";
    }
    
    private String acao06(Token token){
        return "";
    }
    
    private String acao07(Token token){
        return "";
    }
    
    private String acao08(Token token){
        return "";
    }
    
    private String acao09(Token token){
        return "";
    }
    
    private String acao10(Token token){
        return "";
    }
    
    private String acao11(Token token){
        return "";
    }
    
    private String acao12(Token token){
        return "";
    }
    
    private String acao13(Token token){
        return "";
    }
    
    private String acao14(Token token){
        return "";
    }
    
    private String acao15(Token token){
        return "";
    }
    
    private String acao16(Token token){
        return "";
    }
}
