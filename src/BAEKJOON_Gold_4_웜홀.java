import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BAEKJOON_Gold_4_웜홀 {
    private static int INF = 200000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            ArrayList<ArrayList<Node>> graph = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());

                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());

                graph.get(S).add(new Node(E, T));
                graph.get(E).add(new Node(S, T));
            }

            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());

                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());

                graph.get(S).add(new Node(E, -T));
            }

            boolean backToTheFuture = bellmanFord(N, graph);
            System.out.println(backToTheFuture ? "NO" : "YES");
        }
    }

    private static boolean bellmanFord(int N, ArrayList<ArrayList<Node>> graph) {
        int[] d = new int[N + 1];
        Arrays.fill(d, INF);
        d[1] = 0;

        boolean updated = false;

        for (int i = 1; i <= N; i++) {
            updated = false;

            for (int j = 1; j <= N; j++) {
                for (Node node : graph.get(j)) {
                    if (d[node.end] > d[j] + node.weight) {
                        d[node.end] = d[j] + node.weight;
                        updated = true;
                    }
                }
            }

            if (!updated) {
                break;
            }
        }

        return !updated;
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
