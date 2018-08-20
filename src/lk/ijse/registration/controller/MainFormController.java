/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.registration.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.registration.main.IjseRegistration;

/**
 * FXML Controller class
 *
 * @author SSNLIVE
 */
public class MainFormController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private ImageView imgAddStudent;
    @FXML
    private ImageView imgCourse;
    @FXML
    private Label lblMenu;
    @FXML
    private ImageView imgRegisterStudent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(200),root);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }    


    @FXML
    private void ImgClicked(MouseEvent event) {
        if(event.getSource() instanceof ImageView){
            try {
                ImageView icon = (ImageView) event.getSource();
                Parent root = null;
                switch(icon.getId()){
                    case "imgAddStudent":
                        root = FXMLLoader.load(getClass().getResource("/lk/ijse/registration/view/ManageStudentForm.fxml"));
                        break;
                    case "imgRegisterStudent":
                        root = FXMLLoader.load(getClass().getResource("/lk/ijse/registration/view/RegisterStudentForm.fxml"));
                        break;
                    case "imgCourse":
                        root = FXMLLoader.load(getClass().getResource("/lk/ijse/registration/view/ManageCourseForm.fxml"));
                        break;
                }
                if(root != null){
                    Scene subScene = new Scene(root);
                    Stage primaryStage = (Stage) this.root.getScene().getWindow();
                    primaryStage.setScene(subScene);
                    primaryStage.centerOnScreen();
                    primaryStage.show();
                    
                    TranslateTransition tt = new TranslateTransition(Duration.seconds(0.2), subScene.getRoot());
                    tt.setFromX(subScene.getWidth());
                    tt.setToX(0);
                    tt.play();
                }
            } catch (IOException ex) {
                Logger.getLogger(IjseRegistration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
