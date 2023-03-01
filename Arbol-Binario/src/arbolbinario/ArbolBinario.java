package arbolbinario;

import javax.swing.JTextArea;

public class ArbolBinario {

    private Nodo raiz;

    public ArbolBinario() {  //CONSTRUCTOR
        raiz = null;
    }

    //METODOS
    private boolean esHoja(Nodo pr) {
        return pr.getHI() == null && pr.getHD() == null;
    }

    //----------- INSERTAR
    public void insertar(int x) {
        Nodo q = new Nodo(x);
        if (raiz == null) {
            raiz = q;
            return;
        }
        Nodo p = raiz;
        Nodo ant = null;
        while (p != null) {
            ant = p;
            if (x < p.getElem()) {
                p = p.getHI();
            } else {
                if (x > p.getElem()) {
                    p = p.getHD();
                } else {
                    return;
                }
            }
        }
        if (x < ant.getElem()) {
            ant.setHI(q);
        } else {
            ant.setHD(q);
        }
    }

    //--------------- PREORDEN
    private void preOrden(Nodo p, JTextArea T) {
        if (p != null) {
            T.append(String.valueOf(p.getElem()) + " ");
            preOrden(p.getHI(), T);
            preOrden(p.getHD(), T);
        }
    }

    public void preOrden(JTextArea T) {
        preOrden(raiz, T);
    }

    //------------------ INORDEN
    private void inOrden(Nodo p, JTextArea T) {
        if (p != null) {
            inOrden(p.getHI(), T);
            T.append(String.valueOf(p.getElem()) + " ");
            inOrden(p.getHD(), T);
        }
    }

    public void inOrden(JTextArea T) {
        inOrden(raiz, T);
    }

    //------------- POSTORDEN
    private void postOrden(Nodo p, JTextArea T) {
        if (p != null) {
            inOrden(p.getHI(), T);
            inOrden(p.getHD(), T);
            T.append(String.valueOf(p.getElem()) + " ");
        }
    }

    public void postOrden(JTextArea T) {
        postOrden(raiz, T);
    }

    //----------- CANTIDAD
    private int cantidad(Nodo p) {
        int c = 0;
        if (p == null) {
            return c;
        }
        if (esHoja(p)) {
            return c = 1;
        } else {
            int ci = cantidad(p.getHI());
            int cd = cantidad(p.getHD());
            return c = ci + cd + 1;
        }
    }

    public int cantidad() {
        return cantidad(raiz);
    }

    //----------------- SUMAR
    private int sumar(Nodo p) {
        if (p == null) {
            return 0;
        }
        if (esHoja(p)) {
            return p.getElem();
        } else {
            int si = sumar(p.getHI());
            int sd = sumar(p.getHD());
            return si + sd + p.getElem();
        }
    }

    public int sumar() {
        return sumar(raiz);
    }
    //MOSTRAR POR NIVEL

    private void UltimoNivel(Nodo p, int altura, JTextArea jta) {
        if (p != null) {
            if (altura == 1) {
                jta.append(String.valueOf(p.getElem() + " "));
            } else {
                UltimoNivel(p.getHI(), altura - 1, jta);
                UltimoNivel(p.getHD(), altura - 1, jta);
            }
        }
    }

    public void UltimoNivel(JTextArea jta) {
        UltimoNivel(raiz, this.altura(), jta);
    }

    private void UltimoNivel(Nodo p, int altura) {
        if (p != null) {
            if (altura == 1) {
                System.out.print(p.getElem() + " ");
            } else {
                UltimoNivel(p.getHI(), altura - 1);
                UltimoNivel(p.getHD(), altura - 1);
            }
        }
    }

    public void UltimoNivel() {
        UltimoNivel(raiz, this.altura());
    }

    private void PorNivel(Nodo p, int n, JTextArea jta) {
        if (p != null) {

            if (n == 1) {
                jta.append(String.valueOf(p.getElem()) + " ");
            } else {
                PorNivel(p.getHI(), n - 1, jta);
                PorNivel(p.getHD(), n - 1, jta);
            }
        }
    }

