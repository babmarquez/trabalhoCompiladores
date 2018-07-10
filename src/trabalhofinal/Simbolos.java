/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinal;

import java.util.Objects;

/**
 *
 * @author Ana
 */
public class Simbolos {
    private String identificador;
    private char classe; //constante (C) ou variável (V)
    private TipoExpressao tipo;
    private String valor;

    /**
     * @return the identificador
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * @param identificador the identificador to set
     */
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    /**
     * @return the classe
     */
    public char getClasse() {
        return classe;
    }

    /**
     * @param classe the classe to set
     */
    public void setClasse(char classe) {
        this.classe = classe;
    }

    /**
     * @return the tipo
     */
    public TipoExpressao getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(TipoExpressao tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    public Simbolos(String identificador, char classe, TipoExpressao tipo, String valor) {
        this.identificador = identificador;
        this.classe = classe;
        this.tipo = tipo;
        this.valor = valor;
    }

    public Simbolos() {
    }     

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Simbolos other = (Simbolos) obj;
        if (!Objects.equals(this.identificador, other.identificador)) {
            return false;
        }
        return true;
    }
    
}
