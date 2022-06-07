package Controllers;

import DatabaseConnector.DBConnector;
import Hospital.Surgeries;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import java.util.function.Predicate;

public class SurgeriesController implements Initializable {

    @FXML
    private TableView<Surgeries> table;

    @FXML
    private TableColumn<Surgeries, String> surgerNameTable;

    @FXML
    private TableColumn<Surgeries, String> surgerIDTable;

    @FXML
    private JFXTextField sName;

    @FXML
    private JFXTextField surgeryID;

    @FXML
    private JFXTextField surgeryPrice;

    @FXML
    private JFXButton insertSurgery;

    @FXML
    private JFXButton clearInsert;

    @FXML
    private JFXComboBox<String> selectSurgeriesDelete;

    @FXML
    private JFXButton deleteSurgery;

    @FXML
    private JFXButton clearDelete;

    @FXML
    private JFXComboBox<String> SelectUpSurgeryName;

    @FXML
    private JFXTextField newPrice;

    @FXML
    private JFXButton updateSurgeries;

    @FXML
    private JFXButton clearUpdatePrice;

    ArrayList<Surgeries> SurgeryList;
    ArrayList<String> SurgeryNameList;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        clearAll();
        try {
            SurgeryList = new ArrayList<>();
            SurgeryNameList = new ArrayList<>();
            getData();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        assignComboBoxesValues();

    }
    @FXML
    void clearDelete(ActionEvent event) {
        selectSurgeriesDelete.setValue(null);
    }

    @FXML
    void clearInsert(ActionEvent event) {
        sName.clear();
        surgeryID.clear();
        surgeryPrice.clear();
    }

    @FXML
    void clearUpdatePrice(ActionEvent event) {
        SelectUpSurgeryName.setValue(null);
        newPrice.clear();
    }

    public void clearAll(){
        selectSurgeriesDelete.setValue(null);
        sName.clear();
        surgeryID.clear();
        surgeryPrice.clear();
        SelectUpSurgeryName.setValue(null);
        newPrice.clear();
    }

    @FXML
    void deleteSurgery(ActionEvent event) {
        assignComboBoxesValues();
        Predicate<Surgeries> pr = a -> (a.getSurgery_name().equals(selectSurgeriesDelete.getValue()));
        SurgeryList.removeIf(pr);
        SurgeryNameList.remove(selectSurgeriesDelete.getValue());
        /*for (Surgeries surgeri : SurgeryList){
            System.out.println(surgeri.getSurgery_name());
        }*/
        assignComboBoxesValues();
    }

    @FXML
    void insertSurgery(ActionEvent event) {
        boolean flag = true;
        if (sName.getText().isEmpty() || sName.getText().length() > 32) {
            sName.setUnFocusColor(Color.RED);
            sName.clear();
            flag = false;
        }else{
            sName.setUnFocusColor(Color.BLACK);
        }
        if(surgeryID.getText().isEmpty() || !isNumeric(surgeryID.getText()) || !equaledID()){
            surgeryID.setUnFocusColor(Color.RED);
            surgeryID.clear();
            flag = false;
        }else {
            surgeryID.setUnFocusColor(Color.BLACK);
        }
        if(surgeryPrice.getText().isEmpty() || !isFloat(surgeryPrice.getText())){
            surgeryPrice.setUnFocusColor(Color.RED);
            surgeryPrice.clear();
            flag = false;
        }else {
            surgeryPrice.setUnFocusColor(Color.BLACK);
        }
        if(flag){
            SurgeryList.add(new Surgeries(Integer.parseInt(surgeryID.getText()),
                    sName.getText(),
                    Float.parseFloat(surgeryPrice.getText())));
            SurgeryNameList.add(sName.getText());
            //System.out.println(SurgeryList.get(SurgeryList.size()-1).getSurgery_name()+SurgeryList.get(SurgeryList.size()-1).getSurgery_id());
            //System.out.print(surgeryID.getText() + "..." + sName.getText() + "..." + surgeryPrice.getText());
            assignComboBoxesValues();
        }


    }

    @FXML
    void updateSurgeries(ActionEvent event) {
        boolean flag = true;
        if(newPrice.getText().isEmpty() || !isFloat(newPrice.getText())){
            newPrice.setUnFocusColor(Color.RED);
            newPrice.clear();
            flag = false;
        }else {
            newPrice.setUnFocusColor(Color.BLACK);
        }
        if(flag){
            for (Surgeries surgeri : SurgeryList){
                if(surgeri.getSurgery_name().equals(SelectUpSurgeryName.getValue())){
                    //System.out.println(surgeri.getSurgery_price());
                    surgeri.setSurgery_price(Float.parseFloat(newPrice.getText()));
                    //System.out.println(surgeri.getSurgery_price());
                }
            }
        }
        assignComboBoxesValues();
    }

    @FXML
    void selectSurgeriesToDelete(ActionEvent event) {
        selectSurgeriesDelete.setItems(FXCollections.observableArrayList(SurgeryNameList));
    }

    @FXML
    void SelectToUpSurgeryName(ActionEvent event) {
        SelectUpSurgeryName.setItems(FXCollections.observableArrayList(SurgeryNameList));
    }

    private void getData() throws SQLException, ClassNotFoundException, ParseException {
        String SQL;

        DBConnector.connectDB();
        System.out.println("Connection established");

        SQL = "select *\n" + "from Surgeries;";
        Statement stmt = DBConnector.getCon().createStatement();
        ResultSet rs = stmt.executeQuery(SQL);

        while (rs.next()) {
            SurgeryList.add( new Surgeries(Integer.parseInt(rs.getString(1)),
                                rs.getString(2),
                                Float.parseFloat(rs.getString(3))));
            SurgeryNameList.add(rs.getString(2));
        }

        rs.close();
        stmt.close();

        DBConnector.getCon().close();
    }

    public void assignComboBoxesValues() {
        selectSurgeriesDelete.setItems(FXCollections.observableArrayList(SurgeryNameList));
        SelectUpSurgeryName.setItems(FXCollections.observableArrayList(SurgeryNameList));
    }

    public static boolean isNumeric(String string) {
        int intValue;
        if(string == null || string.equals("")) {
            return false;
        }
        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
        }
        return false;
    }

    public static boolean isFloat(String string) {
        float floatValue;
        if(string == null || string.equals("")) {
            return false;
        }
        try {
            floatValue = Float.parseFloat(string);
            return true;
        } catch (NumberFormatException e) {
        }
        return false;
    }
    public boolean equaledID(){
        boolean flag = true;
        for (Surgeries surgeri : SurgeryList){
            if(surgeryID.getText().equals(String.valueOf(surgeri.getSurgery_id()))){
                flag = false;
                break;
            }
        }
        return flag;
    }

}
