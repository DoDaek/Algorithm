import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BAEKJOON_Gold_4_특정한_최단_경로 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int[][] graph = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(graph[i], 200000000);
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a][b] = c;
            graph[b][a] = c;
        }

        int[] distFromOne = dijkstra(N, graph, 1);

        st = new StringTokenizer(br.readLine());

        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int[] distFromV1 = dijkstra(N, graph, v1);
        int[] distFromV2 = dijkstra(N, graph, v2);

        int path1 = distFromOne[v1] + distFromV1[v2] + distFromV2[N];
        int path2 = distFromOne[v2] + distFromV2[v1] + distFromV1[N];

        if (path1 >= 200000000 && path2 >= 200000000) {
            System.out.println(-1);
        } else {
            System.out.println(Math.min(path1, path2));
        }
    }

    private static int[] dijkstra(int N, int[][] graph, int start) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, 200000000);
        dist[start] = 0;

        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });
        queue.add(new int[]{start, 0});

        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            int current = p[0];
            int distance = p[1];

            if (dist[current] < distance) {
                continue;
            }

            for (int i = 1; i <= N; i++) {
                int nextDistance = distance + graph[current][i];
                if (nextDistance < dist[i]) {
                    dist[i] = nextDistance;
                    queue.add(new int[]{i, nextDistance});
                }
            }
        }

        return dist;
    }
}
