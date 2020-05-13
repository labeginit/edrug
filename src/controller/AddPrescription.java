package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import controller.DoctorController;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Medicine;
import model.Patient;
import model.Prescription;
import model.User;
import model.dBConnection.CommonMethods;
import model.dBConnection.DAOPrescription;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class AddPrescription implements Initializable {

    CommonMethods commonMethods = new CommonMethods();
    UserCommon userCommon = new UserCommon();

    @FXML
    private Label ssnLabel, nameLabel, ssnLabel1, nameLabel1, currentDateLabel;

    @FXML
    private Button addPrescriptionsButton, cancelButton, cancelButton1;

    @FXML
    private TextArea DiagnosisTextArea;

    @FXML
    private TableView<Medicine> addPrescriptionTable, currentPrescriptionsTable;

    @FXML
    private TableColumn<Medicine, String> aName, aName1, aSize, aDescription, aDescription1, aProducer, aProducer1;

    @FXML
    private TableColumn<Medicine, Integer> aArticle, aArticle1;

    @FXML
    private TableColumn<DAOPrescription, Integer> aAmount, aAmount1;

    @FXML
    private TableColumn<Prescription, Date> aStartDate1, aEndDate1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        prescriptionInitialize();


        cancelButton.setOnAction(event -> {
            try {
                handleCancelButton(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        cancelButton1.setOnAction(event -> {
            try {
                handleCancelButton(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void receiveData(String ssn) {
        User currentPatient = commonMethods.getUser(ssn);
        ssnLabel.setText(currentPatient.getSsn());
        ssnLabel1.setText(currentPatient.getSsn());
        nameLabel.setText(currentPatient.getLastName() + ", " + currentPatient.getFirstName());
        nameLabel1.setText(currentPatient.getLastName() + ", " + currentPatient.getFirstName());
    }

    @FXML
    private void handleCancelButton(ActionEvent ae) throws IOException {
        userCommon.switchScene(ae, "/view/doctorView.fxml");
    }

    public void prescriptionInitialize () {
        aArticle.setCellValueFactory(new PropertyValueFactory<>("articleNo"));
        aDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        aName.setCellValueFactory(new PropertyValueFactory<>("name"));
        aProducer.setCellValueFactory(new PropertyValueFactory<>("producer"));
        aSize.setCellValueFactory(new PropertyValueFactory<>("packageSize"));
        aAmount.setCellValueFactory(new PropertyValueFactory<>("quantity"));


        ObservableList<Medicine> medicineList = FXCollections.observableArrayList(commonMethods.getMedicineList());
        medicineList.addAll(FXCollections.observableArrayList(commonMethods.getMedicineList()));

        addPrescriptionTable.setItems(medicineList);
    }
}
