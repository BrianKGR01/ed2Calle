
import java.util.*;
import javax.swing.JTextArea;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author TAKESHI
 */
public class ArbolM { //alt+shift+f

    private Nodo raiz;
    private int cn;

    public ArbolM() {
        raiz = null;
    }

    private boolean esHoja(Nodo P) {
        for (int i = 1; i <= P.M; i++) {
            if (P.getHijo(i) != null) {
                return false;
            }
        }
        return true;
    }

    private int getHijoDesc(Nodo P, int x) {
        int i = 1;
        while (i < P.M) {
            if (x < P.getElem(i)) {
                return i;
            }
            if (x == P.getElem(i)) {
                return -1;
            }
            i++;
        }
        return P.M;
    }

    private void Recorrer(Nodo P, int i) {
        int num1 = P.getElem(i);
        int num2 = 0;
        int c = P.CantOcupados();
        while (i <= c) {
            if (P.Ocupado(i + 1)) {
                num2 = P.getElem(i + 1);
            }
            P.setElem(num1, i + 1);
            num1 = num2;
            i++;
        }

    }

    private void InsertaOrd(Nodo P, int x) {
        for (int i = 1; i < P.M; i++) {
            if (P.Vacio(i)) {
                P.setElem(x, i);
                return;
            } else if (x == P.getElem(i)) {
                return;
            } else if (x < P.getElem(i)) {
                Recorrer(P, i);
                P.setElem(x, i);
                return;
            }
        }
    }

    public void Insertar(int x) {
        if (raiz == null) {
            raiz = new Nodo();
            raiz.setElem(x, 1);
        } else {
            Nodo P = raiz;
            Nodo AP = null;
            int i = 0;
            while (P != null) {
                AP = P;
                if (!P.Lleno()) {
                    InsertaOrd(P, x);
                    return;
                }
                i = getHijoDesc(P, x);
                if (i == -1) {
                    return; // x esta en el árbol
                }
                P = P.getHijo(i);
            }
            Nodo Q = new Nodo();
            Q.setElem(x, 1);
            AP.setHijo(Q, i);
        }
    }

    private void InOrden(Nodo P, JTextArea jta1) {
        if (P == null) {
            return;
        } else if (esHoja(P)) {
            int i = 1; //muestra todos los elementos de nodo P
            while (i <= P.CantOcupados()) {
                jta1.append(String.valueOf(P.getElem(i) + " "));
                i++;
            }
        } else {
            for (int i = 1; i <= P.M - 1; i++) {
                InOrden(P.getHijo(i), jta1);//imprime todos sus hijos(izquierdo)
                jta1.append(String.valueOf(P.getElem(i) + " ")); // imprime padre
            }
            InOrden(P.getHijo(P.M), jta1);// imprime derecho o el ultimp enlace de M(osea el 3er flecha)
        }

    }

    public void InOrden(JTextArea jta1) {
        InOrden(raiz, jta1);
    }

    //---------------------------------------------------
    private void PreOrden(Nodo P, JTextArea jta1) {
        if (P == null) {
            return;
        } else if (esHoja(P)) {
            int i = 1; //muestra todos los elementos de nodo P
            while (i <= P.CantOcupados()) {
                jta1.append(String.valueOf(P.getElem(i) + " "));
                i++;
            }
        } else {
            for (int i = 1; i <= P.M - 1; i++) {
                jta1.append(String.valueOf(P.getElem(i) + " ")); //imprime primero padre
                PreOrden(P.getHijo(i), jta1); //izquierda(todos sus hijos)

            }
            PreOrden(P.getHijo(P.M), jta1);//derecho(ultimo elemento que falta hijo de la ultima posicion)
        }

    }

    public void PreOrden(JTextArea jta1) {
        PreOrden(raiz, jta1);
    }

    private void PostOden(Nodo P, JTextArea jta1) {
        if (P == null) {
            return;
        } else if (esHoja(P)) {
            int i = 1; //muestra todos los elementos de nodo P
            while (i <= P.CantOcupados()) {
                jta1.append(String.valueOf(P.getElem(i) + " "));
                i++;
            }
        } else {

            for (int i = 1; i <= P.M - 1; i++) {

                PostOden(P.getHijo(i), jta1); //izquierda
                PostOden(P.getHijo(P.M), jta1);//derecho
                //imprime primero padre
                jta1.append(String.valueOf(P.getElem(i)));
            }
            jta1.append(String.valueOf(P.getElem(1) + " ")); //imprime primero padre
            //   int i = 0;

            //    jta1.append(String.valueOf(P.getElem(i) + " ")); //imprime primero padre
        }

    }

    public void PostOden(JTextArea jta1) {
        PostOden(raiz, jta1);
    }

    //--------------------------------------------------
    // private void PreOrden()

    private int CantidadNodos(Nodo p) {
        if (p == null) {
            return 0;
        } else if (esHoja(p)) {
            return 1;
        } else {
            int cn = 0;
            for (int i = 1; i <= p.M; i++) {
                cn = cn + CantidadNodos(p.getHijo(i));
            }
            return cn + 1;
        }

    }

    public int CantidadNodos() {
        return CantidadNodos(raiz);
    }

    private int CantidadElementos(Nodo p) {
        if (p == null) {
            return 0;
        }
        if (esHoja(p)) {
            return p.CantOcupados();
        } else {
            int c = 0;
            for (int i = 1; i <= p.M; i++) {
                c = c + CantidadElementos(p.getHijo(i));
            }
            return c + p.CantOcupados();
        }
    }

