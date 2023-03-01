/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbinario;

public class arbolAVL {

    private Nodo raiz;

    public arbolAVL() {
        this.raiz = null;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    private boolean eshoja(Nodo p) {
        return p.getHI() == null && p.getHD() == null;
    }

    //----------------------INSERTAR
    public void insertar(int x) {
        Nodo p = new Nodo(x);
        if (raiz == null) {
            raiz = p;
        } else {
            Nodo aux = raiz;
            Nodo q = null;
            while (aux != null) {
                q = aux;
                if (x > aux.getElem()) {
                    aux = aux.getHD();
                } else {
                    aux = aux.getHI();
                    return;
                }
            }
            if (x > q.getElem()) {
                p.setHD(q);
            } else {
                p.setHI(q);
            }
        }
    }
    //----------------------FACTOR DE EQUILIBRIO

    private int factorEquilibrio(Nodo pr) {
        return (altura(pr.getHI()) - altura(pr.getHD()));
    }

    public int factorEquilibrio(int x) {
        Nodo p = this.raiz;
        while (p != null && p.getElem() != x) {//buscar el nodo
            if (x > p.getElem()) {
                p = p.getHD();
            } else {
                p = p.getHI();
            }
        }
        return factorEquilibrio(p);
    }

    //---------------------------------ALTURA DEL ARBOL
    private int mayor(int a, int b) {
        return (a > b) ? a : b;
    }

    private int altura(Nodo pr) {
        return (pr == null) ? 0
                : 1 + mayor(altura(pr.getHI()), altura(pr.getHD()));
    }

    public int altura() {
        return altura(raiz);
    }
}
