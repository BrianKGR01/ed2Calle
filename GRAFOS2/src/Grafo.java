
import java.util.*;

import javax.swing.*;
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

    public float peso() {
        int i = 0;
        Vertice v;
        float s = 0;
        while (i < LVertices.dim()) {
            v = (Vertice) LVertices.getElem(i);
            int j = 0;
            Arco a;
            while (j < v.LArcos.dim()) {
                a = (Arco) v.LArcos.getElem(j);
                s = s + a.getCosto();
                j++;
            }
            i++;
        }
        return s;
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

//verifica si es el grafo es un arbol terniario: es decir tiene 3 hijos...
    public boolean Esternario(String X) {
        Vertice vo = buscarVertice(X);
        vo.marcado = true;
        int i = 0;
        if (vo.LArcos.dim() <= 3) {
            while (i < vo.LArcos.dim()) {
                Arco a = (Arco) vo.LArcos.getElem(i);
                Vertice vd = buscarVertice(a.getNombreVertD());
                i++;
                if (!vd.marcado) {
                    return Esternario(a.getNombreVertD());
                }
            }
            return true;
        }
        return false;
    }

    //crear un grafo que verifique si es un arbol binario que se encuentra en el nodo de la clase 
    public boolean esBinario(String x) {
        Vertice vo = buscarVertice(x);
        vo.marcado = true;
        if (vo.LArcos.dim() <= 2) {
            for (int i = 0; i < vo.LArcos.dim(); i++) {
                Arco a = (Arco) vo.LArcos.getElem(i);
                Vertice vd = buscarVertice(a.getNombreVertD());
                if (!vd.marcado) {
                    return esBinario(a.getNombreVertD());
                }
            }
            return true;
        }
        return false;
    }

    //verifica si existe caminos entre x y y del grafo dado:
    public boolean existeCamino(String x, String y) {
        desmarcarTodos();
        Vertice v = buscarVertice(x);
        v.marcado = true;
        Arco a;
        for (int i = 0; i < v.LArcos.dim(); i++) {
            a = (Arco) v.LArcos.getElem(i);
            Vertice v2 = buscarVertice(a.getNombreVertD());
            if (!v2.marcado) {
                if (v2.getNombre().equals(y)) {
                    return true;
                } else {
                    if (existeCamino(a.getNombreVertD(), y)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

  private void caminototales(Vertice vo, String vd, String mostrar, float c, JTextArea jta) {
        vo.marcado = true;
        float c1 = 0;
        Arco a;
        mostrar = mostrar + vo.getNombre() + "--";
        for (int i = 0; i < vo.LArcos.dim(); i++) {
            a = (Arco) vo.LArcos.getElem(i);
            c1 = a.getCosto();
            c1 = c + c1;
            Vertice v2 = buscarVertice(a.getNombreVertD());
            if (a.getNombreVertD().equals(vd)) {
                    jta.append(mostrar + vd + " == " + String.valueOf(c1) + "--"  + "\n");
            } else {
                caminototales(v2, vd, mostrar, c1, jta);
            }
        }
        vo.marcado = false;
    }

    public void caminototales(String inicio, String fin, JTextArea jta) {
        if (this.existeCamino(inicio, fin)) {
            this.desmarcarTodos();
            Vertice vo = this.buscarVertice(inicio);
            String mostrar = "";
            float c = 0;
            caminototales(vo, fin, mostrar, c, jta);
        }
    }
//ejercicio que suma el total de un vertice x a un vertice y

    public float costocamino(String x, String y) {
        desmarcarTodos();
        Vertice v = buscarVertice(x);
        v.marcado = true;
        Arco a;
        float s = 0;
        float cl = 0;
        for (int i = 0; i < v.LArcos.dim(); i++) {
            a = (Arco) v.LArcos.getElem(i);
            cl = a.getCosto();
            cl = s + cl;
            Vertice aux = buscarVertice(a.getNombreVertD());
            if (!aux.marcado) {
                if (aux.getNombre().equals(y)) {
                    return cl;
                } else {
                    cl = cl + costocamino(a.getNombreVertD(), y);
                }
            }

        }
        return cl;
    }

    public int cantCaminos(String x, String y) {
        desmarcarTodos();
        Vertice v = buscarVertice(x);
        v.marcado = true;
        Arco a;
        int cant = 0;
        for (int i = 0; i < v.LArcos.dim(); i++) {
            a = (Arco) v.LArcos.getElem(i);
            Vertice aux = buscarVertice(a.getNombreVertD());
            if (!aux.marcado) {
                if (aux.getNombre().equals(y)) {
                    cant = cant + 1;
                }
                cant = cant + cantCaminos(a.getNombreVertD(), y);
            }
        }
        return cant;
    }

    public void eliminarArco(String X, String Y) {
        Vertice v;
        v = buscarVertice(X);
        int i = 0;
        Arco a;
        while (i < v.LArcos.dim()) {
            a = (Arco) v.LArcos.getElem(i);
            if (a.getNombreVertD().equals(Y)) {
                v.LArcos.eliminar(i);
            }
            i++;
        }
    }

    public void eliminarVertice(String x) {
        Vertice v;
        for (int i = 0; i < LVertices.dim(); i++) {
            v = (Vertice) LVertices.getElem(i);
            if (v.getNombre().equals(x)) {
                LVertices.eliminar(i);
            }
        }
        Arco a;
        Vertice aux;
        for (int i = 0; i < LVertices.dim(); i++) {
            aux = (Vertice) LVertices.getElem(i);
            for (int j = 0; j < aux.LArcos.dim(); j++) {
                a = (Arco) aux.LArcos.getElem(j);
                if (a.getNombreVertD().equals(x)) {
                    aux.LArcos.eliminar(j);
                }
            }

        }
    }

    public void eliminarCamino(String x, String y) {
        desmarcarTodos();
        Vertice vo = buscarVertice(x);
        vo.marcado = true;
        Arco a;
        for (int i = 0; i < vo.LArcos.dim(); i++) {
            a = (Arco) vo.LArcos.getElem(i);
            Vertice aux = buscarVertice(a.getNombreVertD());
            if (!aux.marcado) {
                if (aux.getNombre().equals(y)) {
                } else {
                    eliminarCamino(a.getNombreVertD(), y);
                }
            }
        }
    }

    public boolean unicocamino(String x, String y) {
        Vertice vo = buscarVertice(x);
        Arco a;
        int c = 0;
        desmarcarTodos();
        vo.marcado = true;
        if (existeCamino(x, y)) {
            for (int i = 0; i < vo.LArcos.dim(); i++) {
                a = (Arco) vo.LArcos.getElem(i);
                Vertice vd = buscarVertice(a.getNombreVertD());
                if (!vd.marcado) {
                    if (vd.getNombre().equals(y)) {
                        c = c + 1;
                    } else {
                        unicocamino(a.getNombreVertD(), y);
                    }
                }
            }
        }
        return c == 1;
    }

/*    public float costoTotalVersion3(String origen, String destino) {
        Vertice verticeOrigen = this.buscarVertice(origen);
        Vertice verticeDestino = this.buscarVertice(destino);
        List<Float> listaCostos = new ArrayList<>();
        float costoTotal = 0;
        if (verticeOrigen != null && verticeDestino != null) {
            desmarcarTodos();
            this.ordenarVerticesAlf();
            LinkedList<String> listaCaminos = new LinkedList<>();
            listaCaminos.add(origen);
            costoTotalVersion(origen, verticeOrigen, verticeDestino, listaCaminos, 0, listaCostos);
            if (!listaCostos.isEmpty()) {
                for (int i = 0; i < listaCostos.size(); i++) {
                    costoTotal = costoTotal + listaCostos.get(i);
                }
                return costoTotal;
            } else {
                return costoTotal;
            }

        }
        return costoTotal;

    }

    private void costoTotalVersion(String origen, Vertice verticeOrigen, Vertice verticeDestino, LinkedList<String> listaCaminos, float costo, List<Float> listaCostos) {
        if (this.existeCamino(verticeOrigen.getNombre(), verticeDestino.getNombre())) {
            for (int i = 0; i < verticeOrigen.LArcos.dim(); i++) {
                Arco aux = (Arco) verticeOrigen.LArcos.getElem(i);
                listaCaminos.add(aux.getNombreVertD());
                costo = costo + aux.getCosto();
                if (listaCaminos.getLast().equals(verticeDestino.getNombre())) {
                    listaCostos.add(costo);
                    // ta.append(listaCaminos.toString() + " Costo Total: " + String.valueOf(costoTotal) + "\n");
                }
                Vertice verticeAux = this.buscarVertice(aux.getNombreVertD());
                if (!verticeAux.getNombre().equals(origen)) {
                    costoTotalVersion(origen, verticeAux, verticeDestino, listaCaminos, costo, listaCostos);
                }
                listaCaminos.removeLast();
                costo = costo - aux.getCosto();
            }
        }
    }*/

    public boolean es_sub_grafo(Grafo b) {

        int i = 0;
        boolean ba = true;
        while (i < b.LVertices.dim() && ba == true) {
            Vertice vb = (Vertice) b.LVertices.getElem(i);
            Vertice v = buscarVertice(vb.getNombre());
            if (v != null) {
                int d = 0;
                while (d < vb.LArcos.dim() && ba == true) {
                    Arco a = (Arco) vb.LArcos.getElem(d);
                    if (!existe_arco(v.getNombre(), a.getNombreVertD())) {
                        ba = false;
                    }
                    d++;
                }

            } else {
                ba = false;
            }
            i++;
        }
        return ba;
    }

//    public boolean subGrafo(Grafo b) {
//        for (int i = 0; i < b.LVertices.dim(); i++) {
//            Vertice vb = (Vertice) b.LVertices.getElem(i);
//            Vertice va=buscarVertice  (vb.getNombre());
//            if (va!= null) {
//                for (int j = 0; j < vb.LArcos.dim(); j++) {
//                    Arco a = (Arco) vb.LArcos.getElem(j);
//                    if (!existeArco(va.getNombre(), a.getNombreVertD())) {
//                        return false;
//                    }
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
    public boolean existe_arco(String x, String y) {
        Vertice v = buscarVertice(x);
        Vertice a = buscarVertice(y);
        if (v != null) {
            return existe_arco(v, a);
        } else {
            return false;
        }
    }

    private boolean existe_arco(Vertice v, Vertice a) {
        boolean b = false;
        for (int i = 0; i < v.LArcos.dim(); i++) {
            Arco ar = (Arco) v.LArcos.getElem(i);
            Vertice w = buscarVertice(ar.getNombreVertD());
            if (w.getNombre().equals(a.getNombre())) {
                b = true;
            }
        }
        return b;
    }

    public boolean existeArco(String x, String y) {
        Vertice v = buscarVertice(x);
        Arco a;
        for (int i = 0; i < v.LArcos.dim(); i++) {
            a = (Arco) v.LArcos.getElem(i);
            Vertice aux = buscarVertice(a.getNombreVertD());
            if (aux.getNombre().equals(y)) {
                return true;
            } else {
                existeArco(a.getNombreVertD(), y);
            }
        }
        return false;
    }
    
}  //end class
