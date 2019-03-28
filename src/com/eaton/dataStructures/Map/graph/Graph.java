package com.eaton.dataStructures.Map.graph;

import com.eaton.dataStructures.Collections.Queue.Queue;
import com.eaton.dataStructures.Map.Map;

import java.util.Iterator;

public interface Graph<V, E> extends Map<V, E> {

    boolean isDirected();

    Integer indexOf(V nodeValue);

    V get(int node);

    void addNode(V nodeValue);
    boolean addEdge(int node1, int node2, E edgeValue);

    E removeEdge(int node1, int node2);

    E getEdge(int node1, int node2);

    boolean isEmpty();

    int nodeSize();

    int edgeSize();

    boolean isConnected(int node1, int node2);

    boolean containsNode(int node);

    Queue<Integer> getNeighbours(int node);

    Queue<Integer> breadthFirst(int node);

    Queue<Integer> depthFirst(int node);

    Queue<Integer> minSpanningTree();

    Queue<Integer> shortesdPath(int node);

    Iterator<Integer> neighbourIterator(int startNode);

    Iterator<Integer> graphBFIterator(int startNode);

    Iterator<Integer> graphDFIterator(int startNode);
}
