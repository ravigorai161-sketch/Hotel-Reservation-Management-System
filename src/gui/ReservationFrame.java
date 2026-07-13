package gui;

import model.Reservation;
import com.toedter.calendar.JDateChooser;
import service.ReservationService;
import model.Room;
import service.RoomService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.util.List;

public class ReservationFrame extends JFrame {

    private final ReservationService reservationService;
    private final DashboardFrame dashboardFrame;

    private JTable table;
    private DefaultTableModel model;

    private JTextField reservationIdField;
    private JTextField customerIdField;
    private JTextField roomNumberField;
    private JDateChooser checkInChooser;
    private JDateChooser checkOutChooser;
    private JTextField searchField;

    private JButton bookButton;
    private JButton updateButton;

    private JButton checkInButton;
    private JButton checkOutButton;
    private JButton refreshButton;
    private JButton searchButton;
    private JButton clearButton;
    private JButton backButton;

    public ReservationFrame(DashboardFrame dashboardFrame) {

        this.dashboardFrame = dashboardFrame;
        this.reservationService = new ReservationService();

        initializeUI();

        loadReservations();

        setVisible(true);
    }

    private void initializeUI() {

        setTitle("Reservation Management");

        setSize(1100, 650);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new java.awt.event.WindowAdapter() {

            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {

                int option = JOptionPane.showConfirmDialog(

                        ReservationFrame.this,

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
                "RESERVATION MANAGEMENT",
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

                        "Reservation ID",
                        "Customer ID",
                        "Room Number",
                        "Check In",
                        "Check Out"

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

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        formPanel.add(new JLabel("Reservation ID"));

        reservationIdField = new JTextField();

        formPanel.add(reservationIdField);

        formPanel.add(new JLabel("Customer ID"));

        customerIdField = new JTextField();

        formPanel.add(customerIdField);

        formPanel.add(new JLabel("Room Number"));

        roomNumberField = new JTextField();

        formPanel.add(roomNumberField);

        checkInChooser = new JDateChooser();
        checkOutChooser = new JDateChooser();

        checkInChooser.setDateFormatString("yyyy-MM-dd");
        checkOutChooser.setDateFormatString("yyyy-MM-dd");

        formPanel.add(checkInChooser);
        formPanel.add(checkOutChooser);



        bottomPanel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        bookButton = new JButton("Book");

        updateButton = new JButton("Update");



        checkInButton = new JButton("Check In");

        checkOutButton = new JButton("Check Out");

        refreshButton = new JButton("Refresh");

        backButton = new JButton("Back");

        Font buttonFont = new Font("Arial", Font.BOLD, 14);

        bookButton.setFont(buttonFont);
        updateButton.setFont(buttonFont);

        checkInButton.setFont(buttonFont);
        checkOutButton.setFont(buttonFont);
        refreshButton.setFont(buttonFont);
        backButton.setFont(buttonFont);
        searchButton.setFont(buttonFont);
        clearButton.setFont(buttonFont);

        buttonPanel.add(bookButton);
        buttonPanel.add(updateButton);

        buttonPanel.add(checkInButton);
        buttonPanel.add(checkOutButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(backButton);

        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);

        refreshButton.addActionListener(e -> loadReservations());

        bookButton.addActionListener(e -> bookReservation());

        updateButton.addActionListener(e -> updateReservation());



        checkInButton.addActionListener(e -> checkInGuest());

        checkOutButton.addActionListener(e -> checkOutGuest());

        searchButton.addActionListener(e -> searchReservation());

        clearButton.addActionListener(e -> {

            clearFields();

            searchField.setText("");

            loadReservations();

        });

        backButton.addActionListener(e -> {

            dispose();

            dashboardFrame.setVisible(true);

        });

        table.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                int row = table.getSelectedRow();

                if (row != -1) {

                    reservationIdField.setText(model.getValueAt(row, 0).toString());

                    customerIdField.setText(model.getValueAt(row, 1).toString());

                    roomNumberField.setText(model.getValueAt(row, 2).toString());

                    checkInChooser.setDate(
                            java.sql.Date.valueOf(
                                    model.getValueAt(row, 3).toString()));

                    checkOutChooser.setDate(
                            java.sql.Date.valueOf(
                                    model.getValueAt(row, 4).toString()));
                }

            }

        });
    }
        private void loadReservations () {

            model.setRowCount(0);

            List<Reservation> reservations =
                    reservationService.getAllReservations();

            for (Reservation reservation : reservations) {

                Room room = new RoomService().getRoomById(
                        reservation.getRoomId());

                int roomNumber = (room != null)
                        ? room.getRoomNumber()
                        : 0;

                model.addRow(new Object[]{

                        reservation.getReservationId(),
                        reservation.getCustomerId(),
                        roomNumber,
                        reservation.getCheckIn(),
                        reservation.getCheckOut()

                });

            }

        }

