import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine();
        }

        Arrays.sort(arr, (s1, s2) -> {
            if (s1.length() == s2.length()) {
                // 길이 같으면, 숫자인 자릿수의 합 계산
                int sum1 = 0, sum2 = 0;
                for (int i = 0; i < s1.length(); i++) {
                    if (s1.charAt(i) >= '1' && s1.charAt(i) <= '9') {
                        sum1 += Integer.parseInt(String.valueOf(s1.charAt(i)));
                    }
                }

                for (int i = 0; i < s2.length(); i++) {
                    if (s2.charAt(i) >= '1' && s2.charAt(i) <= '9') {
                        sum2 += Integer.parseInt(String.valueOf(s2.charAt(i)));
                    }
                }

                if (sum1 == sum2) {
                    // 사전순으로 비교
                    return s1.compareTo(s2);
                }

                // 숫자인 자릿수의 합이 작은 것이 앞으로
                return sum1 - sum2;
            }

            // 길이 다르면, 짧은 것이 앞으로
            return s1.length() - s2.length();
        });

        for (int i = 0; i < n; i++) {
            sb.append(arr[i]).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}

/**
 * 실버 3 시리얼 번호 1431번
 */
