package mvc.employee.model.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import mvc.employee.model.dal.Employee;

import javax.management.Query;

public class EmployeesDAO {

    Connection connection;
    SQLException ex;
    ObservableList<Employee> employees = FXCollections.observableArrayList();


    public EmployeesDAO(Connection connection) {
        this.connection = connection;
    }

    public ObservableList<Employee> getEmployees() {


        try (Statement statement = connection.createStatement();) {

            String query =
                    "SELECT E.EMPLOYEE_ID as EMP_ID, " +
                            "E.FIRST_NAME as FIRST_NAME, " +
                            "E.LAST_NAME as LAST_NAME, " +
                            "E.EMAIL as EMAIL, " +
                            "E.PHONE_NUMBER as PHONE, " +
                            "E.HIRE_DATE as HIRE_DATE, " +
                            "E.JOB_ID, " +
                            "E.SALARY as SALARY, " +
                            "E.MANAGER_ID as MANAGER_ID," +
                            "E.COMMISSION_PCT as COMMISSION,"
                            + "(SELECT E1.FIRST_NAME || ' ' || E1.LAST_NAME FROM EMPLOYEES E1 WHERE E1.EMPLOYEE_ID = E.MANAGER_ID) as MANAGER_ID, "
                            + "(SELECT J.JOB_TITLE FROM JOBS J WHERE J.JOB_ID = E.JOB_ID) as JOB_TITLE, "
                            + "(SELECT D.DEPARTMENT_NAME FROM DEPARTMENTS D WHERE D.DEPARTMENT_ID = E.DEPARTMENT_ID) as DEPT_ID "
                            + "FROM EMPLOYEES E";


            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                employees.add(rs2Employee(resultSet));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return employees;
    }

    //TODO do dokonczenia reszta pol Employee
    public void updateEmployee(Employee e) {

        try (Statement statement = connection.createStatement();) {
            String query;

            if (e.getManagerId() == 0) {
                query = String.format("UPDATE EMPLOYEES E SET " +
                                "E.JOB_ID = '%s', " +
                                "E.FIRST_NAME = '%s', " +
                                "E.LAST_NAME = '%S', " +
                                "E.EMAIL = '%s', " +
                                "E.PHONE_NUMBER = '%s', " +
                                "E.HIRE_DATE = TO_DATE('2012-07-18', 'YYYY-MM-DD')" +
                                "E.SALARY = '%d', " +
                                "E.DEPARTMENT_ID = '%s', " +
                                "E.MANAGER_ID = '' " +
                                "WHERE E.EMPLOYEE_ID = %d",
                        e.getJobId(),
                        e.getFirstName(),
                        e.getLastName(),
                        e.getEmail(),
                        e.getPhone(),
                        java.sql.Date.valueOf(e.getHireDate()).toString(),
                        e.getSalary(),
                        e.getDepartmentId(),
                        e.getEmployeeId());
            } else {

                query = String.format("UPDATE EMPLOYEES E SET " +
                                "E.JOB_ID = '%s', " +
                                "E.FIRST_NAME = '%s', " +
                                "E.LAST_NAME = '%s', " +
                                "E.EMAIL = '%s', " +
                                "E.PHONE_NUMBER = '%s', " +
                                "E.HIRE_DATE = TO_DATE('%s', 'YYYY-MM-DD'), " +
                                "E.SALARY = '%d', " +
                                "E.DEPARTMENT_ID = '%s', " +
                                "E.MANAGER_ID = '%d' " +
                                "WHERE E.EMPLOYEE_ID = %d",
                        e.getJobId(),
                        e.getFirstName(),
                        e.getLastName(),
                        e.getEmail(),
                        e.getPhone(),
                        java.sql.Date.valueOf(e.getHireDate()).toString(),
                        e.getSalary(),
                        e.getDepartmentId(),
                        e.getManagerId(),
                        e.getEmployeeId());
            }
            System.out.println("Query to wykonania: " + query);

            System.out.println(statement.executeQuery(query));
            System.out.println(query);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    //TO DO wstawianie nowego ziomeczka
    public void insertEmployee(Employee e) {

        try (Statement statement = connection.createStatement();) {
            String query;
            String preQuery;
            preQuery = "SELECT MAX(EMPLOYEES.EMPLOYEE_ID) as max FROM EMPLOYEES";
            ResultSet resultSet = statement.executeQuery(preQuery);
            resultSet.next();
            Integer nextId = resultSet.getInt("max");

            if (e.getManagerId() == 0) {
                query = String.format("INSERT INTO EMPLOYEES E " +
                                "(E.EMPLOYEE_ID," +
                                "E.JOB_ID," +
                                "E.FIRST_NAME," +
                                "E.LAST_NAME," +
                                "E.EMAIL," +
                                "E.PHONE_NUMBER," +
                                "E.HIRE_DATE," +
                                "E.SALARY," +
                                "E.DEPARTMENT_ID," +
                                "E.MANAGER_ID)" +
                                " VALUES ('%d','%s','%s','%s','%s','%s',TO_DATE('%s', 'YYYY-MM-DD'),'%d','%s','') ",
                        nextId + 1,
                        e.getJobId(),
                        e.getFirstName(),
                        e.getLastName(),
                        e.getEmail(),
                        e.getPhone(),
                        java.sql.Date.valueOf(e.getHireDate()).toString(),
                        e.getSalary(),
                        e.getDepartmentId());

            } else {
                query = String.format("INSERT INTO EMPLOYEES E " +
                                "(E.EMPLOYEE_ID," +
                                "E.JOB_ID," +
                                "E.FIRST_NAME," +
                                "E.LAST_NAME," +
                                "E.EMAIL," +
                                "E.PHONE_NUMBER," +
                                "E.HIRE_DATE," +
                                "E.SALARY," +
                                "E.DEPARTMENT_ID," +
                                "E.MANAGER_ID)" +
                                " VALUES ('%d','%s','%s','%s','%s','%s',TO_DATE('%s', 'YYYY-MM-DD'),'%d','%s','%d') ",
                        nextId + 1,
                        e.getJobId(),
                        e.getFirstName(),
                        e.getLastName(),
                        e.getEmail(),
                        e.getPhone(),
                        java.sql.Date.valueOf(e.getHireDate()).toString(),
                        e.getSalary(),
                        e.getDepartmentId(),
                        e.getManagerId());
            }
            System.out.println("Query to wykonania: " + query);

            System.out.println(statement.executeQuery(query));
            System.out.println(query);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }


    private Employee rs2Employee(ResultSet resultSet) {
        Employee emp = new Employee();
        try {
            int col = 1;
            emp.setEmployeeId(resultSet.getInt("EMP_ID"));
            col++;
            emp.setFirstName(resultSet.getNString("FIRST_NAME"));
            col++;
            emp.setLastName(resultSet.getNString("LAST_NAME"));
            col++;
            emp.setEmail(resultSet.getNString("EMAIL"));
            col++;
            emp.setPhone(resultSet.getNString("PHONE"));
            col++;
            emp.setHireDate(resultSet.getDate("HIRE_DATE").toLocalDate());
            col++;
            emp.setJobId(resultSet.getNString("JOB_TITLE"));
            col++;
            emp.setSalary(resultSet.getInt("SALARY"));
            col++;
            emp.setManager(resultSet.getNString("MANAGER_ID"));
            col++;
            emp.setManagerId(resultSet.getInt("MANAGER_ID"));
            col++;
            emp.setDepartmentId(resultSet.getNString("DEPT_ID"));
        } catch (SQLException ex) {
            this.ex = ex;
        }
        return emp;
    }
}