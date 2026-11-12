public class Employee {
    private String id;
    private String name;
    private String baseSalary;

    public Employee(String id, String name, String baseSalary) {
        this.id = id;
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public String getBaseSalary() {
        return baseSalary;
    }
}
