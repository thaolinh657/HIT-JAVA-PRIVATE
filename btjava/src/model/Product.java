package model;

public class Product {
    private final String id;
    private String name;
    private double price;
    private int stock;
    private static int count;

    public Product(String id, String name, double price, int stock) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Lỗi: Mã sản phẩm không được để trống!");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Lỗi: Tên sản phẩm không được để trống!");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Lỗi: Giá sản phẩm phải là số dương!");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("Lỗi: Tồn kho không được là số âm!");
        }

        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        count++;
    }

    public static int getCount() {
        return count;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Lỗi: Tên sản phẩm không được để trống. Giữ nguyên tên cũ.");
        }
    }

    public void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        } else {
            System.out.println("Lỗi: Giá cập nhật phải là số dương. Vẫn giữ nguyên giá cũ: " + this.price);
        }
    }

    @Override
    public String toString() {
        return getId() + "|" + getName() + "|" + getPrice() + "| tồn: " + getStock();
    }

    public String getDescription() {
        return name;
    }

    public double shippingFee(int quantity) {
        if (quantity <= 0) {
            System.out.println("Lỗi: Số lượng yêu cầu không hợp lệ.");
            return 0; // Trả về 0 phí vận chuyển nếu số lượng sai
        }
        return 10000 * quantity;
    }

    public void decreaseStock(int amount) {
        if (amount > 0) {
            if (this.stock >= amount) {
                this.stock -= amount;
            } else {
                System.out.println("Lỗi: Không đủ hàng trong kho. Tồn kho hiện tại chỉ còn " + this.stock);
            }
        } else {
            System.out.println("Lỗi: Số lượng giảm phải lớn hơn 0.");
        }
    }

    public void increaseStock(int amount) {
        if (amount > 0) {
            this.stock += amount;
            System.out.println("Số lượng hàng tồn kho sau khi nhập: " + this.stock);
        } else {
            System.out.println("Lỗi: Số lượng nhập phải lớn hơn 0.");
        }
    }
}