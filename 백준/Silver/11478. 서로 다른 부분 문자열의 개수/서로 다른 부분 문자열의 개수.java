import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        Set<String> set = new HashSet<>();
        for (int i = 0; i < line.length(); i++) {
            for (int j = i + 1; j <= line.length(); j++) {
                set.add(line.substring(i, j));
            }
        }

        System.out.println(set.size());
        br.close();
    }
}

/**
 * 실버3 11478번 서로 다른 부분 문자열의 개수
 *
 * 전체 부분 문자열을 이중 for loop 로 구하기 & set 으로 중복 제거해서 관리
 *
 */
