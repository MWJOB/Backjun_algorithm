import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int white = 0;
	static int blue = 0;
	static int[][] paper;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		paper = new int[N][N];
		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		divied(0, 0, N);
		System.out.println(white);
		System.out.println(blue);
	}
	
	public static void divied(int x, int y, int n) {
		boolean flag = true;
		// 색종이 통일 되어 있는지 탐색
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				// 다른 색 하나 있을 경우
				if(paper[x][y] != paper[x + i][y + j]) {
					flag = false;
					break;
				}
				if(!flag) break;
			}
		}
		// 탐색한 영역이 한가지 색으로 통일된 경우
		if (flag) {
			if(paper[x][y] == 0) {
				white++;
			}else {
				blue++;
			}
		}else {
			divied(x, y, n / 2);
			divied(x + n / 2, y, n / 2);
			divied(x, y + n / 2, n / 2);
			divied(x + n / 2, y + n / 2, n / 2);
		}
    }
}