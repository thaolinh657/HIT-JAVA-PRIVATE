import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    static final List<String> names = new ArrayList<>();
    static final List<Double> prices = new ArrayList<>();
    static final List<Integer> quantities = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do{
            System.out.println("===============Menu=============");
            printMenu();
            System.out.println("================================");
            System.out.println("chon: ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice){
                case 1 -> {
                    System.out.println("Đang thêm");
                    addItem(sc);
                }
                case 2 -> xoaItem(sc);
                case 3 -> InGio();
                case 4 -> tinhTongTien();
                case 0 -> System.out.println("Kết thúc.");
                default -> System.out.println("Lựa chọn không hợp lệ.");
            }
        }while (choice != 0);
        sc.close();
    }
    static void printMenu() {
        System.out.println("1. Them san pham");
        System.out.println("2. Xoa san pham");
        System.out.println("3. Xem gio hang");
        System.out.println("4. Tinh tong tien");
        System.out.println("0. Thoat");
    }
    static void addItem(Scanner sc) {
        System.out.print("Nhap ten san pham: ");
        String name = sc.nextLine();
        System.out.print("Nhap gia san pham: ");
        double price = Double.parseDouble(sc.nextLine());
        System.out.print("Nhap so luong: ");
        int quantity = Integer.parseInt(sc.nextLine());
        names.add(name);
        prices.add(price);
        quantities.add(quantity);
        System.out.println("Them san pham thanh cong!");
    }
    static void InGio() {
        if (names.isEmpty()) {
            System.out.println("Gio hang trong!");
            return;
        }
        for (int i = 0; i < names.size(); i++) {
            System.out.println("Ten san pham: " + names.get(i) + " | Gia: " + prices.get(i) + " | SL: " + quantities.get(i)
            );
        }
    }
    static void xoaItem(Scanner sc) {
        if (names.isEmpty()) {
            System.out.println("Gio hang trong!");
            return;
        }
        System.out.print("Nhap ten san pham can xoa: ");
        String name = sc.nextLine();
        int index = names.indexOf(name);
        if (index != -1) {
            names.remove(index);
            prices.remove(index);
            quantities.remove(index);
            System.out.println("Xoa san pham thanh cong!");
        } else {
            System.out.println("Khong tim thay san pham!");
        }
    }
    static void tinhTongTien() {
        double tong = 0;
        for (int i = 0; i < names.size(); i++) {
            tong += prices.get(i) * quantities.get(i);
        }
        System.out.println("Tong tien gio hang: " + tong);
    }
}