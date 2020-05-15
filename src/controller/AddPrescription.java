package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import controller.DoctorController;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;
import model.dBConnection.CommonMethods;
import model.dBConnection.DAOPrescription;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class AddPrescription implements Initializable {

    private User currentUser;
    CommonMethods commonMethods = new CommonMethods();
    UserCommon userCommon = new UserCommon();
    java.util.Date date = new java.util.Date();
    User currentPatient;

    @FXML
    private Label ssnLabel, nameLabel, ssnLabel1, nameLabel1, currentDateLabel;

    @FXML
    private Button addPrescriptionsButton, cancelButton, cancelButton1;

    @FXML
    private TextArea DiagnosisTextArea;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private TableView<Medicine> addPrescriptionTable, currentPrescriptionsTable;

    @FXML
    private TableColumn<Medicine, String> aName, aName1, aSize, aDescription, aDescription1, aProducer, aProducer1;

    @FXML
    private TableColumn<Medicine, Integer> aArticle, aArticle1;

    @FXML
    private TableColumn<PrescriptionLine, Integer> aAmount;

    @FXML
    private TableColumn<PrescriptionLine, Integer> aAmount1;

    @FXML
    private TableColumn<Prescription, Date> aStartDate1, aEndDate1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentUser = UserSingleton.getOurInstance().getUser();
        prescriptionInitialize();
        currentPrescriptionInitialize();

        addPrescriptionsButton.setOnAction(event -> handleAddPrescriptionsButton());


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
        currentPatient = commonMethods.getUser(ssn);
        ssnLabel.setText(currentPatient.getSsn());
        ssnLabel1.setText(currentPatient.getSsn());
        nameLabel.setText(currentPatient.getLastName() + ", " + currentPatient.getFirstName());
        nameLabel1.setText(currentPatient.getLastName() + ", " + currentPatient.getFirstName());
        currentDateLabel.setText(String.valueOf(date));
    }

    @FXML
    private void handleCancelButton(ActionEvent ae) throws IOException {
        userCommon.switchScene(ae, "/view/doctorView.fxml");
    }

    @FXML
    private void handleAddPrescriptionsButton() {
        List<PrescriptionLine> currentPrescriptions = commonMethods.getPrescriptionList(allPrescribedID(), currentUser);
        List<PrescriptionLine> prescriptionLine = instantiatePL();

        if (checkNewPrescription(currentPrescriptions, )) {


            currentPrescriptionInitialize();
        } else {
            Validation.alertPopup("No information was updated", "Unable to add prescription", "Prescription Error");
        }
    }

    public void prescriptionInitialize() {


        aArticle.setCellValueFactory(new PropertyValueFactory<>("articleNo"));
        aDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        aName.setCellValueFactory(new PropertyValueFactory<>("name"));
        aProducer.setCellValueFactory(new PropertyValueFactory<>("producer"));
        aSize.setCellValueFactory(new PropertyValueFactory<>("packageSize"));
        aAmount.setCellValueFactory(new PropertyValueFactory<>("quantityPrescribed"));

        List<Integer> prsID = allPrescribedID();
        ObservableList<Medicine> medicineList = FXCollections.observableArrayList(commonMethods.getMedicineList());
        ObservableList<PrescriptionLine> prescribedAmount = FXCollections.observableArrayList(commonMethods.getPrescriptionList(prsID, currentPatient));
        medicineList.addAll(FXCollections.observableArrayList(commonMethods.getMedicineList()));

        addPrescriptionTable.setItems(medicineList);
    }

    public void currentPrescriptionInitialize() {
        aArticle1.setCellValueFactory(new PropertyValueFactory<>("articleNo"));
        aDescription1.setCellValueFactory(new PropertyValueFactory<>("description"));
        aName1.setCellValueFactory(new PropertyValueFactory<>("name"));
        aProducer1.setCellValueFactory(new PropertyValueFactory<>("producer"));
        aStartDate1.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        aEndDate1.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        aAmount1.setCellValueFactory(new PropertyValueFactory<>("quantityPrescribed"));

        ObservableList<Medicine> medicineList = FXCollections.observableArrayList(commonMethods.getMedicineList());
        medicineList.addAll(FXCollections.observableArrayList(commonMethods.getMedicineList()));

        currentPrescriptionsTable.setItems(medicineList);
    }

    public boolean checkNewPrescription(List<PrescriptionLine> currentPrescriptions, PrescriptionLine newPrescription) {
        for (PrescriptionLine cP :
                currentPrescriptions) {
            if (cP.getQuantityPrescribed() != newPrescription.getQuantityPrescribed()) {
                return true;
            }
        }
        return false;

    }

    public List<Integer> allPrescribedID() {
        List<Integer> allID = new ArrayList<>();
        List<Prescription> list = commonMethods.getPrescription(currentPatient);
        for (Prescription p :
                list) {
            allID.add(p.getId());
        }
        return allID;
    }

    public List<PrescriptionLine> instantiatePL() {

        return
    }
}
