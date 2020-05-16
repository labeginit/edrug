package controller;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import controller.DoctorController;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
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
    ObservableList<PrescriptionLine> prescrLines = FXCollections.observableArrayList();
    ObservableList<Prescription> prescrList = FXCollections.observableArrayList();
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
    private TableView<Medicine> addPrescriptionTable;

    @FXML
    private TableView<PrescriptionLine> currentPrescriptionLineTable;

    @FXML
    private TableView<Prescription> currentPrescriptionsTable;

    @FXML
    private TableColumn<Medicine, String> aName, aSize, aDescription, aProducer;

    @FXML
    private TableColumn<Medicine, Integer> aArticle, aAmount;

    @FXML
    private TableColumn<PrescriptionLine, Integer> aprescrID1, aAmount1;

    @FXML
    private TableColumn<PrescriptionLine, String> aName1;

    @FXML
    private TableColumn<Prescription, Date> aStartDate1, aEndDate1;

    @FXML
    private TableColumn<Prescription, String> aDiagnosis1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentUser = UserSingleton.getOurInstance().getUser();
        prescriptionInitialize();
        currentPrescriptionLineInitialize();

        currentPrescriptionLineTable.setOnMouseClicked(event -> {
            try {
                currentPrescriptionInitialize();
            } catch (Exception ignored) {
            }
        });

        addPrescriptionsButton.setOnAction(event -> {
            try {
                handleAddPrescriptionsButton();
            } catch (Exception ignored) {
            }
        });

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
        makeaAmountEditable();
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
        int i = 0;
        while (true) {
            Integer value = (Integer) addPrescriptionTable.getColumns().get(5).getCellObservableValue(i).getValue();
            System.out.println(value);
            i++;
            if (i == 10) {
                break;
            }
        }
        //if (checkNewPrescription(currentPrescriptions, )) {


        currentPrescriptionInitialize();
        //} else {
        //Validation.alertPopup("No information was updated", "Unable to add prescription", "Prescription Error");
    }

    public void prescriptionInitialize() {

        aArticle.setCellValueFactory(new PropertyValueFactory<>("articleNo"));
        aDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        aName.setCellValueFactory(new PropertyValueFactory<>("name"));
        aProducer.setCellValueFactory(new PropertyValueFactory<>("producer"));
        aSize.setCellValueFactory(new PropertyValueFactory<>("packageSize"));
        aAmount.setCellValueFactory(new PropertyValueFactory<>("quantityReserved"));

        ObservableList<Medicine> medicineList = FXCollections.observableArrayList(commonMethods.getMedicineList());

        addPrescriptionTable.setItems(medicineList);
    }

    private Integer getSelectedRowID() {
        return currentPrescriptionLineTable.getSelectionModel().getSelectedItem().getPrescId();
    }


    public void currentPrescriptionInitialize() {
        aStartDate1.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        aEndDate1.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        aDiagnosis1.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));


        Integer prescrID = getSelectedRowID();

        ObservableList<Prescription> prescriptionList = FXCollections.observableArrayList(commonMethods.getPrescriptionList(currentPatient));
        prescriptionList.removeIf(p -> p.getId() != prescrID);

        currentPrescriptionsTable.setItems(prescriptionList);
    }

    public void currentPrescriptionLineInitialize() {
        aprescrID1.setCellValueFactory(new PropertyValueFactory<>("prescId"));
        aName1.setCellValueFactory(new PropertyValueFactory<>("name"));
        aAmount1.setCellValueFactory(new PropertyValueFactory<>("quantityPrescribed"));

        ObservableList<PrescriptionLine> prescriptionLineList = FXCollections.observableArrayList(commonMethods.getPrescriptionLineList(0, currentPatient));

        currentPrescriptionLineTable.setItems(prescriptionLineList);
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
        List<Prescription> list = commonMethods.getPrescriptionList(currentPatient);
        for (Prescription p :
                list) {
            allID.add(p.getId());
        }
        return allID;
    }

    public void makeaAmountEditable() {

        addPrescriptionTable.setEditable(true);
        aAmount.setCellFactory(TextFieldTableCell.<Medicine, Integer>forTableColumn(new IntegerStringConverter()));
        aAmount.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Medicine, Integer>>() {

                    @Override
                    public void handle(TableColumn.CellEditEvent<Medicine, Integer> t) {
                        if (Validation.isQuantityMedicine(t.getNewValue().toString())) {

                            ((Medicine) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                            ).setQuantity(t.getNewValue());
                            
                        }
                    }
                });
    }
}
