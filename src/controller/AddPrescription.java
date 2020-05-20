package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import model.*;
import model.dBConnection.CommonMethods;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class AddPrescription implements Initializable {

    private User currentUser;
    CommonMethods commonMethods = new CommonMethods();
    UserCommon userCommon = new UserCommon();
    java.util.Date date = new java.util.Date();
    Doctor currentDoctor;
    Patient currentPatient;
    User temp;
    List<PrescriptionLine> prescrLines = new ArrayList<>();
    List<Prescription> prescrList;
    String header = "Add Prescription Error";

    @FXML
    private Tab currentPrescriptionTab;

    @FXML
    private Label ssnLabel, nameLabel, ssnLabel1, nameLabel1, currentDateLabel;

    @FXML
    private Button addPrescriptionsButton, deletePrescriptionsButton, cancelButton, cancelButton1;

    @FXML
    private TextArea diagnosisTextArea;

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
        currentPrescriptionTab.setOnSelectionChanged(event -> currentPrescriptionLineInitialize());

        addPrescriptionsButton.setOnAction(event -> {
            try {
                handleAddPrescriptionsButton();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        deletePrescriptionsButton.setOnAction(event -> {
            try {
                handleDeletePrescriptionButton();
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
        temp = commonMethods.getUser(ssn);
        loadData();
    }

    public void loadData() {
        currentPatient = new Patient(temp.getSsn(), temp.getFirstName(), temp.getLastName(), temp.getBDate(), temp.getZipCode(), temp.getCity(), temp.getAddress(), temp.getEmail(), temp.getPhoneNumber(), temp.getPassword());
        temp = currentUser;
        currentDoctor = new Doctor(temp.getSsn(), temp.getFirstName(), temp.getLastName(), temp.getBDate(), temp.getZipCode(), temp.getCity(), temp.getAddress(), temp.getEmail(), temp.getPhoneNumber(), temp.getPassword());
        ssnLabel.setText(currentPatient.getSsn());
        ssnLabel1.setText(currentPatient.getSsn());
        nameLabel.setText(currentPatient.getLastName() + ", " + currentPatient.getFirstName());
        nameLabel1.setText(currentPatient.getLastName() + ", " + currentPatient.getFirstName());
        currentDateLabel.setText(String.valueOf(date));
        prescrList = commonMethods.getPrescriptionList(currentPatient);
    }

    @FXML
    private void handleCancelButton(ActionEvent ae) throws IOException {
        userCommon.switchScene(ae, "/view/doctorView.fxml");
    }

    @FXML
    private void handleAddPrescriptionsButton() {
        try {
            java.sql.Date startDate = new java.sql.Date(date.getTime());
            java.sql.Date endDate = java.sql.Date.valueOf(endDatePicker.getValue());
            if (!(prescrLines.isEmpty())) {
                if (endDate.after(startDate)) {
                    if (!diagnosisTextArea.getText().isEmpty()) {
                        try {
                            int id = commonMethods.getLastId(Prescription.class) + 1;
                            for (PrescriptionLine pl :
                                    prescrLines) {
                                pl.setPrescId(id);
                            }
                            Prescription p = new Prescription(id, currentDoctor, currentPatient, startDate, endDate, diagnosisTextArea.getText(), prescrLines);
                            commonMethods.addPrescription(p);

                            currentPrescriptionLineInitialize();
                            setEmpty();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        Validation.alertPopup("Unable to add prescription without adding diagnosis", "Create a diagnosis message.", header);
                    }
                } else {
                    Validation.alertPopup("Unable to add prescription without choosing correct end date", "Change the end date to be after the start date.", header);
                }
            } else {
                Validation.alertPopup("Unable to add prescription without choosing drug quantity", "Change the quantity value to prescribe amount.", header);
            }
        } catch (Exception ignroed) {
            Validation.alertPopup("Unable to add prescription without choosing an end date", "Choose an end date in the menu in the top left.", header);
        }
        currentPrescriptionInitialize();
    }

    @FXML
    private void handleDeletePrescriptionButton() {
        PrescriptionLine pl = currentPrescriptionLineTable.getSelectionModel().getSelectedItem();
        if (commonMethods.getPrescriptionLineList(pl.getPrescId(), currentPatient).size() > 1) {
            commonMethods.deletePrescriptionLine(pl);
            currentPrescriptionLineInitialize();
            currentPrescriptionsTable.getItems().clear();
        } else {
            commonMethods.deletePrescriptionLine(pl);
            commonMethods.deletePrescription(pl, currentUser);
            currentPrescriptionLineInitialize();
            currentPrescriptionsTable.getItems().clear();
        }

    }

    private void prescriptionInitialize() {
        aArticle.setCellValueFactory(new PropertyValueFactory<>("articleNo"));
        aDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        aName.setCellValueFactory(new PropertyValueFactory<>("name"));
        aProducer.setCellValueFactory(new PropertyValueFactory<>("producer"));
        aSize.setCellValueFactory(new PropertyValueFactory<>("packageSize"));
        aAmount.setCellValueFactory(new PropertyValueFactory<>("quantityReserved"));

        ObservableList<Medicine> medicineList = FXCollections.observableArrayList(commonMethods.getMedicineList(true, true));

        addPrescriptionTable.setItems(medicineList);
        currentPrescriptionLineTable.setOnMouseClicked(event -> currentPrescriptionInitialize());
    }

    private Integer getSelectedRowID() {
        return currentPrescriptionLineTable.getSelectionModel().getSelectedItem().getPrescId();
    }


    private void currentPrescriptionInitialize() {
        aStartDate1.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        aEndDate1.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        aDiagnosis1.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));

        try {
            Integer prescrID = getSelectedRowID();

            ObservableList<Prescription> prescriptionList = FXCollections.observableArrayList(commonMethods.getPrescriptionList(currentPatient));
            prescriptionList.removeIf(p -> p.getId() != prescrID);

            currentPrescriptionsTable.setItems(prescriptionList);
        } catch (Exception ignored) {
        }
    }

    @FXML
    private void currentPrescriptionLineInitialize() {
        aprescrID1.setCellValueFactory(new PropertyValueFactory<>("prescId"));
        aName1.setCellValueFactory(new PropertyValueFactory<>("name"));
        aAmount1.setCellValueFactory(new PropertyValueFactory<>("quantityPrescribed"));
        ObservableList<PrescriptionLine> prescriptionLineList = FXCollections.observableArrayList(commonMethods.getPrescriptionLineList(0, currentPatient));

        prescriptionLineList.removeIf(pl -> !pl.getPatient().getSsn().equals(currentPatient.getSsn()));

        currentPrescriptionLineTable.setItems(prescriptionLineList);
    }

    private boolean checkNewPrescription(List<PrescriptionLine> currentPrescriptions, PrescriptionLine newPrescription) {
        for (PrescriptionLine cP :
                currentPrescriptions) {
            if (cP.getQuantityPrescribed() != newPrescription.getQuantityPrescribed()) {
                return true;
            }
        }
        return false;

    }

    private int nextPrscID() {
        int i = 1;
        for (Prescription p :
                prescrList) {
            System.out.println(p);
            i++;
        }
        i++;
        return i;
    }

    private void setEmpty() {
        prescriptionInitialize();
        diagnosisTextArea.setText("");
        endDatePicker.setValue(null);
    }

    private void makeaAmountEditable() {

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
                            try {
                                if ((t.getNewValue()) > 0) {

                                    PrescriptionLine pl = new PrescriptionLine(0, currentPatient, t.getRowValue(), t.getNewValue(), 0, "");
                                    prescrLines.removeIf(n -> (n.getArticle() == pl.getArticle()));
                                    prescrLines.add(pl);

                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                });
    }
}
