public class Student {
    private String studentID;
    private String name;
    private int age;
    private Car car;

    public Student(String studentID, String name, int age, Car car) {
        this.studentID = studentID;
        this.name = name;
        this.age = age;
        this.car = car;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void intro() {
        System.out.println("minh ten la " + getName() + ", " + getAge() + ", di xe " + getCar() + "bien so xe " + this.car.getCarId());
    }

    private static String adminPassword = "Em yeu HIT";

    public static void changePassword(String oldPassword, String newPassword) {
        if (adminPassword.equals(oldPassword)) {
            adminPassword = newPassword;
            System.out.println(">> Đổi mật khẩu thành công!");
        } else {
            System.out.println(">> Sai mật khẩu hiện tại. Không thể đổi!");
        }
    }
    public String getStudentID(String password) {
        if (adminPassword.equals(password)) {
            return studentID;
        } else {
            System.out.println(">> Sai mật khẩu.");
            return null;
        }
    }
    public void setStudentID(String studentID, String password) {
        if (adminPassword.equals(password)) {
            this.studentID = studentID;
            System.out.println(">> Cập nhật mã sinh viên thành công!");
        } else {
            System.out.println(">> Sai mật khẩu. Giữ nguyên mã cũ.");
        }
    }
}
