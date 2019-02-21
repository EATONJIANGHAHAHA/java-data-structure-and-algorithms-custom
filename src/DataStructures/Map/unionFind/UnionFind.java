package DataStructures.Map.unionFind;

public interface UnionFind {

    int size();

    boolean isConnected(int p, int q);

    void unionElements(int p, int q);


}
