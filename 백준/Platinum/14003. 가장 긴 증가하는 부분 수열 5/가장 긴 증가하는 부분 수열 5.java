import java.io.*;
import java.util.*;

class Pair_14003 {
    int idx;        // arr에서의 idx 값
    int value;      // arr[idx]의 value

    Pair_14003(int idx, int value) {
        this.idx = idx;
        this.value = value;
    }
}

class Solution_14003 {
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Pair_14003[] lis = new Pair_14003[n];
        int[] idx = new int[n];     // idx[i] : arr[i] 원소가 lis에서 가지는 혹은 가졌던 idx 값
        lis[0] = new Pair_14003(0, arr[0]);
        idx[0] = 0;
        int end = 0;
        for (int i = 1; i < n; i++) {
            if (lis[end].value < arr[i]) {
                lis[end + 1] = new Pair_14003(i, arr[i]);
                idx[i] = end + 1;
                end++;
            } else {
                int l = 0;
                int r = end;
                while (l <= r) {
                    int mid = (l + r) / 2;
                    if (lis[mid].value >= arr[i]) r = mid - 1;
                    else l = mid + 1;
                }

                idx[i] = l;
                lis[l] = new Pair_14003(i, arr[i]);
            }
        }

//        for (int i = 0; i < n; i++) {
//            System.out.println("idx[" + i + "] = " + idx[i]);
//        }

        StringBuilder sb = new StringBuilder();
        sb.append(end + 1).append("\n");

        Stack<Integer> result = new Stack<>();          // 거꾸로 바꾸기 -> stack 발상
        for (int i = n - 1; i >= 0; i--) {
            if (idx[i] == end) {
                result.push(arr[i]);
                end--;
            }
        }

        while (!result.isEmpty()) {
            sb.append(result.pop()).append(" ");
        }
        System.out.println(sb);
        br.close();
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_14003 s = new Solution_14003();
        s.solution();
    }
}

/**
 * 가장 긴 증가하는 부분 수열의 길이 & 정답이 될 수 있는 가장 긴 증가하는 부분 수열 구하기
 * n이 최대 백만 -> 이분 탐색으로 교체해가면서 lis 길이 구해야 한다
 *
 * lis의 결과를 구하기 위해서 arr의 각 원소가 lis에서 가지는 or 이전에 가졌던 index값을 모두 기록
 * -> 모든 arr의 원소를 대상으로 lis 길이 구한 후, lis로 가능한 값을 체크
 *
 */
