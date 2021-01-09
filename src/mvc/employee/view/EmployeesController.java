package mvc.employee.view;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import mvc.OraConn;
import mvc.ViewLoader;
import mvc.employee.model.dal.*;

import java.time.LocalDate;
import java.util.Optional;


public class EmployeesController {

    Employee employee = null;
    //    OraConn con=null;
//    public void setCon(OraConn con) {
//        this.con = con;
//    }
    private OraConn db;


    // TableView, TableColumn
    @FXML
    private TableView<Employee> employeeTable = new TableView();
    @FXML
    private TableColumn<Employee, Integer> employeeIdColumn;
    @FXML
    private TableColumn<Employee, String> firstNameColumn;
    @FXML
    private TableColumn<Employee, String> lastNameColumn;
    @FXML
    private TableColumn<Employee, String> emailColumn;
    @FXML
    private TableColumn<Employee, String> phoneNameColumn;
    @FXML
    private TableColumn<Employee, LocalDate> hireDateColumn;
    //    @FXML
//    private TableColumn<Employee, String> hireDateAsStrColumn;
    @FXML
    private TableColumn<Employee, String> jobIdColumn;
    @FXML
    private TableColumn<Employee, Integer> salaryColumn;
    @FXML
    private TableColumn<Employee, String> managerColumn;
    @FXML
    private TableColumn<Employee, String> departmentIdColumn;
    // Label
    @FXML
    private Label employeeIdLabel;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label phoneNameLabel;
    @FXML
    private Label hireDateLabel;
    @FXML
    private Label jobIdLabel;
    @FXML
    private Label salaryLabel;
    @FXML
    private Label managerLabel;
    @FXML
    private Label departmentIdLabel;

