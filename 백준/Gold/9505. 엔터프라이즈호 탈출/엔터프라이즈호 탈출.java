import java.util.*;
import java.io.*;

import static java.lang.Integer.parseInt;
import static java.lang.Long.MAX_VALUE;


public class Main {
    static long[][] map;

    static long[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = parseInt(br.readLine());

        StringTokenizer st;
        int K, W, H;
        Character type;
        long w;
        Map<Character, Long> costMap = new HashMap<>();
        int sx, sy;
        sx = sy = 0;
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            K = parseInt(st.nextToken());
            W = parseInt(st.nextToken());
            H = parseInt(st.nextToken());

            map = new long[H][W];
            dist = new long[H][W];

            while (K-- > 0) {
                st = new StringTokenizer(br.readLine());
                type = st.nextToken().charAt(0);
                w = Long.parseLong(st.nextToken());

                costMap.put(type, w);
            }

            String line;
            for (int y = 0; y < H; y++) {
                line = br.readLine();
                for (int x = 0; x < W; x++) {
                    Character key = line.charAt(x);

                    if (key.equals('E')) {
                        sx = x;
                        sy = y;
                        map[y][x] = 0;
                        continue;
                    }

                    map[y][x] = costMap.get(key);
                }
            }

            long result = dijkstra(sx, sy, W, H);
            sb.append(result).append("\n");
            costMap.clear();
        }

        System.out.print(sb);
        br.close();
    }

    static void initDist() {
        for (long[] longs : dist) Arrays.fill(longs, MAX_VALUE);
    }


    static long dijkstra(int sx, int sy, int W, int H) {
        initDist();
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(n -> n.w));
        dist[sy][sx] = 0;
        pq.offer(new Node(sx, sy, dist[sy][sx]));

        long minCost = MAX_VALUE;
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int nx, ny;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dist[cur.y][cur.x] < cur.w)
                continue;

            for (int i = 0; i < dx.length; i++) {
                nx = cur.x + dx[i];
                ny = cur.y + dy[i];

                if (nx < 0 || nx >= W || ny < 0 || ny >= H) {
                    minCost = Math.min(minCost, cur.w);
                    continue;
                }

                if (dist[ny][nx] > dist[cur.y][cur.x] + map[ny][nx]) {
                    dist[ny][nx] = dist[cur.y][cur.x] + map[ny][nx];
                    pq.offer(new Node(nx, ny, dist[ny][nx]));
                }
            }

        }

        return minCost;
    }


    static class Node {
        int x, y;
        long w;

        public Node(int x, int y, long w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }
    }
}