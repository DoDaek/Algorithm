import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BAEKJOON_Gold_3_트리의_지름 {
    private static LinkedList<Edge>[] tree;
    private static boolean[] visited;
    private static int vertex;
    private static int farthest;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int V = Integer.parseInt(br.readLine());

        tree = new LinkedList[V + 1];
        for (int i = 0; i <= V; i++) {
            tree[i] = new LinkedList<>();
        }

        for (int i = 1; i <= V; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());

            while (true) {
                int next = Integer.parseInt(st.nextToken());
                if (next == -1) {
                    break;
                }

                int dist = Integer.parseInt(st.nextToken());
                tree[start].add(new Edge(next, dist));
                tree[next].add(new Edge(start, dist));
            }
        }

        visited = new boolean[V + 1];
        farthest = 0;
        int vertex = DFS(1, 0);

        visited = new boolean[V + 1];
        farthest = 0;
        DFS(vertex, 0);
        System.out.println(farthest);
    }

    private static int DFS(int start, int dist) {
        visited[start] = true;

        for (Edge edge : tree[start]) {
            if (!visited[edge.next]) {
                DFS(edge.next, dist + edge.dist);
            }
        }

        if (dist > farthest) {
            vertex = start;
            farthest = dist;
        }

        return vertex;
    }
}

class Edge {
    int next;
    int dist;

    public Edge(int next, int dist) {
        this.next = next;
        this.dist = dist;
    }
}
