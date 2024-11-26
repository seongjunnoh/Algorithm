import java.io.*;
import java.util.*;

public class Main {

    static String line;
    static Set<String> set = new HashSet<>();
    static boolean[] visit;

    static void cal(int idx, int len) {        // idx : 부분수열 시작 idx, len : 부분수열의 길이
        if (idx + len > line.length()) return;

        StringBuilder sb = new StringBuilder();
        for (int i = idx; i < idx + len; i++) {
            sb.append(line.charAt(i));
        }
        set.add(sb.toString());
        
        cal(idx + 1, len);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        line = br.readLine();
        visit = new boolean[line.length()];

        for (int len = 1; len <= line.length(); len++) {
            cal(0, len);
        }

        System.out.println(set.size());
        br.close();
    }
}

/**
 * 실버3 11478번 서로 다른 부분 문자열의 개수
 *
 * 전체 부분 문자열을 재귀함수 로 구하기 & set 으로 중복 제거해서 관리
 *
 */
