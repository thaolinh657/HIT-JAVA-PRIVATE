import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Student> svList = new ArrayList<>();

        svList.add(new Student("SV01", "KHANH", 20, new Car("CAR01", "Lamborghini", "30A-12345")));
        svList.add(new Student("SV02", "An", 19, new Car("CAR02", "Toyota", "30B-67890")));

        int choice;
        do {
            System.out.println("\n========= MENU=========");
            System.out.println("1. Thêm SV ");
            System.out.println("2. Đọc mã SV ");
            System.out.println("3. Sửa mã 1 SV ");
            System.out.println("4. Đổi mật khẩu hệ thống ");
            System.out.println("5. Giới thiệu danh sách ");
            System.out.println("0. Thoát");
            System.out.print("Chon: ");
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                choice = -1;
            }

            switch (choice) {
                case 1 -> addSV(sc, svList);
                case 2 -> docMaSV(sc, svList);
                case 3 -> suaMaSV(sc, svList);
                case 4 -> doiMatKhau(sc);
                case 5 -> introK(svList);
                case 0 -> System.out.println("Thoát chương trình!");
                default -> System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (choice != 0);

        sc.close();
    }

    static void docMaSV(Scanner sc, List<Student> svList) {
        if (svList.isEmpty()) {
            System.out.println("Danh sách trống!"); return;
        }
        System.out.print("Nhập mật khẩu để truy cập ĐỌC mã SV: ");
        String pass = sc.nextLine();
        for (Student s : svList) {
            System.out.print("SV " + s.getName() + " -> Mã: ");
            String id = s.getStudentID(pass);
            if (id != null) {
                System.out.println(id);
            }
        }
    }

    static void suaMaSV(Scanner sc, List<Student> svList) {
        if (svList.isEmpty()) {
            System.out.println("Danh sách trống!"); return;
        }
        System.out.print("Nhập vị trí sinh viên cần sửa mã (0 đến " + (svList.size() - 1) + "): ");
        int index = Integer.parseInt(sc.nextLine());
        System.out.print("Nhập mã sinh viên MỚI: ");
        String newId = sc.nextLine();
        System.out.print("Nhập mật khẩu để cấp quyền SỬA: ");
        String pass = sc.nextLine();

        svList.get(index).setStudentID(newId, pass); // Gọi setter có mật khẩu
    }

    static void doiMatKhau(Scanner sc) {
        System.out.print("Nhập mật khẩu HIỆN TẠI: ");
        String oldPass = sc.nextLine();
        System.out.print("Nhập mật khẩu MỚI: ");
        String newPass = sc.nextLine();
        Student.changePassword(oldPass, newPass); // Gọi phương thức static
    }

    static void addSV(Scanner sc, List<Student> svList) {
        System.out.print("Nhập mã sinh viên: ");
        String studentID = sc.nextLine();
        System.out.print("Nhập tên sinh viên: ");
        String name = sc.nextLine();
        System.out.print("Nhập tuổi: ");
        int age = Integer.parseInt(sc.nextLine());

        System.out.print("Nhập mã xe: ");
        String carId = sc.nextLine();
        System.out.print("Nhập hãng xe: ");
        String brand = sc.nextLine();
        System.out.print("Nhập biển số xe: ");
        String licensePlate = sc.nextLine();

        Car car = new Car(carId, brand, licensePlate);
        svList.add(new Student(studentID, name, age, car));
        System.out.println("Thêm SV thành công!");
    }

    static void introK(List<Student> svList) {
        if (svList.isEmpty()) {
            System.out.println("Danh sách trống!"); return;
        }
        System.out.println("\n--- GIOI THIEU ---");
        for (Student s : svList) {
            s.intro();
        }
    }
}