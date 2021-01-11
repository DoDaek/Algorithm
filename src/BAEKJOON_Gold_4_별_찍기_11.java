import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class BAEKJOON_Gold_4_별_찍기_11 {

    private static int N;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        BFS(0, N);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 1; j < N * 2; j++) {
                if (visited[i][j]) {
                    sb.append("*");
                } else {
                    if (i % 3 == 2 && (visited[i - 1][j])) {
                        sb.append("*");
                    } else {
                        sb.append(" ");
                    }
                }
            }
            sb.append("\n");
        }

        bw.write(sb.toString());

        bw.close();
        br.close();
    }

    private static void BFS(int y, int x) {
        int[][] move = {
                {1, -1}, {1, 1}
        };
        visited = new boolean[N][N * 2];
        visited[y][x] = true;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{y, x});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            if (!visited[current[0]][current[1]]) continue;

            int ny, nx;
            for (int i = 0; i < 2; i++) {
                ny = current[0] + move[i][0];
                nx = current[1] + move[i][1];

                if (isInArr(ny, nx)) {
                    if (!visited[ny][nx]) {
                        queue.add(new int[]{ny, nx});
                        visited[ny][nx] = true;
                    } else if (visited[ny][nx] && ny % 3 == 0) {
                        visited[ny][nx] = false;
                    }
                }
            }
        }
    }

    private static boolean isInArr(int y, int x) {
        return ((0 <= y && y < N) && (0 <= x && x < (N * 2)));
    }
}
