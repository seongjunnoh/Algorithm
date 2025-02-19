import java.io.*;
import java.util.*;

class Solution_1062_1 {

    int n, k;
    String[] arr;
    int[] bit;
    boolean[] visit;
    int max = 0;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine();
        }

        if (k < 5) {        // 예외처리
            System.out.println(0);
            br.close();
            return;
        } else if (k == 26) {
            System.out.println(n);
            br.close();
            return;
        }

        bit = new int[n];         // bit[i] : arr[i]의 비트 표현
        for (int i = 0; i < n; i++) {
            String cur = arr[i];
            int curBit = 0;
            for (int j = 0; j < cur.length(); j++) {
                curBit |= (1 << cur.charAt(j) - 'a');
            }
            bit[i] = curBit;
        }

        visit = new boolean[26];
        visit['a' - 'a'] = true;
        visit['n' - 'a'] = true;
        visit['t' - 'a'] = true;
        visit['i' - 'a'] = true;
        visit['c' - 'a'] = true;

        play(0, 5);

        System.out.println(max);
        br.close();
    }

    void play(int start, int get) {
        if (get == k) {
            cal();
            return;
        }

        for (int i = start; i < 26; i++) {
            if (!visit[i]) {
                visit[i] = true;
                play(i + 1, get + 1);
                visit[i] = false;
            }
        }
    }

    void cal() {
        int visitBit = 0;
        for (int i = 0; i < 26; i++) {
            if (visit[i]) {
                visitBit |= 1 << i;
            }
        }

        int count = 0;      // 현재 visit 로 읽을 수 있는 단어 개수
        for (int i = 0; i < n; i++) {
            if ((bit[i] & visitBit) == bit[i]) count++;
        }

        max = Math.max(max, count);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_1062_1 s = new Solution_1062_1();
        s.solution();
    }
}

/**
 * k개 글자만으로 단어 만들기
 *
 * a, n, t, i, c -> 일단 이거 5 글자 모르면 읽을 수 있는 단어는 0개
 * 나머지 알파벳 : 21개 -> 이것들 중 k-5개를 고르고, n개의 단어들 중 몇개를 읽을 수 있는지 체크
 * -> 이떄 최악의 경우는 21 choose 10 -> 352716 가지
 *
 * 총 시간복잡도 = 360000 * 50 * 15 = 270,000,000 (시간 초과) ??
 * -----------------------------------------
 * 백트래킹으로 미리 가지치기를 하니까 위와 같은 최악의 상황은 오지 않는듯
 * cf) 비트마스킹으로 풀면 시간복잡도가 360000 * 50 으로 준다 (매번 알파벳 비교 안해도 되므로)
 *
 */
