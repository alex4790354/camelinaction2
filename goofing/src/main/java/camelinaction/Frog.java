package camelinaction;


public class Frog {

    public static void main(String[] args) {

        int N = 3;

        int[] jumpNumbers = new int[2000000000];
        jumpNumbers[0] = 0;
        jumpNumbers[1] = -1;
        jumpNumbers[2] = -1;
        jumpNumbers[3] = 1;
        jumpNumbers[4] = 1;
        jumpNumbers[5] = 1;

        for (int i = 6; i < jumpNumbers.length; i++) {
            if ( i % 5 == 0) {
                jumpNumbers[i] = i/5;
            } else {
                jumpNumbers[i] = i/5 + 1;
            }
        }

        System.out.println(String.format("Minimum jump numbers: %s", jumpNumbers[N-2]));

    }

}
