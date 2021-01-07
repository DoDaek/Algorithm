import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BAEKJOON_Gold_4_내려가기 {
    private static int MAX;
    private static int MIN;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        MAX = 0;
        MIN = 0;
        getMaxPoint(N, map);
        getMinPoint(N, map);

        System.out.println(MAX + " " + MIN);

        br.close();
    }

    private static void getMaxPoint(int N, int[][] map) {
        int[][] dp = new int[N][3];
        for (int i = 0; i < N; i++) {
            dp[i] = map[i].clone();
        }

        for (int i = 1; i < N; i++) {
            dp[i][0] += Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] += Math.max(Math.max(dp[i - 1][0], dp[i - 1][1]), dp[i - 1][2]);
            dp[i][2] += Math.max(dp[i - 1][1], dp[i - 1][2]);
        }

        MAX = Math.max(Math.max(dp[N - 1][0], dp[N - 1][1]), dp[N - 1][2]);
    }

    private static void getMinPoint(int N, int[][] map) {
        int[][] dp = new int[N][3];
        for (int i = 0; i < N; i++) {
            dp[i] = map[i].clone();
        }

        for (int i = 1; i < N; i++) {
            dp[i][0] += Math.min(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] += Math.min(Math.min(dp[i - 1][0], dp[i - 1][1]), dp[i - 1][2]);
            dp[i][2] += Math.min(dp[i - 1][1], dp[i - 1][2]);
        }

        MIN = Math.min(Math.min(dp[N - 1][0], dp[N - 1][1]), dp[N - 1][2]);
    }
}
