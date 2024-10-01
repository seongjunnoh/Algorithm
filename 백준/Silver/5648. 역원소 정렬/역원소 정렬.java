import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        String[] input = new String[n];

        // 입력 이렇게 받는게 맞나??
        int idx = 0;
        while (st.countTokens() != 0) {
            input[idx] = st.nextToken();
            idx++;
        }

        while (idx < n) {
            st = new StringTokenizer(br.readLine());

            while (st.countTokens() != 0) {
                input[idx] = st.nextToken();
                idx++;
            }
        }

        for (int i = 0; i < n; i++) {
            String cur = input[i];
            Stack<Character> stack = new Stack<>();

            int length = cur.length();
            for (int j = 0; j < length; j++) {
                stack.push(cur.charAt(j));
            }

            StringBuilder sb = new StringBuilder();
            boolean isFirst = true;
            while (!stack.isEmpty()) {
                Character pop = stack.pop();
                if (isFirst && pop == '0') continue;

                sb.append(pop);
                isFirst = false;
            }

            input[i] = sb.toString();
        }

        // input 을 long 배열로 변경
        long[] inputToLong = new long[n];
        for (int i = 0; i < n; i++) {
            inputToLong[i] = Long.parseLong(input[i]);
        }

        Arrays.sort(inputToLong);

        int size = input.length;
        for (int i = 0; i < size; i++) {
            System.out.println(inputToLong[i]);
        }
        br.close();
    }
}

/**
 * 실버 5 5648 역원소 정렬
 */
