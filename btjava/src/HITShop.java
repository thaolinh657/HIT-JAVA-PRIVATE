import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HITShop {
    private static final Customer currentCustomer = new Customer("C01", "Linh Nguyễn", "0900000001");
    private static final List<Product> products = new ArrayList<>();
    private static Order currentOrder = null;
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice = -1; // Khởi tạo giá trị ban đầu cho choice
        initData();

        do {
            System.out.println("\n===== QUẢN LÝ CỬA HÀNG HITSHOP =====");
            System.out.println("1. Xem danh sách sản phẩm");
            System.out.println("2. Tạo đơn hàng mới");
            System.out.println("3. Thêm mặt hàng vào đơn");
            System.out.println("4. Xóa mặt hàng khỏi đơn");
            System.out.println("5. Xem chi tiết đơn hàng");
            System.out.println("6. Xác nhận đơn hàng");
            System.out.println("7. Hủy đơn hàng");
            System.out.println("0. Thoát");
            System.out.print("Nhập lựa chọn: ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    printProducts();
                    break;
                case 2:
                    currentOrder = new Order("ORD-001", currentCustomer);
                    System.out.println("Đã tạo đơn hàng mới cho khách: " + currentCustomer.getName());
                    break;
                case 3:
                    addItemToOrder();
                    break;
                case 4:
                    removeItemFromOrder();
                    break;
                case 5:
                    viewOrder();
                    break;
                case 6:
                    if (currentOrder != null) {
                        currentOrder.confirm();
                    } else {
                        System.out.println("Lỗi: Bạn chưa tạo đơn hàng nào!");
                    }
                    break;
                case 7:
                    if (currentOrder != null) {
                        currentOrder.cancel();
                    } else {
                        System.out.println("Lỗi: Bạn chưa tạo đơn hàng nào!");
                    }
                    break;
                case 0:
                    System.out.println("Cảm ơn bạn đã sử dụng hệ thống!");
                    break;
                default:
                    if (choice != -1) {
                        System.out.println("Lựa chọn không hợp lệ.");
                    } else {
                        System.out.println("Vui lòng nhập một số hợp lệ!");
                    }
            }
        } while (choice != 0);
        sc.close();
    }
    private static void initData() {
        // Tận dụng Đa hình: Mảng Product nhưng có thể chứa cả Book và ElectronicProduct
        products.add(new Product("P01", "Áo thun", 150000, 20));
        products.add(new Product("P02", "Bình nước", 50000, 10));
        products.add(new Book("P03", "Java cơ bản", 120000, 5, "An Nguyen"));
        products.add(new ElectronicProduct("P04", "Bàn phím", 400000, 3, 12));
    }

    private static void printProducts() {
        System.out.println("\n--- DANH SÁCH KHO HÀNG ---");
        for (Product p : products) {
            System.out.printf("Mã: %s | %-35s | Giá: %,.0f đ | Tồn: %d\n",
                    p.getId(), p.getDescription(), p.getPrice(), p.getStock());
        }
    }

    private static void addItemToOrder() {
        if (currentOrder == null) {
            System.out.println("Lỗi: Bạn phải tạo đơn hàng trước (Chọn phím 2).");
            return;
        }
        printProducts();
        System.out.print("Nhập mã sản phẩm cần thêm (P01, P02...): ");
        String id = sc.nextLine().trim();

        Product selectedProduct = null;
        for (Product p : products) {
            if (p.getId().equalsIgnoreCase(id)) {
                selectedProduct = p;
                break;
            }
        }

        if (selectedProduct == null) {
            System.out.println("Lỗi: Không tìm thấy sản phẩm mã " + id);
            return;
        }
        System.out.print("Nhập số lượng: ");
        int quantity;
        try {
            quantity = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Số lượng phải là số nguyên!");
            return;
        }
        CartItem item = new CartItem(selectedProduct, quantity);
        currentOrder.addItem(item);
    }

    private static void removeItemFromOrder() {
        if (currentOrder == null) {
            System.out.println("Lỗi: Bạn chưa tạo đơn hàng nào.");
            return;
        }
        System.out.print("Nhập mã sản phẩm muốn xóa khỏi đơn hàng: ");
        String pId = sc.nextLine().trim();

        // Tận dụng hàm xóa đã được đóng gói an toàn trong class Order
        currentOrder.removeItem(pId);
    }
    private static void viewOrder() {
        if (currentOrder == null) {
            System.out.println("Bạn chưa tạo đơn hàng nào.");
            return;
        }
        System.out.println("\n=== CHI TIẾT ĐƠN HÀNG " + currentOrder.getId() + " ===");
        System.out.println("Khách hàng: " + currentOrder.getCustomer().getName() + " - SĐT: " + currentOrder.getCustomer().getPhone());
        System.out.println("Trạng thái: " + currentOrder.getStatus());
        System.out.println("Danh sách sản phẩm trong đơn:");

        List<CartItem> items = currentOrder.getItems();
        if (items.isEmpty()) {
            System.out.println(" (Đơn hàng đang trống)");
        } else {
            for (int i = 0; i < items.size(); i++) {
                CartItem item = items.get(i);
                Product p = item.getProduct();
                System.out.println((i + 1) + ". " + p.getId() + " | " + p.getName()
                        + " | Giá: " + p.getPrice()
                        + " | SL: " + item.getQuantity()
                        + " | Thành tiền: " + item.getTotal());
            }
        }
        System.out.println("----------------------------------");
        System.out.printf("Cộng tiền hàng:  %,.0f đ\n", currentOrder.getSubtotal());
        System.out.printf("Phí vận chuyển:  %,.0f đ\n", currentOrder.getShippingFee());
        System.out.printf("TỔNG THANH TOÁN: %,.0f đ\n", currentOrder.getTotal());
    }
}