package model;

public class Book extends Product {
    private String author;
    public Book(String id, String name, double price, int stock, String author) {
        super(id, name, price, stock);

        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Lỗi: Tên tác giả không được để trống!");
        }
        this.author = author;
    }
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author != null && !author.trim().isEmpty()) {
            this.author = author;
        } else {
            System.out.println("Lỗi: Tên tác giả không được để trống. Giữ nguyên tác giả cũ: " + this.author);
        }
    }

    @Override
    public String getDescription() {
        return getName() + " — tác giả: " + author;
    }
    @Override
    public double shippingFee(int quantity) {
        if (quantity <= 0) {
            System.out.println("Lỗi: Số lượng yêu cầu không hợp lệ.");
            return 0;
        }
        return 5000 * quantity;
    }
    @Override
    public String toString() {
        return super.toString() + " | Tác giả: " + author;
    }
}