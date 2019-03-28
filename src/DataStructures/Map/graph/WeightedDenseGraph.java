package DataStructures.Map.graph;

import DataStructures.Collections.Queue.LinkedListQueue;
import DataStructures.Collections.Queue.Queue;
import DataStructures.Collections.list.Array;
import DataStructures.Collections.list.LinkedList;
import DataStructures.Collections.list.List;
import DataStructures.Collections.stack.LinkedListStack;
import DataStructures.Collections.stack.Stack;
import DataStructures.Map.UnionFind.UnionFind;
import DataStructures.Map.UnionFind.UnionFindF;
import DataStructures.Map.tree.Heap;
import DataStructures.Map.tree.Tree;
import DataStructures.Pair;
import com.sun.istack.internal.Nullable;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Iterator;

public class WeightedDenseGraph<V, E extends Comparable<E>> implements Graph<V, E> {

    private List<V> nodes;
    private boolean directed;
    private List<List<Edge>> graph;

    private class Edge implements Comparable<Edge> {

        int start, end;
        E value; //通过判断值是否为空来判断是否存在边

        Edge(@Nullable E value, int start, int end) {
            this.value = value;
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Edge o) {
            return this.value.compareTo(o.value);
        }
    }

    private abstract class BaseIterator implements Iterator<Integer> {

        Integer row;
        Integer next;

        BaseIterator(int row) {
            if (containsNode(row)) {
                this.row = row;
                next = 0;
            }
        }
    }

    private class NeighbourIterator extends BaseIterator {

        NeighbourIterator(int row) {
            super(row);
        }

        public boolean hasNext() {
            while (next < graph.get(row).size() && graph.get(row).get(next).value != null) next++;
            return next != graph.get(row).size();
        }

        public Integer next() {
            return next > graph.get(row).size() ? null : next++;
        }
    }

    private class EdgeIterator implements Iterator<Edge> {

        int startNode, endNode;
        Iterator it;

        EdgeIterator(int startNode) {
            this.startNode = startNode;
            it = neighbourIterator(this.startNode);
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public Edge next() {
            endNode = (int) it.next();
            return null;
        }
    }

    public WeightedDenseGraph() {
        new WeightedDenseGraph(false);
    }

    public WeightedDenseGraph(boolean directed) {
        this.directed = directed;
        nodes = new Array<>();
        graph = new Array<>();
    }

    private void addOneColumn() {
        if (isEmpty()) addFirstElement();
        int count = 0;
        for (List<Edge> list : graph) {
            list.add(new Edge(null, count ++, nodes.size() - 1));
        }
    }

    private void addOneRow() {
        if (isEmpty()) addFirstElement();
        List<Edge> list = new Array<>();
        for (int i = 0; i < graph.get(0).size(); i ++)
            list.add(new Edge(null, graph.size() - 1, i));
        graph.add(list);
    }

    private void addFirstElement() {
        List<Edge> list = new Array<>();
        list.add(new Edge(null, 0, 0));
        graph.add(list);
    }

    private E directedRemoveEdge(int node1, int node2) {
        List<Edge> row = graph.get(node1);
        E value = row.get(node2).value;
        Edge edge = row.get(node2);
        edge.value = null;
        row.set(node2, edge);
        graph.set(node1, row);
        return value;
    }

    private void directedAddEdge(int node1, int node2, E edgeValue) {
        List<Edge> row = graph.get(node1);
        row.set(node2, new Edge(edgeValue, node1, node2));
        graph.set(node1, row);
    }

    private int countEdge() {
        int count = 0;
        for (List<Edge> list : graph) {
            for (Edge edge : list) {
                if (edge.value != null) count++;
            }
        }
        return count;
    }

