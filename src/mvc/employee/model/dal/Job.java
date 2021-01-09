package mvc.employee.model.dal;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Job {
    private StringProperty jobId;
    private StringProperty jobName;

    public Job() {
        jobId = new SimpleStringProperty();
        jobName = new SimpleStringProperty();
    }
///////////////////////////////////////////////////////
    public String getJobId() {
        return jobId.get();
    }

    public StringProperty jobIdProperty() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId.set(jobId);
    }
//////////////////////////////////////////////////////
    public String getJobName() {
        return jobName.get();
    }

    public StringProperty jobNameProperty() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName.set(jobName);
    }
}
