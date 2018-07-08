package trabalhofinal;

import trabalhofinal.pilha.PilhaLista;

public class Semantico implements Constants
{
    PilhaLista<TipoExpressao> pilha = new PilhaLista();
    String codigo = "";
    String operador = "";
    
    String ultimo = "";
    
    public void executeAction(int action, Token token)	throws SemanticError
    {
        System.out.println("Ação #"+action+", Token: "+token);
       
        switch (action){
            case 1: acao01();
                break;
            case 2: acao02();
                break;
            case 3: acao03();
                break;
            case 4: acao04();
                break;
            case 5: acao05(token);
                break;
            case 6: acao06(token);
                break;
            case 7: acao07();
                break;
            case 8: acao08();
                break;
            case 9: acao09(token);
                break;
            case 10: acao10();
                break;
            case 11: acao11();
                break;
            case 12: acao12();
                break;
            case 13: acao13();
                break;
            case 14: acao14(token);
                break;
            case 15: acao15();
                break;
            case 16: acao16();
                break; 
            case 17: acao17(token);
                break;
            case 18: acao18();
                break;    
            case 19: acao19();
                break;    
            case 20: acao20(token);
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
        codigo += "\nadd";
    }
    
    private void acao02(){
        TipoExpressao t1 = pilha.pop();
        TipoExpressao t2 = pilha.pop();
        if ((t1 == TipoExpressao.FLOAT64) || (t2 == TipoExpressao.FLOAT64))
            pilha.push(TipoExpressao.FLOAT64);
        else
            pilha.push(TipoExpressao.INT64);
        codigo += "\nsub";
    }
    
    private void acao03(){
        TipoExpressao t1 = pilha.pop();
        TipoExpressao t2 = pilha.pop();
        if ((t1 == TipoExpressao.FLOAT64) || (t2 == TipoExpressao.FLOAT64))
            pilha.push(TipoExpressao.FLOAT64);
        else
            pilha.push(TipoExpressao.INT64);
        codigo += "\nmul";
    }
    
    private void acao04() throws SemanticError{
        TipoExpressao t1 = pilha.pop();
        TipoExpressao t2 = pilha.pop();
        if (t1.equals(t2))
            pilha.push(t1);
        else
            throw new SemanticError("Erro semantico");
        
        codigo += "\ndiv";
    }
    
    private void acao05(Token token){
        pilha.push(TipoExpressao.INT64);
        codigo += "\nldc.i8 "+token.getLexeme();
        codigo += "\nconv.r8";
    }
    
    private void acao06(Token token){
        pilha.push(TipoExpressao.FLOAT64);
        codigo += "\nldc.r8 "+token.getLexeme().replace(",", ".");
    }
    
    private void acao07() throws SemanticError{
        TipoExpressao t1 = pilha.pop();
        if (t1.equals(TipoExpressao.INT64) || t1.equals(TipoExpressao.FLOAT64))
            pilha.push(t1);
        else
            throw new SemanticError("Erro semantico");
    }
    
    private void acao08() throws SemanticError{
        TipoExpressao t1 = pilha.pop();
        if (t1.equals(TipoExpressao.INT64) || t1.equals(TipoExpressao.FLOAT64))
            pilha.push(t1);
        else
            throw new SemanticError("Erro semantico");
        
        codigo += "\nldc.i8 -1";
        if (t1.equals(TipoExpressao.INT64))
            codigo += "\nconv.r8";
        codigo += "\nmul";
    }
    
    private void acao09(Token token){
        operador = token.getLexeme();
    }
    
    private void acao10() throws SemanticError{
        TipoExpressao t1 = pilha.pop();
        TipoExpressao t2 = pilha.pop();
        if (t1.equals(t2))
            pilha.push(TipoExpressao.BOOL);
        else
            throw new SemanticError("Erro semantico");
        
        switch (operador){
            case ">": codigo += "\ncgt"; break;
            case "<": codigo += "\nclt"; break;
            case "=": codigo += "\nceq"; break;
            case "<=": 
                codigo += "\ncgt"; 
                codigo += "\nldc.i4 0";
                codigo += "\nceq";
                break;
            case ">=": 
                codigo += "\nclt"; 
                codigo += "\nldc.i4 0";
                codigo += "\nceq";
                break;    
            case "!=": 
                codigo += "\nceq"; 
                codigo += "\nldc.i4 0";
                codigo += "\nceq";
                break;     
        }
        
    }
    
    private void acao11(){
        pilha.push(TipoExpressao.BOOL);
        codigo += "\nldc.i4.1";
    }
    
    private void acao12(){
        pilha.push(TipoExpressao.BOOL);
        codigo += "\nldc.i4.0";
    }
    
    private void acao13() throws SemanticError{
        TipoExpressao t1 = pilha.pop();
        if (t1.equals(TipoExpressao.BOOL))
            pilha.push(TipoExpressao.BOOL);
        else
            throw new SemanticError("Erro semantico");
        
        codigo += "\nldc.i4.1";
        codigo += "\nxor";
    }
    
    private void acao14(Token token){
        TipoExpressao t1 = pilha.pop();
        if (t1.equals(TipoExpressao.INT64))
            codigo += "\nconv.i8";
        
        ultimo = t1.name().toLowerCase();
        codigo += "\ncall void [mscorlib]System.Console::Write("+t1.name().toLowerCase()+")";
    }
    
    private void acao15(){
        codigo += ".assembly extern mscorlib {}";
        codigo += "\n.assembly _codigo_objeto{}";
        codigo += "\n.module _codigo_objeto.exe";
        codigo += "\n\n.class public _UNICA{";
        codigo += "\n.method static public void _principal() { ";
        codigo += "\n  ntrypoint";
    }
    
    private void acao16(){
        codigo += "\n ret";
        codigo += "\n }";
        codigo += "\n}";
    }
    
    private void acao17(Token token){
        //TipoExpressao t1 = pilha.pop();
        codigo += "\nldstr \"\\n\"";
        codigo += "\ncall void [mscorlib]System.Console::Write("+ultimo+")";        
    }
    
    private void acao18(){
        /*TipoExpressao t1 = pilha.pop();
        TipoExpressao t2 = pilha.pop();
        if ((t1 == TipoExpressao.BOOL) && (t2 == TipoExpressao.BOOL)){
            
        }*/
        codigo += "\nAção para && AQUI";
    }
    
    private void acao19(){
        /*TipoExpressao t1 = pilha.pop();
        TipoExpressao t2 = pilha.pop();
        if ((t1 == TipoExpressao.BOOL) && (t2 == TipoExpressao.BOOL)){
            
        }*/
        codigo += "\nAção para || AQUI";
    }
    
    private void acao20(Token token){        
        pilha.push(TipoExpressao.STRING);
        codigo += "\nldstr "+token.getLexeme().replace(",", ".");
    }
}
