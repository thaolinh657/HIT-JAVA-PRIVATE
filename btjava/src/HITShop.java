import java.util.ArrayList;
import java.util.Scanner;

public class HITShop {
    static final ArrayList<String> names = new ArrayList<>();
    static final ArrayList<Double> prices = new ArrayList<>();
    static final ArrayList<Integer> quantities = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = -1;

        do {
            System.out.println("==========MENU===========");
            System.out.println("1. Thêm mặt hàng");
            System.out.println("2. Xóa mặt hàng");
            System.out.println("3. Xem giỏ hàng");
            System.out.println("4. Tính tổng tiền và xuất hóa đơn");
            System.out.println("5. Tìm mặt hàng theo tên");
            System.out.println("0. Kết thúc");
            System.out.println("=========================");
            System.out.print("Chọn chức năng: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Nhập tên hàng: ");
                    String name = sc.nextLine();
                    if (name.trim().isEmpty()) {
                        System.out.println("Tên mặt hàng không được để trống!");
                        continue;
                    }

                    System.out.print("Nhập đơn giá: ");
                    double price = sc.nextDouble();
                    if (price <= 0) {
                        System.out.println("Giá phải lớn hơn 0!");
                        sc.nextLine();
                        continue;
                    }

                    System.out.print("Nhập số lượng: ");
                    int quantity = sc.nextInt();
                    sc.nextLine();

                    if (quantity <= 0) {
                        System.out.println("Số lượng phải là số nguyên dương!");
                        continue;
                    }

                    addItem(name, price, quantity);
                }
                case 2 -> {
                    if (names.isEmpty()) {
                        System.out.println("Giỏ hàng trống!");
                        continue;
                    }
                    System.out.print("Nhập số thứ tự cần xóa: ");
                    int seq = sc.nextInt();
                    sc.nextLine();
                    removeItem(seq - 1);
                }
                case 3 -> printItems();
                case 4 -> printInvoice();
                case 5 -> {
                    System.out.print("Nhập tên mặt hàng cần tìm: ");
                    String searchName = sc.nextLine().trim();
                    searchByName(searchName);
                }
                case 0 -> System.out.println("Kết thúc.");
                default -> System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (choice != 0);

        sc.close();
    }
    static void addItem(String name, double price, int quantity) {
        names.add(name);
        prices.add(price);
        quantities.add(quantity);
        System.out.println("Thêm mặt hàng thành công!");
    }

    static void removeItem(int index) {
        if (index >= 0 && index < names.size()) {
            names.remove(index);
            prices.remove(index);
            quantities.remove(index);
            System.out.println("Xóa sản phẩm thành công!");
        } else {
            System.out.println("Không tìm thấy mặt hàng.");
        }
    }

    static void printItems() {
        if (names.isEmpty()) {
            System.out.println("Giỏ hàng trống!");
            return;
        }
        for (int i = 0; i < names.size(); i++) {
            System.out.println((i + 1) + ". Tên sản phẩm: " + names.get(i) + " | Giá: " + prices.get(i) + " | SL: " + quantities.get(i));
        }
    }
    static double calculateTotal() {
        double tong = 0;
        for (int i = 0; i < names.size(); i++) {
            tong += prices.get(i) * quantities.get(i);
        }
        return tong;
    }
    static void printInvoice() {
        double subtotal = calculateTotal();
        double vat = subtotal * 0.10;
        double shippingFee = names.isEmpty() ? 0 : 20000;
        double finalTotal = subtotal + vat + shippingFee;

        System.out.println("Tiền hàng: " + subtotal);
        System.out.println("VAT: " + vat);
        System.out.println("Phí vận chuyển: " + shippingFee);
        System.out.println("Tổng thanh toán: " + finalTotal);
    }

    static void searchByName(String searchName) {
        boolean found = false;
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equalsIgnoreCase(searchName)) {
                System.out.println((i + 1) + ". Tên sản phẩm: " + names.get(i) + " | Giá: " + prices.get(i) + " | SL: " + quantities.get(i));
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy mặt hàng.");
        }
    }
}