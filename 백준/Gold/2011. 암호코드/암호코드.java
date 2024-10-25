import java.io.*;

public class Main {

    static final int mod = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int n = input.length();
        int[] dp = new int[5001];       // dp[i] : input의 i번째 숫자까지 고려할 경우 가능한 해석 가짓수

        int num = Integer.parseInt(String.valueOf(input.charAt(0)));
        if (num == 0) {
            System.out.println("0");
            return;
        }
        dp[0] = 1;
        dp[1] = 1;
        int before = num;

        for (int i = 2; i <= n; i++) {
            num = Integer.parseInt(String.valueOf(input.charAt(i - 1)));

            if (num == 0) {     // before 가 1, 2 가 아니면 해석 x
                if (before != 1 && before != 2) {
                    System.out.println("0");
                    return;
                } else {
                    dp[i] = dp[i - 2];
                }
            } else {
                if (before == 0) dp[i] = dp[i - 1];
                else {      // 이전 수와 현재 수의 조합을 해석할 수 있으면 -> dp[i-1] + dp[i-2] , 없으면 -> dp[i-1]
                    if (1 <= before * 10 + num && before * 10 + num <= 26) dp[i] = (dp[i - 1] % mod + dp[i - 2] % mod) % mod;
                    else dp[i] = dp[i - 1];
                }
            }

            before = num;
        }

        System.out.println(dp[n] % mod);
        br.close();
    }
}

/**
 * 골드 5 2011 암호코드
 *
 * 한자리씩 끊어서 -> ok
 * 두개씩 묶어서 -> 26보다 크면 x
 *
 * 25114
 * 2 -> 1
 * 25 -> 2 해석 & {5} + {25} = 2
 * 251 -> 25 해석 & {5}  = 2
 * 2511 -> 251 해석 & {1} + {25} 해석 & {11} = 2 + 2 = 4
 * 25114 -> 2511 해석 & {4} + {251} 해석 & {14} = 4 + 2 = 6
 *
 * 예외처리 : 0 인 경우
 * 10, 20 인 경우만 가능
 *
 */
