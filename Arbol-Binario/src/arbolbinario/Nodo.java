package arbolbinario;

public class Nodo {

    public static final int m = 2; //para los hijos del nodo
    private Nodo hijoIzq;
    private int elem;
    private Nodo hijoDer;

    public Nodo(int ele) {
        hijoIzq = null;
        elem = ele;
        hijoDer = null;
    }

    public void setHI(Nodo izq) {
        hijoIzq = izq;
    }

    public void setElem(int e) {
        elem = e;
    }

    public void setHD(Nodo der) {
        hijoDer = der;
    }

    public Nodo getHI() {
        return hijoIzq;
    }

    public int getElem() {
        return elem;
    }

    public Nodo getHD() {
        return hijoDer;
    }

    public Nodo gethijo(int i) {
        if (i < 1 || i > m) {
            System.err.println("nodo.gethijo" + i + "no se encuentra el hijo usar 1 o 2");
            return null;
        }
        return (i == 1 ? hijoIzq : hijoDer);
    }

    public void sethijo(Nodo p, int x) {
        if (x < 1 || x > m) {
            System.err.println("nodo.sethijo" + x + " no se encuentra el hijo");
            return;
        }
        if (x == 1) {
            hijoIzq = p;
        } else {
            hijoDer = p;
        }
    }

    public int canthijos() {
        int c = 0;
        for (int i = 1; i <= m; i++) {
            if (gethijo(i) != null) {
                c++;
            }
        }
        return c;
    }
}
