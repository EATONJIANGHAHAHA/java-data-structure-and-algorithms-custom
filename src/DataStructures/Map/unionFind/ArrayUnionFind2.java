package DataStructures.Map.unionFind;

public class ArrayUnionFind2 implements UnionFind {

    private int[] parrent;
    private int[] sz;

    public ArrayUnionFind2(int size) {
        parrent = new int[size];
        for (int i = 0; i < size; i ++) {
            parrent[i] = i;
        }
        sz = new int[size];
        for (int i : sz) i = 1;
    }

    @Override
    public int size() {
        return parrent.length;
    }

    private int find(int p) {
        if (!checkBound(p)) throw new IllegalArgumentException();
        while (p != parrent[p])
            p = parrent[p];
        return p;
    }

    private boolean checkBound(int p) {
        return p < parrent.length && p >= 0;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        if (!(checkBound(p) && checkBound(q))) throw new IllegalArgumentException();
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;
        if (sz[pRoot] < sz[qRoot]) {
            parrent[pRoot] = parrent[qRoot];
            sz[qRoot] += sz[pRoot];
        } else {
            parrent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
    }
}
