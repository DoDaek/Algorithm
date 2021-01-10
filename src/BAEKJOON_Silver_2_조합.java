import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class BAEKJOON_Silver_2_조합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        BigInteger numerator = BigInteger.ONE;
        BigInteger denominator = BigInteger.ONE;
        for (int i = 1; i <= m; i++) {
            numerator = numerator.multiply(BigInteger.valueOf(n - (i - 1)));
            denominator = denominator.multiply(BigInteger.valueOf(i));
        }

        bw.write(numerator.divide(denominator).toString());

        bw.close();
        br.close();
    }
}
