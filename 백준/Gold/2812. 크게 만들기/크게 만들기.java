import java.io.*;
import java.util.*;

class Solution_2812 {
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        char[] digits = br.readLine().toCharArray();

        Deque<Character> deque = new ArrayDeque<>();
        int toRemove = k;       // 남은 삭제 count

        for (char c : digits) {
            while (!deque.isEmpty() && toRemove > 0 && deque.peekLast() < c) {
                deque.pollLast();
                toRemove--;
            }

            deque.offerLast(c);
        }

        while (toRemove > 0) {      // 삭제할 count가 남아있다면
            deque.pollLast();
            toRemove--;
        }

        for (char c : deque) {
            sb.append(c);
        }
        System.out.println(sb);
        br.close();
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_2812 s = new Solution_2812();
        s.solution();
    }
}

/**
 * 완전 탐색으로 모든 경우의 수를 계산하면 시간 초과
 * 
 * deque 사용해서 구현
 */