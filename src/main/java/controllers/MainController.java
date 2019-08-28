package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import logic.Settings;

import java.io.IOException;

public class MainController {

    //------settings FXMLs-----------
    @FXML
    private ToggleButton cacheYes;
    @FXML
    private ToggleButton cacheNo;
    @FXML
    private ToggleButton runTimeYes;
    @FXML
    private ToggleButton runTimeNo;
    @FXML
    private TextField cachePathField;
    @FXML
    private Button saveButton;
    //------------------------------

    //-----YoutubeAPI FXMLs--------
    @FXML
    private Button task1Button;
    @FXML
    private Button task2Button;
    @FXML
    private Button task3Button;
    @FXML
    private Button task4Button;
    @FXML
    private Button task5Button;
    @FXML
    private Button task6Button;
    //------------------------------

    @FXML
    public void initialize() {
        initializeSettings();
        initializeTasks();
    }

    private void initializeSettings() {
        Settings settings = new Settings();
        settings.load();

        setShowTime(settings);
        setUseCache(settings);
        setCachePathField(settings);

        saveButton.setOnAction(event -> {
            settings.saveSettings(getUseCache(), getShowTime(), getPathToCache());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Изменения сохранены.");
            alert.showAndWait();
        });
    }

    private boolean getUseCache(){
        return cacheYes.isSelected();
    }

    private void setUseCache(Settings settings){
            cacheYes.setSelected(settings.getUseCash());
            cacheNo.setSelected(!settings.getUseCash());
    }

    private boolean getShowTime(){
        return runTimeYes.isSelected();
    }

    private void setShowTime(Settings settings){
        runTimeYes.setSelected(settings.getShowTime());
        runTimeNo.setSelected(!settings.getShowTime());
    }

    private String getPathToCache(){
        return cachePathField.getText().toString();
    }

    private void setCachePathField(Settings settings){
        cachePathField.setText(settings.getPathToCash());
    }

    private void initializeTasks() {
        task1Button.setOnAction((event) -> {
            String windowTitle = "1. Отобразить глобальную информацию о канале";
            String fxmlPath = "../fxml/task1Form.fxml";
            openNewTask(fxmlPath, windowTitle);
        });

        task2Button.setOnAction((event) -> {
            String windowTitle = "2. Сравнить глобальную информацию о каналах";
            String fxmlPath = "../fxml/task2Form.fxml";
            openNewTask(fxmlPath, windowTitle);
        });

        task3Button.setOnAction((event) -> {
            String windowTitle = "3. Сортировать каналы по их данным";
            String fxmlPath = "../fxml/task3Form.fxml";
            openNewTask(fxmlPath, windowTitle);
        });

        task4Button.setOnAction((event) -> {
            String windowTitle = "4. Медиа резонанс";
            String fxmlPath = "../fxml/task4Form.fxml";
            openNewTask(fxmlPath, windowTitle);
        });

        task5Button.setOnAction((event) -> {
            String windowTitle = "5. Сравнить Медиа резонанс";
            String fxmlPath = "../fxml/task5Form.fxml";
            openNewTask(fxmlPath, windowTitle);
        });

        task6Button.setOnAction((event) -> {
            String windowTitle = "6. Сортировать по Медиа резонансу";
            String fxmlPath = "../fxml/task6Form.fxml";
            openNewTask(fxmlPath, windowTitle);
        });
    }

    private void openNewTask(String fxmlPath, String windowTitle) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(fxmlPath));

            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle(windowTitle);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("form load task1.fxml exception");
        }
    }

}
