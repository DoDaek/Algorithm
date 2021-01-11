import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BAEKJOON_Gold_4_치즈 {

    private static int N, M;
    private static int[][] paper;
    private static int[][] move = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int totalCheese = 0;

        paper = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
                if (paper[i][j] == 1) {
                    totalCheese += 1;
                }
            }
        }

        int time = 0;

        while (totalCheese > 0) {
            findExternalSpace();
            totalCheese -= countMeltedCheese();
            time += 1;
        }

        bw.write(String.valueOf(time));

        bw.close();
        br.close();
    }

    private static int countMeltedCheese() {
        int count = 0;

        int externalSpace;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                externalSpace = 0;
                if (paper[i][j] == 1) {
                    int y, x;
                    for (int k = 0; k < 4; k++) {
                        y = i + move[k][0];
                        x = j + move[k][1];

                        if (isInPaper(y, x) && paper[y][x] == 2) {
                            externalSpace += 1;
                        }
                    }

                    if (externalSpace >= 2) {
                        count += 1;
                        paper[i][j] = 0;
                    }
                }
            }
        }

        return count;
    }

    private static void findExternalSpace() {
        int[][] startPoints = {
                {0, 0}, {N - 1, 0}, {0, M - 1}, {N - 1, M - 1}
        };

        boolean[][] visited = new boolean[N][M];

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            queue.add(startPoints[i]);
            visited[startPoints[i][0]][startPoints[i][1]] = true;

            while (!queue.isEmpty()) {
                int[] current = queue.poll();

                paper[current[0]][current[1]] = 2;

                int ny, nx;
                for (int k = 0; k < 4; k++) {
                    ny = current[0] + move[k][0];
                    nx = current[1] + move[k][1];

                    if (isInPaper(ny, nx) && (paper[ny][nx] == 0 || paper[ny][nx] == 2) && !visited[ny][nx]) {
                        queue.add(new int[]{ny, nx});
                        visited[ny][nx] = true;
                    }
                }
            }
        }
    }

    private static boolean isInPaper(int y, int x) {
        return ((0 <= y && y < N) && (0 <= x && x < M));
    }
}