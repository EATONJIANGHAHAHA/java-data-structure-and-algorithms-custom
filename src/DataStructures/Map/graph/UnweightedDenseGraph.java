package DataStructures.Map.graph;

import DataStructures.Collections.Queue.LinkedListQueue;
import DataStructures.Collections.Queue.Queue;
import DataStructures.Collections.list.Array;

import DataStructures.Collections.list.List;
import DataStructures.Collections.stack.LinkedListStack;
import DataStructures.Collections.stack.Stack;
import DataStructures.Pair;
import com.sun.istack.internal.Nullable;

import java.util.Iterator;

/**
 * 无权稠密图，邻接矩阵实现。
 *
 * @param <V>
 * @param <E>
 */
public class UnweightedDenseGraph<V, E> implements Graph<V, E> {

    private List<V> nodes;
    private boolean directed;
    private List<List<Edge>> graph;

    private class Edge {

        boolean connected;
        E value;

        Edge(@Nullable E value) {
            this.value = value;
            connected = false;
        }

        Edge(boolean connected, @Nullable E value) {
            this.connected = connected;
            this.value = value;
        }
    }

    public UnweightedDenseGraph() {
        new UnweightedDenseGraph<>(false);
    }

    public UnweightedDenseGraph(boolean directed) {
        this.directed = directed;
        graph = new Array<>();
        nodes = new Array<>();
    }

    /**
     * 想当前矩阵中添加新的一列，即每一行最后添加一个新标记。
     */
    private void addOneColumn() {
        if (isEmpty()) addFirstElement();
        for (List<Edge> list : graph) list.add(new Edge(null));
    }

    /**
     * 向当前矩阵中添加新的一行，行数与矩阵的行数相等。
     *
     * @return
     */
    private void addOneRow() {
        if (isEmpty()) addFirstElement();
        List<Edge> list = new Array<>();
        for (int i = 0; i < graph.get(0).size(); i++) list.add(new Edge(null));
        graph.add(list);
    }


    private void addFirstElement() {
        List<Edge> list = new Array<>();
        list.add(new Edge(null));
        graph.add(list);
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

    private void directedAddEdge(int node1, int node2, E edgeValue) {
        List<Edge> row = graph.get(node1);
        row.set(node2, new Edge(true, edgeValue));
        graph.set(node1, row);
    }

    @Override
    public E removeEdge(int node1, int node2) {
        if (!containsNode(node1) || !containsNode(node2)) return null;
        E value = directedRemoveEdge(node1, node2);
        if (!directed) directedRemoveEdge(node2, node1);
        return value;
    }

    private E directedRemoveEdge(int node1, int node2) {
        List<Edge> row = graph.get(node1);
        E value = row.get(node2).value;
        Edge edge = row.get(node2);
        edge.connected = false;
        edge.value = null;
        row.set(node2, edge);
        graph.set(node1, row);
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

    private int countEdge() {
        int count = 0;
        for (List<Edge> list : graph) {
            for (Edge edge : list) {
                if (edge.connected) count++;
            }
        }
        return count;
    }

    @Override
    public boolean isConnected(int node1, int node2) {
        if (!containsNode(node1) || !containsNode(node2)) return false;
        return graph.get(node1).get(node2).connected;
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
            if (edges.get(i).connected) neighbours.offer(i);
        }
        return neighbours;
    }

    public Stack<Integer> getNeighboursStack(int node) {
        Stack<Integer> neighbours = new LinkedListStack<>();
        List<Edge> edges = graph.get(node);
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).connected) neighbours.push(i);
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
        return null;
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

    private void depthF(int node) {
        if (!containsNode(node)) return;
        Queue<Integer> result = new LinkedListQueue<>();
        Stack<Integer> pass = new LinkedListStack<>();
        Stack<Iterator<Integer>> its = new LinkedListStack<>();
        boolean[] visited = new boolean[graph.size()];
        pass.push(node);
        its.push(neighbourIterator(node));
        Iterator<Integer> it = its.peek();
        Integer current = node;
        while (!pass.isEmpty()) {
            while (visited[current] && it.hasNext()) current = it.next(); //迭代器需要修改
            if (!visited[current]) { //todo: 检查
                visited[pass.peek()] = true;
                result.offer(pass.peek());
                pass.push(current);
                it = neighbourIterator(pass.peek());
                its.push(it);
            } else {
                pass.pop();
                its.pop();
                it = its.peek();
            }
        }
    }

    @Deprecated
    @Override
    public Queue<Integer> minSpanningTree() {
        return null;
    }

    @Deprecated
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
        return new UDGIterator();
    }

    private class UDGIterator implements Iterator<Pair<V, E>> {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Pair<V, E> next() {
            return null;
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
            while (next < graph.get(row).size() && !graph.get(row).get(next).connected) next++;
            return next != graph.get(row).size();
        }

        public Integer next() {
            return next > graph.get(row).size() ? null : next++;
        }
    }

    public static void main(String[] args) {
        int one = 1;
        int two = 2;
        System.out.println(one < two ? one++ : two++);
        System.out.println(one + " " + two);
    }
}
