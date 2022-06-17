/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3player;

import java.io.File;
import java.security.cert.Extension;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author MWIGO-JON-MARK
 */
public class MP3Player extends Application
{
    
        File open;
        FileChooser file;
        Media mp3;
        MediaPlayer mp;
        Duration current;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Application.launch(args);
    }

    /**
     * 
     * @param stage
     * @throws Exception 
     */
    @Override
    public void start(Stage stage) throws Exception
    {
        Node left = null;
        Node right = null;
        Label center = new Label();
        center.setFont(Font.font(24));
        FadeTransition fade = new FadeTransition(Duration.millis(1000), center);
        fade.setFromValue(1.0);
        fade.setToValue(0.2);
        fade.setCycleCount(50);
        fade.setAutoReverse(true);
        fade.setDuration(Duration.INDEFINITE);
        
        Button addbtn = new Button("Add File");
        Label status = new Label();
        HBox top = new HBox(addbtn, status);
        top.setSpacing(20);
        
        Button play = new Button("Play");
        Button pause = new Button("Pause");
        HBox bottom = new HBox(play,pause);
        bottom.setSpacing(20);
        
        addbtn.setOnAction(e->{
            file = new FileChooser();
            file.getExtensionFilters().add(new ExtensionFilter("Audio Files", "*.mp3", "*.mp4", "*.wav", "*.ogg"));
            
            open = file.showOpenDialog(stage);
            
            center.setText(open.getName());
        });
        
        play.setOnAction(e->{
            if(open != null)
            {
                mp3 = new Media(open.toURI().toString());
                mp = new MediaPlayer(mp3);
                mp.seek(current);
                mp.play();
                mp.setAutoPlay(true);
                status.setText("Status: Playing...");
                fade.play();
            }
        });
        
        pause.setOnAction(e->{
            mp.setAutoPlay(true);
            mp.pause();
            status.setText("Status: Paused ");
            current = mp.getCurrentTime();
            fade.pause();
        });
        
        BorderPane root = new BorderPane(center, top, right, bottom, left);
        
        Scene scene = new Scene(root, 500, 100);
        
        stage.setScene(scene);
        stage.setTitle("My Music Player");
        stage.show();
    }
    
}
