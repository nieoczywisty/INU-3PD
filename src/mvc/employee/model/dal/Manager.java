package mvc.employee.model.dal;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Manager {
    private IntegerProperty managerId;
    private StringProperty managerName;

    public Manager() {
        managerId = new SimpleIntegerProperty(0);
        managerName = new SimpleStringProperty("");
    }
//////////////////////////////////////////////////////////////
    public int getManagerId() {
        return managerId.get();
    }

    public IntegerProperty managerIdProperty() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId.set(managerId);
    }
/////////////////////////////////////////////////////////////
    public String getManagerName() {
        return managerName.get();
    }

    public StringProperty managerNameProperty() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName.set(managerName);
    }

}
