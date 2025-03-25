package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class Swing extends JFrame {
    private final CarShowroomContainer carShowroomContainer;
    private JPanel appPanel;
    private JTable centersTable;
    private JTable vehiclesTable;
    private JButton addCenterButton;
    private JButton removeCenterButton;
    private JButton addVehicleButton;
    private JButton removeVehicleButton;
    private JButton sortCentersButton;
    private JButton sortVehiclesButton;
    private DefaultTableModel centersTableModel;
    private DefaultTableModel vehiclesTableModel;

    public Swing() {
        carShowroomContainer = new CarShowroomContainer();
        initializeUI();
        initializeEventListeners();
        DataGenerator.getInstance().generateData(carShowroomContainer);
        updateCentersTable(new ArrayList<>(carShowroomContainer.getSalony().values()));
    }

    private void initializeUI() { //user inteface
        setContentPane(appPanel);
        setTitle("Car Showroom Management");
        setSize(1300, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        //parametry do konstruktora tabeli to obiekty string z nazwami kolumn i ilosc wierzy poczatkowo
        centersTableModel = new DefaultTableModel(new Object[]{"Nazwa", "Maksymalna Pojemność"}, 0);
        vehiclesTableModel = new DefaultTableModel(new Object[]{"Marka", "Model", "Stan", "Cena", "Rok produkcji", "Przebieg", "Pojemność silnika"}, 0);
        centersTable.setModel(centersTableModel);
        vehiclesTable.setModel(vehiclesTableModel);
    }

    private void initializeEventListeners() {
        centersTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                handleCenterSelection();
            }
        });

        addCenterButton.addActionListener(e -> addCenter());
        removeCenterButton.addActionListener(e -> removeCenter());
        addVehicleButton.addActionListener(e -> addNewVehicle());
        removeVehicleButton.addActionListener(e -> removeVehicle());

        sortVehiclesButton.addActionListener(e -> sortVehicles());
        sortCentersButton.addActionListener(e -> sortCenters());
    }

    private void handleCenterSelection() {
        if (!centersTable.getSelectionModel().isSelectionEmpty()) {
            int selectedRow = centersTable.getSelectedRow();
            if (selectedRow >= 0) { // Sprawdzenie, czy rzeczywiście coś jest zaznaczone
                String centerName = (String) centersTableModel.getValueAt(selectedRow, 0);
                CarShowroom selectedShowroom = carShowroomContainer.getSalony().get(centerName);
                updateVehicleTable(selectedShowroom);
            }
        }
    }

    private void sortVehicles() {
        int selectedRow = centersTable.getSelectedRow();
        if (selectedRow != -1) {
            String showroomName = (String) centersTableModel.getValueAt(selectedRow, 0);
            CarShowroom showroom = carShowroomContainer.getSalony().get(showroomName);
            if (showroom != null) {
                showroom.sortByName();
                updateVehicleTable(showroom);
            }
        } else {
            JOptionPane.showMessageDialog(null /*srodek ekranu*/, "Select a showroom first.");
        }
    }

    private void sortCenters() {
        updateCentersTable(carShowroomContainer.sortCentersByLoad());
    }

    private void addCenter() {
        JTextField nameField = new JTextField();
        JTextField maxCapacityField = new JTextField();
        Object[] message = {
                "Nazwa salonu:", nameField,
                "Maksymalna pojemność:", maxCapacityField
        };

        //OptionPane to klasa do okien dialogowych
        int option = JOptionPane.showConfirmDialog(null, message, "Dodaj nowy salon", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText();
                int maxCapacity = Integer.parseInt(maxCapacityField.getText());
                CarShowroom newShowroom = new CarShowroom(name, maxCapacity);
                carShowroomContainer.addCenter(newShowroom);
                centersTableModel.addRow(new Object[]{name, maxCapacity});
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid number format");
            }
        }
    }

    private void removeCenter() {
        int selectedRow = centersTable.getSelectedRow();
        if (selectedRow >= 0) {
            carShowroomContainer.removeCenter((String) centersTableModel.getValueAt(selectedRow, 0));
            centersTableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a center to remove.");
        }
    }

    private void addNewVehicle() {
        int selectedRow = centersTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a showroom first.");
            return;
        }
        String showroomName = (String) centersTableModel.getValueAt(selectedRow, 0);
        CarShowroom showroom = carShowroomContainer.getSalony().get(showroomName);
        JTextField markaField = new JTextField();
        JTextField modelField = new JTextField();
        JComboBox<ItemCondition> stanField = new JComboBox<>(ItemCondition.values()); //to do wybierania stanu auta
        JTextField cenaField = new JTextField();
        JTextField rokProdukcjiField = new JTextField();
        JTextField przebiegField = new JTextField();
        JTextField pojemnoscSilnikaField = new JTextField();

        Object[] message = {
                "Marka:", markaField,
                "Model:", modelField,
                "Stan:", stanField,
                "Cena:", cenaField,
                "Rok produkcji:", rokProdukcjiField,
                "Przebieg:", przebiegField,
                "Pojemność silnika:", pojemnoscSilnikaField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Dodaj nowy pojazd", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                Vehicle newVehicle = new Vehicle(
                        markaField.getText(),
                        modelField.getText(),
                        (ItemCondition) stanField.getSelectedItem(),
                        Double.parseDouble(cenaField.getText()),
                        Integer.parseInt(rokProdukcjiField.getText()),
                        Double.parseDouble(przebiegField.getText()),
                        Double.parseDouble(pojemnoscSilnikaField.getText())
                );
                showroom.addProduct(newVehicle);
                updateVehicleTable(showroom);  // Refresh the vehicle table
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid numbers");
            }
        }
    }

    private void removeVehicle() {
        int selectedRow = vehiclesTable.getSelectedRow();
        if (selectedRow >= 0) {
            String showroomName = (String) centersTableModel.getValueAt(centersTable.getSelectedRow(), 0);
            CarShowroom showroom = carShowroomContainer.getSalony().get(showroomName);
            Vehicle vehicleToRemove = showroom.getLista().get(selectedRow);
            showroom.getProduct(vehicleToRemove);
            updateVehicleTable(showroom);  // Refresh the vehicle table
        } else {
            JOptionPane.showMessageDialog(this, "Please select a vehicle to remove.");
        }
    }

    private void updateCentersTable(List<CarShowroom> sortedCenters) {
        centersTableModel.setRowCount(0);
        for (CarShowroom showroom : sortedCenters) {
            centersTableModel.addRow(new Object[]{showroom.getNazwaSalonu(), showroom.getMaksPojemnoscSalonu()});
        }
    }

    private void updateVehicleTable(CarShowroom showroom) {
        vehiclesTableModel.setRowCount(0);
        for (Vehicle v : showroom.getLista()) {
            vehiclesTableModel.addRow(new Object[]{v.marka, v.model, v.stan, v.cena, v.rokProdukcji, v.przebieg, v.pojemnoscSilnika});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Swing::createAndShowGUI); //odwołanie się do statycznej metody createAndShowGUI w klasie Swing
    }

    private static void createAndShowGUI() {
        Swing swing;
        swing = new Swing();
        swing.setVisible(true);
    }
}
