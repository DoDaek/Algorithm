import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BAEKJOON_Gold_4_트리의_지름 {
    private static ArrayList<ArrayList<Node>> tree;
    private static boolean[] visited;
    private static int farthestVertex;
    private static int farthest;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        tree = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }

        StringTokenizer st;
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            tree.get(parent).add(new Node(child, weight));
            tree.get(child).add(new Node(parent, weight));
        }

        visited = new boolean[n + 1];
        farthestVertex = 1;
        farthest = 0;

        getFarthestNode(1, 0);

        visited = new boolean[n + 1];

        getFarthestNode(farthestVertex, 0);

        System.out.println(farthest);

        br.close();
    }

    private static void getFarthestNode(int start, int weight) {
        visited[start] = true;

        if (weight > farthest) {
            farthestVertex = start;
            farthest = weight;
        }

        for (Node node : tree.get(start)) {
            if (!visited[node.end]) {
                getFarthestNode(node.end, weight + node.weight);
            }
        }
    }

    static class Node {
        int end;
        int weight;

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }
}
