package com.example.java_lab07;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.Comparator;
import java.util.Optional;

public class Controller {
    @FXML
    private ComboBox<String> townsComboBox;

    @FXML
    private TextField searchByNameFiels;

    @FXML
    private RadioButton cenaRadioButton;

    @FXML
    private RadioButton nazwaRadioButton;

    @FXML
    private RadioButton rokRadioButton;

    @FXML
    private CheckBox showAvailableCheckBox;

    @FXML
    private Button zarezerwujButton;

    @FXML
    private Button searchOkButton;

    @FXML
    private Text reservationText;

    @FXML
    private TableView<Vehicle> vehicleTableView;

    private CarShowroomContainer container;

    public void handleReservedButton() {
        Vehicle selectedVehicle = vehicleTableView.getSelectionModel().getSelectedItem();
        if (selectedVehicle != null) {
            if (selectedVehicle.getAvailability() == ItemAvailability.AVAILABLE) {
                Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationAlert.setTitle("Potwierdzenie rezerwacji");
                confirmationAlert.setHeaderText("Czy na pewno chcesz zarezerwować wybrany samochód?");
                confirmationAlert.setContentText(selectedVehicle.getBrand() + " " + selectedVehicle.getModel());

                Optional<ButtonType> result = confirmationAlert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    selectedVehicle.setAvailability(ItemAvailability.RESERVED);
                    reservationText.setText("Pojazd zarezerwowano pomyślnie.\nSkontaktuj się z wybranym salonem w celu dokonania transakcji");
                    refreshTable();
                }
            } else {
                reservationText.setText("Wybrany samochód jest już zarezerwowany.");
            }
        } else {
            reservationText.setText("Nie wybrano żadnego samochodu.");
        }
    }

    @FXML
    private void handleSearchOkButton() {
        String searchText = searchByNameFiels.getText().trim();
        updateTable(searchText);
    }


    private class VehicleTableRow extends TableRow<Vehicle> {
        @Override
        protected void updateItem(Vehicle vehicle, boolean empty) {
            super.updateItem(vehicle, empty);

            if (vehicle == null || empty) {
                setTooltip(null);
            } else {
                String tooltipText = "Salon: " + vehicle.getShowroom().getNazwaSalonu() +
                        "\nPrzebieg: " + vehicle.getPrzebieg() + " km" +
                        "\nSilnik: " + vehicle.getPojemnoscSilnika() + " l";

                Tooltip tooltip = new Tooltip(tooltipText);
                setTooltip(tooltip);
            }
        }
    }

    @FXML
    private void initialize() {
        container = new CarShowroomContainer();
        DataGenerator.getInstance().generateData(container);

        // Konfiguracja kolumn w TableView
        TableColumn<Vehicle, String> brandColumn = new TableColumn<>("Marka");
        brandColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBrand()));
        brandColumn.prefWidthProperty().bind(vehicleTableView.widthProperty().multiply(0.2)); // 20% szerokości tabeli

        TableColumn<Vehicle, String> modelColumn = new TableColumn<>("Model");
        modelColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModel()));
        modelColumn.prefWidthProperty().bind(vehicleTableView.widthProperty().multiply(0.2)); // 20% szerokości tabeli

        TableColumn<Vehicle, Integer> yearColumn = new TableColumn<>("Rok Produkcji");
        yearColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getYear()).asObject());
        yearColumn.prefWidthProperty().bind(vehicleTableView.widthProperty().multiply(0.2)); // 20% szerokości tabeli

        TableColumn<Vehicle, String> locationColumn = new TableColumn<>("Lokalizacja");
        locationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLocation()));
        locationColumn.prefWidthProperty().bind(vehicleTableView.widthProperty().multiply(0.2)); // 20% szerokości tabeli

        TableColumn<Vehicle, Double> priceColumn = new TableColumn<>("Cena");
        priceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
        priceColumn.prefWidthProperty().bind(vehicleTableView.widthProperty().multiply(0.2)); // 20% szerokości tabeli

        TableColumn<Vehicle, String> availabilityColumn = new TableColumn<>("Dostępność");
        availabilityColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAvailability().toString()));
        availabilityColumn.prefWidthProperty().bind(vehicleTableView.widthProperty().multiply(0.2));

        vehicleTableView.getColumns().clear();
        vehicleTableView.getColumns().addAll(brandColumn, modelColumn, yearColumn, locationColumn, priceColumn, availabilityColumn);
        vehicleTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        vehicleTableView.setRowFactory(tv -> new VehicleTableRow());

        townsComboBox.getItems().add("Wszystkie miasta");
        for (CarShowroom showroom : container.getSalony().values()) {
            String townName = showroom.getNazwaSalonu();
            if (!townsComboBox.getItems().contains(townName)) {
                townsComboBox.getItems().add(townName);
            }
        }
        townsComboBox.getSelectionModel().select("Wszystkie miasta");
        updateTable("");
    }
    private void refreshTable() {
        vehicleTableView.refresh();
    }
    private void updateTable(String searchText) {
        ObservableList<Vehicle> filteredList = FXCollections.observableArrayList();
        String selectedTown = townsComboBox.getSelectionModel().getSelectedItem();
        boolean showAvailableOnly = showAvailableCheckBox.isSelected();

        for (CarShowroom showroom : container.getSalony().values()) {
            if (selectedTown == null || selectedTown.equals("Wszystkie miasta") || showroom.getNazwaSalonu().equals(selectedTown)) {
                for (Vehicle vehicle : showroom.getLista()) {
                    if ((searchText.isEmpty() || vehicle.getNazwa().toLowerCase().contains(searchText.toLowerCase()))
                            && (!showAvailableOnly || vehicle.getAvailability() == ItemAvailability.AVAILABLE)) {
                        filteredList.add(vehicle);
                    }
                }
            }
        }

        vehicleTableView.setItems(filteredList);
    }

    @FXML
    private void handleShowAvailableCheckBox() {
        updateTable(searchByNameFiels.getText().trim());
    }

    @FXML
    private void handleSortRadioButton() {
        ToggleGroup toggleGroup = new ToggleGroup();

        nazwaRadioButton.setToggleGroup(toggleGroup);
        cenaRadioButton.setToggleGroup(toggleGroup);
        rokRadioButton.setToggleGroup(toggleGroup);

        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue.equals(nazwaRadioButton)) {
                    sortByNazwa();
                } else if (newValue.equals(cenaRadioButton)) {
                    sortByCena();
                } else if (newValue.equals(rokRadioButton)) {
                    sortByRok();
                }
            }
        });
    }

    private void sortByNazwa() {
        ObservableList<Vehicle> sortedList = vehicleTableView.getItems().sorted(Comparator.comparing(Vehicle::getNazwa));
        vehicleTableView.setItems(sortedList);
    }

    private void sortByCena() {
        ObservableList<Vehicle> sortedList = vehicleTableView.getItems().sorted(Comparator.comparingDouble(Vehicle::getPrice));
        vehicleTableView.setItems(sortedList);
    }

    private void sortByRok() {
        ObservableList<Vehicle> sortedList = vehicleTableView.getItems().sorted(Comparator.comparingInt(Vehicle::getYear));
        vehicleTableView.setItems(sortedList);
    }

    @FXML
    private void handleTownSelection() {
        String selectedTown = townsComboBox.getSelectionModel().getSelectedItem();
        updateTable("");
    }

}