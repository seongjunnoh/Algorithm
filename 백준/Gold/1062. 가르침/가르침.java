import java.io.*;
import java.util.*;

class Solution_1062 {

    int n, k;
    String[] arr;
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

        visit = new boolean[26];
        visit['a' - 'a'] = true;
        visit['n' - 'a'] = true;
        visit['t' - 'a'] = true;
        visit['i' - 'a'] = true;
        visit['c' - 'a'] = true;

        play(0, 0);

        System.out.println(max);
        br.close();
    }

    void play(int start, int get) {
        if (get == k - 5) {
            cal();
            return;
        }

        for (int i = start; i < 26; i++) {
            char c = (char) ((int) 'a' + i);
            if (!visit[c - 'a']) {
                visit[c - 'a'] = true;
                play(i + 1, get + 1);
                visit[c - 'a'] = false;
            }
        }
    }

    void cal() {
        int count = 0;

        for (int i = 0; i < n; i++) {
            String word = arr[i];

            boolean flag = true;
            for (int j = 0; j < word.length(); j++) {
                if (!visit[word.charAt(j) - 'a']) {
                    flag = false;
                    break;
                }
            }

            if (flag) count++;
        }

        max = Math.max(max, count);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_1062 s = new Solution_1062();
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
 *
 */