    public void PorNivel(JTextArea jta, int n) {
        PorNivel(raiz, n, jta);
    }

    private int sumapornivel(Nodo p, int nivel) {
        if (p == null) {
            return 0;
        } else {
            if (nivel <= 1) {
                return p.getElem();
            } else {
                int hi = sumapornivel(p.getHI(), nivel - 1);
                int hd = sumapornivel(p.getHD(), nivel - 1);
                return hi + hd;
            }
        }
    }

    public int sumapornivel(int x) {
        return sumapornivel(raiz, x);
    }

    private void mostrarnivelX(Nodo raiz, int x, JTextArea ta) {
        if (raiz == null) {
            return;
        } else {
            if (x == 1) {
                ta.append(String.valueOf(raiz.getElem() + " "));
            }
            mostrarnivelX(raiz.getHI(), x - 1, ta);
            mostrarnivelX(raiz.getHD(), x - 1, ta);
        }
    }

    public void mostrarnivelX(int nivel, JTextArea jta) {
        mostrarnivelX(raiz, nivel, jta);
    }

    public void desdenivel(int n, JTextArea ta) {
        while (n <= altura()) {
            mostrarnivelX(n, ta);
            n++;
        }
    }

    public boolean esprimo(int x) {

        for (int i = 2; i < x / 2; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;

    }

    //---------------------SUMAR PARES
    private int sumarPares(Nodo raiz) {
        if (raiz == null) {
            return 0;
        } else {
            if (esHoja(raiz)) {

                if (raiz.getElem() % 2 == 0) {
                    return raiz.getElem();
                } else {
                    return 0;
                }

            } else {
                int s1 = sumar(raiz.getHI());
                int s2 = sumar(raiz.getHD());
                if (raiz.getElem() % 2 == 0) {
                    return raiz.getElem() + s1 + s2;
                } else {
                    return s1 + s2;
                }

            }
        }
    }

    public int sumarPares() {
        return sumarPares(raiz);
    }

    private int mayorArbol(Nodo raiz) {
        int a = 0;
        int d = 0;
        if (raiz == null) {
            return 0;
        } else {
            if (raiz.getHD() == null) {
                return raiz.getElem();
            }
            if (raiz.getHD() != null) {
                a = raiz.getHD().getElem();
            }
            d = mayorArbol(raiz.getHD());
        }
        return (d > a) ? d : a;
    }

    public int mayorArbol() {
        return mayorArbol(raiz);
    }
//MOSTRAR LOS PARES

    private void mostrarPares(Nodo raiz, JTextArea text) {
        if (raiz == null) {
            return;
        } else {
            if (esHoja(raiz)) {
                if (raiz.getElem() % 2 == 0) {
                    text.append(String.valueOf(raiz.getElem()) + " ");
                }
            } else {
                mostrarPares(raiz.getHI(), text);
                mostrarPares(raiz.getHD(), text);
                if (raiz.getElem() % 2 == 0) {
                    text.append(String.valueOf(raiz.getElem()) + " ");
                }
            }
        }
    }

    public void mostrarPares(JTextArea text) {
        mostrarPares(raiz, text);
    }
    //---------------------mostrar mayores de x

    private void mostrarmayores(Nodo raiz, int x, JTextArea text) {
        if (raiz == null) {
            return;
        } else {
            if (esHoja(raiz)) {
                if (raiz.getElem() > x) {
                    text.append(String.valueOf(raiz.getElem() + " "));
                }
            } else {
                mostrarmayores(raiz.getHD(), x, text);
                mostrarmayores(raiz.getHI(), x, text);
                if (raiz.getElem() > x) {
                    text.append(String.valueOf(raiz.getElem() + " "));
                }
            }
        }
    }

    public void mostrarmayores(int x, JTextArea text) {
        mostrarmayores(raiz, x, text);
    }

    //-------------------------------ES HIJO 
    private boolean eshijo(Nodo raiz, int x) {
        if (raiz == null) {
            return false;
        } else {
            if (!esHoja(raiz)) {
                if (raiz.getHI() != null && raiz.getHI().getElem() == x) {
                    return true;
                }
                if (raiz.getHD() != null && raiz.getHD().getElem() == x) {
                    return true;
                }
            }
            return false;
        }

    }

    public boolean eshijo(int x) {
        return eshijo(raiz, x);
    }
//----------------------------------MOSTRAR PADRES

    private void mostrarPadres(Nodo raiz, JTextArea text) {
        if (raiz == null || esHoja(raiz)) {
            return;
        } else {
            mostrarPadres(raiz.getHI(), text);
            mostrarPadres(raiz.getHD(), text);
            if (raiz.getHI() != null || raiz.getHD() != null) {
                text.append(String.valueOf(raiz.getElem() + " "));
            }
        }
    }

    public void mostrarPadres(JTextArea text) {
        mostrarPadres(raiz, text);

    }

    //-------------------MOSTRAR HIJOS
    /*private void mostrarhijos(Nodo raiz, int x, JTextArea text) {
        if (raiz == null) {
            return;
        } else {
            if (!esHoja(raiz)) {
                if (raiz.getElem() == x) {
                    if (raiz.canthijos() == 2) {
                        text.append(String.valueOf(raiz.getHI().getElem() + " "));
                        text.append(String.valueOf(raiz.getHD().getElem()) + " ");
                    } else {
                        if (raiz.canthijos() == 1) {
                            if (raiz.getHI() != null && raiz.getHD() == null) {
                                text.append(String.valueOf(raiz.getHI().getElem() + " "));
                            } else if (raiz.getHD() != null && raiz.getHI() == null) {
                                text.append(String.valueOf(raiz.getHD().getElem()) + " ");
                            }
                        }
                    }
                }
            }
            mostrarhijos(raiz.getHD(), x, text);
            mostrarhijos(raiz.getHI(), x, text);
        }
    }*/
    private void mostrarhijos(Nodo raiz, int x, JTextArea text) {
        if (raiz == null) {
            return;
        } else {
            if (!esHoja(raiz)) {
                if (raiz.getElem() == x) {
                    if (raiz.getHI() != null && raiz.getHD() != null) {
                        text.append(String.valueOf(raiz.getHI().getElem() + " "));
                        text.append(String.valueOf(raiz.getHD().getElem()) + " ");
                    } else {
                        if (raiz.getHI() != null && raiz.getHD() == null) {
                            text.append(String.valueOf(raiz.getHI().getElem() + " "));
                        } else if (raiz.getHD() != null && raiz.getHI() == null) {
                            text.append(String.valueOf(raiz.getHD().getElem()) + " ");
                        }
                    }
                }
            }
            mostrarhijos(raiz.getHD(), x, text);
            mostrarhijos(raiz.getHI(), x, text);
        }
    }

    public void mostrarhijos(int x, JTextArea text) {
        mostrarhijos(raiz, x, text);
    }
    //-------------------------------MOSTRAR NIETOS

    private void mostrarnietos(Nodo raiz, int x, JTextArea text) {
        if (raiz == null) {
            return;
        } else {
            if (raiz.getElem() == x) {
                if (raiz.getHD() != null) {//---------------------DERECHA
                    if (raiz.getHD().getHI() != null) {
                        text.append(String.valueOf(raiz.getHD().getHI().getElem()) + " ");
                    }
                    if (raiz.getHD().getHD() != null) {
                        text.append(String.valueOf(raiz.getHD().getHD().getElem()) + " ");
                    }
                }
                if (raiz.getHI() != null) {//---------------------IZQUIERDA
                    if (raiz.getHI().getHD() != null) {
                        text.append(String.valueOf(raiz.getHI().getHD().getElem()) + " ");
                    }
                    if (raiz.getHI().getHI() != null) {
                        text.append(String.valueOf(raiz.getHI().getHI().getElem()) + " ");
                    }
                }
            }//END IF
            if (x > raiz.getElem()) {
                mostrarnietos(raiz.getHD(), x, text);
            } else {
                mostrarnietos(raiz.getHI(), x, text);
            }
        }
    }

    public void mostrarnietos(int x, JTextArea text) {
        mostrarnietos(raiz, x, text);
    }

    //--------------------------mostrarpadres
    private void mostrarpadres(Nodo raiz, JTextArea text) {
        if (raiz == null) {
            return;
        } else {
            if (!esHoja(raiz)) {
                if (altura(raiz) >= 2) {
                    text.append(String.valueOf(raiz.getElem()));
                }
                mostrarPadres(raiz.getHI(), text);
                mostrarPadres(raiz.getHD(), text);
            }
            return;
        }
    }

    public void mostrarpadres(JTextArea text) {
        mostrarPadres(raiz, text);
    }

    private void mostrarabuelos(Nodo raiz, JTextArea text) {
        if (raiz == null) {
            return;
        } else {
            if (!esHoja(raiz)) {
                if (altura(raiz) >= 3) {
                    text.append(String.valueOf(raiz.getElem() + " "));
                }
                mostrarabuelos(raiz.getHI(), text);
                mostrarabuelos(raiz.getHD(), text);
            }
        }
    }

    public void mostrarabuelos(JTextArea text) {
        mostrarabuelos(raiz, text);
    }

    private boolean espadre(Nodo raiz, int p, int h) {
        if (raiz == null) {
            return false;
        } else {
            if (raiz != null && raiz.getElem() == p) {
                if (raiz.getHI() != null && raiz.getHI().getElem() == h) {
                    return true;
                } else if (raiz.getHD() != null && raiz.getHD().getElem() == h) {
                    return true;
                }
            }
        }
        boolean ai = espadre(raiz.getHI(), p, h);
        boolean ad = espadre(raiz.getHD(), p, h);
        return ai || ad;
    }

    public boolean espadre(int p, int h) {
        return espadre(raiz, p, h);
    }

    private boolean eshermano(Nodo raiz, int x, int y) {
        if (raiz != null) {
            if (raiz.getHI() != null && raiz.getHI().getElem() == x || raiz.getHD() != null && raiz.getHD().getElem() == x) {
                if (raiz.getHI() != null && raiz.getHI().getElem() == y || raiz.getHD() != null && raiz.getHD().getElem() == y) {
                    return true;
                }
                return false;
            }
            return (eshermano(raiz.getHI(), x, y) || eshermano(raiz.getHD(), x, y));
        }
        return false;
    }

    public boolean eshermano(int x, int y) {
        return eshermano(raiz, x, y);
    }

    private boolean eshijo(Nodo raiz, int h, int p) {
        if (raiz != null) {
            if (raiz.getHI() != null && raiz.getHI().getElem() == h || raiz.getHD() != null && raiz.getHD().getElem() == h) {
                if (raiz.getElem() == p) {
                    return true;
                }
                return false;
            }
        }
        return (eshijo(raiz.getHI(), h, p) || eshijo(raiz.getHD(), h, p));
    }

    public boolean eshijo(int h, int p) {
        return eshijo(raiz, h, p);
    }

    public boolean estio(int t, int x) {
        int y = x;
        int p = devolverPadre(x);
        if (eshermano(t, p)) {
            if (eshijo(y, p)) {
                return true;
            }
        }
        return false;
    }

    public boolean essobrino(int s, int t) {
        int p = devolverPadre(s);
        if (eshermano(p, t)) {
            return true;
        }
        return false;
    }

    private int Rhijo(Nodo raiz, int p) {
        if (raiz != null && raiz.getElem() == p) {
            if (raiz.getHI() != null) {
                return raiz.getHI().getElem();
            }
            if (raiz.getHD() != null) {
                return raiz.getHD().getElem();
            }
        }
        int i = Rhijo(raiz.getHI(), p);
        int d = Rhijo(raiz.getHD(), p);
        return 0;
    }

//----------------------ALTURA
    private int altura(Nodo raiz) {
        if (raiz == null) {
            return 0;
        } else {
            if (esHoja(raiz)) {
                return 1;
            } else {
                int alturaIzq = altura(raiz.getHI());
                int alturaDer = altura(raiz.getHD());
                if (alturaIzq > alturaDer) {
                    return alturaIzq + 1;
                } else {
                    return alturaDer + 1;
                }
            }
        }
    }

    public int altura() {
        return altura(raiz);
    }
//-----------------------------Existe

    private boolean existe(Nodo raiz, int x) {
        if (raiz == null) {
            return false;
        } else {
            if (esHoja(raiz)) {
                return raiz.getElem() == x;
            } else {
                boolean ai = existe(raiz.getHI(), x);
                boolean ad = existe(raiz.getHD(), x);
                if (ai || ad) {
                    return true;
                }
                return raiz.getElem() == x;
            }
        }
    }

    public boolean existe(int x) {
        return existe(raiz, x);
    }

    public void eliminar(int x) {
        raiz = eliminar(raiz, x);
    }

    private Nodo eliminar(Nodo raiz, int x) {
        if (raiz == null) {
            return null;
        }
        if (esHoja(raiz)) {
            if (raiz.getElem() == x) {
                return null;
            }
        }
        if (raiz.getElem() == x) {
            int pre = prefijo(raiz);
            raiz.setElem(pre);
            raiz.setHI(eliminar(raiz.getHI(), pre));
        }
        raiz.setHI(eliminar(raiz.getHI(), x));
        raiz.setHD(eliminar(raiz.getHD(), x));
        return raiz;

    }

    public int prefijo(Nodo raiz) {
        if (raiz == null) {
            return -1;
        }
        Nodo aux = raiz.getHI();
        while (aux != null) {
            aux = aux.getHD();
        }
        return aux.getElem();
    }

    public int postfijo(Nodo aux) {
        if (aux == null || esHoja(aux)) {
            return -1;
        }
        Nodo x = aux.getHD();
        while (x.getHI() != null) {
            x = x.getHI();
        }
        return x.getElem();
    }
    //------------------------DOS ARBOLES SON IGUALES?

    private boolean iguales(Nodo raizA, Nodo raizB) {//raiz arbol A y B
        if (raizA == null && raizB == null) {
            return true;
        } else {
            if (raizA == null && raizB != null || raizA != null && raizB == null) {
                return false;
            }
            if (esHoja(raizA) && esHoja(raizB)) {
                return raizA.getElem() == raizB.getElem();//preguntando por el padre de A y B
            }
            boolean ai = iguales(raizA.getHI(), raizB.getHI());
            boolean ad = iguales(raizA.getHD(), raizB.getHD());
            if (ai && ad) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean iguales(ArbolBinario B) {
        return iguales(this.raiz, B.raiz);//this hace referencia al arbol A
    }

    //---------------------------------------------------TiO
    private int devolverPadre(Nodo pr, int x) {
        if (pr == null) {
            return 0;
        } else if (altura(pr) >= 2) {
            if (pr.getHI() != null && pr.getHI().getElem() == x) {
                return pr.getElem();
            }

            if (pr.getHD() != null && pr.getHD().getElem() == x) {
                return pr.getElem();
            }

        }
        int ai = devolverPadre(pr.getHI(), x);
        int ad = devolverPadre(pr.getHD(), x);

        if (ai != 0) {
            return ai;
        } else if (ad != 0) {
            return ad;
        } else {
            return 0;
        }

    }

    public int devolverPadre(int x) {
        return devolverPadre(raiz, x);
    }

    public int devolverHermano(Nodo pr, int x) {
        if (pr == null) {
            return 0;
        } else if (altura(pr) >= 2) {
            if (pr.getHI() != null && pr.getHI().getElem() == x && pr.getHD() != null) {
                return pr.getHD().getElem();
            }
            if (pr.getHD() != null && pr.getHD().getElem() == x && pr.getHI() != null) {
                return pr.getHI().getElem();
            }
        }
        int ai = devolverHermano(pr.getHI(), x);
        int ad = devolverHermano(pr.getHD(), x);

        if (ai != 0) {
            return ai;
        } else if (ad != 0) {
            return ad;
        } else {
            return 0;
        }
    }

    public int devolverHermano(int x) {
        return devolverHermano(raiz, x);
    }

    private int devolverhijo(Nodo raiz, int x) {
        if (raiz == null) {
            return 0;
        } else {
            if (raiz.getHI() != null && raiz.getHI().getElem() == x) {
                return raiz.getHI().getElem();
            }
            if (raiz.getHD() != null && raiz.getHD().getElem() == x) {
                return raiz.getHD().getElem();
            }
        }
        int i = devolverhijo(raiz.getHI(), x);
        int d = devolverhijo(raiz.getHD(), x);
        if (i != 0) {
            return i;
        } else {
            if (d != 0) {
                return d;
            }
        }
        return 0;
    }

    public int devolverhijo(int x) {
        return devolverhijo(raiz, x);
    }

    public int devolverTio(int x) {
        if (raiz == null) {
            return 0;
        } else {
            int p = devolverPadre(x);
            return devolverHermano(p);
        }
    }

    public int devolverprimo(int x) {
        if (raiz == null) {
            return 0;
        } else {
            int t = devolverTio(x);
            return devolverhijo(t);

        }
    }
// mostrar los nodos que no tienen hermanos

    public void mostrar1h(JTextArea ta) {
        mostrar1h(raiz, ta);
    }

    private void mostrar1h(Nodo pr, JTextArea ta) {
        if (pr == null) {
            return;
        } else {
            if (pr.getElem() != 0) {
                if (pr.getHI() != null && pr.getHD() == null) {
                    ta.append(String.valueOf(pr.getHI().getElem()) + " ");
                } else {
                    if (pr.getHI() == null && pr.getHD() != null) {
                        ta.append(String.valueOf(pr.getHD().getElem()) + " ");
                    }
                }
            }
            mostrar1h(pr.getHI(), ta);
            mostrar1h(pr.getHD(), ta);
        }
    }

    private void mostrarArbol(Nodo p) {
        if (p == null) {
            return;
        }
        if (esHoja(p)) {
            System.out.println(p.getElem() + " ");
        } else {
            System.out.println(p.getElem() + " ");
            if (p.getHI() != null) {
                mostrarArbol(p.getHI());
            }
            if (p.getHD() != null) {
                mostrarArbol(p.getHD());
            }
        }
    }

    public void mostrarprimosX(int x, JTextArea text) {
        int t = devolverTio(x);
        mostrarhijos(t, text);
    }

    public void mostrarSobrinosX(int x, JTextArea ta) {
        int h = devolverHermano(x);
        mostrarhijos(h, ta);
    }

    public void mostrarArbol() {
        mostrarArbol(raiz);
    }

    private void eliminiarnivelN(Nodo raiz, int nivel, int aux) {
        if (raiz == null) {
            return;
        }
        if (nivel == 1) {
            raiz = null;

            if (aux == nivel - 1) {
                if (raiz.getHI() != null) {
                    raiz.setHI(null);
                }
                if (raiz.getHD() != null) {
                    raiz.setHD(null);
                }
            } else {
                eliminiarnivelN(raiz.getHI(), nivel, aux + 1);
                eliminiarnivelN(raiz.getHD(), nivel, aux + 1);
            }
        }
    }

    public void eliminarnivelN(int x) {
        eliminiarnivelN(raiz, x, 1);
    }

    public static void main(String[] args) {
        ArbolBinario ab = new ArbolBinario();
        ab.insertar(50);
        ab.insertar(20);
        ab.insertar(60);
        ab.insertar(18);
        ab.insertar(25);
        ab.insertar(54);
        ab.insertar(68);
        System.out.println(ab.existe(50));
        System.out.println(ab.sumar());
        System.out.println(ab.sumapornivel(3));
        //System.out.println(ab.espadre(20, 18));
        //System.out.println(ab.cantidad());
        //ab.UltimoNivel();
        ab.mostrarArbol();
    }

}
