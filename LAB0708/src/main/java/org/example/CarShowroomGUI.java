package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

public class CarShowroomGUI extends JFrame {
    private Session session;
    private CarShowroomDAO showroomDAO;
    private VehicleDAO vehicleDAO;
    private JList<String> centerList;
    private DefaultListModel<String> centerListModel;
    private JTable vehicleTable;
    private DefaultTableModel vehicleTableModel;
    private CarShowroomSerializer serializer = new CarShowroomSerializer();
    private CarShowroomCSV carShowroomCSV = new CarShowroomCSV();

    public CarShowroomGUI(Session session) {
        this.session = session;
        this.showroomDAO = new CarShowroomDAO(session);
        this.vehicleDAO = new VehicleDAO(session);

        setTitle("Car Showroom Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        centerListModel = new DefaultListModel<>();
        String[] columnNames = {"Brand", "Model", "Condition", "Price", "Year", "Mileage", "Engine Capacity"};
        vehicleTableModel = new DefaultTableModel(columnNames, 0);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        centerList = new JList<>(centerListModel);
        JScrollPane centerScrollPane = new JScrollPane(centerList);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        mainPanel.add(centerScrollPane, constraints);

        vehicleTable = new JTable(vehicleTableModel);
        JScrollPane vehicleScrollPane = new JScrollPane(vehicleTable);
        constraints.gridx = 1;
        constraints.gridy = 0;
        mainPanel.add(vehicleScrollPane, constraints);

        loadCarShowrooms();

        JButton removeCenterButton = new JButton("Remove Car Showroom");
        JButton removeVehicleButton = new JButton("Remove Vehicle");
        JButton addCenterButton = new JButton("Add Car Showroom Center");
        JButton addVehicleButton = new JButton("Add New Vehicle");
        JButton serializeButton = new JButton("Serialize");
        JButton deserializeButton = new JButton("Deserialize");
        JButton exportCSVButton = new JButton("Export to CSV");
        JButton importCSVButton = new JButton("Import from CSV");

        JPanel buttonPanel = new JPanel(new GridLayout(4, 2));
        buttonPanel.add(removeCenterButton);
        buttonPanel.add(removeVehicleButton);
        buttonPanel.add(addCenterButton);
        buttonPanel.add(addVehicleButton);
        buttonPanel.add(serializeButton);
        buttonPanel.add(deserializeButton);
        buttonPanel.add(exportCSVButton);
        buttonPanel.add(importCSVButton);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(buttonPanel, constraints);

        addCenterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCarShowroom();
            }
        });

        removeCenterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeCarShowroom();
            }
        });

        addVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addVehicle();
            }
        });

        removeVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeVehicle();
            }
        });

        serializeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serializer.saveShowrooms(showroomDAO.readAll(), "data.txt");
            }
        });

        deserializeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<CarShowroom> showrooms = serializer.loadShowrooms("data.txt");
                centerListModel.clear();
                vehicleTableModel.setRowCount(0);
                for (CarShowroom showroom : showrooms) {
                    centerListModel.addElement(showroom.getNazwaSalonu());
                }
                loadVehiclesForSelectedShowroom(); // Aktualizuj tabelę samochodów po deserializacji
            }
        });

        exportCSVButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = JOptionPane.showInputDialog("Enter file name for export to CSV:");
                if (fileName != null && !fileName.trim().isEmpty()) {
                    String selectedCenter = centerList.getSelectedValue();
                    Optional<CarShowroom> carShowroom = showroomDAO.readByName(selectedCenter);
                    CarShowroomCSV.exportShowroomToCSV(carShowroom.get(), fileName);
                }
            }
        });

        importCSVButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = JOptionPane.showInputDialog("Enter file name for import from CSV:");
                if (fileName != null && !fileName.trim().isEmpty()) {
                    CarShowroom importedShowroom = CarShowroomCSV.importShowroomFromCSV(fileName);
                    if (importedShowroom != null) {
                        showroomDAO.create(importedShowroom);
                        centerListModel.clear();
                        vehicleTableModel.setRowCount(0);
                        loadCarShowrooms();
                    }
                }
            }
        });

        getContentPane().add(mainPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadCarShowrooms() {
        List<CarShowroom> showrooms = showroomDAO.readAll();
        for (CarShowroom showroom : showrooms) {
            centerListModel.addElement(showroom.getNazwaSalonu());
        }
        centerList.addListSelectionListener(e -> loadVehiclesForSelectedShowroom());
    }

    private void loadVehiclesForSelectedShowroom() {
        vehicleTableModel.setRowCount(0);
        String selectedCenter = centerList.getSelectedValue();
        if (selectedCenter != null) {
            Optional<CarShowroom> showroomOpt = showroomDAO.readByName(selectedCenter);
            if (showroomOpt.isPresent()) {
                CarShowroom showroom = showroomOpt.get();
                List<Vehicle> vehicles = showroom.getListaSamochodow();
                for (Vehicle vehicle : vehicles) {
                    Object[] rowData = {
                            vehicle.getMarka(),
                            vehicle.getModel(),
                            vehicle.getStan(),
                            vehicle.getCena(),
                            vehicle.getRokProdukcji(),
                            vehicle.getPrzebieg(),
                            vehicle.getPojemnoscSilnika()
                    };
                    vehicleTableModel.addRow(rowData);
                }
            }
        }
    }

    private void addCarShowroom() {
        String nazwa = JOptionPane.showInputDialog("Enter Car Showroom Name:");
        double pojemnosc = Double.parseDouble(JOptionPane.showInputDialog("Enter Max Capacity:"));
        CarShowroom showroom = new CarShowroom(nazwa, pojemnosc);

        showroomDAO.create(showroom);

        centerListModel.addElement(nazwa);
    }

    private void addVehicle() {
        String selectedCenter = centerList.getSelectedValue();
        if (selectedCenter != null) {
            Optional<CarShowroom> showroomOpt = showroomDAO.readByName(selectedCenter);
            if (showroomOpt.isPresent()) {
                CarShowroom showroom = showroomOpt.get();
                String marka = JOptionPane.showInputDialog("Enter Brand:");
                String model = JOptionPane.showInputDialog("Enter Model:");
                ItemCondition stan = (ItemCondition) JOptionPane.showInputDialog(
                        null, "Choose Item Condition:", "Item Condition",
                        JOptionPane.QUESTION_MESSAGE, null, ItemCondition.values(), ItemCondition.NEW);

                double cena = Double.parseDouble(JOptionPane.showInputDialog("Enter Price:"));
                Integer rokProdukcji = Integer.parseInt(JOptionPane.showInputDialog("Enter Production Year:"));
                double przebieg = Double.parseDouble(JOptionPane.showInputDialog("Enter Mileage:"));
                double pojemnoscSilnika = Double.parseDouble(JOptionPane.showInputDialog("Enter Engine Capacity:"));

                Vehicle vehicle = new Vehicle(marka, model, stan, cena, rokProdukcji, przebieg, pojemnoscSilnika);
                showroom.addProduct(vehicle);

                vehicleDAO.create(vehicle);

                Object[] rowData = {
                        vehicle.getMarka(),
                        vehicle.getModel(),
                        vehicle.getStan(),
                        vehicle.getCena(),
                        vehicle.getRokProdukcji(),
                        vehicle.getPrzebieg(),
                        vehicle.getPojemnoscSilnika()
                };
                vehicleTableModel.addRow(rowData);
            }
        }
    }

    private void removeCarShowroom() {
        String selectedCenter = centerList.getSelectedValue();
        if (selectedCenter != null) {
            Optional<CarShowroom> showroomOpt = showroomDAO.readByName(selectedCenter);
            if (showroomOpt.isPresent()) {
                showroomDAO.delete(showroomOpt.get().getId());

                centerListModel.removeElement(selectedCenter);
                vehicleTableModel.setRowCount(0);
            }
        }
    }

    private void removeVehicle() {
        int selectedRow = vehicleTable.getSelectedRow();
        if (selectedRow != -1) {
            String selectedCenter = centerList.getSelectedValue();
            if (selectedCenter != null) {
                Optional<CarShowroom> showroomOpt = showroomDAO.readByName(selectedCenter);
                if (showroomOpt.isPresent()) {
                    CarShowroom showroom = showroomOpt.get();
                    String marka = (String) vehicleTableModel.getValueAt(selectedRow, 0);
                    String model = (String) vehicleTableModel.getValueAt(selectedRow, 1);

                    Optional<Vehicle> vehicleOpt = showroom.getListaSamochodow().stream()
                            .filter(v -> v.getMarka().equals(marka) && v.getModel().equals(model))
                            .findFirst();

                    if (vehicleOpt.isPresent()) {
                        Vehicle vehicle = vehicleOpt.get();
                        showroom.removeProduct(vehicle.nazwa());

                        vehicleDAO.delete(vehicle.getId());

                        vehicleTableModel.removeRow(selectedRow);
                    }
                }
            }
        }
    }
}