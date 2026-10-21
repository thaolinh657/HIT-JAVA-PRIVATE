import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap ten hang: ");
        String tenHang =sc.nextLine();
        System.out.println("Nhap don gia: ");
        double giaCa = sc.nextInt();
        System.out.println("Nhap soluong: ");
        int soLuong = sc.nextInt();
        double thanhTien=giaCa*soLuong;
    }
}