package mvc.employee.model.dal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ManagerDAO {
    Connection connection;
    SQLException ex;
    ObservableList<Manager> managers = FXCollections.observableArrayList();

    public ManagerDAO(Connection connection) {
        this.connection = connection;
    }
    public ObservableList<Manager> getManagers() {


        try (Statement statement = connection.createStatement();) {



            String query = "SELECT e.employee_id as MANAGER_ID, (e.first_name || ' ' || e.last_name) as MANAGER_NAME FROM employees e where e.employee_id in (SELECT DISTINCT MANAGER_ID FROM employees)";


            ResultSet resultSet = statement.executeQuery(query);
            managers.add(new Manager());
            while (resultSet.next()) {
                managers.add(rs2Manager(resultSet));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return managers;
    }

    private Manager rs2Manager(ResultSet resultSet) {
        Manager man = new Manager();
        try {
            int col = 1;
            man.setManagerId(resultSet.getInt("MANAGER_ID"));
            col++;
            man.setManagerName(resultSet.getNString("MANAGER_NAME"));


        } catch (SQLException ex) {
            this.ex = ex;
        }
        return man;
    }
}
