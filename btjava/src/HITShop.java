import model.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class HITShop {
    private static final customer currentCustomer =
            new customer("C01", "Linh Nguyễn", "0900000001");
    private static final List<cartItem> cart = new ArrayList<>();

    public static void main(String[] args) {
        Product product1 = new Product("P01", "Áo thun", 150000, 20);
        Product product2 = new Product("P02", "Bình nước", 50000, 10);
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("1. Thêm mặt hàng");
            System.out.println("2. Xóa mặt hàng");
            System.out.println("3. Xem giỏ hàng");
            System.out.println("4. Xem tổng tiền");
            System.out.println("0. Thoát");
            System.out.print("Nhập lựa chọn: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println(product1.getId() + " | " + product1.getName());
                    System.out.println(product2.getId() + " | " + product2.getName());
                    System.out.print("Nhập mã sản phẩm: ");
                    String id = scanner.nextLine();
                    Product product;
                    if (id.equals("P01")) {
                        product = product1;
                    } else if (id.equals("P02")) {
                        product = product2;
                    } else {
                        System.out.println("Không tìm thấy sản phẩm.");
                        break;
                    }

                    System.out.print("Nhập số lượng: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();
                    addItem(product, quantity);
                    break;
                case 2:
                    printItems();
                    System.out.print("Nhập số thứ tự cần xóa: ");
                    int displayNumber = scanner.nextInt();
                    scanner.nextLine();
                    removeItem(displayNumber - 1);
                    break;
                case 3:
                    printItems();
                    break;
                case 4:
                    System.out.println("Tổng tiền: " + calculateTotal());
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (choice != 0);

        scanner.close();
    }

    static void addItem(Product product, int quantity) {
        if (quantity <= 0) {
            System.out.println("Số lượng phải lớn hơn 0.");
            return;
        }
        cart.add(new cartItem(product, quantity));
    }

    static void removeItem(int index) {
        if (index < 0 || index >= cart.size()) {
            System.out.println("Không tìm thấy mặt hàng.");
            return;
        }
        cart.remove(index);
    }

    static void printItems() {
        System.out.println("Khách hàng: " + currentCustomer);
        if (cart.isEmpty()) {
            System.out.println("Giỏ hàng đang trống.");
            return;
        }
        for (int i = 0; i < cart.size(); i++) {
            cartItem item = cart.get(i);
            Product product = item.getProduct();
            System.out.println((i + 1) + ". " + product.getId() + " | "
                    + product.getName() + " | " + product.getPrice()
                    + " | SL: " + item.getQuantity() + " | Thành tiền: " + item.getTotal());
        }
    }

    static double calculateTotal() {
        double total = 0;
        for (cartItem item : cart) {
            total += item.getTotal();
        }
        return total;
    }
}
