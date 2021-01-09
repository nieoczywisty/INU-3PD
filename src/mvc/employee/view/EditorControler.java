package mvc.employee.view;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.StringConverter;
import mvc.OraConn;
import mvc.employee.model.dal.*;

import java.time.LocalDate;
import java.util.Optional;

public class EditorControler {

    protected Stage stage = new Stage();

    private Employee selectedEmployee;

    private EmployeesDAO employeesDAO;

    public void setEmployeeDAO(EmployeesDAO employeesDAO) {
        this.employeesDAO = employeesDAO;
    }

    protected Stage getStage() {
        return stage;
    }

    private void setFirstNameTextBox(String firstName) {
        FirstNameTextBox.setText(firstName);
    }

    private void setEmailTextBox(String email) {
        EmailTextBox.setText(email);
    }

    private void setLastNameTextBox(String lastName) {
        LastNameTextBox.setText(lastName);
    }

    private void setPhoneTextBox(String Phone) {
        PhoneTextBox.setText(Phone);
    }

    private void setHireDatePicker(LocalDate hireDate) {
        HireDatePicker.setValue(hireDate);
    }

    private void setSalaryTextField(int salary) {
        SalaryTextField.setText(String.valueOf(salary));
    }

    protected void loadFields(Employee selectedEmplyee) {
        this.selectedEmployee = selectedEmplyee;
        setFirstNameTextBox(selectedEmplyee.getFirstName());
        setLastNameTextBox(selectedEmplyee.getLastName());
        setPhoneTextBox(selectedEmplyee.getPhone());
        setEmailTextBox(selectedEmplyee.getEmail());
        setHireDatePicker(selectedEmplyee.getHireDate());
        setSalaryTextField(selectedEmplyee.getSalary());
    }

    @FXML
    private TextField FirstNameTextBox;

    @FXML
    private TextField LastNameTextBox;

    @FXML
    private TextField EmailTextBox;

    @FXML
    private TextField PhoneTextBox;

    @FXML
    private DatePicker HireDatePicker;

    @FXML
    private ComboBox<Job> JobComboBox;

    @FXML
    private ComboBox<Department> DeptComboBox;

    @FXML
    private ComboBox<Manager> ManagerComboBox;

    @FXML
    private TextField SalaryTextField;

    @FXML
    void onExit(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Wyjść bez zapisywania?");
        alert.setTitle("( ͡° ͜ʖ ͡°)");
        alert.setContentText("stracisz wprowadzone zmiany, kontynuować?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.orElse(ButtonType.CANCEL) == ButtonType.OK) {
            event.consume();
            stage.close();
        }
    }

    @FXML
    void onSaveAndExit(ActionEvent event) {
        if(selectedEmployee.getEmployeeId() == 0){
            selectedEmployee.setJobId(JobComboBox.getSelectionModel().getSelectedItem().getJobId());
            selectedEmployee.setFirstName(FirstNameTextBox.getText());
            selectedEmployee.setDepartmentId(DeptComboBox.getSelectionModel().getSelectedItem().getDepartmentId());
            selectedEmployee.setEmail(EmailTextBox.getText());
            selectedEmployee.setLastName(LastNameTextBox.getText());
            selectedEmployee.setManagerId(ManagerComboBox.getSelectionModel().getSelectedItem().getManagerId());
            selectedEmployee.setPhone(PhoneTextBox.getText());
            selectedEmployee.setSalary(Integer.parseInt(SalaryTextField.getText()));
            selectedEmployee.setHireDate(HireDatePicker.getValue());
            employeesDAO.insertEmployee(selectedEmployee);
        }else {
            selectedEmployee.setJobId(JobComboBox.getSelectionModel().getSelectedItem().getJobId());
            selectedEmployee.setFirstName(FirstNameTextBox.getText());
            selectedEmployee.setDepartmentId(DeptComboBox.getSelectionModel().getSelectedItem().getDepartmentId());
            selectedEmployee.setEmail(EmailTextBox.getText());
            selectedEmployee.setLastName(LastNameTextBox.getText());
            selectedEmployee.setManagerId(ManagerComboBox.getSelectionModel().getSelectedItem().getManagerId());
            selectedEmployee.setPhone(PhoneTextBox.getText());
            selectedEmployee.setSalary(Integer.parseInt(SalaryTextField.getText()));
            selectedEmployee.setHireDate(HireDatePicker.getValue());
            employeesDAO.updateEmployee(selectedEmployee);
        }
        stage.close();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    protected void loadJobComboBoxContent(ObservableList<Job> jobs) {
        JobComboBox.itemsProperty().setValue(jobs);
        for (Job job : JobComboBox.getItems()) {
            if (selectedEmployee.getJobId().equals(job.getJobName())) {
                JobComboBox.getSelectionModel().select(job);
                break;
            }else{
                JobComboBox.getSelectionModel().selectFirst();
            }
        }
        convertJobComboDisplayList();
    }

    private void convertJobComboDisplayList() {
        JobComboBox.setConverter(new StringConverter<Job>() {
            @Override
            public String toString(Job job) {
                return job.getJobName();
            }

            @Override
            public Job fromString(final String string) {
                return JobComboBox.getItems().stream().filter(job -> job.getJobName().equals(string)).findFirst().orElse(null);
            }
        });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    protected void loadDepartmentComboBoxContent(ObservableList<Department> dept) {

        DeptComboBox.itemsProperty().setValue(dept);
        for (Department dep : DeptComboBox.getItems()) {
            if (selectedEmployee.getDepartmentId().equals(dep.getDepartmentName())) {
                DeptComboBox.getSelectionModel().select(dep);
                break;
            }else {
                DeptComboBox.getSelectionModel().selectFirst();
            }
        }
        convertDeptComboDisplayList();
    }

    private void convertDeptComboDisplayList() {
        DeptComboBox.setConverter(new StringConverter<Department>() {
            @Override
            public String toString(Department dept) {
                return dept.getDepartmentName();
            }

            @Override
            public Department fromString(final String string) {
                return DeptComboBox.getItems().stream().filter(dept -> dept.getDepartmentName().equals(string)).findFirst().orElse(null);
            }
        });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    protected void loadManagerComboBoxContent(ObservableList<Manager> man) {
        ManagerComboBox.itemsProperty().setValue(man);
        for (Manager manager : ManagerComboBox.getItems()) {
            if (selectedEmployee.getManagerId() == manager.getManagerId()) {
                ManagerComboBox.getSelectionModel().select(manager);
                break;
//            }else if(selectedEmployee.getManagerId() == 0){
//                ManagerComboBox.getSelectionModel().select(0);
            }else {
                ManagerComboBox.getSelectionModel().selectFirst();
            }
        }
        convertManagerComboDisplayList();

    }

    private void convertManagerComboDisplayList() {
        ManagerComboBox.setConverter(new StringConverter<Manager>() {
            @Override
            public String toString(Manager man) {
                return man.getManagerName();
            }

            @Override
            public Manager fromString(final String string) {
                return ManagerComboBox.getItems().stream().filter(manager -> manager.getManagerName().equals(string)).findFirst().orElse(null);
            }
        });
    }

}

