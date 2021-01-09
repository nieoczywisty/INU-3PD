package mvc;

import java.sql.Connection;
import java.util.Optional;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import mvc.employee.model.dal.EmployeesDAO;
import mvc.employee.view.EmployeesController;
import mvc.employee.view.MainController;


public class Main extends Application {

    protected static  OraConn db = new OraConn();


    @Override
    public void init() {
        db.setConnection("jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "XGUCIO01", "xgucio01");
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            ViewLoader<AnchorPane, EmployeesController> viewLoaderEmp = new ViewLoader<>("employee/view/Employees.fxml");
            AnchorPane anchorPane = viewLoaderEmp.getLayout();

            ViewLoader<BorderPane, MainController> viewLoaderMain = new ViewLoader<>("employee/view/Main.fxml");
            BorderPane borderPane = viewLoaderMain.getLayout();

            EmployeesController empControler = viewLoaderEmp.getController();
            MainController mainController = viewLoaderMain.getController();

            empControler.setEmployees(new EmployeesDAO(db.getConnection()).getEmployees());

            borderPane.setCenter(anchorPane);

            Scene scene = new Scene(borderPane);

            mainController.setStage(primaryStage);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Pracownicy");
            primaryStage.setOnHiding( e -> primaryStage_Hiding(e));
            primaryStage.setOnCloseRequest(
                    e -> Stage_CloseRequest(e));
            primaryStage.show();


        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void primaryStage_Hiding(WindowEvent e) {
        db.close();
    }
    private void Stage_CloseRequest(WindowEvent e) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Kończenie pracy");
        alert.setTitle("( ͡° ͜ʖ ͡°)");
        alert.setContentText("Czy chcesz zamknąć aplikację?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.orElse(ButtonType.CANCEL) != ButtonType.OK)
            e.consume();
    }
    @Override
    public void stop() {
        db.closeConnection();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
