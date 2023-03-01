package ArbolB;

import javax.swing.JTextArea;

public class Arbol {

    private Nodo raiz;

    public Arbol() {
        raiz = null;
    }

    private boolean esHoja(Nodo p) {
        return (p.getHI() == null && p.getHD() == null);
    }

    public void insertar(int x) {
        Nodo q = new Nodo(x);
        if (raiz == null) {
            raiz = q;
            return;
        }
        Nodo p = raiz;
        Nodo ap = null;
        while (p != null) {
            ap = p;
            if (x < p.getElem()) {
                p = p.getHI();
            } else if (x > p.getElem()) {
                p = p.getHD();
            } else {
                return;
            }
        }
        if (x < ap.getElem()) {
            ap.setHI(q);
        } else {
            ap.setHD(q);
        }
    }

    private void preOrden(Nodo p, JTextArea jta) {
        if (p != null) {
            jta.append(String.valueOf(p.getElem() + "  "));
            preOrden(p.getHI(), jta);
            preOrden(p.getHD(), jta);
        }
    }

    public void preOrden(JTextArea jta) {
        preOrden(raiz, jta);
    }

    private void inOrden(Nodo p, JTextArea jta) {
        if (p != null) {
            inOrden(p.getHI(), jta);
            int x = p.getElem();
            jta.append(String.valueOf(x + "  "));
            inOrden(p.getHD(), jta);
        }
    }
   
    
    public void inOrden(JTextArea jta) {
        inOrden(raiz, jta);
    }
    
    public int altura() {
        return altura(this.raiz);
    }

    private int altura(Nodo p) {
        if (p == null) {
            return 0;
        }
        if (esHoja(p)) {
            return 0;
        }
        int ai = altura(p.getHI());
        int ad = altura(p.getHD());
        if (ad > ai) {
            return ad + 1;
        }
        return ai + 1;
    }

    private void RotacionIzq(Nodo p) {
        Nodo q = new Nodo(p.getElem());
        Nodo r = p.getHD();
        if (r.getHD() != null) {
            Nodo s = r.getHD();
            if (r.getHI() != null) {
                p.setElem(r.getElem());
                p.setHI(q);
                p.setHD(s);
                Nodo p_I = p.getHI();
                p_I.setHD(r.getHI());
            } else {//Rotacion a la Izquierda
                p.setElem(r.getElem());
                p.setHI(q);
                p.setHD(s);
            }
        } else {//Rotacion Izquierda-Derecha
            p.setElem(r.getHI().getElem());
            r.setHI(null);
            p.setHI(q);
        }
    }

    private void RotacionDer(Nodo p) {

        Nodo q = new Nodo(p.getElem());
        Nodo r = p.getHI();
        if (r.getHI() != null) {
            Nodo s = r.getHI();
            if (r.getHD() != null) {
                p.setElem(r.getElem());
                p.setHI(s);
                p.setHD(q);
                Nodo p_d = p.getHD();
                p_d.setHI(r.getHD());
            } else {//Rotacion a la Derecha
                p.setElem(r.getElem());
                p.setHI(s);
                p.setHD(q);
            }
        } else {//Rotacion Derecha-Izquierda
            p.setElem(r.getHD().getElem());
            r.setHD(null);
            p.setHD(q);
        }
    }

    public void rotacionDer() {
        RotacionDer(raiz);
    }

    public void rotacionIzq() {
        RotacionIzq(raiz);
    }

    private Nodo rotacionIzq(Nodo p) {
        Nodo q = p.getHD();
        p.setHD(q.getHI());
        q.setHI(p);
        return q;

    }

    private Nodo rotacionDer(Nodo p) {
        Nodo q = p.getHI();
        p.setHI(q.getHD());
        q.setHD(p);
        return q;
    }

    private Nodo rotacionIzqDer(Nodo p) {
        p.setHD(rotacionDer(p.getHD()));
        return rotacionIzq(p);

    }

    private Nodo rotacionDerIzq(Nodo p) {
        p.setHI(rotacionIzq(p.getHI()));
        return rotacionDer(p);

    }

    private Nodo balancear(Nodo p) {
        if (altura2(p.getHI()) - altura2(p.getHD()) == 2) {
            if (altura2(p.getHI().getHI()) - altura2(p.getHI().getHD()) == 1) {
                p = rotacionDer(p);
            } 
            else 
                p = rotacionDerIzq(p);            
        }
        if (altura2(p.getHD()) - altura2(p.getHI()) == 2) {
            if (altura2(p.getHD().getHD()) - altura2(p.getHD().getHI()) == 1) {
                p = rotacionIzq(p);
            } 
            else 
                p = rotacionIzqDer(p);            
        }
        return p;
    }

    private Nodo insertarAvl(Nodo p, int x) {
        if (raiz == null) {
            Nodo q = new Nodo(x);
            this.raiz = q;
            return raiz;
        }
        if (p == null) {
            Nodo q = new Nodo(x);
            return q;
        }
        if (x < p.getElem()) {
              p.setHI(insertarAvl(p.getHI(), x));
              p = balancear(p);
        } 
        else 
            
           if (x > p.getElem()) {
              p.setHD(insertarAvl(p.getHD(), x));
              p = balancear(p);
           } 
        else{ ///ya existe el elemento            
            }        
        return p;
    }

    public boolean insertarAvl(int elem) {
        try {
            this.raiz = insertarAvl(raiz, elem);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    protected int altura2(Nodo p) {
        if (p != null) {
            int ai = altura2(p.getHI());
            int ad = altura2(p.getHD());
            if (ai > ad) {
                return ai + 1;
            } else {
                return ad + 1;
            }
        }
        return 0;
    }


}
