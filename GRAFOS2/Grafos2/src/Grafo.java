
import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class Grafo {

    private Lista LVertices;

    public Grafo() {
        LVertices = new Lista();
    }

    public void crearVertice(String nomV) {
        LVertices.insertarUlt(new Vertice(nomV));
    }

    public Vertice buscarVertice(String nomV) {
        Vertice vertice;
        int i = 0;
        while (i < LVertices.dim()) {
            vertice = (Vertice) LVertices.getElem(i);
            if (vertice.getNombre().equals(nomV)) {
                return vertice;
            }
            i++;
        }
        return null;
    }

    public void insertarArco(String X, String Y, float co) {
        Vertice vo = buscarVertice(X);
        Vertice vd = buscarVertice(Y);
        vo.insertarArco(new Arco(vd, co));
    }

    public void imprimir(JTextArea jta) {
        int i = 0;
        Vertice v;
        Arco a;
        while (i < LVertices.dim()) {
            v = (Vertice) LVertices.getElem(i);
            int j = 0;
            while (j < v.LArcos.dim()) {
                jta.append(v.getNombre());
                jta.append("-->");
                a = (Arco) v.LArcos.getElem(j); //Muestra el arco donde apunto
                jta.append(a.getNombreVertD() + "  " + a.getCosto());
                jta.append("\n");
                j++;
            }
            i++;
        }
    }

    //METODOS DE ENSEÑANZA    
    public float peso() {
        int i = 0;
        Vertice v;
        Arco a;
        float x = 0;
        while (i < LVertices.dim()) {
            v = (Vertice) LVertices.getElem(i);
            int j = 0;
            while (j < v.LArcos.dim()) {

                a = (Arco) v.LArcos.getElem(j); //Muestra el arco donde apunto
                x = x + a.getCosto();
                j++;
            }
            i++;
        }
        return x;
    }

    public void desmarcarTodos() {
        for (int i = 0; i < this.LVertices.dim(); i++) {
            Vertice v = (Vertice) this.LVertices.getElem(i);
            v.marcado = false;
        }
    }

    public void ordenarVerticesAlf() {
        Vertice aux;
        Vertice v1;
        Vertice v2;
        for (int i = 0; i < LVertices.dim(); i++) {
            for (int j = 0; j < LVertices.dim() - 1; j++) {
                v1 = (Vertice) LVertices.getElem(j);
                v2 = (Vertice) LVertices.getElem(j + 1);
                if (v1.getNombre().compareTo(v2.getNombre()) > 0) {
                    aux = (Vertice) LVertices.getElem(j);
                    LVertices.setElem(v2, j);
                    LVertices.setElem(aux, j + 1);
                }
            }
        }
        for (int i = 0; i < LVertices.dim(); i++) {
            Vertice v = (Vertice) LVertices.getElem(i);
            v.ordenarArcosAlf();
        }
    }

    public void DFS(String A, JTextArea jta) {
        jta.append("DFS: ");
        desmarcarTodos();
        ordenarVerticesAlf();
        Vertice v = buscarVertice(A);
        dfs(v, jta);
        jta.append("\n");
    }

    private void dfs(Vertice v, JTextArea jta) {
        jta.append(v.getNombre() + " ");
        v.marcado = true;
        Arco a;
        for (int i = 0; i < v.LArcos.dim(); i++) {
            a = (Arco) v.LArcos.getElem(i);
            Vertice w = buscarVertice(a.getNombreVertD());
            if (!w.marcado) {
                dfs(w, jta);
            }
        }
    }

    public void BFS(String s, JTextArea jta) {
        desmarcarTodos();
        ordenarVerticesAlf();
        Arco a;
        Vertice v = buscarVertice(s), w;
        LinkedList<Vertice> C;
        C = new LinkedList<Vertice>();
        C.add(v);
        v.marcado = true;
        jta.append("BFS: ");
        do {
            v = C.pop();
            jta.append(v.getNombre() + " ");
            for (int i = 0; i < v.LArcos.dim(); i++) {
                a = (Arco) v.LArcos.getElem(i);
                w = buscarVertice(a.getNombreVertD());
                if (!w.marcado) {
                    C.add(w);
                    w.marcado = true;
                }
            }
        } while (!C.isEmpty());
        jta.append("\n");
    }

    public float ArcosSaliente() {
        int i = 0;
        Vertice v;
        Arco a;
        float x = 0;
        while (i < LVertices.dim()) {
            v = (Vertice) LVertices.getElem(i);
            int j = 0;
            x = x + v.LArcos.dim();
            while (j < v.LArcos.dim()) {

                a = (Arco) v.LArcos.getElem(j); //Muestra el arco donde apunto

                j++;
            }
            i++;
        }
        return x;
    }

    public boolean existeCamino(String X, String Y) {
        Vertice p = buscarVertice(X);
        Vertice q = buscarVertice(Y);
        if (p != null && q != null) {
            desmarcarTodos();
            ordenarVerticesAlf();
            Vertice v = buscarVertice(X);
            return existeCamino(v, Y);
        }
        return false;
    }

    private boolean existeCamino(Vertice v, String Y) {
        v.marcado = true;
        Arco a;
        for (int i = 0; i < v.LArcos.dim(); i++) {
            a = (Arco) v.LArcos.getElem(i);
            Vertice w = buscarVertice(a.getNombreVertD());
            if (!w.marcado) {
                if (w.getNombre().equals(Y)) {
                    return true;
                } else {
                    if (existeCamino(w, Y));
                    //si de b a c llego true; //ESTOOO NO ENTENDI, ANALIZAR
                    return true;
                }                   //aqui sigo llamando a la recursiviad y W se tranforma en mi vertice inicial

            } else {
                if (w.getNombre().equals(Y)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int cantidadCaminos(String X, String Y) {
        Vertice p = buscarVertice(X);
        Vertice q = buscarVertice(Y);
        if (p != null && q != null) {
            desmarcarTodos();
            ordenarVerticesAlf();
            Vertice v = buscarVertice(X);
            return cantidadCaminos(v, Y);
        }
        return 0;
    }

    private int cantidadCaminos(Vertice v, String Y) {
        v.marcado = true;
        int c = 0;
        Arco a;
        for (int i = 0; i < v.LArcos.dim(); i++) {
            a = (Arco) v.LArcos.getElem(i);
            Vertice w = buscarVertice(a.getNombreVertD());
            if (!w.marcado) {
                if (w.getNombre().equals(Y)) {
                    c = c + 1;
                } else {
                    c = c + cantidadCaminos(w, Y);
                    w.marcado = false;
                }
            }
        }
        return c;
    }

    //*********************************BFS**************************************
    public boolean ExisteCamino(String s, String x) {
        desmarcarTodos();
        ordenarVerticesAlf();
        Arco a; //int c=0;
        Vertice v = buscarVertice(s), w;
        LinkedList<Vertice> C;
        C = new LinkedList<Vertice>();
        C.add(v);
        v.marcado = true;
        //jta.append("BFS: ");
        do {
            v = C.pop();
            //jta.append(v.getNombre() + " ");
            for (int i = 0; i < v.LArcos.dim(); i++) {
                a = (Arco) v.LArcos.getElem(i);
                w = buscarVertice(a.getNombreVertD());
                if (!w.marcado) {
                    if (w.getNombre().equals(x)) {
                        return true;
                    } else {
                        C.add(w);
                    }
                    w.marcado = true;
                }
            }

        } while (!C.isEmpty());
        return false;
        //jta.append("\n");
    }

    public boolean existeCaminoBFS(String X, String Y) {
        desmarcarTodos();
        ordenarVerticesAlf();
        Arco a;
        Vertice v = buscarVertice(X), w;
        boolean aux = false;
        LinkedList<Vertice> C;
        C = new LinkedList<Vertice>();
        C.add(v);
        v.marcado = true;
        do {
            v = C.pop();
            for (int i = 0; i < v.LArcos.dim(); i++) {
                a = (Arco) v.LArcos.getElem(i);
                w = buscarVertice(a.getNombreVertD());
                if (!w.marcado) {
                    C.add(w);
                    w.marcado = true;
                    if (w.getNombre().equals(Y)) {
                        aux = true;
                    }
                }
            }
        } while (!C.isEmpty());
        return aux;
    }

    public int CantCaminoBFS(String X, String Y) {
        desmarcarTodos();
        ordenarVerticesAlf();
        Arco a;
        Vertice v = buscarVertice(X), w;
        int cant = 0;
        LinkedList<Vertice> C;
        C = new LinkedList<Vertice>();
        C.add(v);
        v.marcado = true;
        do {
            v = C.pop();
            for (int i = 0; i < v.LArcos.dim(); i++) {
                a = (Arco) v.LArcos.getElem(i);
                w = buscarVertice(a.getNombreVertD());
                if (!w.marcado) {
                    C.add(w);
                    w.marcado = true;
                    if (w.getNombre().equals(Y)) {
                        w.marcado = false;
                        cant = cant + 1;

                    }
                }
            }
        } while (!C.isEmpty());
        return cant;
    }

    public boolean conexo() {
        int i = 0;
        while (i < LVertices.dim()) {
            Vertice vo = (Vertice) LVertices.getElem(i);
            int j = 0;
            while (j < LVertices.dim()) {
                Vertice vd = (Vertice) LVertices.getElem(j);
                if (!existeCamino(vo.getNombre(), vd.getNombre())) {
                    return false;
                }
                j++;
            }
            i++;
        }
        return true;
    }
    //-Mostrar los caminos que existen de un vertice Origen a un vertice destino (todos los caminos)

    public boolean esArbolBinario(String X) {
        Vertice vo = buscarVertice(X);
        vo.marcado = true;
        if (vo.LArcos.dim() <= 2) {
            for(int i=0;i<vo.LArcos.dim();i++) {
                Arco a = (Arco) vo.LArcos.getElem(i);
                Vertice w = buscarVertice(a.getNombreVertD());
                if (!w.marcado) {
                    return esArbolBinario(a.getNombreVertD());
                }
            }
            return true;
        }
        return false;

    }
//muestra todos los caminos que hay en el grafo desde el vertice 1 hasta el ultimo vertice
    private void MostrarTodosLosCaminos(Vertice v, LinkedList<String> l, JTextArea jta) {
        Arco a;
        for (int i = 0; i < v.LArcos.dim(); i++) {
            a = (Arco) v.LArcos.getElem(i);
            l.add(a.getNombreVertD());
            jta.append(l.toString() + "\n");
            Vertice p = buscarVertice(a.getNombreVertD());
            MostrarTodosLosCaminos(p, l, jta);
            l.removeLast();
        }
    }
    
    public void MostrarTodosLosCaminos(JTextArea jta) {
        Vertice v;
        LinkedList<String> l = new LinkedList<>();
        for (int i = 0; i < LVertices.dim(); i++) {
            v = (Vertice) LVertices.getElem(i);
            l.add(v.getNombre());
            MostrarTodosLosCaminos(v, l, jta);
            l.clear();
        }
    }

    private void MostrarCaminos(Vertice x, Vertice y, LinkedList<String> l, JTextArea jta) {
        if (existeCamino(x.getNombre(), y.getNombre())) {
            Arco a;
            for (int i = 0; i < x.LArcos.dim(); i++) {
                a = (Arco) x.LArcos.getElem(i);
                l.add(a.getNombreVertD());
                if (l.getLast().equals(y.getNombre())) {
                    jta.append(l.toString() + "\n");
                }
                Vertice v = buscarVertice(a.getNombreVertD());
                MostrarCaminos(v, y, l, jta);
                l.removeLast();
            }
        }
    }

    public void MostrarCaminos(String x, String y, JTextArea jta) {
        Vertice v1 = buscarVertice(x);
        Vertice v2 = buscarVertice(y);
        LinkedList<String> l = new LinkedList<>();
        l.add(v1.getNombre());
        MostrarCaminos(v1, v2, l, jta);
        l.clear();
    }

    private void costoTotalVersion3(String origen, Vertice verticeOrigen, Vertice verticeDestino, LinkedList<String> listaCaminos, float costo,List<Float> listaCostos){
        if (this.existeCamino(verticeOrigen.getNombre(), verticeDestino.getNombre())) {
            for (int i = 0; i < verticeOrigen.LArcos.dim(); i++) {
                Arco aux = (Arco) verticeOrigen.LArcos.getElem(i);
                listaCaminos.add(aux.getNombreVertD());
                costo = costo + aux.getCosto();
                if (listaCaminos.getLast().equals(verticeDestino.getNombre())) {
                    listaCostos.add(costo);
                    //ta.append(listaCaminos.toString() + " Costo Total: " + String.valueOf(costoTotal) + "\n");
                }
                Vertice verticeAux = buscarVertice(aux.getNombreVertD());
                if (!verticeAux.getNombre().equals(origen)) {
                    costoTotalVersion3(origen, verticeAux, verticeDestino, listaCaminos, costo, listaCostos);
                }
                listaCaminos.removeLast();
                costo = costo - aux.getCosto();
            }
        }
    

    }
    public void imprimircaminos(JTextArea jta){
        Vertice v;
        LinkedList<String> l= new LinkedList<>();
        Arco a;
        for (int i = 0; i < LVertices.dim(); i++) {
            l.clear();
            v=(Vertice)LVertices.getElem(i);
            l.add(v.getNombre());
            jta.append(l.toString());
            for (int j = 0; j < v.LArcos.dim(); j++) {
                a=(Arco)v.LArcos.getElem(j);
                l.add(a.getNombreVertD());
                jta.append(l.toString());
            }
        }
    }
}  //end class
