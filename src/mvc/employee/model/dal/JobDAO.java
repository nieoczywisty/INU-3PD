package mvc.employee.model.dal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JobDAO {

    Connection connection;
    SQLException ex;
    ObservableList<Job> jobs = FXCollections.observableArrayList();

    public JobDAO(Connection connection) {
        this.connection = connection;
    }

    public ObservableList<Job> getJobs() {


        try (Statement statement = connection.createStatement();) {

            String query = "SELECT J.JOB_TITLE as JOB_NAME, J.JOB_ID as JOB_ID FROM JOBS J";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                jobs.add(rs2Job(resultSet));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return jobs;
    }

    private Job rs2Job(ResultSet resultSet) {
        Job job = new Job();
        try {
            int col = 1;
            job.setJobId(resultSet.getNString("JOB_ID"));
            col++;
            job.setJobName(resultSet.getNString("JOB_NAME"));

        } catch (SQLException ex) {
            this.ex = ex;
        }
        return job;
    }
}
