import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edge{
        int tg, time, money;

        public Edge(int tg, int time, int money) {
            this.tg = tg;
            this.time = time;
            this.money = money;
        }
    }

    static final int INF = 1000000000;
    static int n, limitT, limitM;
    static List<Edge>[] list = new ArrayList[101];
    static int[] d = new int[101];

    static boolean DFS(int cur, int time, int money){

        if(cur == n)
            return true;

        boolean result = false;

        for(Edge ne: list[cur]){
            int next = ne.tg;
            int nTime = time + ne.time;
            int nMoney = money + ne.money;

            if(nTime > limitT || nMoney > limitM) continue;

            result |= DFS(next, nTime, nMoney);

            if(result)
                if(d[next] > nMoney)
                    d[next] = nMoney;
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        limitT = Integer.parseInt(st.nextToken());
        limitM = Integer.parseInt(st.nextToken());

        for(int i=1; i<=n; i++){
            list[i] = new ArrayList<>();
            d[i] = INF;
        }

        int l = Integer.parseInt(br.readLine());

        for(int i=0; i<l; i++){
            st = new StringTokenizer(br.readLine());

            int h = Integer.parseInt(st.nextToken());
            int tg = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            int money = Integer.parseInt(st.nextToken());

            list[h].add(new Edge(tg, time, money));
            list[tg].add(new Edge(h, time, money));
        }
        d[1] = 0;
        DFS(1, 0, 0);

        System.out.println(d[n] == INF? -1:d[n]);
    }
}