    public int CantidadElementos() {
        return CantidadElementos(raiz);
    }

    private int altura(Nodo p) {
        if (p == null) {
            return 0;
        } else if (esHoja(p)) {
            return 1;
        } else {
            int m = 0;
            for (int i = 1; i <= p.M; i++) {
                int a = altura(p.getHijo(i));
                if (a > m) {
                    m = a;
                }
            }
            return m + 1;
        }
    }

    public int altura() {
        return altura(raiz);
    }

   // PRACTICAAAAA ARBOLES MVIAS
    
    private int sumaxnivel(Nodo actual, int n) {
         int x=0;int y=0;
        if (actual == null) {
            return 0;
        } else {
            if (n == 1) {
              int suma=0;
        for(int i=1;i<=actual.M-1;i++){
            suma=suma+sumaxnivel(actual.getHijo(i),n-1);
            if(actual!=null){
                suma=suma+ actual.getElem(i);
            }
        }
        suma=suma+sumaxnivel(actual.getHijo(actual.M),n-1);
    return suma;
        }
    }
        return 0;
 }
    public int sumaxnivel(int n){
        return sumaxnivel(raiz, n);
    }
    private int sumaT(Nodo actual){
            if(actual==null){
            return 0;
        }
        int suma=0;
        for(int i=1;i<=actual.M-1;i++){
            suma=suma+sumaT(actual.getHijo(i));
            if(actual!=null){
                suma=suma+ actual.getElem(i);
            }
        }
        suma=suma+sumaT(actual.getHijo(actual.M));
    return suma;
    }
    public int suma(){
        return sumaT(raiz);
    }
    
    private void mostrarNivel(Nodo p, JTextArea jta, int n) {
        if (p == null) {
            return;
        } else {
            if (n == 1) {
                for (int i = 1; i <= p.CantOcupados(); i++) {
                    jta.append(String.valueOf(p.getElem(i)) + " ");
                }
            } else {
                for (int i = 1; i <=p.M; i++) {
                    mostrarNivel(p.getHijo(i), jta, n - 1);
                }
            }
        }
    }

    public void mostrarNivel(int n, JTextArea jta) {
        mostrarNivel(raiz, jta, n);
    }

    private void mostrarNivelUltimo(Nodo p, JTextArea jta, int n) {
        if (p == null) {
            return;
        } else {
            if (n == 1) {
                for (int i = 1; i <= p.CantOcupados(); i++) {
                    jta.append(String.valueOf(p.getElem(i)) + " ");
                }
            } else {
                for (int i = 1; i <= p.M; i++) {
                    mostrarNivel(p.getHijo(i), jta, n - 1);
                }
            }
        }
    }

    public void mostrarNivelultimo(JTextArea jta) {
        mostrarNivel(raiz, jta, altura(raiz));
    }



    private void DesdeNivel(Nodo p, int altura, int n, JTextArea jta) {
        while (n <= altura) {
            mostrarNivel(n, jta);
            n++;
        }
    }

    public void DesdNivel(JTextArea jta, int n) {
        DesdeNivel(raiz, altura(raiz), n, jta);
    }

    private void MostrarDesdeN(Nodo p, JTextArea jta, int n, int altura) {
        if (p != null) {
            while (n >= 1) {
                this.mostrarNivel(n, jta);
                n--;
            }
        }
    }

    public void MostrarDesdeN(JTextArea jta, int n) {
        MostrarDesdeN(raiz, jta, n, this.altura());
    }

    private boolean igualesR(Nodo T, Nodo P) {

        if (T == null && P == null) {
            return true;
        }
        if (T != null && P == null || T == null && P != null) {
            return false;
        }
        if (esHoja(T) && esHoja(P)) {
            if (T.iguales(P)) {
                return true;
            }
        } else {
            boolean b = false;
            for (int i = 1; i <= T.M; i++) {
                b = igualesR(T.getHijo(i), P.getHijo(i));
                if (!b) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean esSubArbol(Nodo a, Nodo b) {
        if (a == null && b == null) {
            return true;
        }
        if (a != null && b != null) {

            if (a.iguales(b)) {
                return igualesR(a, b);
            } else {
                boolean vand = false;
                for (int i = 1; i <= b.M; i++) {
                    vand = esSubArbol(a, b.getHijo(i));
                    if (vand) {
                        return vand;
                    }
                }
                return vand;
            }
        } else {
            return false;
        }
    }

    public boolean esSubArbol(ArbolM B) {
        return esSubArbol(this.raiz, B.raiz);
    }
    public LinkedList<Integer> listanivel(int x){
        LinkedList<Integer> lista=new LinkedList<>();
        listanivel(raiz,lista,x);
        return lista;
    }
    private void listanivel(Nodo actual,LinkedList<Integer> lista,int x){
        if(actual!=null){
            if(x==1){
                for (int i = 1; i <= actual.CantOcupados(); i++) {
                    lista.add(actual.getElem(i));
                }
            }else{
                for (int i = 1; i <=actual.M; i++) {
                    listanivel(actual.getHijo(i), lista, x-1);
                }
                //listanivel(actual.getHijo(actual.M), lista, x-1);
            }
        }
    }
    public void Sumadesdenivel(int x,JTextArea jta){
        int c=listanivel(x).size();
        while(c!=0){
            int suma=0;
            for (int i = 0; i < listanivel(x).size(); i++) {
                suma=suma+listanivel(x).get(i);
            }
            jta.append("nivel "+String.valueOf(x)+": "+String.valueOf(suma)+"\n" );
            x++;
            c=listanivel(x).size();
        }
    }
}
