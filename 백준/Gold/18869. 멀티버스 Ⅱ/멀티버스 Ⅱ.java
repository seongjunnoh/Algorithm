import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[][] universe = new int[m][n];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int[] space = new int[n];
            int[] temp = new int[n];
            for (int j = 0; j < n; j++) {
                space[j] = Integer.parseInt(st.nextToken());
                temp[j] = space[j];
            }

            // temp 배열 정렬 후, 좌표 압축
            Arrays.sort(temp);
            Map<Integer, Integer> map = new HashMap<>();
            int rank = 0;
            for (int j = 0; j < n; j++) {
                if (!map.containsKey(temp[j])) map.put(temp[j], rank++);
            }

            // arr[i] 에 rank 결과 입력
            for (int j = 0; j < n; j++) {
                universe[i][j] = map.get(space[j]);
            }
        }

        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                if (Arrays.equals(universe[i], universe[j])) result++;
            }
        }

        System.out.println(result);
        br.close();
    }
}

/**
 * 골드 5 18869 멀티버스 2
 *
 * 접근법???
 * -> 각 우주를 좌표압축(= 모든 원소에 rank 매기기) 시킨 후, m개의 우주 중 몇개의 쌍이 같은지 count
 */
