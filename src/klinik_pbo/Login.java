/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package klinik_pbo;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.KeyEvent; // Import for KeyEvent
import java.awt.event.KeyListener; // Import for KeyListener

/**
 *
 * @author avvjelly
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        setLocationRelativeTo(null); // Center the window
        setupModernDesign();
        addEnterKeyListeners(); // Add the new method call here
    }

    private void setupModernDesign() {
        // Set modern colors and fonts
        Color primaryColor = new Color(37, 99, 235); // Blue
        Color secondaryColor = new Color(59, 130, 246); // Light blue
        Color backgroundColor = new Color(248, 250, 252); // Light gray
        Color cardColor = Color.WHITE;

        Font titleFont = new Font("Segoe UI", Font.BOLD, 28);
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 14);

        // Main frame styling
        this.getContentPane().setBackground(backgroundColor);

        // Left panel (logo panel) styling
        FrameLoginKiri.setBackground(new Color(15, 23, 42)); // Dark blue
        FrameLoginKiri.setBorder(new EmptyBorder(40, 40, 40, 40));

        // Right panel styling
        FrameLoginKanan.setBackground(cardColor);
        FrameLoginKanan.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240), 1),
                new EmptyBorder(40, 40, 40, 40)));

        // Title styling
        LabelForm.setFont(titleFont);
        LabelForm.setForeground(new Color(30, 41, 59));
        LabelForm.setText("Welcome Back");

        // Add subtitle
        JLabel subtitle = new JLabel("Please sign in to your account");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(new Color(100, 116, 139));

        // Label styling
        LabelUsername.setFont(labelFont);
        LabelUsername.setForeground(new Color(51, 65, 85));
        LabelUsername.setText("Username");

        LabelPassword.setFont(labelFont);
        LabelPassword.setForeground(new Color(51, 65, 85));
        LabelPassword.setText("Password");

        // Input field styling
        styleTextField(FieldUsername);
        styleTextField(FieldPassword);

        // Button styling
        ButtonLogin.setFont(buttonFont);
        ButtonLogin.setBackground(primaryColor);
        ButtonLogin.setForeground(Color.WHITE);
        ButtonLogin.setBorder(BorderFactory.createEmptyBorder(12, 24, 12, 24));
        ButtonLogin.setFocusPainted(false);
        ButtonLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add hover effect to button
        ButtonLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                ButtonLogin.setBackground(secondaryColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ButtonLogin.setBackground(primaryColor);
            }
        });

        // Add icons to fields (if you have icon resources)
        // You can uncomment these if you have the icon files
        /*
         * ImageIcon userIcon = new
         * ImageIcon(getClass().getResource("/icons/user.png"));
         * ImageIcon passIcon = new
         * ImageIcon(getClass().getResource("/icons/lock.png"));
         * JLabel userIconLabel = new JLabel(userIcon);
         * JLabel passIconLabel = new JLabel(passIcon);
         */
    }

    private void styleTextField(JComponent field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
                new EmptyBorder(12, 16, 12, 16)));
        field.setBackground(Color.WHITE);

        // Add focus effect
        field.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                field.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(37, 99, 235), 2),
                        new EmptyBorder(11, 15, 11, 15)));
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                field.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
                        new EmptyBorder(12, 16, 12, 16)));
            }
        });
    }

    // New method to add KeyListeners
    private void addEnterKeyListeners() {
        // Add KeyListener to FieldUsername
        FieldUsername.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Not used
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    FieldPassword.requestFocusInWindow(); // Move focus to password field
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Not used
            }
        });

        // Add KeyListener to FieldPassword
        FieldPassword.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Not used
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    ButtonLogin.doClick(); // Simulate a click on the login button
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Not used
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainContainer = new javax.swing.JPanel();
        FrameLoginKiri = new javax.swing.JPanel();
        LogoContainer = new javax.swing.JPanel();
        Logo = new javax.swing.JLabel();
        WelcomeText = new javax.swing.JLabel();
        SubtitleText = new javax.swing.JLabel();
        FrameLoginKanan = new javax.swing.JPanel();
        FormContainer = new javax.swing.JPanel();
        LabelForm = new javax.swing.JLabel();
        SubtitleLabel = new javax.swing.JLabel();
        UsernameContainer = new javax.swing.JPanel();
        LabelUsername = new javax.swing.JLabel();
        FieldUsername = new javax.swing.JTextField();
        PasswordContainer = new javax.swing.JPanel();
        LabelPassword = new javax.swing.JLabel();
        FieldPassword = new javax.swing.JPasswordField();
        ButtonContainer = new javax.swing.JPanel();
        ButtonLogin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Klinik Management System - Login");
        setResizable(false);

        MainContainer.setBackground(new java.awt.Color(248, 250, 252));
        MainContainer.setLayout(new java.awt.BorderLayout());

        FrameLoginKiri.setBackground(new java.awt.Color(15, 23, 42));
        FrameLoginKiri.setPreferredSize(new java.awt.Dimension(450, 500));

        LogoContainer.setBackground(new java.awt.Color(15, 23, 42));
        LogoContainer.setLayout(new java.awt.GridBagLayout());

        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/klinik_pbo/logo (3).png"))); // NOI18N
        LogoContainer.add(Logo, new java.awt.GridBagConstraints());

        WelcomeText.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        WelcomeText.setForeground(new java.awt.Color(255, 255, 255));
        WelcomeText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        WelcomeText.setText("Klinik Management");

        SubtitleText.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        SubtitleText.setForeground(new java.awt.Color(148, 163, 184));
        SubtitleText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SubtitleText.setText("Modern Healthcare Solution");

        javax.swing.GroupLayout FrameLoginKiriLayout = new javax.swing.GroupLayout(FrameLoginKiri);
        FrameLoginKiri.setLayout(FrameLoginKiriLayout);
        FrameLoginKiriLayout.setHorizontalGroup(
                FrameLoginKiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(LogoContainer, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(FrameLoginKiriLayout.createSequentialGroup()
                                .addGap(20, 20, 20) // Reduced left padding
                                .addGroup(FrameLoginKiriLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(WelcomeText, javax.swing.GroupLayout.DEFAULT_SIZE, 360,
                                                Short.MAX_VALUE) // Adjusted to fill new space (400 - 20 - 20 = 360)
                                        .addComponent(SubtitleText, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(20, 20, 20)) // Reduced right padding
        );
        FrameLoginKiriLayout.setVerticalGroup(
                FrameLoginKiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(FrameLoginKiriLayout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(LogoContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 150,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(WelcomeText)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(SubtitleText)
                                .addContainerGap(176, Short.MAX_VALUE)));

        MainContainer.add(FrameLoginKiri, java.awt.BorderLayout.WEST);

        FrameLoginKanan.setBackground(new java.awt.Color(255, 255, 255));
        FrameLoginKanan.setPreferredSize(new java.awt.Dimension(450, 500));

        FormContainer.setBackground(new java.awt.Color(255, 255, 255));

        LabelForm.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        LabelForm.setForeground(new java.awt.Color(30, 41, 59));
        LabelForm.setText("Welcome Back");

        SubtitleLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        SubtitleLabel.setForeground(new java.awt.Color(100, 116, 139));
        SubtitleLabel.setText("Please sign in to your account");

        UsernameContainer.setBackground(new java.awt.Color(255, 255, 255));

        LabelUsername.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LabelUsername.setForeground(new java.awt.Color(51, 65, 85));
        LabelUsername.setText("Username");

        FieldUsername.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        FieldUsername.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(203, 213, 225)),
                javax.swing.BorderFactory.createEmptyBorder(12, 16, 12, 16)));

        javax.swing.GroupLayout UsernameContainerLayout = new javax.swing.GroupLayout(UsernameContainer);
        UsernameContainer.setLayout(UsernameContainerLayout);
        UsernameContainerLayout.setHorizontalGroup(
                UsernameContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(LabelUsername, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FieldUsername));
        UsernameContainerLayout.setVerticalGroup(
                UsernameContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(UsernameContainerLayout.createSequentialGroup()
                                .addComponent(LabelUsername)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(FieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 44,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)));

        PasswordContainer.setBackground(new java.awt.Color(255, 255, 255));

        LabelPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LabelPassword.setForeground(new java.awt.Color(51, 65, 85));
        LabelPassword.setText("Password");

        FieldPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        FieldPassword.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(203, 213, 225)),
                javax.swing.BorderFactory.createEmptyBorder(12, 16, 12, 16)));

        javax.swing.GroupLayout PasswordContainerLayout = new javax.swing.GroupLayout(PasswordContainer);
        PasswordContainer.setLayout(PasswordContainerLayout);
        PasswordContainerLayout.setHorizontalGroup(
                PasswordContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(LabelPassword, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FieldPassword));
        PasswordContainerLayout.setVerticalGroup(
                PasswordContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PasswordContainerLayout.createSequentialGroup()
                                .addComponent(LabelPassword)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(FieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 44,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)));

        ButtonContainer.setBackground(new java.awt.Color(255, 255, 255));
        ButtonContainer.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT)); // Changed from CENTER to LEFT

        ButtonLogin.setBackground(new java.awt.Color(37, 99, 235));
        ButtonLogin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ButtonLogin.setForeground(new java.awt.Color(255, 255, 255));
        ButtonLogin.setText("Sign In");
        ButtonLogin.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 24, 12, 24));
        ButtonLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ButtonLogin.setFocusPainted(false);
        ButtonLogin.setPreferredSize(new java.awt.Dimension(150, 44));
        ButtonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonLoginActionPerformed(evt);
            }
        });
        ButtonContainer.add(ButtonLogin);

        javax.swing.GroupLayout FormContainerLayout = new javax.swing.GroupLayout(FormContainer);
        FormContainer.setLayout(FormContainerLayout);
        FormContainerLayout.setHorizontalGroup(
                FormContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(FormContainerLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(FormContainerLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(LabelForm)
                                        .addComponent(SubtitleLabel)
                                        .addComponent(UsernameContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 330,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(PasswordContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 330,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ButtonContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 330,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(70, Short.MAX_VALUE)));
        FormContainerLayout.setVerticalGroup(
                FormContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(FormContainerLayout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(LabelForm)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SubtitleLabel)
                                .addGap(40, 40, 40)
                                .addComponent(UsernameContainer, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(PasswordContainer, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(ButtonContainer, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(140, Short.MAX_VALUE)));

        javax.swing.GroupLayout FrameLoginKananLayout = new javax.swing.GroupLayout(FrameLoginKanan);
        FrameLoginKanan.setLayout(FrameLoginKananLayout);
        FrameLoginKananLayout.setHorizontalGroup(
                FrameLoginKananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(FormContainer, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        FrameLoginKananLayout.setVerticalGroup(
                FrameLoginKananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(FormContainer, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        MainContainer.add(FrameLoginKanan, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(MainContainer, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(MainContainer, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_ButtonLoginActionPerformed
        String username = FieldUsername.getText().trim();
        String password = new String(FieldPassword.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            showModernDialog("Harap isi semua field", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Connection conn = koneksi.getKoneksi();
            String sql = "SELECT * FROM user WHERE LOWER(username) = ? AND password = ?";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username.toLowerCase());
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");
                showModernDialog("Login Sukses sebagai " + role, "Sukses", JOptionPane.INFORMATION_MESSAGE);

                // Redirect sesuai role
                if ("admin".equals(role)) {
                    DashboardAdmin admin = new DashboardAdmin();
                    admin.setVisible(true);
                } else if ("dokter".equals(role)) {
                    DashboardDokter dokter = new DashboardDokter();
                    dokter.setVisible(true);
                } else {
                    showModernDialog("Role tidak dikenali!", "Error", JOptionPane.ERROR_MESSAGE);
                }

                this.dispose();
            } else {
                showModernDialog("Username atau Password salah!", "Login Gagal", JOptionPane.ERROR_MESSAGE);
                FieldPassword.setText(""); // Clear password field
            }

        } catch (HeadlessException | SQLException e) {
            showModernDialog("An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }// GEN-LAST:event_ButtonLoginActionPerformed

    private void showModernDialog(String message, String title, int messageType) {
        // Create a custom dialog with modern styling
        JDialog dialog = new JDialog(this, title, true);
        dialog.setLayout(new BorderLayout());

        // This line centers the dialog relative to the parent frame.
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new BorderLayout(20, 20));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.WHITE);

        JLabel messageLabel = new JLabel(
                "<html><p style='width: 300px; text-align: center;'>" + message + "</p></html>");
        messageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton okButton = new JButton("OK");
        okButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        okButton.setBackground(new Color(37, 99, 235));
        okButton.setForeground(Color.WHITE);
        okButton.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        okButton.setFocusPainted(false);
        okButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        okButton.addActionListener(e -> dialog.dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Center the button
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(okButton);

        panel.add(messageLabel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        dialog.add(panel);
        dialog.pack(); // Pack the dialog to fit its components
        dialog.setMinimumSize(dialog.getPreferredSize()); // Prevent shrinking below preferred size
        dialog.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the modern look and feel */
        try {
            UIManager.setLookAndFeel(UIManager.getLookAndFeel());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        /* Create and display the form */
        SwingUtilities.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonLogin;
    private javax.swing.JPanel ButtonContainer;
    private javax.swing.JPasswordField FieldPassword;
    private javax.swing.JTextField FieldUsername;
    private javax.swing.JPanel FormContainer;
    private javax.swing.JPanel FrameLoginKanan;
    private javax.swing.JPanel FrameLoginKiri;
    private javax.swing.JLabel LabelForm;
    private javax.swing.JLabel LabelPassword;
    private javax.swing.JLabel LabelUsername;
    private javax.swing.JLabel Logo;
    private javax.swing.JPanel LogoContainer;
    private javax.swing.JPanel MainContainer;
    private javax.swing.JPanel PasswordContainer;
    private javax.swing.JLabel SubtitleLabel;
    private javax.swing.JLabel SubtitleText;
    private javax.swing.JPanel UsernameContainer;
    private javax.swing.JLabel WelcomeText;
    // End of variables declaration//GEN-END:variables
}