import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N], B = new int[N], C = new int[N], D = new int[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        Map<Integer, Integer> CD = new HashMap<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!CD.containsKey(C[i] + D[j])) {
                    CD.put(C[i] + D[j], 1);
                } else {
                    CD.put(C[i] + D[j], CD.get(C[i] + D[j])+1);
                }
            }
        }

        long ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (CD.containsKey(-A[i]-B[j])) {
                    ans += CD.get(-A[i]-B[j]);
                }
            }
        }
        System.out.println(ans);
    }

}