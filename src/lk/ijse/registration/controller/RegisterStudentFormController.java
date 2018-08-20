/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.registration.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.registration.business.BOFactory;
import lk.ijse.registration.business.custom.StudentBO;
import lk.ijse.registration.business.custom.CourseBO;
import lk.ijse.registration.business.custom.RegisterBO;
import lk.ijse.registration.dto.RegisterDTO;
import lk.ijse.registration.dto.StudentDTO;
import lk.ijse.registration.main.IjseRegistration;

/**
 * FXML Controller class
 *
 * @author SSNLIVE
 */
public class RegisterStudentFormController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField txtStudentID;
    @FXML
    private JFXTextField txtStudentName;
    @FXML
    private JFXTextField txtStudentAdr;
    @FXML
    private JFXComboBox<String> cmbCourseID;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnCancel;
    @FXML
    private JFXTextField txtCourseFee;
    
    RegisterBO registerBO = (RegisterBO)BOFactory.getInstance().getBO(BOFactory.BOType.Register);
    CourseBO courseBO = (CourseBO)BOFactory.getInstance().getBO(BOFactory.BOType.Course);
    StudentBO studentBO = (StudentBO)BOFactory.getInstance().getBO(BOFactory.BOType.Student);
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setComboBox();
    }  
    
    
    
    
    private void setComboBox(){
        try {
            ArrayList<String> array = courseBO.getCourseIDs();
            
            cmbCourseID.setItems(FXCollections.observableArrayList(array));
        } catch (Exception ex) {
            Logger.getLogger(RegisterStudentFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    @FXML
    private void CheckStdClicked(ActionEvent event) {
        String stID = txtStudentID.getText();
        try {
            StudentDTO student = studentBO.findStudent(stID);
            
            txtStudentName.setText(student.getName());
            txtStudentAdr.setText(student.getAddress());
        } catch (Exception ex) {
            Logger.getLogger(RegisterStudentFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void courseOnAction(ActionEvent event) {
        
       
    }

    @FXML
    private void AddRegistration(ActionEvent event) {
       String stID =  txtStudentID.getText();
       String CID = cmbCourseID.getValue().toString();
       Date date = new Date();
       DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
       BigDecimal fee = new BigDecimal(txtCourseFee.getText());
       
       RegisterDTO register = new RegisterDTO(stID, CID,date, fee);

       boolean result = false;
        try {
            result = registerBO.registerStudent(register);
        } catch (Exception ex) {
            Logger.getLogger(RegisterStudentFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
       if(result == true){
           new Alert(Alert.AlertType.INFORMATION, "Student had been successfully added", ButtonType.OK).show();
       }else{
           new Alert(Alert.AlertType.INFORMATION, "Registration failed...", ButtonType.OK).show();
       }
       
    }
    
    @FXML
    private void BtnCancelClicked(ActionEvent event) {
        txtStudentID.setText("");
        txtStudentName.setText("");
        txtStudentAdr.setText("");
        txtCourseFee.setText("");
        cmbCourseID.getSelectionModel().clearSelection();
    }


    @FXML
    private void navigateToHome(ActionEvent event) {
        IjseRegistration.navigateToHome(root, (Stage)this.root.getScene().getWindow());
    }


    
}