    private Stack<Integer> getNeighboursStack(int node) {
        Stack<Integer> neighbours = new LinkedListStack<>();
        List<Edge> edges = graph.get(node);
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).value != null) neighbours.push(i);
        }
        return neighbours;
    }

    @Override
    public boolean isDirected() {
        return directed;
    }

    @Override
    public Integer indexOf(V nodeValue) {
        return nodes.indexOf(nodeValue);
    }

    @Override
    public V get(int node) {
        return nodes.get(node);
    }

    @Override
    public void addNode(V nodeValue) {
        nodes.add(nodeValue);
        addOneColumn();
        addOneRow();
    }

    @Override
    public boolean addEdge(int node1, int node2, E edgeValue) {
        if (!containsNode(node1) || !containsNode(node2)) return false;
        directedAddEdge(node1, node2, edgeValue);
        if (!directed) directedAddEdge(node2, node1, edgeValue);
        return true;
    }

    @Override
    public E removeEdge(int node1, int node2) {
        if (!containsNode(node1) || !containsNode(node2)) return null;
        E value = directedRemoveEdge(node1, node2);
        if (!directed) directedRemoveEdge(node2, node1);
        return value;
    }

    @Override
    public E getEdge(int node1, int node2) {
        if (!containsNode(node1) || !containsNode(node2)) return null;
        List<Edge> row = graph.get(node1);
        return row.get(node2).value;
    }

    @Override
    public boolean isEmpty() {
        return nodes.isEmpty();
    }

    @Override
    public int nodeSize() {
        return nodes.size();
    }

    @Override
    public int edgeSize() {
        int number = countEdge();
        if (directed) return number;
        return number / 2;
    }

    @Override
    public boolean isConnected(int node1, int node2) {
        if (!containsNode(node1) || !containsNode(node2)) return false;
        return graph.get(node1).get(node2).value != null;
    }

    @Override
    public boolean containsNode(int node) {
        return node < nodes.size() && node >= 0;
    }

    @Override
    public Queue<Integer> getNeighbours(int node) {
        Queue<Integer> neighbours = new LinkedListQueue<>();
        List<Edge> edges = graph.get(node);
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).value != null) neighbours.offer(i);
        }
        return neighbours;
    }

    @Override
    public Queue<Integer> breadthFirst(int node) {
        if (!containsNode(node)) return null;
        Queue<Integer> result = new LinkedListQueue<>();
        boolean[] visited = new boolean[graph.size()];
        Queue<Integer> queue = new LinkedListQueue<>();
        queue.offer(node);
        result.offer(node);
        while (!queue.isEmpty()) {
            for (Integer neighbour : getNeighbours(queue.poll())) {
                if (!visited[neighbour]) {
                    queue.offer(neighbour);
                    visited[neighbour] = true;
                    result.offer(neighbour);
                }
            }
        }
        return result;
    }

    @Override
    public Queue<Integer> depthFirst(int node) {
        if (!containsNode(node)) return null;
        Queue<Integer> result = new LinkedListQueue<>();
        boolean[] visited = new boolean[graph.size()];
        for (boolean b : visited) b = false;
        Stack<Integer> stack = new LinkedListStack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            Integer current = stack.pop();
            if (visited[current]) continue;
            result.offer(current);
            visited[node] = true;
            Stack<Integer> neighbours = getNeighboursStack(current);
            while (!stack.isEmpty()) {
                Integer neighbour = neighbours.pop();
                if (!visited[neighbour]) stack.push(neighbour);
            }
        }
        return result;
    }

    /**
     * currently supports undirected graph only.
     * @return
     */
    @Override
    public Queue<Integer> minSpanningTree() {
        if (!directed) return lazyPrim();
        else throw new NotImplementedException();
    }

/*    private Queue<Integer> Kruskal() {
        Queue<Integer> result = new LinkedListQueue<>();
        if (nodes.isEmpty()) return result;
        UnionFind<Integer, Void, Integer> unionFind = new UnionFindF<>();
        Tree<Edge, Void> heap = new Heap<>((o1, o2) -> o2.value.compareTo(o1.value));
        if (!nodes.isEmpty()) unionFind.add(0, 0);
        for (int i = 0; i < graph.size(); i ++) {
            unionFind.add(i, i);
            for (int j = 0; j < i; j ++) {
                Edge edge = graph.get(i).get(j);
                if (edge.value != null)
                    heap.insert(edge);
            }
        }
        if (heap.isEmpty()) return result;
        Edge edge = heap.deleteARI();
        result.offer(edge.start);
        while (!heap.isEmpty()) {
            if (unionFind.isConnected(edge.start, edge.end))
                continue;
            result.offer(edge.end);
            unionFind.unionElements(edge.start, edge.end);
            edge = heap.deleteARI();
        }
        return result;
    }*/

    private Queue<Integer> lazyPrim() {
        Queue<Integer> result = new LinkedListQueue<>();
        if (nodes.isEmpty()) return result;
        int node = 0;
        Tree<Edge, Void> heap = new Heap<>((o1, o2) -> o2.value.compareTo(o1.value));
        boolean[] cutter = new boolean[nodes.size()];
        do {
            result.offer(node);
            cutter[node] = true;
            for (Edge edge : graph.get(node))
                if (edge.value != null)
                    heap.insert(edge);
            do {
                Edge smallest = heap.deleteARI();
                node = smallest.end;
            } while (cutter[node]);
        } while (!heap.isEmpty());
        return result;
    }

    @Override
    public Queue<Integer> shortesdPath(int node) {
        return null;
    }

    @Override
    public Iterator<Integer> neighbourIterator(int startNode) {
        return new NeighbourIterator(startNode);
    }

    @Override
    public Iterator<Integer> graphBFIterator(int startNode) {
        return null;
    }

    @Override
    public Iterator<Integer> graphDFIterator(int startNode) {
        return null;
    }

    @Override
    public Iterator<Pair<V, E>> iterator() {
        return null;
    }
}
