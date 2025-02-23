import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // 빠른 입력을 위한 BufferedReader 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 용액의 개수 입력
        int n = Integer.parseInt(br.readLine());
        int[] lq = new int[n];
        
        // 용액의 값을 공백으로 구분하여 입력받음
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            lq[i] = Integer.parseInt(st.nextToken());
        }
        
        // 배열이 정렬되어 있지 않다면, 정렬을 진행합니다.
        // Arrays.sort(lq);
        
        // 현재까지 찾은 두 용액의 합 절댓값 중 최소값을 저장할 변수
        int minSumAbs = Integer.MAX_VALUE;
        // 최소 합을 이루는 두 용액을 저장할 배열
        int[] ans = new int[2];
        
        // 각 용액을 기준으로 다른 용액과의 합을 이분 탐색을 통해 확인합니다.
        for (int i = 0; i < n; i++) {
            // 이분 탐색을 위한 시작(left)과 끝(right) 인덱스 설정
            int left = i + 1;
            int right = n - 1;
            
            while (left <= right) {
                // 중간 인덱스 계산
                int mid = (left + right) / 2;
                // 현재 용액(lq[i])와 중간 용액(lq[mid])의 합
                int sum = lq[i] + lq[mid];
                
                // 현재 합의 절댓값이 최소값보다 작으면 결과 업데이트
                if (Math.abs(sum) < minSumAbs) {
                    minSumAbs = Math.abs(sum);
                    ans[0] = lq[i];
                    ans[1] = lq[mid];
                }
                
                // 합이 음수이면, 합을 크게 만들기 위해 left를 증가시킵니다.
                if (sum < 0) {
                    left = mid + 1;
                } else {
                    // 합이 0 이상이면, 합을 작게 만들기 위해 right를 감소시킵니다.
                    right = mid - 1;
                }
            }
        }
        
        // 최종적으로 두 용액의 값을 출력합니다.
        System.out.println(ans[0] + " " + ans[1]);
    }
}
