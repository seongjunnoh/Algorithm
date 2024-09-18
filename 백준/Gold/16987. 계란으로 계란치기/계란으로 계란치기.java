import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {

    static int n;
    static int max = 0;

    static void dfs(int index, int broken, int[] s, int[] w) {
        if (index == n) {
            max = Math.max(max, broken);
            return;
        }

        // index 번째의 계란이 이미 깨진 상태이면 다음 계란으로 dfs
        if (s[index] <= 0) {
            dfs(index + 1, broken, s, w);
            return;
        }

        // index 위치의 계란으로 어떤 계란을 칠 지 정하기
        for (int i=0; i<n; i++) {
            // i 번째 계란이 이미 깨진 상태이면 다음 반복문으로
            if (i == index || s[i] <= 0) continue;

            // i 번째 계란을 치기로 결정
            s[index] -= w[i];
            s[i] -= w[index];

            int newBroken = broken;            // broken 값 보존하기 위해 local 변수 도입

            if (s[index] <= 0) newBroken++;
            if (s[i] <= 0) newBroken++;

            // i 번째 계란으로 다시 dfs
            dfs(index + 1, newBroken, s, w);

            // s, w 원상 복구
            s[index] += w[i];
            s[i] += w[index];
        }

        // 이번 차례에 깰 계란이 없으면 -> max 값 조정
        max = Math.max(max, broken);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        int[] s = new int[n];                   // 내구도
        int[] w = new int[n];                   // 무게

        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            s[i] = Integer.parseInt(st.nextToken());
            w[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0, s, w);

        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
        br.close();
    }
}