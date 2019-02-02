package graph;

import java.util.ArrayList;
import java.util.LinkedList;

public class WeightedUndirectedGraph<V> {

    private int numberOfVertices;

    private int numberOfEdges;

    private ArrayList<ArrayList<Integer>> adjMatrix;

    private LinkedList<VertexInfo> vertexInfos;

    private class VertexInfo {

        V vertext;

        //this id here is just the array index within the adjmatrix. It is managed manually.
        int id;

        boolean visited;

        VertexInfo(V vertext){
            id = numberOfVertices;
            visited = false;
            this.vertext = vertext;
            numberOfVertices++;
        }
    }

    public WeightedUndirectedGraph() {
        this.numberOfVertices = 0;
        this.adjMatrix = new ArrayList<>();
        this.vertexInfos = new LinkedList<>();
    }

    public boolean hasVertex(V v) {
        VertexInfo vertexInfo = findVertexInfo(v);
        if (vertexInfo != null)
            return this.vertexInfos.contains(vertexInfo);
        return false;
    }

    public boolean areAdjacent(V s, V e) {
        if (hasVertex(s) && hasVertex(e)) {
            VertexInfo evi = findVertexInfo(e);
            for (ArrayList<Integer> list: this.adjMatrix)
                if (list.get(evi.id) != 0) return true;
        }
        return false;
    }

    private VertexInfo findVertexInfo(V vertex) {
        for (VertexInfo v: this.vertexInfos)
            if (v.vertext.equals(vertex)) return v;
        return null;
    }

    private void addVertex(V v) {
        this.vertexInfos.add(new VertexInfo(v));
        for (ArrayList list: adjMatrix)
            list.add(0);
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < adjMatrix.get(0).size(); i++) {
            arrayList.add(0);
        }
    }

    public void addEdge(V s, V e, Integer weight) {
        if (hasVertex(s) & hasVertex(e)) {
            VertexInfo start = findVertexInfo(s);
            VertexInfo end = findVertexInfo(e);
            this.adjMatrix.get(start.id).set(end.id, weight);
            this.adjMatrix.get(end.id).set(start.id, weight);
            this.numberOfEdges++;
        }
    }

    public void removeVertex(V v) {
        VertexInfo vertexInfo = findVertexInfo(v);
        if (vertexInfo != null) {
            for (int i = vertexInfo.id + 1; i < vertexInfos.size(); i++) {
                VertexInfo vertexInfo1 = vertexInfos.get(i);
                vertexInfo1.id = i - 1;
                vertexInfos.set(i, vertexInfo1);
            }
            this.vertexInfos.remove(vertexInfo.id);
            this.adjMatrix.remove(vertexInfo.id);
            for (ArrayList list: this.adjMatrix)
                list.remove(vertexInfo.id);
            numberOfVertices--;
        }
    }

    public void removeEdge(V s, V e) {
        if (hasVertex(s) & hasVertex(e)) {
            VertexInfo start = findVertexInfo(s);
            VertexInfo end = findVertexInfo(e);
            this.adjMatrix.get(start.id).set(end.id, 0);
            this.adjMatrix.get(end.id).set(start.id, 0);
            this.numberOfEdges--;
        }
    }

    public void setEdgeWeight(V s, V e, int weight) {
        addEdge(s, e, weight);
    }

    public Integer getEdgeWeight(V s, V e) {
        VertexInfo start = findVertexInfo(s);
        VertexInfo end = findVertexInfo(e);
        return this.adjMatrix.get(start.id).get(end.id);
    }

    public Integer degree(V v) {
        Integer degree = 0;
        VertexInfo vertexInfo = findVertexInfo(v);
        if (vertexInfo != null)
            for (Integer weight: this.adjMatrix.get(vertexInfo.id))
                if (weight != 0)
                    degree++;
        return degree;
    }

    public Integer weightedDegree(V v) {
        Integer degree = 0;
        VertexInfo vertexInfo = findVertexInfo(v);
        if (vertexInfo != null)
            for (Integer weight: this.adjMatrix.get(vertexInfo.id))
                if (weight != 0)
                    degree += weight;
        return degree;
    }

    public Integer numVerticies () {
        return this.numberOfVertices;
    }

    public Integer numEdges() {
        return this.numberOfEdges;
    }

    public Integer totalWeight() {
        Integer sum = 0;
        if (this.adjMatrix.size() != 0) {
            for (ArrayList<Integer> list: this.adjMatrix) {
                for (Integer i: list) {
                    sum += i;
                }
            }
        }
        return sum/2;
    }

    public LinkedList<V> getVertices() {
        LinkedList<V> vertices = new LinkedList<>();
        for (VertexInfo vertexInfo: this.vertexInfos)
            vertices.add(vertexInfo.vertext);
        return vertices;
    }

    public LinkedList<V> getNeighbours(V v) {
        LinkedList<V> vertices = new LinkedList<>();
        VertexInfo vertexInfo = findVertexInfo(v);
        for (Integer i: this.adjMatrix.get(vertexInfo.id)) {
            if (i != 0)
                vertices.add(this.vertexInfos.get(i).vertext);
        }
        return vertices;
    }

    public LinkedList<Integer> getEdge(V v) {
        LinkedList<Integer> edges = new LinkedList<>();
        VertexInfo vertexInfo = findVertexInfo(v);
        for (Integer i: this.adjMatrix.get(vertexInfo.id)) {
            if (i != 0)
                edges.add(i);
        }
        return edges;
    }

    /*public sequentialList<V> depthFirst() {

    }

    public sequentialList<V> breadthFirest() {

    }

    public WeightedUndirectedGraph mst() {

    }*/
}
