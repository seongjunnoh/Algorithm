import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String start = st.nextToken();
        String end = st.nextToken();
        String realEnd = st.nextToken();

        Set<String> set = new HashSet<>();

        String line;
        int count = 0;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) break;

            st = new StringTokenizer(line);
            String time = st.nextToken();
            String name = st.nextToken();

            // 입장 관리
            if (time.compareTo(start) <= 0) set.add(name);

            // 퇴장 관리
            if (time.compareTo(end) >= 0 && time.compareTo(realEnd) <= 0) {
                if (set.contains(name)) {
                    count++;
                    set.remove(name);       // 중복 카운트 막기 위해서
                }
            }
        }

        System.out.println(count);
        br.close();
    }
}

/**
 * 실버 2 19583번 싸이버개강총회
 *
 * 임력 크기 = 10만
 *
 */
