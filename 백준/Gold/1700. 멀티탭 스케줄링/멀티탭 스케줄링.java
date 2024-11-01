import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Set<Integer> multiTab = new HashSet<>();
        int[] orders = new int[k];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            orders[i] = Integer.parseInt(st.nextToken());
        }
        
        int count = 0;
        for (int i = 0; i < k; i++) {
            int machine = orders[i];
            int size = multiTab.size();
            
            if (multiTab.contains(machine)) continue;           // 이미 꽂혀 있으면 continue
            if (size < n) {          // 빈공간이 있으면 그냥 꽂으면 됨
                multiTab.add(machine);
                continue;
            }

            // 빈 공간이 없으면 멀티탭에서 하나를 뽑아야 함
            int latestUseIdx = -1;          
            int remove = -1;

            for (int plug : multiTab) {
                int nextUseIdx = Integer.MAX_VALUE;

                for (int j = i + 1; j < k; j++) {
                    if (orders[j] == plug) {
                        nextUseIdx = j;
                        break;
                    }
                }

                // 가장 나중에 사용되는 plug를 찾아서 remove (아예 사용되지 않으면 이게 remove가 됨)
                if (nextUseIdx > latestUseIdx) {            
                    latestUseIdx = nextUseIdx;
                    remove = plug;
                }
            }

            multiTab.remove(remove);
            multiTab.add(machine);
            count++;
        }

        System.out.println(count);
        br.close();
    }
}

/**
 * 골드 1 1700 멀티탭 스케줄링
 *
 * 주어진 순서대로 전기용품 사용할 때, 멀티탭에서 코드를 최소로 뺴도록 매번 어떤 플러그를 뽑을지 잘 선택해야 함
 *
 * 현재 멀티탭 빈 공간 있음 -> 빈 공간에 플러그 꼽음
 * 현재 멀티탭 빈 공간 없음 -> 플러그 뽑아야 함
 *                          => 어떤 플러그를 ??
 *
 * 앞으로 사용될 플러그는 최대한 안건드리는게 좋음
 * -------------------------------------------------------
 *
 * 멀티탭이 가득 차 있을 때,
 * 1. 멀티탭의 plug 중 앞으로 사용하지 않을 플러그가 있다면 해당 플러그를 뽑으면 됨
 * 2. 멀티탭의 모든 plug 를 재사용한다면, 이 중 가장 나중에 사용할 plug를 뽑으면 됨
 *      => 나중에 사용할 것을 뽑지않고 남겨놓으면, 멀티탭의 상태의 변경이 발생할 확률이 더 높아진다고 할 수 있음
 *
 */
