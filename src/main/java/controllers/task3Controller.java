package controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import logic.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class task3Controller {

    @FXML
    private ChoiceBox sortByComboBox;
    @FXML
    private Label runTimeLabel;
    @FXML
    private Label runTimeTextLabel;
    @FXML
    private Button executeButton;
    @FXML
    private Button addButton;
    @FXML
    private TextField iDTextField;
    @FXML
    private ListView channelIDsListView;
    @FXML
    private TextArea resultTextArea;

    private Settings settings;
    private CacheController cacheController;
    private DataController dataController;
    private ArrayList<ChannelData> channels;

    @FXML
    public void initialize() {
        settings = new Settings();
        cacheController = new CacheController(settings);
        dataController = new DataController(settings, cacheController);
        channels = new ArrayList<>();

        runTimeLabel.setVisible(settings.getShowTime());
        runTimeTextLabel.setVisible(settings.getShowTime());

        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Имя канала",
                        "Дата создания канала",
                        "Кол-во подписчиков",
                        "Кол-во видео на канале",
                        "Кол-во просмотров всех видео",
                        "Количество комментариев"
                );

        sortByComboBox.setItems(options);
        sortByComboBox.setValue(options.get(0));

        executeButton.setOnAction(event -> {
            Runnable task = () -> {
                long startTime = System.nanoTime();

                String sortParameter = sortByComboBox.getValue().toString();
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
                String channelId = iDTextField.getText();
                ChannelData channelData = dataController.getChannelData(channelId);
                if (channelData != null) {
                    channels.add(channelData);
                    channelIDsListView.getItems().add(channelId);
                    iDTextField.setText("");
                }
            };
            Platform.runLater(task);
        });

    }

}
