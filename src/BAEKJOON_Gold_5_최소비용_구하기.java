import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BAEKJOON_Gold_5_최소비용_구하기 {
    private static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(start).add(new Node(end, cost));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int shortest = dijkstra(N, graph, start, end);
        System.out.println(shortest);

        br.close();
    }

    private static int dijkstra(int N, ArrayList<ArrayList<Node>> graph, int start, int end) {
        int[] d = new int[N + 1];
        Arrays.fill(d, INF);
        d[start] = 0;

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (Node node : graph.get(current.end)) {
                if (node.cost != INF && d[node.end] > current.cost + node.cost) {
                    d[node.end] = current.cost + node.cost;
                    queue.add(new Node(node.end, d[node.end]));
                }
            }
        }

        return d[end];
    }

    static class Node implements Comparable<Node> {
        int end;
        int cost;

        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
