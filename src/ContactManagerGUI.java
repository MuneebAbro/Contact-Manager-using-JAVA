import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactManagerGUI extends JFrame {
    private ContactManager contactManager;
    private JTextField nameField, phoneNumberField;
    private JTextArea outputArea;

    public ContactManagerGUI() {
        super("Contact Manager");
        contactManager = new ContactManager();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // Center the window on the screen

        initializeComponents();

        setVisible(true);
    }

    private void initializeComponents() {
        // Create panels
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Set borders and padding
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Create components
        JLabel nameLabel = new JLabel("Name:");
        JLabel phoneLabel = new JLabel("Phone Number:");
        nameField = new JTextField(30); // Increased width
        phoneNumberField = new JTextField(30); // Increased width

        // Set font for labels
        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        nameLabel.setFont(labelFont);
        phoneLabel.setFont(labelFont);

        // Add components to input panel
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(phoneLabel);
        inputPanel.add(phoneNumberField);

        // Buttons
        JButton addButton = new JButton("Add Contact");
        JButton searchButton = new JButton("Search Contact");
        JButton deleteButton = new JButton("Delete Contact");
        JButton displayButton = new JButton("Display Contacts");
        JButton exitButton = new JButton("Exit");

        // Set button styles
        Font buttonFont = new Font("Arial", Font.BOLD, 14);

        // Add button action listeners and customize colors
        addButton.setFont(buttonFont);
        addButton.setBackground(new Color(59, 89, 182)); // Blue color
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String phoneNumber = phoneNumberField.getText().trim();
                if (!name.isEmpty() && !phoneNumber.isEmpty()) {
                    contactManager.insert(new Contact(name, phoneNumber));
                    outputArea.append("Contact added successfully.\n");
                    nameField.setText("");
                    phoneNumberField.setText("");
                } else {
                    JOptionPane.showMessageDialog(ContactManagerGUI.this,
                            "Name and Phone Number cannot be empty",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        searchButton.setFont(buttonFont);
        searchButton.setBackground(new Color(46, 139, 87)); // Green color
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                Contact contact = contactManager.search(name);
                if (contact != null) {
                    outputArea.append("Contact found: " + contact + "\n");
                } else {
                    outputArea.append("Contact not found.\n");
                }
            }
        });

        deleteButton.setFont(buttonFont);
        deleteButton.setBackground(Color.RED);
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                contactManager.delete(name);
                outputArea.append("Contact deleted if it existed.\n");
            }
        });

        displayButton.setFont(buttonFont);
        displayButton.setBackground(new Color(240, 173, 78)); // Orange color
        displayButton.setForeground(Color.WHITE);
        displayButton.setFocusPainted(false);
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contactManager.displayContacts(outputArea);
            }
        });

        exitButton.setFont(buttonFont);
        exitButton.setBackground(Color.RED);
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusPainted(false);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Add buttons to button panel
        buttonPanel.add(addButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(exitButton);

        // Set font and style for output area
        outputArea = new JTextArea(15, 40);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Arial", Font.BOLD, 14)); // Bold and larger font
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add panels to main panel
        mainPanel.add(inputPanel, BorderLayout.WEST);
        mainPanel.add(buttonPanel, BorderLayout.EAST);

        // Add main panel to frame
        add(mainPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ContactManagerGUI();
            }
        });
    }
}