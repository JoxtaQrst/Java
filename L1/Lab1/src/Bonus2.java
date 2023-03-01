import java.util.Scanner;

public class Bonus2 {
    public static void main(String[] argc) {
        int n, degree;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of the vertices ");
        n = input.nextInt();
        System.out.println("Enter the number of the degree ");
        degree = input.nextInt();

        int[][] adjMatrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((j - i) % n <= degree / 2) {
                    adjMatrix[i][j] = 1;
                    adjMatrix[j][i] = 1;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
