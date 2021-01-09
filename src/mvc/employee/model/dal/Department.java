package mvc.employee.model.dal;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Department {

    private StringProperty departmentId;
    private StringProperty departmentName;

    public Department() {
        departmentId = new SimpleStringProperty();
        departmentName = new SimpleStringProperty();
    }

    ///////////////////////////////////////////////////////////////
    public String getDepartmentId() {
        return departmentId.get();
    }

    public StringProperty departmentIdProperty() {
        return departmentId;
    }
    public void setDepartmentId(String departmentId) {
        this.departmentId.set(departmentId);
    }
//////////////////////////////////////////////////////////////

    public String getDepartmentName() {
        return departmentName.get();
    }

    public StringProperty departmentNameProperty() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName.set(departmentName);
    }
}
