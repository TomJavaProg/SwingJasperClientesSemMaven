package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;

import controller.GerarRelatorio;
import dao.DAO;
import model.Cliente;
import model.ModeloTabela;

public class JPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldBusca;
	private JTable table;
	private JScrollPane scrollPane;
	private ArrayList<Cliente> clientes; 	// typeArgument

	private JPrincipal jPrincipal; // Usado no cadastrar, para recarregar a tela principal

	private TableRowSorter<ModeloTabela> rowSorter; // Para o filtro da tabela

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JPrincipal frame = new JPrincipal();
					frame.setLocationRelativeTo(frame);
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
	public JPrincipal() {

		this.jPrincipal = this;
		// Usado no cadastrar, para recarregar a tela principal
		// Salvando a própria instância para usar o jCadastro

		DAO dao = new DAO();

		try {
			clientes = dao.listarCliente();

		} catch (Exception e) {
			e.printStackTrace();
		}

//		clientes = new ArrayList<Cliente>();
//		clientes.add(new Cliente("417D3A82-632A-31A8-73DC-66CE7927DDB2","Charissa Russell","26588671156","cursus@icloud.edu","(03) 2347 7327","1823 Non Rd."));
//		clientes.add(new Cliente("613E6E1B-659E-7A57-62C8-F86272371556","Colton Chapman","43669251148","ornare.libero@yahoo.edu","(09) 2762 7159","4085 Donec Rd."));
//		clientes.add(new Cliente("AC0321C2-0F53-9929-D157-AA74A5872453","Dorian Houston","97278975053","ac.risus@hotmail.couk","(07) 8946 8688","912-1591 Malesuada St."));
//		clientes.add(new Cliente("A6B1242A-E6C3-722A-E2E6-84E347296923","Madonna Morrison","99499303459","amet.lorem@aol.ca","(08) 4327 4765","492-2834 Est, Av."));
//		clientes.add(new Cliente("24EB16C2-CE97-CD15-DE06-1AD018CE4709","Christopher Williamson","94071817816","nunc.commodo@hotmail.edu","(01) 1653 8101","Ap #972-8361 Ac Av."));
//		clientes.add(new Cliente("17990665-36C5-2DB0-4AF5-754247C4ADC7","Fatima Hopkins","31307746815","vivamus.molestie.dapibus@protonmail.couk","(02) 3246 5787","P.O. Box 760, 5737 Tincidunt, Rd."));
//		clientes.add(new Cliente("C4359D29-452D-43FC-F16B-662543AA9B16","Veronica Cohen","71364695801","sodales.mauris.blandit@yahoo.org","(05) 1117 8254","Ap #572-8523 Magna. Rd."));
//		clientes.add(new Cliente("71D446EB-956C-8AE3-4141-61589AC524CD","Ursa Brooks","56270697212","at.iaculis.quis@outlook.couk","(05) 5154 5344","370-1890 Risus Rd."));
//		clientes.add(new Cliente("CF7431CC-6243-A87A-2133-9C73F01671B1","Veronica Riggs","70422038609","sodales.purus@hotmail.couk","(08) 4132 7278","Ap #767-8943 Sit Rd."));
//		clientes.add(new Cliente("729292D0-2C5A-47CE-2463-EAACA2519509","Rhonda Emerson","29714843350","ac.facilisis.facilisis@outlook.ca","(02) 7448 0516","P.O. Box 361, 6762 Nisi St."));


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));


		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNovoCliente = new JButton("Novo Cliente");
		btnNovoCliente.setBackground(new Color(153, 193, 241));
		btnNovoCliente.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {

				// Chamando a tela de cadrastro quando pressionar o botão
				JCadastro jCadastro = new JCadastro(null, jPrincipal);
				jCadastro.setLocationRelativeTo(jCadastro); // Setando a localização
				jCadastro.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Para fechar apenas a tela de cadastro
				jCadastro.setVisible(true); // Deixando ela visível

			}
		});
		
		btnNovoCliente.setBounds(22, 36, 136, 25);
		contentPane.add(btnNovoCliente);

		textFieldBusca = new JTextField();
		textFieldBusca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				filtrar();
			}

		});
		textFieldBusca.setBounds(409, 36, 382, 25);
		contentPane.add(textFieldBusca);
		textFieldBusca.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 83, 1156, 360);
		contentPane.add(scrollPane);


		// TABETA
		ModeloTabela modeloTabela = new ModeloTabela(clientes);

		table = new JTable();
		table.setModel(modeloTabela);

		// Evento para editar o litem da tabela
		table.addMouseListener(new MouseAdapter() {  	// inheritedMethod
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 1) {
					// 1 = Botão esquedo
					// 2 = Scrool
					// 3 = Botão direito

					try {
						Cliente clienteSelecionado = dao.consultarCliente(modeloTabela.getValueAt(table.getSelectedRow(), 0).toString());
						// Retorna o campo "0" corresponde a primeira coluna ID, que será usado no consultarCliente
						// e vai trazer o objeto do cliente correspondente

						JCadastro jcadastro = new JCadastro(clienteSelecionado, jPrincipal); // Passando o cliente para a tela de cadastro
						jcadastro.setLocationRelativeTo(jcadastro); // Setando a localização, para mostrar ao centro
						jcadastro.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Para fechar apenas a tela de cadastro
						jcadastro.setVisible(true); // Deixando ela visível

					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});

//		table.setModel(new DefaultTableModel(
//			new Object[][] {
//			},
//			new String[] {
//				"ID", "CPF/CNPJ", "Nome", "E-mail", "Telefone", "Endere\u00E7o"
//			}
//		));

		rowSorter = new TableRowSorter<>(modeloTabela); // Intsanciando passando o objeto da tabela
		table.setRowSorter(rowSorter);

		scrollPane.setViewportView(table);

		JLabel lblFiltro = new JLabel("Filtro");
		lblFiltro.setBounds(355, 41, 43, 15);
		contentPane.add(lblFiltro);
		
		JButton btnGerarRelatrio = new JButton("Gerar Relatório");
		btnGerarRelatrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new GerarRelatorio();
			}
		});
		btnGerarRelatrio.setBounds(1024, 36, 154, 25);
		btnGerarRelatrio.setBackground(new Color(153, 193, 241));
		contentPane.add(btnGerarRelatrio);

	}


	// Criando um filtro para qualquer um dos campos
	private void filtrar() {
		String busca = textFieldBusca.getText().trim(); // busca o texto e remove os espaços

		if (busca.length() == 0) {
			rowSorter.setRowFilter(null);
		}else {
			rowSorter.setRowFilter(RowFilter.regexFilter("(?i)"+busca)); // ?i para aceitar minuscula e maiuscula
		}
	}
}
