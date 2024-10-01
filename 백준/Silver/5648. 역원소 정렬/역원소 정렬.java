import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        List<Long> numbers = new ArrayList<>();     // 역순 변환된 숫자를 저장할 리스트

        // 입력받아서 numbers에 add
        while (numbers.size() < n) {
            while (st.hasMoreTokens()) {
                String reversedNum = new StringBuilder(st.nextToken()).reverse().toString();
                numbers.add(Long.parseLong(reversedNum));
                if (numbers.size() == n) break;
            }

            if (numbers.size() < n) {
                st = new StringTokenizer(br.readLine());
            }
        }

        Collections.sort(numbers);

        StringBuilder sb = new StringBuilder();
        for (Long num : numbers) {
            sb.append(num).append("\n");
        }
        System.out.println(sb);

        br.close();
    }
}

/**
 * 실버 5 5648 역원소 정렬
 */
