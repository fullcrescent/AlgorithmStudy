package fullcrescent.백준.Gold.로봇_청소기;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 로봇_청소기 {
    static int N;
    static int M;
    static int[][] array;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s1 = br.readLine().split(" ");

        N = Integer.parseInt(s1[0]);
        M = Integer.parseInt(s1[1]);

        String[] s2 = br.readLine().split(" ");
        int x = Integer.parseInt(s2[0]);
        int y = Integer.parseInt(s2[1]);
        int direction = Integer.parseInt(s2[2]);
        direction = direction==1 ? 3 : direction==3 ? 1 : direction;

        array = new int[N][M];
        for(int i=0; i<N; i++) {
            String[] s3 = br.readLine().split(" ");

            for(int j=0; j<M; j++) {
                array[i][j] = Integer.parseInt(s3[j]);
            }
        }

        System.out.println(function(x, y, direction));
    }

    private static int function(int x, int y, int direction) {
        return new Robot(x, y, direction).clean();
    }

    static int[][] dir = {{-1,0}, {0, -1}, {1,0}, {0,1}};

    static class Robot {
        int x;
        int y;
        int direction;

        public Robot(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        public int clean() {
            array[this.x][this.y] = 2;
            int count = 1;

            while(true) {
                if(turn()) {
                    count++;
                }else if(!back()) {
                    break;
                }
            }

            return count;
        }

        public boolean turn() {
            int turnCount = 0;

            while(turnCount<4) {
                direction = (direction + 1) % 4;
                int dx = this.x + dir[direction][0];
                int dy = this.y + dir[direction][1];

                if(array[dx][dy]==0) {
                    array[dx][dy] = 2;
                    this.x = dx;
                    this.y = dy;
                    return true;
                }

                turnCount++;
            }

            return false;
        }

        public boolean back() {
            this.x += dir[(direction+2)%4][0];
            this.y += dir[(direction+2)%4][1];

            return array[this.x][this.y] != 1;
        }
    }
}
