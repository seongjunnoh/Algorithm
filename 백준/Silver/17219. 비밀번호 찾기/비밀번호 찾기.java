import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String key = st.nextToken();
            String val = st.nextToken();
            map.put(key, val);
        }

        for (int i = 0; i < m; i++) {
            String target = br.readLine();
            bw.write(map.get(target));
            bw.write("\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}

/**
 * 실버 4 17219 비밀번호 찾기
 */
