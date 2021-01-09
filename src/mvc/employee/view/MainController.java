package mvc.employee.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import mvc.ViewLoader;

import java.util.Optional;

public class MainController {
    private Stage primaryStage;
    ViewLoader<AnchorPane, EmployeesController> viewLoaderEmp;

    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setEmployeeFXML(ViewLoader<AnchorPane,
            EmployeesController> viewLoaderEmp) {
        this.viewLoaderEmp = viewLoaderEmp;
    }

    @FXML
    private void onAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Mateusz Żebrowski");
        alert.setHeaderText("Informacja o twórcy");
        Optional<ButtonType> result = alert.showAndWait();
    }

    @FXML
    private void onExit() {
        primaryStage.fireEvent(
                new WindowEvent(primaryStage,
                        WindowEvent.WINDOW_CLOSE_REQUEST));
    }
}
