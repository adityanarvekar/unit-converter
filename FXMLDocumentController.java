/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitconverter;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 *
 * @author aditya
 */
public class FXMLDocumentController implements Initializable {
    
    boolean isFirstEnabled = false;
    DecimalFormat df = new DecimalFormat("#.##");
    @FXML
    private Label label1;
    
    @FXML
    private Label label2;
    
    @FXML
    private Label errorText;
    
    @FXML
    private TextField inputNum1;    
    
    @FXML
    private TextField inputNum2;
    
    @FXML
    private RadioButton tempRadio;
    
    @FXML
    private RadioButton heightRadio;
    
    @FXML
    private RadioButton massRadio;
   
    @FXML
    private Button btnConvert;
    
    @FXML
    private void convertAction(ActionEvent event) {
        errorText.setText("");
        
        if(tempRadio.isSelected()){
              if(inputNum1.getText().length()>0){
                  
                boolean isInputValid=getInputValidation(inputNum1,"^\\d+(\\.\\d{1,10})?\\s(?i)(c)\\b$");
                if(isInputValid){
                    String celciusstring= inputNum1.getText().toLowerCase().trim();
                    celciusstring=celciusstring.trim();
                    celciusstring=celciusstring.replace(" c","");
                    double celciusval=Double.parseDouble(celciusstring);
                    double fahrenheit=((celciusval*9)/5) + 32;
                    inputNum2.setText(df.format(fahrenheit)+" F");
                }
                else{
                    errorText.setText("Invalid Input in Centigrade.\nFor eg. correct input should be 32 C"); 
                }
            }
            else if(inputNum2.getText().length()>0){
                
                boolean isInputValid=getInputValidation(inputNum2,"^\\d+(\\.\\d{1,10})?\\s(?i)(f)\\b$");
                if(isInputValid){
                    String fahrenheitstring= inputNum2.getText().toLowerCase().trim();
                     fahrenheitstring=fahrenheitstring.trim();
                     fahrenheitstring=fahrenheitstring.replace(" f","");
                    double fahrenheitval=Double.parseDouble(fahrenheitstring);
                    double celcius=(fahrenheitval - 32) * 5/9 ;
                    inputNum1.setText(df.format(celcius)+" C");
                }
                else{
                    errorText.setText("Invalid Input in Fahrenheit.\nFor eg. correct input should be 62 F");
                }
            }
            else if(inputNum1.getText().length()==0 && inputNum2.getText().length()==0){
                errorText.setText("Please enter an input");
            }
              
        }
        else if(heightRadio.isSelected()){
            
             if(inputNum1.getText().length()>0){
           
                boolean isInputValid=getInputValidation(inputNum1,"^\\d+(\\.\\d{1,10})?\\s(?i)(m)\\b$");
                if(isInputValid){
                 
                    
                    String meterstring= inputNum1.getText().toLowerCase().trim();
                    meterstring=meterstring.replace(" m","");
                    double meterval=Double.parseDouble(meterstring);
                    double ftinc=meterval*3.281;
                    String ftincString=df.format(ftinc);
                    ftincString=ftincString.replace(".", " ft ");
                    ftincString=ftincString+" in";
                    if(ftincString.contains(" 00 in")){
                         ftincString=ftincString.replace(" 00 in","");
                    }
                    
                    inputNum2.setText(ftincString);
                    
                    
                    
                }
                else{
                        errorText.setText("Invalid Input in Meter.\nFor eg. correct input should be 5.89 m");
                }
            }
            else if(inputNum2.getText().length()>0){
                
                boolean isInputValid=getInputValidation(inputNum2,"^\\d\\s(?i)(ft)\\b\\s[0-9]\\s(?i)(in)$|^\\d+(\\.\\d{1,10})?\\s(?i)(ft)\\b$"); 
                if(isInputValid){
                 
                    
                    
                    String feetandinchstring= inputNum2.getText().toLowerCase().trim();
                    if(feetandinchstring.matches("^\\d+(\\.\\d{1,10})?\\s(?i)(ft)\\b$")){
                        feetandinchstring=feetandinchstring.replace(" ft","");    
                    }
                    else{
                        feetandinchstring=feetandinchstring.replace(" ft ",".");   
                        feetandinchstring=feetandinchstring.replace(" in","");
                    }
                    double feetandinchval=Double.parseDouble(feetandinchstring);
                    double meterval=feetandinchval/3.281;
                    String meterString=df.format(meterval);
                    meterString=meterString.replace(".", " m ");
                    meterString=meterString+" cm";
                    if(meterString.contains(" 00 cm")){
                         meterString=meterString.replace(" 00 cm","");
                    }
                    inputNum1.setText(meterString);
                    
                    
                }
                else{
                    errorText.setText("Invalid Input in Feet.\nFor eg. correct input should be 3 ft 2 in");
                }
            }
             else if(inputNum1.getText().length()==0 && inputNum2.getText().length()==0){
                errorText.setText("Please enter an input");
            }
            
        }
        else if(massRadio.isSelected()){
              if(inputNum1.getText().length()>0){
              boolean isInputValid=getInputValidation(inputNum1,"^\\d+(\\.\\d{1,10})?\\s(?i)(kg)\\b$"); 
                if(isInputValid){
                   
                    
                    
                    String kilostring = inputNum1.getText().toLowerCase().trim();
                    kilostring=kilostring.replace(" kg","");
                    double kiloval=Double.parseDouble(kilostring);
                    double lbozval=kiloval*2.205;
                    String lbozString=df.format(lbozval);
                    lbozString=lbozString.replace(".", " lb ");
                    lbozString=lbozString+" oz";
                    if(lbozString.contains(" 00 oz")){
                         lbozString=lbozString.replace(" 00 oz","");
                    }
                    
                    inputNum2.setText(lbozString);
                    
                }
                else{
                     errorText.setText("Invalid Input in Kilogram. For eg. correct input should be 3.89 kg");
                }
               
            
            }
            else if(inputNum2.getText().length()>0){
                
                boolean isInputValid=getInputValidation(inputNum2,"^\\d\\s(?i)(lb)\\b\\s[0-9]\\s(?i)(oz)|^\\d+(\\.\\d{1,10})?\\s(?i)(lb)\\b$"); 
                
                
                if(isInputValid){
                    
                    
                    String poundString= inputNum2.getText().toLowerCase().trim();
                    
                    if(poundString.matches("|^\\d+(\\.\\d{1,10})?\\s(?i)(lb)\\b$")){
                    poundString=poundString.replace(" lb","");                        
                    }
                    else{
                    poundString=poundString.replace(" lb ",".");
                    poundString=poundString.replace(" oz","");                        
                    }
                    

                    double kiloString=Double.parseDouble(poundString);
                    double lbozval=kiloString/2.205;
                    String lbozString=df.format(lbozval);
                    inputNum1.setText(lbozString+" KG");
                    
                    
                    
                }
                else{
                    errorText.setText("Invalid Input in Pounds. For eg. correct input should be 25 lbs 5 oz");
                }
                
                
                
                
               
            }
              else if(inputNum1.getText().length()==0 && inputNum2.getText().length()==0){
                errorText.setText("Please enter an input");
            }
            
            
        }
    }
    
