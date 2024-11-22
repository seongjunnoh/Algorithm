import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String start = st.nextToken();
        String end = st.nextToken();
        String realEnd = st.nextToken();

        Set<String> enter = new HashSet<>();
        Set<String> exit = new HashSet<>();

        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) break;

            st = new StringTokenizer(line);
            String time = st.nextToken();
            String name = st.nextToken();

            // 입장 관리
            if (time.compareTo(start) <= 0) enter.add(name);

            // 퇴장 관리
            if (time.compareTo(end) >= 0 && time.compareTo(realEnd) <= 0) exit.add(name);
        }

        // enter, exit 에 모두 존재하는 학생만이 출석 인정
        int count = 0;
        Iterator<String> iterator = enter.iterator();
        while (iterator.hasNext()) {
            if (exit.contains(iterator.next())) count++;
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
