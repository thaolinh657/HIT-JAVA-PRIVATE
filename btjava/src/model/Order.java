package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    public static final String PENDING = "PENDING";
    public static final String CONFIRMED = "CONFIRMED";
    public static final String CANCELLED = "CANCELLED";

    private final String id;
    private Customer customer;
    private List<CartItem> items;
    private String status;

    public Order(String id, Customer customer) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Lỗi: Mã đơn hàng không được để trống!");
        }
        this.id = id;
        this.customer = customer;

        this.items = new ArrayList<>();
        this.status = PENDING;
    }

    public String getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public List<CartItem> getItems() {
        List<CartItem> copyList = new ArrayList<>();
        for (CartItem item : this.items) {
            copyList.add(new CartItem(item.getProduct(), item.getQuantity()));
        }
        return Collections.unmodifiableList(copyList);
    }

    public void addItem(CartItem item) {
        if (!this.status.equals(PENDING)) {
            System.out.println("Lỗi: Đơn hàng đã " + this.status + ", không thể thêm hàng.");
            return;
        }
        if (item == null || item.getProduct() == null || item.getQuantity() <= 0) {
            System.out.println("Lỗi: Dòng hàng thiếu thông tin hoặc số lượng không hợp lệ.");
            return;
        }
        if (item.getQuantity() > item.getProduct().getStock()) {
            System.out.println("Lỗi: Sản phẩm '" + item.getProduct().getName() + "' chỉ còn " + item.getProduct().getStock() + " cái (Yêu cầu: " + item.getQuantity() + ").");
            return;
        }

        for (CartItem existingItem : this.items) {
            if (existingItem.getProduct().getId().equals(item.getProduct().getId())) {
                System.out.println("Lỗi: Sản phẩm '" + item.getProduct().getName() + "' đã có trong đơn.");
                return;
            }
        }
        this.items.add(new CartItem(item.getProduct(), item.getQuantity()));
    }

    public void removeItem(String productId) {
        if (!this.status.equals(PENDING)) {
            System.out.println("Lỗi: Đơn hàng đã " + this.status + ", không thể xóa hàng.");
            return;
        }

        CartItem itemToRemove = null;
        for (CartItem item : this.items) {
            if (item.getProduct().getId().equals(productId)) {
                itemToRemove = item;
                break;
            }
        }

        if (itemToRemove != null) {
            this.items.remove(itemToRemove);
            System.out.println("Đã xóa sản phẩm khỏi đơn.");
        } else {
            System.out.println("Lỗi: Không tìm thấy sản phẩm mã '" + productId + "' trong đơn.");
        }
    }

    public double getSubtotal() {
        double subtotal = 0;
        for (CartItem item : this.items) {
            subtotal += item.getProduct().getPrice() * item.getQuantity();
        }
        return subtotal;
    }

    public double getShippingFee() {
        double fee = 0;
        for (CartItem item : this.items) {
            fee += item.getProduct().shippingFee(item.getQuantity());
        }
        return fee;
    }

    public double getTotal() {
        return getSubtotal() + getShippingFee();
    }

    public void confirm() {
        if (!this.status.equals(PENDING)) {
            System.out.println("Lỗi: Chỉ có thể xác nhận đơn PENDING.");
            return;
        }
        if (this.items.isEmpty()) {
            System.out.println("Lỗi: Đơn hàng trống.");
            return;
        }

        for (CartItem item : this.items) {
            if (item.getQuantity() > item.getProduct().getStock()) {
                System.out.println("Lỗi: Xác nhận thất bại. Sản phẩm '" + item.getProduct().getName() + "' không đủ tồn kho.");
                return;
            }
        }
        this.status = CONFIRMED;
        System.out.println("Đã xác nhận đơn hàng.");
    }

    public void cancel() {
        if (this.status.equals(PENDING)) {
            this.status = CANCELLED;
            System.out.println("Đã hủy đơn hàng.");
        } else {
            System.out.println("Lỗi: Không thể hủy đơn hàng đã CONFIRMED hoặc CANCELLED.");
        }
    }
}