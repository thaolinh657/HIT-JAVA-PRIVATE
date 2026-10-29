import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap chieu dai: ");
        double chieuDai = sc.nextDouble();
        System.out.print("Nhap chieu rong: ");
        double chieuRong= sc.nextDouble();
        double cv =2*(chieuDai+chieuRong);
        double dt = (chieuDai*chieuRong);
        System.out.println("chieudai: "+ chieuDai);
        System.out.println("chieurong: " + chieuRong);
        System.out.println("Chu vi: " + cv);
        System.out.println("Dien Tich: " + dt);
    }
}