import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {

    static int n;
    static int[] arr;           // arr[row] = col -> (row, col) 에 퀸이 위치
    static int result = 0;

    static void dfs(int row) {
        if (row == n) {
            // 모든 행을 다 채우면 return
            result++;
            return;
        }

        for (int i = 0; i < n; i++) {
            arr[row] = i;           // (row,i) 에 퀸 위치

            if (check(row)) {
                // (row, i) 에 퀸을 진짜 위치시킬 수 있는지 체크
                // 위치시킬 수 있으면 다음 행으로 넘어가서 dfs
                // 아니면 반복문 계속 수행(== (row, i+1)에 퀸 위치 & 진짜 위치할 수 있는지 확인)
                dfs(row + 1);
            }
        }
    }

    static boolean check(int row) {
        for (int i = 0; i < row; i++) {
            // 0 ~ row-1 행 까지 중 i 열에 퀸이 위치하면 (row, i) 에 퀸 위치할 수 X
            if (arr[i] == arr[row]) return false;

            // 열의 차와 행의 차가 같은 경우(== (row, i)와 대각선에 위치한 경우) -> (row, i) 에 퀸 위치할 수 X
            if (Math.abs(row - i) == Math.abs(arr[row] - arr[i])) return false;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        dfs(0);

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }
}