    @FXML
    private void clearTextFields(MouseEvent event) {
        inputNum1.clear();
        inputNum2.clear();
    }
    
    @FXML
    private void convertTemperature(ActionEvent event) {
        
        resetControls(inputNum1,inputNum2,heightRadio,massRadio,"Centigrade","Fahrenheit","32 C","64 F");
       
    }
    
    @FXML
    private void convertHeight(ActionEvent event) {
        
        resetControls(inputNum1,inputNum2,tempRadio,massRadio,"Meter","Feet","1.85 m","6 ft 1 in");
        
    }
    
    @FXML
    private void convertMass(ActionEvent event) {
        
         resetControls(inputNum1,inputNum2,heightRadio,tempRadio,"Kilogram","Pounds","3.69 kg","3 lb 2 oz");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inputNum1.setOnMouseClicked(e -> {
            inputNum1.setText("");
            inputNum2.setText("");
        });
        inputNum2.setOnMouseClicked(e -> {
            inputNum1.setText("");
            inputNum2.setText("");
        });
        btnConvert.setDefaultButton(true);
    }    

    private boolean getInputValidation(TextField tf1, String reg) {
                Pattern p = Pattern.compile(reg);
                Matcher m = p.matcher(tf1.getText().toLowerCase().trim());
                if(m.find()){
                    return true;
                }
                else{
                   return false;
                }
    }

    private void resetControls(TextField tf1, TextField tf2, RadioButton r1, RadioButton r2, String str1, String str2, String hint1, String hint2) {
       
        
        
        tf1.setPromptText(hint1);
        tf2.setPromptText(hint2);
        tf1.setText("");
        tf2.setText("");
        r1.setSelected(false);
        r2.setSelected(false);
        label1.setText(str1);
        label2.setText(str2);
        errorText.setText("");
        
    }
    
}
