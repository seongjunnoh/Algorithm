import java.io.*;
import java.util.*;

class Solution_2437 {

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        // l ~ r 까지가 현재 측정가능한 무게의 범위
        int l = 0;
        int r = 0;
        for (int i = 0; i < n; i++) {
            int nL = l + arr[i];
            int nR = r + arr[i];

            if (nL - r <= 1) {
                r = nR;
            } else break;
        }

        System.out.println(r + 1);
        br.close();
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_2437 s = new Solution_2437();
        s.solution();
    }
}

/**
 * 추들의 합으로 만들 수 없는 최소의 양의 정수 구하기
 * 추들을 오름차순 정렬, 비교할 무게에 대해서 lowerBound 구하기 & lowerBound를 뺀 남은 무게에 대해서 계속 반복
 * -> 비교할 무게 1개에 대해서 n*logn 시간 걸림 -> 이걸 최대 몇번 반복 ??
 * -> 시간 초과
 * -----------------------------------------------------
 * 측정할 수 없는 무게 중 최소값을 구하는 것이 목적이므로 가벼운 추부터 고려해서 측정가능한 무게를 만들자
 * 추들을 오름차순 정렬 후, 비교가능한 무게 범위에 현재 추를 사용해서 추가로 비교할 수 있는 범위를 누적
 * -> 무게 범위가 끊어지는 부분이 발생하면 이게 정답
 */