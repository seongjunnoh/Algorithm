import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        String[] arr = new String[input.length()];

        for (int i = 0; i < input.length(); i++) {
            StringBuilder sb = new StringBuilder();
            for (int start = i; start < input.length(); start++) {
                sb.append(input.charAt(start));
            }
            arr[i] = sb.toString();
        }

        Arrays.sort(arr);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}

/**
 * 실버 4 11656 접미사 배열
 */
