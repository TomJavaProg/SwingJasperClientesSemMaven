package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Criptografia;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JPasswordField passwordFieldUsuario;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JLogin frame = new JLogin();

					// Para centralizar na tela quando executar.
					frame.setLocationRelativeTo(null);

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
//		contentPane.setBackground(UIManager.getColor("PasswordField.inactiveForeground"));
		contentPane.setBackground(new Color(49, 62, 64));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(62, 11, 316, 240);
		panel.setBackground(new Color(204, 207, 208));
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblBemVindo = new JLabel("Bem Vindo");
		lblBemVindo.setFont(new Font("Dialog", Font.BOLD, 14));
		lblBemVindo.setBounds(116, 12, 84, 15);
		lblBemVindo.setForeground(Color.BLUE); // Setando a cor do texto.
//		lblBemVindo.setBackground(new Color(204, 207, 208));
		panel.add(lblBemVindo);

		JLabel lblNewLabel = new JLabel("Usuário");
		lblNewLabel.setBounds(52, 52, 70, 15);
		panel.add(lblNewLabel);

		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(52, 68, 202, 25);
		panel.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setBounds(52, 130, 70, 15);
		panel.add(lblNewLabel_1);

		passwordFieldUsuario = new JPasswordField();
		passwordFieldUsuario.setBounds(52, 145, 202, 25);
		panel.add(passwordFieldUsuario);

		JButton btnLogar = new JButton("Logar");
		btnLogar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

//				Criptografia criptografia = new Criptografia("123456", Criptografia.MD5);
				Criptografia criptografia = new Criptografia(passwordFieldUsuario.getText(), Criptografia.MD5);

				System.out.println(criptografia.criptografar());
//				E10ADC3949BA59ABBE56E057F20F883E

				if(textFieldUsuario.getText() != null &&
						!textFieldUsuario.getText().isEmpty() &&
						!textFieldUsuario.getText().isBlank() &&
						passwordFieldUsuario.getText() != null &&
						!passwordFieldUsuario.getText().isEmpty() &&
						!passwordFieldUsuario.getText().isBlank()) {

					// Verificar se a senha digitada é o mesmo valor criptografado
					if (criptografia.criptografar().equals("E10ADC3949BA59ABBE56E057F20F883E")) {

						JOptionPane.showMessageDialog(lblNewLabel_1, "Informações válidas");

						dispose(); // descartar, fechar a tela de login

						// Chamando a telha principal depois de fazer o login
						JPrincipal jPrincipal = new JPrincipal();
						jPrincipal.setLocationRelativeTo(jPrincipal); // centralizando a tela
						jPrincipal.setVisible(true);
					}

				}else {
					JOptionPane.showMessageDialog(lblNewLabel_1, "Verifique as informações", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnLogar.setBounds(99, 195, 117, 25);
//		btnLogar.setBackground(new Color(49, 62, 64));
		panel.add(btnLogar);
	}
}
