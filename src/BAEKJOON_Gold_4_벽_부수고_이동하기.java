import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BAEKJOON_Gold_4_벽_부수고_이동하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - 48;
            }
        }

        int distance = BFS(N, M, map);
        System.out.println(distance);

        br.close();
    }

    private static int BFS(int N, int M, int[][] map) {
        int[][] move = {
                {0, 1}, {0, -1}, {1, 0}, {-1, 0}
        };
        boolean[][][] visited = new boolean[N][M][2];

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 0, 1}); // TODO y 좌표, x 좌표, 깬 벽의 수, 총 이돌거리
        visited[0][0][0] = true;
        visited[0][0][1] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            if (current[0] == (N - 1) && current[1] == (M - 1)) { // TODO 목적지 도착
                return current[3];
            }

            int ny, nx;
            for (int i = 0; i < 4; i++) {
                ny = current[0] + move[i][0];
                nx = current[1] + move[i][1];

                if (!isInMap(N, M, ny, nx)) continue;

                if (map[ny][nx] == 1) { // TODO 도착한 곳이 벽일 때
                    if (current[2] == 0 && !visited[ny][nx][1]) { // TODO 이전까지 깬 벽 없이 이동했을 때
                        visited[ny][nx][1] = true;
                        queue.add(new int[]{ny, nx, 1, current[3] + 1});
                    }
                } else if (map[ny][nx] == 0) { // TODO 도착한 곳이 빈 곳일 떄
                    if (!visited[ny][nx][current[2]]) {
                        queue.add(new int[]{ny, nx, current[2], current[3] + 1});
                        visited[ny][nx][current[2]] = true;
                    }
                }
            }
        }

        return -1;
    }

    private static boolean isInMap(int N, int M, int y, int x) {
        return ((0 <= y && y < N) && (0 <= x && x < M));
    }
}
