/*
 어린이날 여행 20230321
 
 코딩나라는 1번부터 N번 도시와 M개의 단방향 도로가 존재하는 나라이다.
코딩 나라의 코코의 가족은 5월 5일 어린이날에 다른 도시로 1박 2일 여행을 가려고 한다.
여행지는 현재 코코의 가족의 차량에 남아있는 기름통의 칸을 모두 소모하여 갈 수 있는 도시로 여행지로 정하려고 한다.
코딩나라에서 모든 도시 간의 도로의 거리는 동일하며, 한 도시로부터 다른 도시까지는 정확히 한 칸의 기름이 소모된다.
코딩나라의 도시, 도로 정보와, 남은 기름통의 칸 수, 그리고 코코의 가족이 가 살고 있는 도시 번호가 주어졌을 때, 코코의 가족이 어린이날 여행지로 갈 수 있는 후보 도시들을 오름차순으로 출력하는 프로그램을 작성하라.

[예시 1]
다음은 예제 입력1을 시각화한 것이다.

image.png

코코의 집이 1번이고, 남은 기름통의 칸이 2라고 했을 때 :
1 -> 2 : 기름통 1칸 소모 (1 - 2)
1 -> 3 : 기름통 2칸 소모 (1 - 2 - 3)
1 -> 4 : 기름통 2칸 소모 (1 - 2 - 4)
로 남은 기름통의 수로 갈 수 있는 가장 먼  2개의 도시인 3, 4가 여행지의 후보가 되며, 오름차순으로 3, 4를 순서대로 출력한다.
2번 도시는 남은 기름통을 모두 소모하지 못하여, 여행지 후보에 포함되지 않는다.

[예시 2]
다음은 예제 입력2를 시각화한 것이다.

image.png

코코의 집이 1번이고, 남은 기름통의 칸이 2라고 했을 때 :
1 -> 2 : 기름통 1칸 소모 (1 - 2)
2번 도시는 남은 기름통을 모두 소모하지 못하여, 여행지 후보에 포함되지 않는다.
기름통을 모두 소모하여 갈 수 있는 도시가 없기 때문에, -1을 출력한다.

입력
첫번째 줄에 도시의 개수 N, 도로의 개수 M, 남은 기름통의 칸 수 K, 그리고 코코의 가족의 출발 도시 번호 P가 주어진다. (2 ≤N≤ 300,000, 1 ≤M≤ 1,000,000, 1 ≤K≤ 300,000, 1 ≤P≤N)
두번째 줄부터 M개의 줄에 걸쳐 두 개의 자연수 A, B가 공백으로 분리되어 주어진다. 이는 A부터 B로 향하는 단방향 도로가 존재한다는 의미이다.(1 ≤A,B≤N)
A와 B가 동일한 입력은 주어지지 않는다.

출력
코코의 가족이 남은 기름통을 모두 소모하여 갈 수 있는 여행지 후보의 도시들을 한 줄 에 하나씩 오름차순으로 출력한다.
남은 기름통을 모두 소모하여 갈 수 있는 여행지 후보가 없다면, -1을 출력한다.

입력 예시 1 
4 4 2 1 
1 2
2 3
3 4
2 4

출력 예시 1
3
4

입력 예시 2 
4 3 2 1
1 2
4 2
3 2

출력 예시 2
-1 
 */
//중요!!!!!!!!!!!!!!
package type.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class M2_2 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n, m, k, p; 
    // ≤ 300,000 인접행렬 사용 X -> 인접 리스트
    static ArrayList<Integer>al[]; 
    // K거리내로 방문 가능한 노드를 저장할 Arraylist
    static ArrayList<Integer>ans = new ArrayList<>(); 

    static class Node {
        int num; // 노드의 번호 
        int cnt; // 사용한 기름통의 개수
        //constructor
        Node(int num, int cnt){
            this.num = num;
            this.cnt = cnt; 
        }
    }

    static void bfs(int node) {
        // 1. queue 설정
        // 노드 번호 -> (노드번호, 여태까지 몇개의 기름통을 써서 왔는지)
        // 기름통의 사용량 == K -> 갈 수 있는 하나의 후보지를 찾았다!
        Queue<Node>q = new LinkedList<>();
        // 첫 노드 삽입 
        q.add(new Node(node, 0));

        // 2. visited
        int[] visited = new int[n+1];
        visited[node] = 1; 

        // bfs
        while(!q.isEmpty()) {
            // 맨앞의 노드 빼옴
            Node now = q.poll(); 

            // ** 조금 더 빠르게 끝내기 위한 코드 
            // k개수의 기름통이 넘어가는곳 == > 어차피 못가는 곳
            // 지금까지 사용된 기름통이 > K --> 종료
            if(now.cnt > k)
                return; 

            // K만큼을 사용해서 갈수있는 목적지 = 정답에 추가
            // 지금 노드까지 오는데에 사용된 기름통의 수 == k -> 하나의 후보지를 찾았다!
            if(now.cnt == k)
                ans.add(now.num);

            for(int i = 0; i < al[now.num].size(); i++) {
                // 방문체크 -> 중복방문 X 
                int next = al[now.num].get(i);
                if(visited[next] == 1)
                    continue;
                visited[next] = 1;
                // 다음 노드 : next 번호 , 지금 여기까지 오기 위해서 사용한 기름통 + 1
                q.add(new Node(next, now.cnt+1));
            }
        }

    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());

        // 인접리스트 init
        al = new ArrayList[n+1];
        for(int i = 0; i <= n; i++)
            al[i] = new ArrayList<>();

        // input
        for(int i = 0; i < m; i++) {
            int from, to;
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            // 인접리스트 -> 단방향 연결 
            al[from].add(to); 
        }
        // p번노드부터 시작하는 bfs
        // bfs -> K거리만큼 내에 있는 모든 노드를 저장. 
        bfs(p);

        // 출력 
        // 만약 후보지가 없으면 = -1 
        if(ans.size() == 0)
            System.out.println(-1);
        else {
            // 오름차순 출력 --> 정렬 
            Collections.sort(ans);
            for(int i = 0; i < ans.size(); i++) {
                System.out.println(ans.get(i));
            }
        }

    }
}