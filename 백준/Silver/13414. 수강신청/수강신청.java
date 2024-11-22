import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        Set<String> set = new LinkedHashSet<>();
        for (int i = 0; i < l; i++) {
            String num = br.readLine();

            if (set.contains(num)) set.remove(num);
            set.add(num);
        }

        Iterator<String> iterator = set.iterator();
        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (iterator.hasNext()) {
            if (count++ == k) break;
            sb.append(iterator.next()).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}

/**
 * 실버 3 13414 수강신청
 *
 * 대기목록의 중복 학생을 처리하기 위해 stack 저장 -> 역순으로 map에 순위 기록
 *
 * 출력시에도 순서유지한채로 출력해야 하는 듯
 *
 * ---------------------------------------------------
 * LinkedHashSet 사용
 */
