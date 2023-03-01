
public class Main {
    public static void main(String[] argc) {
        String[] languages ={"C","C++","C#","Python","Go","Rust","JavaScript","PHP","Swift","Java"};
        System.out.println("Hello world!");
        int n=(int)(Math.random()*1_000_000);
        System.out.println(n);
        int nby3=n*3;
        System.out.println(nby3);

        String binaryn=Integer.toBinaryString(n);
        System.out.println(binaryn);

        nby3+=0b10101;
        System.out.println(nby3);

        nby3+=0xFF;
        System.out.println(nby3);

        nby3*=6;
        System.out.println(nby3);

        while(nby3>=10)
        {
            int newSum=0;
            while(nby3>0)
            {
                newSum+=nby3%10;
                nby3/=10;
            }
            nby3=newSum;
        }
        System.out.println(nby3);
        System.out.println("Willy-nilly, this semester i will learn " + languages[nby3]);
    }
}