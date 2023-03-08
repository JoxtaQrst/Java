import java.util.Scanner;
import java.util.InputMismatchException;
public class Homework {

    public static void main(String[] argc){
        //LATIN MATRIX
        Scanner input = new Scanner(System.in);
        int number=0;
        try{
            int integer= Integer.parseInt(argc[0]);
            System.out.println("You entered an integer " + integer);
            number=integer;
        }
        catch (Exception e)
        {
            System.out.println("Not an integer!");
        }
        int k=number + 1; //rotation point
        int i=0;
        int[][] LatinMatrix = new int[number][number];
        long startTime= System.nanoTime();
        for(int b =1;b<=number;b++,i++)
        {
            int temp=k;
            int j=0;
            while(temp<=number)
            {
                //System.out.printf(temp + " ");
                LatinMatrix[i][j]=temp;
                temp++;
                j++;
            }

            for(int a=1;a<k;a++)
            {
                //System.out.printf(a + " ");
                LatinMatrix[i][j]=a;
                j++;
            }
            k--;
        }
        long endTime= System.nanoTime();
        long elapsedTime=endTime-startTime;
        if(number>30_000)
        {
            System.out.println("Elapsed time " + elapsedTime);
        }
        else {
            for(int c=0;c<LatinMatrix.length;c++)
            {
                String line=" ";
                System.out.printf("Line " + c);
                for(int j=0;j<LatinMatrix[c].length;j++)
                {
                    line=line+LatinMatrix[c][j];
                }
                System.out.println(line);
            }
            System.out.println();
            for(int j=0;j<LatinMatrix.length;j++)
            {
                String column=" ";
                System.out.printf("Column " + j);
                for(int c=0;c<LatinMatrix[j].length;c++)
                {
                    column=column+LatinMatrix[c][j];
                }
                System.out.println(column);
            }
        }
    }
}
