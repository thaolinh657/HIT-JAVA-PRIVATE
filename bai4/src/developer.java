public class developer extends Employee{
    private int overtimeHours;

    public developer(String id, String name, String baseSalary, int overtimeHours) {
        super(id, name, baseSalary);
        this.overtimeHours = overtimeHours;
    }

    public int getOvertimeHours() {
        return overtimeHours;
    }

    public void setOvertimeHours(int overtimeHours) {
        this.overtimeHours = overtimeHours;
    }
    public int thuNhap{
        return baseSalary() + overtimeHours *20000;
    }
}