        private void bookReservation () {

            try {

                Reservation reservation = new Reservation();

                reservation.setCustomerId(
                        Integer.parseInt(customerIdField.getText().trim()));

                int roomNumber =
                        Integer.parseInt(roomNumberField.getText().trim());

                Room room =
                        new RoomService().getRoomByNumber(roomNumber);

                if (room == null) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Room Number not found.");

                    return;
                }

                reservation.setRoomId(room.getRoomId());

                reservation.setCheckIn(
                        new java.sql.Date(
                                checkInChooser.getDate().getTime()));

                reservation.setCheckOut(
                        new java.sql.Date(
                                checkOutChooser.getDate().getTime()));
                if (reservationService.bookRoom(reservation)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Reservation Booked Successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);

                    clearFields();

                    loadReservations();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Booking Failed.");

                }

            } catch (Exception e) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter valid details.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);

            }

        }

        private void updateReservation () {

            try {

                Reservation reservation = new Reservation();

                reservation.setReservationId(
                        Integer.parseInt(reservationIdField.getText().trim()));

                reservation.setCustomerId(
                        Integer.parseInt(customerIdField.getText().trim()));

                Room room = new RoomService().getRoomByNumber(
                        Integer.parseInt(roomNumberField.getText().trim()));

                if (room == null) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Room Number not found.");

                    return;
                }

                reservation.setRoomId(room.getRoomId());
                reservation.setCheckIn(
                        new java.sql.Date(
                                checkInChooser.getDate().getTime()));

                reservation.setCheckOut(
                        new java.sql.Date(
                                checkOutChooser.getDate().getTime()));

                if (reservationService.updateReservation(reservation)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Reservation Updated.");

                    clearFields();

                    loadReservations();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Update Failed.");

                }

            } catch (Exception e) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter valid details.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);

            }

        }

        private void checkInGuest () {

            try {

                int reservationId =
                        Integer.parseInt(
                                reservationIdField.getText().trim());

                if (reservationService.checkIn(reservationId)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Guest Checked In.");

                    loadReservations();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Check-In Failed.");

                }

            } catch (Exception e) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter valid Reservation Id.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);

            }

        }

        private void checkOutGuest () {

            try {

                int reservationId =
                        Integer.parseInt(
                                reservationIdField.getText().trim());

                if (reservationService.checkOut(reservationId)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Guest Checked Out.");

                    clearFields();

                    loadReservations();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Check-Out Failed.");

                }

            } catch (Exception e) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter valid Reservation Id.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);

            }

        }

        private void searchReservation () {

            String keyword =
                    searchField.getText().trim();

            model.setRowCount(0);

            List<Reservation> reservations =
                    reservationService.getAllReservations();

            for (Reservation reservation : reservations) {

                if (String.valueOf(
                        reservation.getReservationId()).contains(keyword)

                        ||

                        String.valueOf(
                                reservation.getCustomerId()).contains(keyword)

                        ||

                        String.valueOf(
                                reservation.getRoomId()).contains(keyword)) {

                    model.addRow(new Object[]{

                            reservation.getReservationId(),
                            reservation.getCustomerId(),
                            reservation.getRoomId(),
                            reservation.getCheckIn(),
                            reservation.getCheckOut()

                    });

                }

            }

        }

        private void clearFields () {

            reservationIdField.setText("");

            customerIdField.setText("");

            roomNumberField.setText("");

            checkInChooser.setDate(null);

            checkOutChooser.setDate(null);

            table.clearSelection();

        }

    }