    @FXML
    private void initialize() {
        employeeTable.setTableMenuButtonVisible(true);
        employeeIdColumn.setCellValueFactory(cellData ->
                cellData.getValue().employeeIdProperty().asObject());
        firstNameColumn.setCellValueFactory(cellData ->
                cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData ->
                cellData.getValue().lastNameProperty());
        emailColumn.setCellValueFactory(cellData ->
                cellData.getValue().emailProperty());
        phoneNameColumn.setCellValueFactory(cellData ->
                cellData.getValue().phoneProperty());
        hireDateColumn.setCellValueFactory(cellData ->
                cellData.getValue().hireDateProperty());
        jobIdColumn.setCellValueFactory(cellData ->
                cellData.getValue().jobIdProperty());
        salaryColumn.setCellValueFactory(cellData ->
                cellData.getValue().salaryProperty().asObject());
        managerColumn.setCellValueFactory(cellData ->
                cellData.getValue().managerProperty());
        departmentIdColumn.setCellValueFactory(cellData ->
                cellData.getValue().departmentIdProperty());
// ustaw wartości pól
        updateEmployee(null);
// słuchaj zmiany zaznaczonego wiersza
        employeeTable.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) ->
                        updateEmployee(newValue));
    }

    public void setEmployees(ObservableList<Employee> olEmployees) {
        employeeTable.getItems().clear();
        employeeTable.setItems(olEmployees);
// zaznacz pierwszy wiersz w widoku tabeli (o ile nie jest pusta)
        if (!employeeTable.getItems().isEmpty()) {
            employeeTable.getSelectionModel().select(0);
        }
    }

    private void updateEmployee(Employee emp) {
        employee = emp;
        if (emp != null) {
            employeeIdLabel.setText(Integer.toString(emp.getEmployeeId()));
            firstNameLabel.setText(emp.getFirstName());
            lastNameLabel.setText(emp.getLastName());
            emailLabel.setText(emp.getEmail());
            phoneNameLabel.setText(emp.getPhone());
            hireDateLabel.setText(emp.getHireDate().toString());
            jobIdLabel.setText(emp.getJobId());
            salaryLabel.setText(Integer.toString(emp.getSalary()));
            managerLabel.setText((emp.getManager()));
            departmentIdLabel.setText(emp.getDepartmentId());

            employeeIdColumn.setCellValueFactory(cellData ->
                    cellData.getValue().employeeIdProperty().asObject());
            firstNameColumn.setCellValueFactory(cellData ->
                    cellData.getValue().firstNameProperty());
            lastNameColumn.setCellValueFactory(cellData ->
                    cellData.getValue().lastNameProperty());
            emailColumn.setCellValueFactory(cellData ->
                    cellData.getValue().emailProperty());
            phoneNameColumn.setCellValueFactory(cellData ->
                    cellData.getValue().phoneProperty());
            hireDateColumn.setCellValueFactory(cellData ->
                    cellData.getValue().hireDateProperty());
            jobIdColumn.setCellValueFactory(cellData ->
                    cellData.getValue().jobIdProperty());
            salaryColumn.setCellValueFactory(cellData ->
                    cellData.getValue().salaryProperty().asObject());
            managerColumn.setCellValueFactory(cellData ->
                    cellData.getValue().managerProperty());
            departmentIdColumn.setCellValueFactory(cellData ->
                    cellData.getValue().departmentIdProperty());
        } else {
            employeeIdLabel.setText("");
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            emailLabel.setText("");
            phoneNameLabel.setText("");
            hireDateLabel.setText("");
            jobIdLabel.setText("");
            salaryLabel.setText("");
            managerLabel.setText("");
            departmentIdLabel.setText("");
        }
    }
    private void refresh(){
        setEmployees(new EmployeesDAO(db.getConnection()).getEmployees());
    }

    @FXML
    private void deleteEmployee() {
        int selIdx =
                employeeTable
                        .getSelectionModel()
                        .getSelectedIndex();
        if (selIdx >= 0)
            employeeTable.getItems().remove(selIdx);
    }

    @FXML
    private void editEmployeeBtn() {

        ViewLoader<GridPane, EditorControler> employeeEdit = new ViewLoader<>("employee/view/Editor.fxml");
        GridPane gridPane = employeeEdit.getLayout();
        EditorControler editor = employeeEdit.getController();
        Scene scene = new Scene(gridPane);
        Stage editStage = editor.getStage();
        editStage.setScene(scene);
        editStage.setOnCloseRequest(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Wyjść bez zapisywania?");
            alert.setTitle("( ͡° ͜ʖ ͡°)");
            alert.setContentText("stracisz wprowadzone zmiany, kontynuować?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.orElse(ButtonType.CANCEL) != ButtonType.OK) {
                event.consume();
            }
        });
        db = new OraConn();
        db.setConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "XGUCIO01", "xgucio01");

        editor.loadFields(employeeTable.getSelectionModel().getSelectedItem());
        editor.loadDepartmentComboBoxContent(new DepartmentDAO(db.getConnection()).getDepartments());
        editor.loadJobComboBoxContent(new JobDAO(db.getConnection()).getJobs());
        editor.loadManagerComboBoxContent(new ManagerDAO(db.getConnection()).getManagers());
        editor.setEmployeeDAO(new EmployeesDAO(db.getConnection()));

        editStage.showAndWait();
        refresh();
    }
    @FXML
    void addEmployeeBtn() {
        ViewLoader<GridPane, EditorControler> employeeEdit = new ViewLoader<>("employee/view/Editor.fxml");
        GridPane gridPane = employeeEdit.getLayout();
        EditorControler editor = employeeEdit.getController();
        Scene scene = new Scene(gridPane);
        Stage editStage = editor.getStage();
        editStage.setScene(scene);
        editStage.setOnCloseRequest(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Wyjść bez zapisywania?");
            alert.setTitle("( ͡° ͜ʖ ͡°)");
            alert.setContentText("stracisz wprowadzone zmiany, kontynuować?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.orElse(ButtonType.CANCEL) != ButtonType.OK) {
                event.consume();
            }
        });
        db = new OraConn();
        db.setConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "XGUCIO01", "xgucio01");

        editor.loadFields(new Employee());
        editor.loadDepartmentComboBoxContent(new DepartmentDAO(db.getConnection()).getDepartments());
        editor.loadJobComboBoxContent(new JobDAO(db.getConnection()).getJobs());
        editor.loadManagerComboBoxContent(new ManagerDAO(db.getConnection()).getManagers());
        editor.setEmployeeDAO(new EmployeesDAO(db.getConnection()));

        editStage.showAndWait();
        refresh();
    }

}



