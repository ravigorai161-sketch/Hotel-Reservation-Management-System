package gui;

import model.Room;
import service.RoomService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RoomFrame extends JFrame {

    private final RoomService roomService;
    private final DashboardFrame dashboardFrame;

    private JTable table;
    private DefaultTableModel model;

    private JTextField roomNumberField;
    private JTextField roomTypeField;
    private JTextField priceField;
    private JTextField statusField;
    private JTextField searchField;

    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton refreshButton;
    private JButton searchButton;
    private JButton clearButton;
    private JButton backButton;

    public RoomFrame(DashboardFrame dashboardFrame) {

        this.dashboardFrame = dashboardFrame;
        this.roomService = new RoomService();

        initializeUI();

        loadRooms();

        setVisible(true);
    }

    private void initializeUI() {

        setTitle("Room Management");

        setSize(900, 600);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new java.awt.event.WindowAdapter() {

            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {

                int option = JOptionPane.showConfirmDialog(
                        RoomFrame.this,
                        "Return to Dashboard?",
                        "Exit",
                        JOptionPane.YES_NO_OPTION);

                if (option == JOptionPane.YES_OPTION) {

                    dispose();

                    dashboardFrame.setVisible(true);

                }

            }

        });

        setLayout(new BorderLayout(10, 10));

        JPanel northPanel = new JPanel(new BorderLayout());

        JLabel title = new JLabel(
                "ROOM MANAGEMENT",
                SwingConstants.CENTER);

        title.setFont(new Font("Arial", Font.BOLD, 24));

        northPanel.add(title, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel();

        searchPanel.add(new JLabel("Search"));

        searchField = new JTextField(20);

        searchButton = new JButton("Search");

        clearButton = new JButton("Clear");

        searchPanel.add(searchField);

        searchPanel.add(searchButton);

        searchPanel.add(clearButton);

        northPanel.add(searchPanel, BorderLayout.SOUTH);

        add(northPanel, BorderLayout.NORTH);

        model = new DefaultTableModel(
                new Object[]{
                        "Room No",
                        "Room Type",
                        "Price",
                        "Status"
                }, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {

                return false;

            }

        };

        table = new JTable(model);

        table.setRowHeight(28);

        table.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);

        table.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 14));

        JScrollPane scrollPane =
                new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel =
                new JPanel(new BorderLayout());

        JPanel formPanel =
                new JPanel(new GridLayout(4, 2, 10, 10));

        formPanel.add(new JLabel("Room Number"));

        roomNumberField = new JTextField();

        formPanel.add(roomNumberField);

        formPanel.add(new JLabel("Room Type"));

        roomTypeField = new JTextField();

        formPanel.add(roomTypeField);

        formPanel.add(new JLabel("Price"));

        priceField = new JTextField();

        formPanel.add(priceField);

        formPanel.add(new JLabel("Status"));

        statusField = new JTextField();

        formPanel.add(statusField);

        bottomPanel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        refreshButton = new JButton("Refresh");
        backButton = new JButton("Back");

        Font buttonFont =
                new Font("Arial", Font.BOLD, 14);

        addButton.setFont(buttonFont);
        updateButton.setFont(buttonFont);
        deleteButton.setFont(buttonFont);
        refreshButton.setFont(buttonFont);
        backButton.setFont(buttonFont);
        searchButton.setFont(buttonFont);
        clearButton.setFont(buttonFont);

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(backButton);

        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);

        refreshButton.addActionListener(e -> loadRooms());

        addButton.addActionListener(e -> addRoom());

        updateButton.addActionListener(e -> updateRoom());

        deleteButton.addActionListener(e -> deleteRoom());

        searchButton.addActionListener(e -> searchRoom());

        clearButton.addActionListener(e -> {

            clearFields();

            searchField.setText("");

            loadRooms();

        });

        backButton.addActionListener(e -> {

            dispose();

            dashboardFrame.setVisible(true);

        });

        table.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                int row = table.getSelectedRow();

                if (row != -1) {

                    roomNumberField.setText(
                            model.getValueAt(row, 0).toString());

                    roomTypeField.setText(
                            model.getValueAt(row, 1).toString());

                    priceField.setText(
                            model.getValueAt(row, 2).toString());

                    statusField.setText(
                            model.getValueAt(row, 3).toString());

                }

            }

        });
    }
        private void loadRooms () {

            model.setRowCount(0);

            List<Room> rooms = roomService.getAllRooms();

            for (Room room : rooms) {

                model.addRow(new Object[]{

                        room.getRoomNumber(),
                        room.getRoomType(),
                        room.getPrice(),
                        room.getStatus()

                });

            }

            if (model.getRowCount() > 0) {

                table.setRowSelectionInterval(0, 0);

            }

        }

        private void addRoom () {

            try {

                Room room = new Room();

                room.setRoomNumber(
                        Integer.parseInt(roomNumberField.getText().trim()));

                room.setRoomType(
                        roomTypeField.getText().trim());

                room.setPrice(
                        Double.parseDouble(priceField.getText().trim()));

                room.setStatus(
                        statusField.getText().trim());

                if (roomService.addRoom(room)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Room Added Successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);

                    clearFields();

                    loadRooms();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Failed to Add Room.");

                }

            } catch (Exception e) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter valid details.");

            }

        }

        private void updateRoom () {

            int row = table.getSelectedRow();

            if (row == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a room.");

                return;

            }

            try {

                Room room = new Room();

                room.setRoomNumber(
                        Integer.parseInt(roomNumberField.getText().trim()));

                room.setRoomType(
                        roomTypeField.getText().trim());

                room.setPrice(
                        Double.parseDouble(priceField.getText().trim()));

                room.setStatus(
                        statusField.getText().trim());

                if (roomService.updateRoom(room)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Room Updated Successfully.");

                    clearFields();

                    loadRooms();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Update Failed.");

                }

            } catch (Exception e) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter valid details.");

            }

        }

        private void deleteRoom () {

            int row = table.getSelectedRow();

            if (row == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a room.");

                return;

            }

            int roomNumber =
                    Integer.parseInt(
                            model.getValueAt(row, 0).toString());

            int option =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Delete this room?",
                            "Confirm",
                            JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {

                if (roomService.deleteRoom(roomNumber)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Room Deleted Successfully.");

                    clearFields();

                    loadRooms();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Delete Failed.");

                }

            }

        }

        private void searchRoom () {

            String keyword =
                    searchField.getText().trim().toLowerCase();

            model.setRowCount(0);

            List<Room> rooms =
                    roomService.getAllRooms();

            for (Room room : rooms) {

                if (String.valueOf(room.getRoomNumber()).contains(keyword)
                        ||
                        room.getRoomType().toLowerCase().contains(keyword)
                        ||
                        room.getStatus().toLowerCase().contains(keyword)) {

                    model.addRow(new Object[]{

                            room.getRoomNumber(),
                            room.getRoomType(),
                            room.getPrice(),
                            room.getStatus()

                    });

                }

            }

        }

        private void clearFields () {

            roomNumberField.setText("");

            roomTypeField.setText("");

            priceField.setText("");

            statusField.setText("");

            table.clearSelection();

        }

    }

