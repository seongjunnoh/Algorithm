import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Set<Integer> set = new HashSet<>();            // x+y 의 집합
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {              // x,y 는 같아도 됨
                set.add(arr[i] + arr[j]);
            }
        }

        Arrays.sort(arr, Collections.reverseOrder());
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (set.contains(arr[i] - arr[j])) {
                    System.out.println(arr[i]);
                    br.close();
                    return;
                }
            }
        }
    }
}

/**
 * 골드 4 2295 세 수의 합
 *
 * 3개의 수를 더해서 만들 수 있는 수 중 arr에 포함되는 가장 큰 수 구하기 (x+y+z = k)
 * -> x,y,z 를 모두 구하기 위해 3중 for loop => 시간 초과
 * -> x+y = z-k 로 식 변형 => 2중 for loop 2번으로 변경
 *
 * => x,y,z,k 는 서로 같아도 됨 !!!
 */
