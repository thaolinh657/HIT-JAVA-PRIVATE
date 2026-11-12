package model;

public class ElectronicProduct extends Product {
    private int warrantyMonths;

    public ElectronicProduct(String id, String name, double price, int stock, int warrantyMonths) {
        super(id, name, price, stock);
        if (warrantyMonths < 0) {
            throw new IllegalArgumentException("Lỗi: Số tháng bảo hành không được là số âm!");
        }
        this.warrantyMonths = warrantyMonths;
    }
    public int getWarrantyMonths() {
        return warrantyMonths;
    }
    public void setWarrantyMonths(int warrantyMonths) {
        if (warrantyMonths >= 0) {
            this.warrantyMonths = warrantyMonths;
        } else {
            System.out.println("Lỗi: Số tháng bảo hành không được âm! Vẫn giữ nguyên: " + this.warrantyMonths);
        }
    }
    @Override
    public String toString() {
        return super.toString() + " | Bảo hành: " + warrantyMonths + " tháng";
    }
    @Override
    public double shippingFee(int quantity){
        if(quantity <= 0 ) {
            System.out.println("Yêu cầu không hợp lệ ");
        }
        return 30000*quantity;
    }
}