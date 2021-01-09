package mvc.employee.model.dal;

import java.time.LocalDate;
import java.util.Objects;

import javafx.beans.binding.ObjectExpression;
import javafx.beans.property.*;

public class Employee {
    private IntegerProperty employeeId;
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty email;
    private StringProperty phone;
    private ObjectProperty<LocalDate> hireDate;
    private StringProperty jobId;
    private IntegerProperty salary;
    private IntegerProperty managerId;
    private StringProperty departmentId;
    private StringProperty manager;


    public Employee() {
        employeeId = new SimpleIntegerProperty(0);
        firstName = new SimpleStringProperty("");
        lastName = new SimpleStringProperty("");
        email = new SimpleStringProperty("");
        phone = new SimpleStringProperty("");
        hireDate = new SimpleObjectProperty<LocalDate>(LocalDate.now());
        jobId = new SimpleStringProperty("");
        salary = new SimpleIntegerProperty(0);
        managerId = new SimpleIntegerProperty(0);
        departmentId = new SimpleStringProperty("");
        manager = new SimpleStringProperty("");

    }
////////////////////////////////////////////////////////
    public int getEmployeeId() {
        return this.employeeId.get();
    }
    public void setEmployeeId(int employeeId) {
        this.employeeId.set(employeeId);
    }
    public IntegerProperty employeeIdProperty(){
        return employeeId;
    }
/////////////////////////////////////////////////////////
    public String getFirstName() {
        return firstName.get();
    }
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }
    public StringProperty firstNameProperty() {
        return firstName;
    }
/////////////////////////////////////////////////////////
    public String getLastName() {
        return lastName.get();
    }
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }
    public StringProperty lastNameProperty() {
        return lastName;
    }
/////////////////////////////////////////////////////////
    public String getEmail() {
        return email.get();
    }
    public void setEmail(String email) {
        this.email.set(email);
    }
    public StringProperty emailProperty() {
        return email;
    }
/////////////////////////////////////////////////////////
    public String getPhone() {
        return phone.get();
    }
    public void setPhone(String phone) {
        this.phone.set(phone);
    }
    public StringProperty phoneProperty() {
        return email;
    }
////////////////////////////////////////////////////////
    public LocalDate getHireDate() {
        return hireDate.get();
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate.set(hireDate);
    }
    public ObjectExpression<LocalDate> hireDateProperty() {
        return hireDate;
    }
////////////////////////////////////////////////////////
    public String getJobId() {
        return jobId.get();
    }

    public void setJobId(String jobId) {
        this.jobId.set(jobId);
    }
    public StringProperty jobIdProperty() {
        return jobId;
    }
////////////////////////////////////////////////////////
    public int getSalary() {
        return salary.get();
    }
    public void setSalary(int salary) {
        this.salary.set(salary);
    }
    public IntegerProperty salaryProperty() {
        return salary;
    }
/////////////////////////////////////////////////////////
    public Integer getManagerId() {
        return managerId.get();
    }
    public void setManagerId(Integer managerId) {
        this.managerId.set(managerId);
    }
    public IntegerProperty managerIdProperty() {
        return managerId;
    }
////////////////////////////////////////////////////////
    public String getDepartmentId() {
        return departmentId.get();
    }
    public void setDepartmentId(String departmentId) {
        this.departmentId.set(departmentId);
    }
    public StringProperty departmentIdProperty() {
        return departmentId;
    }
////////////////////////////////////////////////////////
    public String getManager() {
        return manager.get();
    }
    public void setManager(String manager) {
        this.manager.set(manager);
    }
    public StringProperty managerProperty() {
        return manager;
    }
}
