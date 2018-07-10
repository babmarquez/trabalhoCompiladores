package trabalhofinal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import trabalhofinal.pilha.PilhaLista;

public class Semantico implements Constants
{
    PilhaLista<TipoExpressao> pilhaTipos = new PilhaLista();
    PilhaLista<Integer> pilhaRotulos = new PilhaLista();
    ArrayList<String> listaIdentificadores = new ArrayList<String>();
    Map<String, Simbolos> tabelaSimbolos = new HashMap<String, Simbolos>();
    String codigo = "";
    String operador = "";
    TipoExpressao tipoVar;
    
    String ultimo = "";
    int rotuloCount = 1;
    
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
            case 21: acao21(token);
                break;    
            case 22: acao22(token);
                break;   
            case 23: acao23(token);
                break;  
            case 24: acao24(token);
                break;
            case 25: acao25(token);
                break;
            case 26: acao26(token);
                break;    
            case 27: acao27();
                break;    
            case 28: acao28();
                break;     
            case 29: acao29();
                break;     
            case 30: acao30();
                break;    
            case 31: acao31();
                break;    
            case 32: acao32(token);
                break;    
                
        }        
    }	
    
    private void acao01() throws SemanticError{
        TipoExpressao t1 = pilhaTipos.pop();
        TipoExpressao t2 = pilhaTipos.pop();
        if ((t1 != TipoExpressao.FLOAT64) && (t1 != TipoExpressao.INT64) &&
            (t2 != TipoExpressao.FLOAT64) && (t2 != TipoExpressao.INT64))
            throw new SemanticError("tipos incompatíveis em operação aritmética binária");
        else
        if ((t1 == TipoExpressao.FLOAT64) || (t2 == TipoExpressao.FLOAT64))
            pilhaTipos.push(TipoExpressao.FLOAT64);
        else
            pilhaTipos.push(TipoExpressao.INT64); 
       
        codigo += "\nadd";
    }
    
    private void acao02() throws SemanticError{
        TipoExpressao t1 = pilhaTipos.pop();
        TipoExpressao t2 = pilhaTipos.pop();
        if ((t1 != TipoExpressao.FLOAT64) && (t1 != TipoExpressao.INT64) &&
            (t2 != TipoExpressao.FLOAT64) && (t2 != TipoExpressao.INT64))
            throw new SemanticError("tipos incompatíveis em operação aritmética binária");
        else
        if ((t1 == TipoExpressao.FLOAT64) || (t2 == TipoExpressao.FLOAT64))
            pilhaTipos.push(TipoExpressao.FLOAT64);
        else
            pilhaTipos.push(TipoExpressao.INT64);
        codigo += "\nsub";
    }
    
    private void acao03() throws SemanticError{
        TipoExpressao t1 = pilhaTipos.pop();
        TipoExpressao t2 = pilhaTipos.pop();
        if ((t1 != TipoExpressao.FLOAT64) && (t1 != TipoExpressao.INT64) &&
            (t2 != TipoExpressao.FLOAT64) && (t2 != TipoExpressao.INT64))
            throw new SemanticError("tipos incompatíveis em operação aritmética binária");
        else
        if ((t1 == TipoExpressao.FLOAT64) || (t2 == TipoExpressao.FLOAT64))
            pilhaTipos.push(TipoExpressao.FLOAT64);
        else
            pilhaTipos.push(TipoExpressao.INT64);
        codigo += "\nmul";
    }
    
    private void acao04() throws SemanticError{
        TipoExpressao t1 = pilhaTipos.pop();
        TipoExpressao t2 = pilhaTipos.pop();
        if ((t1 != TipoExpressao.FLOAT64) && (t1 != TipoExpressao.INT64) &&
            (t2 != TipoExpressao.FLOAT64) && (t2 != TipoExpressao.INT64))
            throw new SemanticError("tipos incompatíveis em operação aritmética binária");
        else
        if (t1.equals(t2)){
            pilhaTipos.push(t1);        
            codigo += "\ndiv";
        }
    }
    
    private void acao05(Token token){
        pilhaTipos.push(TipoExpressao.INT64);
        codigo += "\nldc.i8 "+token.getLexeme();
        codigo += "\nconv.r8";
    }
    
    private void acao06(Token token){
        pilhaTipos.push(TipoExpressao.FLOAT64);
        codigo += "\nldc.r8 "+token.getLexeme().replace(",", ".");
    }
    
    private void acao07() throws SemanticError{
        TipoExpressao t1 = pilhaTipos.pop();
        if (t1.equals(TipoExpressao.INT64) || t1.equals(TipoExpressao.FLOAT64))
            pilhaTipos.push(t1);
        else
            throw new SemanticError("tipo incompatível em operação aritmética unária");
    }
    
    private void acao08() throws SemanticError{
        TipoExpressao t1 = pilhaTipos.pop();
        if (t1.equals(TipoExpressao.INT64) || t1.equals(TipoExpressao.FLOAT64)){
            pilhaTipos.push(t1);
            
            codigo += "\nldc.i8 -1";
            if (t1.equals(TipoExpressao.INT64))
                codigo += "\nconv.r8";
            codigo += "\nmul";
        }else
            throw new SemanticError("tipo incompatível em operação aritmética unária");
        
    }
    
    private void acao09(Token token){
        operador = token.getLexeme();
    }
    
    private void acao10() throws SemanticError{
        TipoExpressao t1 = pilhaTipos.pop();
        TipoExpressao t2 = pilhaTipos.pop();
        if ((t1.equals(t2)) || (t1.equals(TipoExpressao.FLOAT64) && t2.equals(TipoExpressao.INT64)) ||
            (t1.equals(TipoExpressao.INT64) && t2.equals(TipoExpressao.FLOAT64)))
            pilhaTipos.push(TipoExpressao.BOOL);
        else
            throw new SemanticError("tipos incompatíveis em expressão relacional");
        
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
        pilhaTipos.push(TipoExpressao.BOOL);
        codigo += "\nldc.i4.1";
    }
    
    private void acao12(){
        pilhaTipos.push(TipoExpressao.BOOL);
        codigo += "\nldc.i4.0";
    }
    
    private void acao13() throws SemanticError{
        TipoExpressao t1 = pilhaTipos.pop();
        if (t1.equals(TipoExpressao.BOOL))
            pilhaTipos.push(TipoExpressao.BOOL);
        else
            throw new SemanticError("tipo incompatível em operação lógica unária");
        
        codigo += "\nldc.i4.1";
        codigo += "\nxor";
    }
    
    private void acao14(Token token){
        TipoExpressao t1 = pilhaTipos.pop();
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
        codigo += "\n  .entrypoint";
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
    
    private void acao18() throws SemanticError{
        TipoExpressao t1 = pilhaTipos.pop();
        TipoExpressao t2 = pilhaTipos.pop();
        if ((t1 == TipoExpressao.BOOL) && (t2 == TipoExpressao.BOOL)){
            
        }else{
            throw new SemanticError("tipos incompatíveis em operação lógica binária");
        }
        
        codigo += "\nAção para && AQUI";
    }
    
    private void acao19() throws SemanticError{
        TipoExpressao t1 = pilhaTipos.pop();
        TipoExpressao t2 = pilhaTipos.pop();
        if ((t1 == TipoExpressao.BOOL) && (t2 == TipoExpressao.BOOL)){
            
        }else{
            throw new SemanticError("tipos incompatíveis em operação lógica binária");
        }
        
        codigo += "\nAção para || AQUI";
    }
    
    private void acao20(Token token){        
        pilhaTipos.push(TipoExpressao.STRING);
        codigo += "\nldstr "+token.getLexeme().replace(",", ".");
    }
    
    private void acao21(Token token){                        
        if (token.getLexeme().toUpperCase().equals("INT")){
            tipoVar = TipoExpressao.INT64;
        }else if (token.getLexeme().toUpperCase().equals("FLOAT")){
            tipoVar = TipoExpressao.FLOAT64;
        }else if (token.getLexeme().toUpperCase().equals("BOOL")){
            tipoVar = TipoExpressao.BOOL;
        }else if (token.getLexeme().toUpperCase().equals("STRING")){
            tipoVar = TipoExpressao.STRING;
        };
    }
    
    private void acao22(Token token) throws SemanticError{      
        if (listaIdentificadores.indexOf(token.getLexeme()) > 0)
            throw new SemanticError(token.getLexeme()+" já declarado");
        else
            listaIdentificadores.add(token.getLexeme());
    }    
    
    private void acao23(Token token) throws SemanticError{      
        for (String id : listaIdentificadores){
            if (tabelaSimbolos.containsKey(id))
                throw new SemanticError(token.getLexeme()+" já declarado");
            
            Simbolos s1 = new Simbolos();
            s1.setClasse('V');
            s1.setIdentificador(id);
            s1.setTipo(tipoVar);
            
            tabelaSimbolos.put(id, s1);
            
            codigo+= "\n.locals("+tipoVar.name().toLowerCase()+" "+id+")";
        }            
        listaIdentificadores.clear();
    }
    
    private void acao24(Token token) throws SemanticError{      
        for (String id : listaIdentificadores){
            if (tabelaSimbolos.containsKey(id)){
                Simbolos s1 = tabelaSimbolos.get(id);
                String classe;
                
                switch (s1.getTipo()){
                    case INT64  : classe = "Int64";
                        break;
                    case FLOAT64: classe = "Double";
                        break;    
                    case STRING : classe = "String";
                        break;
                    case BOOL   : classe = "Bool";
                        break;
                    default: 
                        throw new SemanticError("tipo de dado não especificado");
                }
                
                codigo+= "\ncall string [mscorlib]System.Console::ReadLine()";
                codigo+= "\ncall "+s1.getTipo().name().toLowerCase()+" [mscorlib]System."+classe+"::Parse(string)";
                codigo+= "\nstloc "+id;
            }else
                throw new SemanticError(token.getLexeme()+" não declarado");
                        
        }            
        listaIdentificadores.clear();
    }
    
    private void acao25(Token token) throws SemanticError{  
        if (tabelaSimbolos.containsKey(token.getLexeme())){
            Simbolos s1 = tabelaSimbolos.get(token.getLexeme());        
        
            pilhaTipos.push(s1.getTipo());
            if (s1.getClasse() == 'V')
                codigo += "\nldloc "+token.getLexeme();
            else
                codigo += "\nldloc "+s1.getValor();
            
            if (s1.getTipo() == TipoExpressao.INT64)
                codigo += "\nconv.r8";
        }else{
            throw new SemanticError(token.getLexeme()+" não declarado");
        }
    }
    
    private void acao26(Token token) throws SemanticError{  
        String id = listaIdentificadores.get(listaIdentificadores.size()-1);
        if (tabelaSimbolos.containsKey(id)){
            Simbolos s1 = tabelaSimbolos.get(id);                
            TipoExpressao t1 = pilhaTipos.pop();
            
            if (s1.getTipo().equals(t1)){
                if (t1.equals(TipoExpressao.INT64))
                    codigo += "\nconv.i8";
                codigo += "\nstloc "+id;
            }else
                throw new SemanticError("tipo incompatível em comando de atribuição");
        }else{
            throw new SemanticError(token.getLexeme()+" não declarado");
        }
    }
    
    private void acao27(){  
        pilhaRotulos.push(rotuloCount);
        codigo += "\nlabel"+rotuloCount+":";
        rotuloCount++;
    }
    
    private void acao28() {  
        pilhaRotulos.push(rotuloCount);
        codigo += "\nbrfalse label"+rotuloCount;
        rotuloCount++;
    }
    
    private void acao29() {  
        int rotulo = pilhaRotulos.pop();
        codigo += "\nlabel"+rotulo+":";
    }
    
    private void acao30(){
        int rotulo = pilhaRotulos.pop();
        codigo += "\nbr label"+rotuloCount;
        codigo += "\nlabel"+rotulo+":";
        pilhaRotulos.push(rotuloCount);
        rotuloCount++;
    }
    
    private void acao31(){
        int rotulo1 = pilhaRotulos.pop();
        int rotulo2 = pilhaRotulos.pop();
        
        codigo += "\nbr label"+rotulo2;
        codigo += "\nlabel"+rotulo1+":";
    }
    
    private void acao32(Token token) throws SemanticError{                
        String id = listaIdentificadores.get(listaIdentificadores.size());
        
        if (tabelaSimbolos.containsKey(id)){
            throw new SemanticError(token.getLexeme()+" já declarado");
        }
        
        Simbolos simbolo = new Simbolos();
        simbolo.setClasse('C');
        simbolo.setIdentificador(id);
        simbolo.setValor(token.getLexeme());
        
        switch (token.getId()){
            case 5: simbolo.setTipo(TipoExpressao.STRING);
                break;
            case 4: simbolo.setTipo(TipoExpressao.FLOAT64);
                break;
            case 3: simbolo.setTipo(TipoExpressao.INT64);
                break;
            default: simbolo.setTipo(TipoExpressao.BOOL);
                break;
        }            
        
        tabelaSimbolos.put(id, simbolo);
    }
}
