import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends javax.swing.JFrame implements ActionListener {

    private static final int TOTAL_SLOTS = 20;
    private static final String FILE_NAME = "data.txt";

    private Vehicle[] slots = new Vehicle[TOTAL_SLOTS];
    private MyQueue waitingQueue = new MyQueue();
    private MyStack activityHistory = new MyStack();
    private HashTable plateIndex = new HashTable(29);

    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JPanel centerPanel;
    private javax.swing.JPanel entryPanel;
    private javax.swing.JLabel entryTitle;
    private javax.swing.JPanel entryFields;
    private javax.swing.JLabel plateLabel;
    private javax.swing.JTextField plateField;
    private javax.swing.JLabel ownerLabel;
    private javax.swing.JTextField ownerField;
    private javax.swing.JLabel typeLabel;
    private javax.swing.JComboBox<String> typeCombo;
    private javax.swing.JPanel entryButtons;
    private javax.swing.JButton parkButton;
    private javax.swing.JButton queueButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JLabel tableTitle;
    private javax.swing.JScrollPane parkedScroll;
    private javax.swing.JTable parkedTable;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JPanel summaryPanel;
    private javax.swing.JLabel totalCaptionLabel;
    private javax.swing.JLabel totalValueLabel;
    private javax.swing.JLabel occupiedCaptionLabel;
    private javax.swing.JLabel occupiedValueLabel;
    private javax.swing.JLabel availableCaptionLabel;
    private javax.swing.JLabel availableValueLabel;
    private javax.swing.JPanel queuePanel;
    private javax.swing.JLabel queueTitle;
    private javax.swing.JScrollPane queueScroll;
    private javax.swing.JTable queueTable;
    private javax.swing.JPanel historyPanel;
    private javax.swing.JLabel historyTitle;
    private javax.swing.JScrollPane historyScroll;
    private javax.swing.JTable historyTable;
    private javax.swing.JPanel toolbarPanel;
    private javax.swing.JTextField searchField;
    private javax.swing.JButton searchButton;
    private javax.swing.JButton binaryButton;
    private javax.swing.JLabel sortLabel;
    private javax.swing.JComboBox<String> sortCombo;
    private javax.swing.JButton sortButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JButton updateButton;
    private javax.swing.JButton undoButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton loadButton;
    private javax.swing.JLabel statusBar;

    public MainFrame() {
        initComponents();
        setSize(1280, 850);
        setLocationRelativeTo(null);
        loadDataSilently();
        refreshAll();
    }

    private void initComponents() {

        headerPanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        centerPanel = new javax.swing.JPanel();
        entryPanel = new javax.swing.JPanel();
        entryTitle = new javax.swing.JLabel();
        entryFields = new javax.swing.JPanel();
        plateLabel = new javax.swing.JLabel();
        plateField = new javax.swing.JTextField();
        ownerLabel = new javax.swing.JLabel();
        ownerField = new javax.swing.JTextField();
        typeLabel = new javax.swing.JLabel();
        typeCombo = new javax.swing.JComboBox<>();
        entryButtons = new javax.swing.JPanel();
        parkButton = new javax.swing.JButton();
        queueButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        tablePanel = new javax.swing.JPanel();
        tableTitle = new javax.swing.JLabel();
        parkedScroll = new javax.swing.JScrollPane();
        parkedTable = new javax.swing.JTable();
        rightPanel = new javax.swing.JPanel();
        summaryPanel = new javax.swing.JPanel();
        totalCaptionLabel = new javax.swing.JLabel();
        totalValueLabel = new javax.swing.JLabel();
        occupiedCaptionLabel = new javax.swing.JLabel();
        occupiedValueLabel = new javax.swing.JLabel();
        availableCaptionLabel = new javax.swing.JLabel();
        availableValueLabel = new javax.swing.JLabel();
        queuePanel = new javax.swing.JPanel();
        queueTitle = new javax.swing.JLabel();
        queueScroll = new javax.swing.JScrollPane();
        queueTable = new javax.swing.JTable();
        historyPanel = new javax.swing.JPanel();
        historyTitle = new javax.swing.JLabel();
        historyScroll = new javax.swing.JScrollPane();
        historyTable = new javax.swing.JTable();
        toolbarPanel = new javax.swing.JPanel();
        searchField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        binaryButton = new javax.swing.JButton();
        sortLabel = new javax.swing.JLabel();
        sortCombo = new javax.swing.JComboBox<>();
        sortButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        undoButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        loadButton = new javax.swing.JButton();
        statusBar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Parking Management System");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                FileHandler.save(slots, waitingQueue, activityHistory, FILE_NAME);
                System.exit(0);
            }
        });

        headerPanel.setLayout(new BorderLayout());
        titleLabel.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 24));
        titleLabel.setText("Parking Management System");
        titleLabel.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));
        headerPanel.add(titleLabel, BorderLayout.WEST);

        entryPanel.setLayout(new BorderLayout(0, 10));
        entryPanel.setBorder(BorderFactory.createTitledBorder("Vehicle Entry"));
        entryTitle.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 16));
        entryTitle.setText("Vehicle Entry");
        entryTitle.setVisible(false);

        plateLabel.setText("Plate Number");
        ownerLabel.setText("Owner Name");
        typeLabel.setText("Vehicle Type");
        typeCombo.setModel(new DefaultComboBoxModel<>(new String[]{"Car", "Motorcycle", "Truck", "Van"}));

        javax.swing.GroupLayout fieldsLayout = new javax.swing.GroupLayout(entryFields);
        entryFields.setLayout(fieldsLayout);
        fieldsLayout.setHorizontalGroup(
            fieldsLayout.createSequentialGroup()
                .addGroup(fieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(plateLabel)
                    .addComponent(ownerLabel)
                    .addComponent(typeLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(fieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(plateField)
                    .addComponent(ownerField)
                    .addComponent(typeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        fieldsLayout.setVerticalGroup(
            fieldsLayout.createSequentialGroup()
                .addGroup(fieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(plateLabel)
                    .addComponent(plateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(fieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ownerLabel)
                    .addComponent(ownerField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(fieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(typeLabel)
                    .addComponent(typeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        parkButton.setText("Park Vehicle");
        parkButton.addActionListener(this);
        queueButton.setText("Add to Queue");
        queueButton.addActionListener(this);
        clearButton.setText("Clear");
        clearButton.addActionListener(this);

        entryButtons.setLayout(new GridLayout(3, 1, 0, 8));
        entryButtons.add(parkButton);
        entryButtons.add(queueButton);
        entryButtons.add(clearButton);

        entryPanel.add(entryFields, BorderLayout.NORTH);
        entryPanel.add(entryButtons, BorderLayout.SOUTH);

        tablePanel.setLayout(new BorderLayout(0, 10));
        tablePanel.setBorder(BorderFactory.createTitledBorder("Parked Vehicles"));
        tableTitle.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 16));
        tableTitle.setText("Parked Vehicles");
        parkedTable.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"Slot No.", "Plate Number", "Owner", "Vehicle Type", "Time In", "Status"}
        ));
        parkedTable.setDefaultEditor(Object.class, null);
        parkedTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        parkedScroll.setViewportView(parkedTable);
        tablePanel.add(tableTitle, BorderLayout.NORTH);
        tablePanel.add(parkedScroll, BorderLayout.CENTER);

        summaryPanel.setBorder(BorderFactory.createTitledBorder("Slot Summary"));
        summaryPanel.setLayout(new GridLayout(2, 3, 5, 5));
        totalCaptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        totalCaptionLabel.setText("Total");
        totalValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        totalValueLabel.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 20));
        totalValueLabel.setText("20");
        occupiedCaptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        occupiedCaptionLabel.setText("Occupied");
        occupiedValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        occupiedValueLabel.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 20));
        occupiedValueLabel.setText("0");
        availableCaptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        availableCaptionLabel.setText("Available");
        availableValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        availableValueLabel.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 20));
        availableValueLabel.setText("20");
        summaryPanel.add(totalCaptionLabel);
        summaryPanel.add(occupiedCaptionLabel);
        summaryPanel.add(availableCaptionLabel);
        summaryPanel.add(totalValueLabel);
        summaryPanel.add(occupiedValueLabel);
        summaryPanel.add(availableValueLabel);

        queuePanel.setLayout(new BorderLayout(0, 5));
        queuePanel.setBorder(BorderFactory.createTitledBorder("Waiting Queue (FIFO)"));
        queueTitle.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 14));
        queueTitle.setText("Waiting Queue (FIFO)");
        queueTable.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"Pos", "Plate", "Owner", "Type"}
        ));
        queueTable.setDefaultEditor(Object.class, null);
        queueScroll.setViewportView(queueTable);
        queuePanel.add(queueTitle, BorderLayout.NORTH);
        queuePanel.add(queueScroll, BorderLayout.CENTER);

        historyPanel.setLayout(new BorderLayout(0, 5));
        historyPanel.setBorder(BorderFactory.createTitledBorder("Activity History (Stack - LIFO)"));
        historyTitle.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 14));
        historyTitle.setText("Activity History (Stack - LIFO)");
        historyTable.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"Pos", "Action", "Plate", "Slot"}
        ));
        historyTable.setDefaultEditor(Object.class, null);
        historyScroll.setViewportView(historyTable);
        historyPanel.add(historyTitle, BorderLayout.NORTH);
        historyPanel.add(historyScroll, BorderLayout.CENTER);

        rightPanel.setLayout(new GridLayout(3, 1, 0, 10));
        rightPanel.add(summaryPanel);
        rightPanel.add(queuePanel);
        rightPanel.add(historyPanel);

        centerPanel.setLayout(new BorderLayout(12, 0));
        centerPanel.add(entryPanel, BorderLayout.WEST);
        centerPanel.add(tablePanel, BorderLayout.CENTER);
        centerPanel.add(rightPanel, BorderLayout.EAST);

        toolbarPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 6));
        searchField.setColumns(12);
        searchButton.setText("Search");
        searchButton.addActionListener(this);
        binaryButton.setText("Binary Search");
        binaryButton.addActionListener(this);
        sortLabel.setText("Sort by:");
        sortCombo.setModel(new DefaultComboBoxModel<>(new String[]{"Plate Number", "Time In", "Vehicle Type"}));
        sortButton.setText("Sort");
        sortButton.addActionListener(this);
        exitButton.setText("Exit Vehicle");
        exitButton.addActionListener(this);
        updateButton.setText("Update");
        updateButton.addActionListener(this);
        undoButton.setText("Undo Last Action");
        undoButton.addActionListener(this);
        saveButton.setText("Save");
        saveButton.addActionListener(this);
        loadButton.setText("Load");
        loadButton.addActionListener(this);

        toolbarPanel.add(searchField);
        toolbarPanel.add(searchButton);
        toolbarPanel.add(binaryButton);
        toolbarPanel.add(sortLabel);
        toolbarPanel.add(sortCombo);
        toolbarPanel.add(sortButton);
        toolbarPanel.add(exitButton);
        toolbarPanel.add(updateButton);
        toolbarPanel.add(undoButton);
        toolbarPanel.add(saveButton);
        toolbarPanel.add(loadButton);

        statusBar.setText("Welcome to the Parking Management System.");
        statusBar.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(headerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(centerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(toolbarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(statusBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(headerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(centerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(toolbarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == parkButton) {
            parkVehicle();
        } else if (source == queueButton) {
            addToQueue();
        } else if (source == clearButton) {
            clearFields();
        } else if (source == searchButton) {
            searchVehicle();
        } else if (source == binaryButton) {
            binarySearchVehicle();
        } else if (source == sortButton) {
            sortVehicles();
        } else if (source == exitButton) {
            exitVehicle();
        } else if (source == updateButton) {
            updateVehicle();
        } else if (source == undoButton) {
            undoLastAction();
        } else if (source == saveButton) {
            saveData();
        } else if (source == loadButton) {
            loadData();
        }
    }

    private void parkVehicle() {
        String plate = plateField.getText().trim();
        String owner = ownerField.getText().trim();
        String type = (String) typeCombo.getSelectedItem();

        String error = validateInput(plate, owner);
        if (error != null) {
            JOptionPane.showMessageDialog(this, error, "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!isPlateUnique(plate)) {
            JOptionPane.showMessageDialog(this, "This plate number already exists in the lot or in the queue.",
                    "Duplicate Plate", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int slot = findFreeSlot();
        if (slot == -1) {
            int answer = JOptionPane.showConfirmDialog(this,
                    "The parking lot is full. Do you want to add the vehicle to the waiting queue?",
                    "Lot Full", JOptionPane.YES_NO_OPTION);
            if (answer == JOptionPane.YES_OPTION) {
                addToQueue();
            }
            return;
        }

        Vehicle vehicle = new Vehicle(plate, owner, type, slot);
        slots[slot - 1] = vehicle;
        activityHistory.push(new Action("Parked", vehicle, slot));

        setStatus("Vehicle " + plate + " parked at Slot " + slot + ".");
        clearFields();
        refreshAll();
    }

    private void addToQueue() {
        String plate = plateField.getText().trim();
        String owner = ownerField.getText().trim();
        String type = (String) typeCombo.getSelectedItem();

        String error = validateInput(plate, owner);
        if (error != null) {
            JOptionPane.showMessageDialog(this, error, "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!isPlateUnique(plate)) {
            JOptionPane.showMessageDialog(this, "This plate number already exists in the lot or in the queue.",
                    "Duplicate Plate", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Vehicle vehicle = new Vehicle(plate, owner, type, 0);
        waitingQueue.enqueue(vehicle);

        setStatus("Vehicle " + plate + " added to the waiting queue.");
        clearFields();
        refreshAll();
    }

    private void exitVehicle() {
        int row = parkedTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a vehicle in the Parked Vehicles table first.",
                    "No Selection", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int slot = Integer.parseInt(parkedTable.getValueAt(row, 0).toString());
        Vehicle vehicle = slots[slot - 1];
        slots[slot - 1] = null;
        activityHistory.push(new Action("Exited", vehicle, slot));

        String message = "Vehicle " + vehicle.getPlateNumber() + " exited from Slot " + slot + ".";

        if (!waitingQueue.isEmpty()) {
            Vehicle next = waitingQueue.dequeue();
            next.setSlotNumber(slot);
            next.recordTimeIn();
            slots[slot - 1] = next;
            activityHistory.push(new Action("Parked", next, slot));
            message = message + " Vehicle " + next.getPlateNumber()
                    + " parked from the queue at Slot " + slot + ".";
        }

        setStatus(message);
        refreshAll();
    }

    private void updateVehicle() {
        int row = parkedTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a vehicle in the Parked Vehicles table first.",
                    "No Selection", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int slot = Integer.parseInt(parkedTable.getValueAt(row, 0).toString());
        Vehicle vehicle = slots[slot - 1];
        if (vehicle == null) {
            return;
        }

        JTextField ownerInput = new JTextField(vehicle.getOwnerName());
        JComboBox<String> typeInput = new JComboBox<>(new String[]{"Car", "Motorcycle", "Truck", "Van"});
        typeInput.setSelectedItem(vehicle.getVehicleType());

        JPanel panel = new JPanel(new GridLayout(0, 2, 8, 8));
        panel.add(new JLabel("Plate Number"));
        panel.add(new JLabel(vehicle.getPlateNumber()));
        panel.add(new JLabel("Owner Name"));
        panel.add(ownerInput);
        panel.add(new JLabel("Vehicle Type"));
        panel.add(typeInput);

        int result = JOptionPane.showConfirmDialog(this, panel, "Update Vehicle",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        String owner = ownerInput.getText().trim();
        if (owner.equals("") || !isOwnerValid(owner)) {
            JOptionPane.showMessageDialog(this, "Owner name must contain letters only (spaces are allowed).",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        vehicle.setOwnerName(owner);
        vehicle.setVehicleType((String) typeInput.getSelectedItem());
        setStatus("Vehicle " + vehicle.getPlateNumber() + " updated.");
        refreshAll();
    }

    private void undoLastAction() {
        if (activityHistory.isEmpty()) {
            JOptionPane.showMessageDialog(this, "There is no action to undo.",
                    "Undo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        Action action = activityHistory.pop();
        int slot = action.getSlotNumber();
        String plate = action.getVehicle().getPlateNumber();

        if (action.getType().equals("Parked")) {
            slots[slot - 1] = null;
            setStatus("Undo: vehicle " + plate + " removed from Slot " + slot + ".");
        } else {
            if (slots[slot - 1] == null) {
                slots[slot - 1] = action.getVehicle();
                setStatus("Undo: vehicle " + plate + " returned to Slot " + slot + ".");
            } else {
                int free = findFreeSlot();
                if (free != -1) {
                    slots[free - 1] = action.getVehicle();
                    action.getVehicle().setSlotNumber(free);
                    setStatus("Undo: vehicle " + plate + " returned to Slot " + free + ".");
                } else {
                    activityHistory.push(action);
                    setStatus("Undo failed: the parking lot is full.");
                }
            }
        }
        refreshAll();
    }

    private void clearFields() {
        plateField.setText("");
        ownerField.setText("");
        typeCombo.setSelectedIndex(0);
        plateField.requestFocusInWindow();
    }

    private void searchVehicle() {
        String text = searchField.getText().trim();
        if (text.equals("")) {
            JOptionPane.showMessageDialog(this,
                    "Please type a plate number or an owner name to search.",
                    "Empty Search", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Vehicle exact = plateIndex.get(text);
        if (exact != null) {
            selectTableRow(exact.getPlateNumber());
            setStatus("Hash table: vehicle " + exact.getPlateNumber()
                    + " found at Slot " + exact.getSlotNumber() + ".");
            return;
        }

        Vehicle[] parked = new Vehicle[TOTAL_SLOTS];
        int count = collectParked(parked);
        Vehicle found = Algorithms.linearSearch(parked, count, text);
        if (found == null) {
            JOptionPane.showMessageDialog(this, "Vehicle not found.",
                    "Search", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        selectTableRow(found.getPlateNumber());
        setStatus("Linear search: vehicle " + found.getPlateNumber()
                + " found at Slot " + found.getSlotNumber() + ".");
    }

    private void binarySearchVehicle() {
        String plate = searchField.getText().trim();
        if (plate.equals("")) {
            JOptionPane.showMessageDialog(this,
                    "Please type a full plate number to search.",
                    "Empty Search", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Vehicle[] sorted = new Vehicle[TOTAL_SLOTS];
        int count = collectParked(sorted);
        Algorithms.insertionSort(sorted, count, "Plate Number");

        Vehicle found = Algorithms.binarySearch(sorted, count, plate);
        if (found == null) {
            JOptionPane.showMessageDialog(this, "Plate number not found.",
                    "Binary Search", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        selectTableRow(found.getPlateNumber());
        setStatus("Binary search: vehicle " + found.getPlateNumber()
                + " found at Slot " + found.getSlotNumber() + ".");
    }

    private void selectTableRow(String plate) {
        DefaultTableModel model = (DefaultTableModel) parkedTable.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 1).toString().equalsIgnoreCase(plate)) {
                parkedTable.setRowSelectionInterval(i, i);
                parkedTable.scrollRectToVisible(parkedTable.getCellRect(i, 0, true));
                return;
            }
        }
    }

    private void sortVehicles() {
        String key = sortCombo.getSelectedItem().toString();

        Vehicle[] temp = new Vehicle[TOTAL_SLOTS];
        int count = collectParked(temp);

        Algorithms.insertionSort(temp, count, key);
        showVehicles(temp, count);

        setStatus("Parked vehicles sorted by " + key + ".");
    }

    private String validateInput(String plate, String owner) {
        if (plate.equals("")) {
            return "Plate Number cannot be empty.";
        }
        if (owner.equals("")) {
            return "Owner Name cannot be empty.";
        }
        if (!isPlateValid(plate)) {
            return "Plate number must be 5 to 8 characters,\n"
                    + "letters and numbers with an optional space. Example: ABC 1234";
        }
        if (!isOwnerValid(owner)) {
            return "Owner name must contain letters only (spaces are allowed).";
        }
        return null;
    }

    private boolean isPlateValid(String plate) {
        if (plate.length() < 5 || plate.length() > 8) {
            return false;
        }
        int letters = 0;
        int digits = 0;
        for (int i = 0; i < plate.length(); i++) {
            char c = plate.charAt(i);
            if (Character.isLetter(c)) {
                letters++;
            } else if (Character.isDigit(c)) {
                digits++;
            } else if (c != ' ') {
                return false;
            }
        }
        return letters > 0 && digits > 0;
    }

    private boolean isOwnerValid(String owner) {
        for (int i = 0; i < owner.length(); i++) {
            char c = owner.charAt(i);
            if (!Character.isLetter(c) && c != ' ') {
                return false;
            }
        }
        return true;
    }

    private boolean isPlateUnique(String plate) {
        if (plateIndex.contains(plate)) {
            return false;
        }
        Vehicle[] queued = waitingQueue.toArray();
        for (int i = 0; i < queued.length; i++) {
            if (queued[i].getPlateNumber().equalsIgnoreCase(plate)) {
                return false;
            }
        }
        return true;
    }

    private int findFreeSlot() {
        for (int i = 0; i < TOTAL_SLOTS; i++) {
            if (slots[i] == null) {
                return i + 1;
            }
        }
        return -1;
    }

    private int collectParked(Vehicle[] out) {
        int count = 0;
        for (int i = 0; i < TOTAL_SLOTS; i++) {
            if (slots[i] != null) {
                out[count] = slots[i];
                count++;
            }
        }
        return count;
    }

    private void refreshAll() {
        plateIndex.rebuild(slots);

        Vehicle[] parked = new Vehicle[TOTAL_SLOTS];
        int count = collectParked(parked);
        showVehicles(parked, count);
        showQueue();
        showHistory();
        updateSummary();
    }

    private void showVehicles(Vehicle[] list, int count) {
        DefaultTableModel model = (DefaultTableModel) parkedTable.getModel();
        model.setRowCount(0);
        for (int i = 0; i < count; i++) {
            Vehicle v = list[i];
            model.addRow(new Object[]{v.getSlotNumber(), v.getPlateNumber(), v.getOwnerName(),
                v.getVehicleType(), v.getTimeIn(), "Parked"});
        }
    }

    private void showQueue() {
        DefaultTableModel model = (DefaultTableModel) queueTable.getModel();
        model.setRowCount(0);
        Vehicle[] items = waitingQueue.toArray();
        for (int i = 0; i < items.length; i++) {
            String pos = (i == 0) ? "FRONT" : "" + (i + 1);
            model.addRow(new Object[]{pos, items[i].getPlateNumber(),
                items[i].getOwnerName(), items[i].getVehicleType()});
        }
    }

    private void showHistory() {
        DefaultTableModel model = (DefaultTableModel) historyTable.getModel();
        model.setRowCount(0);
        Action[] items = activityHistory.toArray();
        for (int i = 0; i < items.length; i++) {
            String pos = (i == 0) ? "TOP" : "" + (i + 1);
            model.addRow(new Object[]{pos, items[i].getType(),
                items[i].getVehicle().getPlateNumber(), "" + items[i].getSlotNumber()});
        }
    }

    private void updateSummary() {
        int occupied = 0;
        for (int i = 0; i < TOTAL_SLOTS; i++) {
            if (slots[i] != null) {
                occupied++;
            }
        }
        totalValueLabel.setText("" + TOTAL_SLOTS);
        occupiedValueLabel.setText("" + occupied);
        availableValueLabel.setText("" + (TOTAL_SLOTS - occupied));
    }

    private void setStatus(String message) {
        statusBar.setText(message);
    }

    private void saveData() {
        boolean ok = FileHandler.save(slots, waitingQueue, activityHistory, FILE_NAME);
        if (ok) {
            setStatus("Data saved to " + FILE_NAME + ".");
        } else {
            JOptionPane.showMessageDialog(this, "Could not save the data to " + FILE_NAME + ".",
                    "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadData() {
        boolean ok = FileHandler.load(slots, waitingQueue, activityHistory, FILE_NAME);
        if (ok) {
            refreshAll();
            setStatus("Data loaded from " + FILE_NAME + ".");
        } else {
            JOptionPane.showMessageDialog(this, "Could not load the data from " + FILE_NAME + ".",
                    "Load Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadDataSilently() {
        FileHandler.load(slots, waitingQueue, activityHistory, FILE_NAME);
    }
}
