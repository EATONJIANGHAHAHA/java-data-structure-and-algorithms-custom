package DataStructures.Map.unionFind;

/**
 * 查询两个节点是否连接为O(1)
 */
public class ArrayUnionFind implements UnionFind{

    private int[] id;

    public ArrayUnionFind(int size) {
        id = new int[size];
        for (int i = 0; i < id.length; i ++) {
            id[i] = i; //表示从属于不同的集合
        }
    }

    private int find(int p) {
        if (!checkBound(p)) throw new IllegalArgumentException();
        return id[p];
    }

    private boolean checkBound(int p) {
        return p >= 0 && p < id.length;
    }

    @Override
    public int size() {
        return id.length;
    }

    /**
     * 查看元素P和元素Q是否从属同一个集合
     * @param p
     * @param q
     * @return
     */
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        if (!checkBound(p) && !checkBound(q)) throw new IllegalArgumentException();
        int setNumber = find(q);
        for (int i : id)
            if (i == setNumber) i = find(p);
    }
}
