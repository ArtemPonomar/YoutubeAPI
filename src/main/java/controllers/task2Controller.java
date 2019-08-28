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

public class task2Controller {

    @FXML
    private Label runTimeLabel;
    @FXML
    private Label runTimeTextLabel;
    @FXML
    private Button executeButton;
    @FXML
    private TextField channel1IDTextField;
    @FXML
    private TextField channel2IDTextField;
    @FXML
    private TextArea channel1TextArea;
    @FXML
    private TextArea channel2TextArea;

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
            Runnable task = () -> {
                long startTime = System.nanoTime();
                String channel1Id = channel1IDTextField.getText();
                String channel2Id = channel2IDTextField.getText();
                ChannelData channel1Data = dataController.getChannelData(channel1Id);
                ChannelData channel2Data = dataController.getChannelData(channel2Id);
                long runTime = (System.nanoTime() - startTime);
                double runTimeInSeconds = TimeUnit.MILLISECONDS.convert(runTime, TimeUnit.NANOSECONDS) / 1000.0;
                runTimeLabel.setText(String.valueOf(runTimeInSeconds));
                if (channel1Data == null) {
                    channel1TextArea.setText("Канала с указаным ID не найдено.");
                } else {
                    channel1TextArea.setText(channel1Data.toString());
                }
                if (channel2Data == null) {
                    channel2TextArea.setText("Канала с указаным ID не найдено.");
                } else {
                    channel2TextArea.setText(channel2Data.toString());
                }
            };
            Platform.runLater(task);
        });
    }
}
