package graph;

import java.util.ArrayList;
import java.util.LinkedList;

public class WeightedUndirectedGraph<Vertex> {

    private int numberOfVertices;

    private int numberOfEdges;

    private ArrayList<ArrayList<Integer>> adjMatrix;

    private LinkedList<VertexInfo> vertexInfos;

    private class VertexInfo {

        Vertex vertex;

        //this id here is just the array index within the adjmatrix. It is managed manually.
        int id;

        boolean visited;

        VertexInfo(Vertex vertex){
            id = numberOfVertices;
            visited = false;
            this.vertex = vertex;
            numberOfVertices++;
        }
    }

    public WeightedUndirectedGraph() {
        this.numberOfVertices = 0;
        this.adjMatrix = new ArrayList<>();
        this.vertexInfos = new LinkedList<>();
    }

    public boolean hasVertex(Vertex v) {
        VertexInfo vertexInfo = findVertexInfo(v);
        if (vertexInfo != null)
            return this.vertexInfos.contains(vertexInfo);
        return false;
    }

    public boolean areAdjacent(Vertex s, Vertex e) {
        if (hasVertex(s) && hasVertex(e)) {
            VertexInfo evi = findVertexInfo(e);
            for (ArrayList<Integer> list: this.adjMatrix)
                if (list.get(evi.id) != 0) return true;
        }
        return false;
    }

    private VertexInfo findVertexInfo(Vertex vertex) {
        for (VertexInfo v: this.vertexInfos)
            if (v.vertex.equals(vertex)) return v;
        return null;
    }

    private void addVertex(Vertex vertex) {
        this.vertexInfos.add(new VertexInfo(vertex));
        for (ArrayList list: adjMatrix)
            list.add(0);
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < adjMatrix.get(0).size(); i++) {
            arrayList.add(0);
        }
    }

    public void addEdge(Vertex s, Vertex e, Integer weight) {
        if (hasVertex(s) & hasVertex(e)) {
            VertexInfo start = findVertexInfo(s);
            VertexInfo end = findVertexInfo(e);
            this.adjMatrix.get(start.id).set(end.id, weight);
            this.adjMatrix.get(end.id).set(start.id, weight);
            this.numberOfEdges++;
        }
    }

    public void removeVertex(Vertex vertex) {
        VertexInfo vertexInfo = findVertexInfo(vertex);
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

    public void removeEdge(Vertex s, Vertex e) {
        if (hasVertex(s) & hasVertex(e)) {
            VertexInfo start = findVertexInfo(s);
            VertexInfo end = findVertexInfo(e);
            this.adjMatrix.get(start.id).set(end.id, 0);
            this.adjMatrix.get(end.id).set(start.id, 0);
            this.numberOfEdges--;
        }
    }

    public void setEdgeWeight(Vertex s, Vertex e, int weight) {
        addEdge(s, e, weight);
    }

    public Integer getEdgeWeight(Vertex s, Vertex e) {
        VertexInfo start = findVertexInfo(s);
        VertexInfo end = findVertexInfo(e);
        return this.adjMatrix.get(start.id).get(end.id);
    }

    public Integer degree(Vertex vertex) {
        Integer degree = 0;
        VertexInfo vertexInfo = findVertexInfo(vertex);
        if (vertexInfo != null)
            for (Integer weight: this.adjMatrix.get(vertexInfo.id))
                if (weight != 0)
                    degree++;
        return degree;
    }

    public Integer weightedDegree(Vertex vertex) {
        Integer degree = 0;
        VertexInfo vertexInfo = findVertexInfo(vertex);
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

    public LinkedList<Vertex> getVertices() {
        LinkedList<Vertex> vertices = new LinkedList<>();
        for (VertexInfo vertexInfo: this.vertexInfos)
            vertices.add(vertexInfo.vertex);
        return vertices;
    }

    public LinkedList<Vertex> getNeighbours(Vertex vertex) {
        LinkedList<Vertex> vertices = new LinkedList<>();
        VertexInfo vertexInfo = findVertexInfo(vertex);
        for (Integer i: this.adjMatrix.get(vertexInfo.id)) {
            if (i != 0)
                vertices.add(this.vertexInfos.get(i).vertex);
        }
        return vertices;
    }

    public LinkedList<Integer> getEdge(Vertex vertex) {
        LinkedList<Integer> edges = new LinkedList<>();
        VertexInfo vertexInfo = findVertexInfo(vertex);
        for (Integer i: this.adjMatrix.get(vertexInfo.id)) {
            if (i != 0)
                edges.add(i);
        }
        return edges;
    }

    /*public sequentialList<Vertex> depthFirst() {

    }

    public sequentialList<Vertex> breadthFirest() {

    }

    public WeightedUndirectedGraph mst() {

    }*/
}
