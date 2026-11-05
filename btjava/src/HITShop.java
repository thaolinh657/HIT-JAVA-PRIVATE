import model.Product;
import model.customer;
import model.cartItem;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HITShop {
    private static final customer currentCustomer =
            new customer("C01", "Linh Nguyễn", "0900000001");
    private static final List<cartItem> cart = new ArrayList<>();
    private static final Product p1 = new Product("P01", "Áo thun", 150000, 20);
    private static final Product p2 = new Product("P02", "Bình nước", 50000, 10);
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String choice;
        System.out.println("so luong san pham "+ Product.getCount());
        do {
            System.out.println("========== MENU ==========");
            System.out.println("1. Thêm mặt hàng");
            System.out.println("2. Xóa mặt hàng");
            System.out.println("3. Xem giỏ hàng");
            System.out.println("4. Xem tổng tiền");
            System.out.println("0. Thoát");
            System.out.println("==========================");
            System.out.print("Chọn chức năng: ");
            choice = sc.nextLine().trim();

            switch (choice) {
                case "1" -> handleAdd(sc);
                case "2" -> handleRemove(sc);
                case "3" -> printItems();
                case "4" -> handleTotal();
                case "0" -> System.out.println("Chương trình kết thúc.");
                default -> System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (!choice.equals("0"));

        sc.close();
    }
    private static void handleAdd(Scanner sc) {
        System.out.println("Sản phẩm có sẵn:");
        System.out.println("- " + p1.getId() + ": " + p1.getName());
        System.out.println("- " + p2.getId() + ": " + p2.getName());

        System.out.print("Nhập mã sản phẩm: ");
        String id = sc.nextLine().trim();

        Product selectedProduct = null;
        if (id.equalsIgnoreCase(p1.getId())) {
            selectedProduct = p1;
        } else if (id.equalsIgnoreCase(p2.getId())) {
            selectedProduct = p2;
        }

        if (selectedProduct == null) {
            System.out.println("Không tìm thấy sản phẩm.");
            return;
        }

        System.out.print("Nhập số lượng: ");
        int qty;
        try {
            qty = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Số lượng phải lớn hơn 0.");
            return;
        }

        if (qty <= 0) {
            System.out.println("Số lượng phải lớn hơn 0.");
            return;
        }
        addItem(selectedProduct, qty);
    }
    private static void handleRemove(Scanner sc) {
        System.out.print("Nhập số thứ tự cần xóa: ");
        try {
            int displayNumber = Integer.parseInt(sc.nextLine().trim());
            removeItem(displayNumber - 1);
        } catch (NumberFormatException e) {
            System.out.println("Không tìm thấy mặt hàng.");
        }
    }
    private static void handleTotal() {
        System.out.println("Tổng tiền: " + calculateTotal());
    }

    static void addItem(Product product, int quantity) {
        cart.add(new cartItem(product, quantity));
    }

    static void removeItem(int index) {
        if (index >= 0 && index < cart.size()) {
            cart.remove(index);
        } else {
            System.out.println("Không tìm thấy mặt hàng.");
        }
    }

    static void printItems() {
        System.out.println("Khách hàng: " + currentCustomer.toString());
        if (cart.isEmpty()) {
            System.out.println("Giỏ hàng đang trống.");
            return;
        }

        for (int i = 0; i < cart.size(); i++) {
            cartItem item = cart.get(i);
            Product p = item.getProduct();
            System.out.println((i + 1) + ". " + p.getId() + " | " + p.getName() + " | "
                    + p.getPrice() + " | SL: " + item.getQuantity() + " | Thành tiền: " + item.getTotal());
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