package Lab3.Spiral;

public class Spiral {
    public void printCounterclockwise(int[][] tab) {
        int left = 0;
        int right = tab[0].length;
        int up = 0;
        int down = tab.length;

        int tb = 0;
        int rl = 0;

        while (up!=down && left!=right){ //dopuki sa jakeis elementy
            while (tb < down) { //od góry do dołu
                System.out.print(tab[tb][rl] + ", ");
                tb++;
            }
            tb--;
            left++;
            rl++;

            while (rl < right) { //od lewa do prawa
                System.out.print(tab[tb][rl] + ", ");
                rl++;
            }
            if(left == right)break;
            rl--;
            down--;
            tb--;

            while (tb > up) { //od dołu do góry
                System.out.print(tab[tb][rl] + ", ");
                tb--;
            }
            if(up == down)break;
            right--;

            while (rl > left) { //z prawa do lewa
                System.out.print(tab[tb][rl] + ", ");
                rl--;
            }
            up++;
        }
    }
}