import java.io.*;
import java.util.StringTokenizer;

public class BAEKJOON_Gold_3_트리의_순회 {
    private static int[] postOrder;
    private static int[] rootIndex;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int[] inOrder = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            inOrder[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        postOrder = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            postOrder[i] = Integer.parseInt(st.nextToken());
        }

        rootIndex = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            rootIndex[inOrder[i]] = i;
        }

        sb = new StringBuilder();
        getPreOrder(1, n, 1, n);
        bw.write(sb.toString());

        bw.close();
        br.close();
    }

    private static void getPreOrder(int inOrderStart, int inOrderEnd, int postOrderStart, int postOrderEnd) {
        if (inOrderStart > inOrderEnd || postOrderStart > postOrderEnd) {
            return;
        }

        int root = postOrder[postOrderEnd];
        sb.append(root).append(" ");
        int left = rootIndex[root] - inOrderStart;

        getPreOrder(inOrderStart, rootIndex[root] - 1, postOrderStart, postOrderStart + left - 1);
        getPreOrder(rootIndex[root] + 1, inOrderEnd, postOrderStart + left, postOrderEnd - 1);
    }
}
