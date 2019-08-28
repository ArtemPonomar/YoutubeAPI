package controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import logic.*;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class task6Controller {
    @FXML
    private Label runTimeLabel;
    @FXML
    private Label runTimeTextLabel;
    @FXML
    private Button executeButton;
    @FXML
    private TextField idTextField;
    @FXML
    private ListView channelIDsListView;
    @FXML
    private Button addButton;
    @FXML
    private TextArea resultTextArea;

    private Settings settings;
    private CacheController cacheController;
    private DataController dataController;
    ArrayList<ChannelData> channels;

    @FXML
    public void initialize(){
        settings = new Settings();
        cacheController = new CacheController(settings);
        dataController = new DataController(settings, cacheController);
        channels = new ArrayList<>();

        runTimeLabel.setVisible(settings.getShowTime());
        runTimeTextLabel.setVisible(settings.getShowTime());

        executeButton.setOnAction(event -> {
            Runnable task = () -> {
                long startTime = System.nanoTime();

                String sortParameter = "Количество комментариев";
                ChannelsListController.sortChannels(channels, sortParameter);
                ChannelsListController.channelsListToString(channels);

                long runTime = (System.nanoTime() - startTime);
                double runTimeInSeconds = TimeUnit.MILLISECONDS.convert(runTime, TimeUnit.NANOSECONDS) / 1000.0;
                runTimeLabel.setText(String.valueOf(runTimeInSeconds));

                resultTextArea.setText(ChannelsListController.channelsListToString(channels));
            };
            Platform.runLater(task);
        });

        addButton.setOnAction(event -> {
            Runnable task = ()-> {
                String channelId = idTextField.getText();
                ChannelData channelData = dataController.getFullChannelData(channelId);
                if (channelData != null) {
                    channels.add(channelData);
                    channelIDsListView.getItems().add(channelId);
                    idTextField.setText("");
                }
            };
            Platform.runLater(task);
        });
    }
}
