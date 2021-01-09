package mvc.employee.model.dal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DepartmentDAO {

    Connection connection;
    SQLException ex;
    ObservableList<Department> departments = FXCollections.observableArrayList();

    public DepartmentDAO(Connection connection) {
        this.connection = connection;
    }

    public ObservableList<Department> getDepartments() {


        try (Statement statement = connection.createStatement();) {

            String query = "SELECT D.DEPARTMENT_NAME as DEPT_NAME, D.DEPARTMENT_ID as DEPT_ID FROM DEPARTMENTS D";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                departments.add(rs2Department(resultSet));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return departments;
    }

    private Department rs2Department(ResultSet resultSet) {
        Department dep = new Department();
        try {
            int col = 1;
            dep.setDepartmentId(resultSet.getNString("DEPT_ID"));
            col++;
            dep.setDepartmentName(resultSet.getNString("DEPT_NAME"));

        } catch (SQLException ex) {
            this.ex = ex;
        }
        return dep;
    }
}
