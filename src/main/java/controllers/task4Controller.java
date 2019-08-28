package controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import logic.CacheController;
import logic.ChannelData;
import logic.Settings;
import logic.DataController;

import java.util.concurrent.TimeUnit;

public class task4Controller {
    @FXML
    private Label runTimeLabel;
    @FXML
    private Label runTimeTextLabel;
    @FXML
    private Button executeButton;
    @FXML
    private TextField idTextField;
    @FXML
    private TextArea textArea;

    private Settings settings;
    private CacheController cacheController;
    private DataController dataController;

    @FXML
    public void initialize(){
        settings = new Settings();
        cacheController = new CacheController(settings);
        dataController = new DataController(settings, cacheController);

        runTimeLabel.setVisible(settings.getShowTime());
        runTimeTextLabel.setVisible(settings.getShowTime());

        executeButton.setOnAction(event -> {
            Runnable task1 = () -> {
                long startTime = System.nanoTime();
                String channelId = idTextField.getText();
                ChannelData channelData = dataController.getFullChannelData(channelId);
                long runTime = (System.nanoTime() - startTime);
                double runTimeInSeconds = TimeUnit.MILLISECONDS.convert(runTime, TimeUnit.NANOSECONDS) / 1000.0;
                runTimeLabel.setText(String.valueOf(runTimeInSeconds));
                if (channelData == null) {
                    textArea.setText("Канала с указаным ID не найдено.");
                    return;
                }
                textArea.setText(channelData.toString());
            };
            Platform.runLater(task1);
        });
    }
}
