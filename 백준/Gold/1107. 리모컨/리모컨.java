import java.io.*;
import java.util.*;

class Solution_1107 {

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Set<Integer> buttons = new HashSet<>();     // 고장나지 않은 버튼들
        for (int i = 0; i < 10; i++) {
            buttons.add(i);
        }

        int m = Integer.parseInt(br.readLine());
        if (m > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                buttons.remove(Integer.parseInt(st.nextToken()));
            }
        }

        int min = Math.abs(n - 100);        // 100에서 +, - 로만 움직인 경우
        
        if (min == 0) {
            System.out.println("0");
            br.close();
            return;
        }

        for (int i = 0; i <= 1_000_000; i++) {          // 0 ~ 백만 까지의 수 중에서 만들 수 있는 채널번호 찾기 & min update
            String num = String.valueOf(i);

            boolean flag = true;
            for (int j = 0; j < num.length(); j++) {
                int digit = Integer.parseInt(String.valueOf(num.charAt(j)));
                if (!buttons.contains(digit)) flag = false;
            }

            if (flag) {     // i 는 만들 수 있는 채널번호
                min = Math.min(min, num.length() + Math.abs(n - i));
            }
        }

        System.out.println(min);
        br.close();
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_1107 s = new Solution_1107();
        s.solution();
    }
}

/**
 * 숫자버튼만 고장
 *
 * 100에서 +, - 로만 움직여서 n에 도달하는 경우 vs 숫자버튼들로 채널 입력 후 +, - 로 움직여서 n에 도달하는 경우 중 최소값 구하기
 * -> 2번째 방법은 가능한 채널 번호 모두 만들어서 n과 비교
 * -> n이 최대 50만 이므로, 0 ~ 100만까지의 채널 번호 중 만들 수 있는 채널 번호를 만들어서 n과 비교하면 된다
 */