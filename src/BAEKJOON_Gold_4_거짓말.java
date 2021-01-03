import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BAEKJOON_Gold_4_거짓말 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 사람의 수
        int M = Integer.parseInt(st.nextToken()); // 파티의 수

        st = new StringTokenizer(br.readLine());
        int truthCount = Integer.parseInt(st.nextToken()); // 진실을 아는 사람의 수
        Queue<Integer> truthList = new LinkedList<>();
        for (int i = 0; i < truthCount; i++) {
            truthList.add(Integer.parseInt(st.nextToken()));
        }

        ArrayList<ArrayList<Integer>> parties = new ArrayList<>();
        ArrayList<Integer> party;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int partyCount = Integer.parseInt(st.nextToken());

            party = new ArrayList<>();
            for (int j = 0; j < partyCount; j++) {
                party.add(Integer.parseInt(st.nextToken()));
            }

            parties.add(party);
        }

        int count = countOfLies(M, truthList, parties);
        System.out.println(count);
    }

    private static int countOfLies(int M, Queue<Integer> truthList, ArrayList<ArrayList<Integer>> parties) {
        boolean[] cannotLie = new boolean[M];

        while (!truthList.isEmpty()) {
            int person = truthList.poll();

            for (int i = 0; i < M; i++) {
                if (!cannotLie[i]) {
                    if (parties.get(i).contains(person)) {
                        truthList.addAll(parties.get(i));
                        cannotLie[i] = true;
                    }
                }
            }
        }

        int count = 0;
        for (int i = 0; i < M; i++) {
            if (!cannotLie[i]) {
                count += 1;
            }
        }

        return count;
    }
}
