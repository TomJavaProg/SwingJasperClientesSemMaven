package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import dao.DAO;
import model.Cliente;

public class JCadastro extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JLabel lblNome;
	private JTextField textField_Nome;

	private JLabel lblCpfcnpj;
	private JTextField textField_CPFCNPJ;

	private JLabel lblTelefone;
	private JTextField textField_Telefone;

	private JLabel lblEmail;
	private JTextField textField_Email;

	private JLabel lblEndereo;
	private JTextArea textArea_Endereco;
	private JLabel lblCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JCadastro frame = new JCadastro(null, null); // clienteSelecionado, jPrincipal
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
	public JCadastro(Cliente clienteSelecionado, JPrincipal jPrincipal) {
		// clienteSelecionado > para editar
		// jPrincipal > para recarregar a tela

		DAO dao = new DAO();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 410);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNome = new JLabel("Nome");
		lblNome.setBounds(29, 61, 70, 15);
		contentPane.add(lblNome);

		textField_Nome = new JTextField();
		textField_Nome.setBounds(29, 77, 675, 30);
		contentPane.add(textField_Nome);
		textField_Nome.setColumns(10);

		lblCpfcnpj = new JLabel("CPF/CNPJ");
		lblCpfcnpj.setBounds(29, 119, 70, 15);
		contentPane.add(lblCpfcnpj);

		textField_CPFCNPJ = new JTextField();
		textField_CPFCNPJ.setColumns(10);
		textField_CPFCNPJ.setBounds(29, 136, 320, 30);
		contentPane.add(textField_CPFCNPJ);

		lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(384, 119, 70, 15);
		contentPane.add(lblTelefone);

		textField_Telefone = new JTextField();
		textField_Telefone.setColumns(10);
		textField_Telefone.setBounds(384, 136, 320, 30);
		contentPane.add(textField_Telefone);

		lblEmail = new JLabel("Email");
		lblEmail.setBounds(29, 178, 70, 15);
		contentPane.add(lblEmail);

		textField_Email = new JTextField();
		textField_Email.setColumns(10);
		textField_Email.setBounds(29, 194, 675, 30);
		contentPane.add(textField_Email);

		lblEndereo = new JLabel("Endereço");
		lblEndereo.setBounds(29, 236, 70, 15);
		contentPane.add(lblEndereo);

		textArea_Endereco = new JTextArea();
		textArea_Endereco.setBorder(UIManager.getBorder("TextField.border"));
		textArea_Endereco.setBounds(29, 252, 675, 70);
		contentPane.add(textArea_Endereco);


		// Botões "Incluir":"Alterar" ---------------------------------------------------

		// A informação do botão vai mudar dependendo o objeto cliente
		JButton btnIncluirAlterar = new JButton(clienteSelecionado==null?"Incluir":"Alterar");
		btnIncluirAlterar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				// String id, String nome, String cpfcnpj, String email, String telefone, String endereco
				Cliente cliente = new Cliente(
							null,
							textField_Nome.getText(),
							textField_CPFCNPJ.getText(),
							textField_Email.getText(),
							textField_Telefone.getText(),
							textArea_Endereco.getText()
						);

				// Precisa ser null para ser feito o cadastro
				if (clienteSelecionado == null) {

					// Se os campos forem direfentes de vazio, continua com o cadastro
					if (!"".equalsIgnoreCase(textField_Nome.getText()) && !"".equalsIgnoreCase(textField_CPFCNPJ.getText())) {
						dao.cadastrarCliente(cliente);
						abrirTelaPrincipal(jPrincipal); // Quando incluir novo registro, reiniciar a tela principal

					}else {
						JOptionPane.showMessageDialog(null, "Os campos 'Nome' e 'CPF/CNPJ' são obrigatórios!");
						// A localização é null
					}


				}else {
					if (!"".equalsIgnoreCase(textField_Nome.getText()) && !"".equalsIgnoreCase(textField_CPFCNPJ.getText())) {
						// Se não for nullo, então chama a alteração para finalizar a edição
						dao.alterarCliente(clienteSelecionado.getId(), cliente);
						abrirTelaPrincipal(jPrincipal);

					}else {
						JOptionPane.showMessageDialog(null, "Os campos 'Nome' e 'CPF/CNPJ' são obrigatórios!");
						// A localização é null
					}
				}
			}
		});
		btnIncluirAlterar.setBackground(new Color(153, 193, 241));
		btnIncluirAlterar.setBounds(163, 334, 117, 25);
		contentPane.add(btnIncluirAlterar);


		// Botão Excluir -----------------------------------------------------------
		JButton btnExluir = new JButton("Excluir");
		btnExluir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				dao.excluirCliente(clienteSelecionado.getId());
				abrirTelaPrincipal(jPrincipal);
			}
		});
		btnExluir.setBackground(new Color(246, 97, 81));
		btnExluir.setBounds(444, 334, 117, 25);
		btnExluir.setVisible(false); // Não mostrar como padrão, ver apenas no alterar
		contentPane.add(btnExluir);

		lblCliente = new JLabel("Cliente");
		lblCliente.setFont(new Font("Dialog", Font.BOLD, 18));
		lblCliente.setBounds(29, 24, 99, 25);
		contentPane.add(lblCliente);



		// Preencher Campos na Alteração -------------------------------------------

		// Se o objeto de cliente for diferente de null, então esta ação será uma edição
		if (clienteSelecionado != null) {
			// Passa o objeto para os campos serem setados
			preencherCampos(clienteSelecionado);

			btnExluir.setVisible(true);
		}
	}


	// Busca os valores da classe e seta nos campos de edição
	private void preencherCampos(Cliente clienteSelecionado) {
		textField_Nome.setText(clienteSelecionado.getNome());
		textField_CPFCNPJ.setText(clienteSelecionado.getCpfcnpj());
		textField_Email.setText(clienteSelecionado.getEmail());
		textField_Telefone.setText(clienteSelecionado.getTelefone());
		textArea_Endereco.setText(clienteSelecionado.getEndereco());
	}

	// Método para reabrir a tela principal e mostrar a lista atualizada
	private void abrirTelaPrincipal(JPrincipal jPrincipal) {
		jPrincipal.dispose(); // Fechando a tela principal
		dispose(); // Fechando a tela atual

		// Instanciando novamente a tela principal e configurando
		jPrincipal = new JPrincipal();
		jPrincipal.setLocationRelativeTo(jPrincipal);
		jPrincipal.setVisible(true);

	}
}
