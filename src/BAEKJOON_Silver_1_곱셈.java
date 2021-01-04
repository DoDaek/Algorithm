import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BAEKJOON_Silver_1_곱셈 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        long C = Long.parseLong(st.nextToken());

        System.out.println(pow(A % C, B, C));
    }

    private static long pow(long a, long b, long c) {
        if (b == 1) return a;

        long temp = pow(a, b / 2, c) % c;

        if (b % 2 == 0) {
            return (temp * temp) % c;
        } else {
            return (((temp * temp) % c) * a) % c;
        }
    }
}
