import java.util.Scanner;

public class HITShop {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ten hang: ");
        String tenHang =sc.nextLine();
        System.out.print("Nhap don gia: ");
        double giaCa = sc.nextInt();
        System.out.print("Nhap soluong: ");
        int soLuong = sc.nextInt();
        double thanhTien=giaCa*soLuong;
        System.out.println("Thanh Tien: " + thanhTien);
        double VAT_RATE = 0.1;
        int SHIPPING_FEE=20000;
        double tong = thanhTien+VAT_RATE*thanhTien+SHIPPING_FEE;
        System.out.println("Tong thanh tien: " +tong);
    }
}
