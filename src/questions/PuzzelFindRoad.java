package questions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class PuzzelFindRoad {

    public static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public static int m;
    public static int n;
    public static char[][] maze;
    public static boolean[][][] visit;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");
        m = Integer.parseInt(strs[0]);
        n = Integer.parseInt(strs[1]);
        maze = new char[m][n];
        visit = new boolean[m][n][1024];
        for (int i = 0; i < m; i++) {
            maze[i] = br.readLine().toCharArray();
        }

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (maze[i][j] == '2') {
                    res = bfs(i, j);
                }
            }
        }
        System.out.println(res);
    }

    private static int bfs(int x, int y) {
        LinkedList<Node> queue = new LinkedList<Node>();
        queue.add(new Node(x, y, 0, 0));
        visit[x][y][0] = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dir[i][0], ny = node.y + dir[i][1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n || maze[nx][ny] == '0') continue;
                if (maze[nx][ny] == '3') return node.step + 1;

                //无法开门
                if (maze[nx][ny] >= 'A' && maze[nx][ny] <= 'Z'
                        && ((node.keys & (1 << (maze[nx][ny] - 'A'))) == 0)) continue;

                int keys = node.keys;
                if (maze[nx][ny] >= 'a' && maze[nx][ny] <= 'z') {
                    keys = node.keys | (1 << (maze[nx][ny] - 'a'));
                }


                if (!visit[nx][ny][keys]) {
                    visit[nx][ny][keys] = true;
                    queue.offer(new Node(nx, ny, keys, node.step + 1));
                }
            }
        }
        return -1;
    }

    static class Node {
        int x;
        int y;
        int keys;
        int step;
        Node(int x, int y, int keys, int step) {
            this.x = x;
            this.y = y;
            this.keys = keys;
            this.step = step;
        }
    }
}

