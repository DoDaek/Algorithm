import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BAEKJOON_Gold_1_제곱_ㄴㄴ_수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long min = Long.parseLong(st.nextToken());
        long max = Long.parseLong(st.nextToken());

        boolean[] isSquare = new boolean[(int) (max - min) + 1];

        for (long i = 2; i * i <= max; i++) {
            long square = i * i;
            long start = min / square == 0 ? min / square : min / square + 1;
            for (long j = start; square * j <= max; j++) {
                isSquare[(int) (square * j - min)] = true;
            }
        }

        int count = 0;
        for (int i = 0; i < isSquare.length; i++) {
            if (!isSquare[i]) {
                count += 1;
            }
        }

        System.out.println(count);
    }
}
