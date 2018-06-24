/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinal.pilha;

/**
 *
 * @author Win7U
 * @param <T>
 */
public class PilhaLista<T> implements Pilha<T>{
    private Lista lista;

    public PilhaLista() {
        lista = new Lista();
    }       
    
    @Override
    public void push(T info) {
        lista.inserir(info);
    }

    @Override
    public T pop() {
        T p = (T) lista.getPrimeiro().getInfo();
        lista.retirar(lista.getPrimeiro().getInfo());
        
        return p;
    }

    @Override
    public T peek() {
        return (T) lista.getPrimeiro().getInfo();
    }

    @Override
    public boolean estaVazia() {
        return lista.estaVazia();
    }

    @Override
    public void liberar() {
        lista = new Lista();        
    }
    
}
