import java.io.*;
import java.util.*;

class Solution_1759 {

    Set<Character> mo = new HashSet<>();
    char[] arr;
    int l, c;
    Stack<Character> selected = new Stack<>();
    StringBuilder sb = new StringBuilder();

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        arr = new char[c];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < c; i++) {
            char input = st.nextToken().charAt(0);
            if (input == 'a' || input == 'e' || input == 'i' || input == 'o' || input == 'u') mo.add(input);

            arr[i] = input;
        }

        Arrays.sort(arr);         // 알파벳 오름차순 정렬

        back(0, 0);

        System.out.println(sb);
        br.close();
    }

    void back(int count, int startIdx) {      // 현재 count개 뽑았다
        if (count == l) {
            List<Character> list = new ArrayList<>(selected);
            check(list);
            return;
        }

        for (int i = startIdx; i < c; i++) {
            selected.push(arr[i]);
            back(count + 1, i + 1);
            selected.pop();
        }
    }

    void check(List<Character> list) {
        StringBuilder result = new StringBuilder();
        int jaCount = 0;
        int moCount = 0;
        for (Character c : list) {
            if (mo.contains(c)) moCount++;
            else jaCount++;

            result.append(c);
        }

        if (jaCount >= 2 && moCount >= 1) sb.append(result).append("\n");
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_1759 s = new Solution_1759();
        s.solution();
    }
}

/**
 * 골드 5 1759번 암호 만들기
 *
 * 모음먼저 뽑고, 자음 뽑기 -> 백트래킹으로 구현
 * 뽑은 l개의 문자들을 정렬된 문자열로 변환 & 변환한 문자열들을 다시 사전순 정렬 -> TreeSet 사용
 * -> 메모리 초과 -> 백트래킹을 쓸데없이 많이해서 그런듯,,,
 * ---------------------------------------------
 * 입력받은 모든 문자를 정렬 & 백트래킹으로 l개 뽑자
 * -> 뽑은 l자리 문자열이 정답조건 충족하면 ok, 아니면 no
 * -> 백트래킹 시 같은 수의 조합을 뽑지 않도록 주의 -> startIdx 도입
 */
