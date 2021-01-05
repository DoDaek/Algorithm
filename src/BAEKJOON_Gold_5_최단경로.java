import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BAEKJOON_Gold_5_최단경로 {
    private static int INF = 2000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int K = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<Node>> grpah = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            grpah.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            grpah.get(u).add(new Node(v, w));
        }

        int[] shortest = dijkstra(V, K, grpah);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            if (shortest[i] == INF) {
                sb.append("INF");
            } else {
                sb.append(shortest[i]);
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
        br.close();
    }

    private static int[] dijkstra(int V, int K, ArrayList<ArrayList<Node>> graph) {
        int[] d = new int[V + 1];
        Arrays.fill(d, INF);
        d[K] = 0;

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(K, 0));

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (Node node : graph.get(current.end)) {
                if (current.weight + node.weight < d[node.end]) {
                    d[node.end] = current.weight + node.weight;
                    queue.add(new Node(node.end, d[node.end]));
                }
            }
        }

        return d;
    }
}

class Node implements Comparable<Node> {
    int end;
    int weight;

    public Node(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}