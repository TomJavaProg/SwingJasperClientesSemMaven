package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.Backup;

public class JBackup extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private Backup backup;
	private ArrayList<String> arquivosBackup;
	private String[] nomesBackup;
	private String itemSelecionado;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JBackup frame = new JBackup();
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
	public JBackup() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 450, 300);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
						
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 35, 1128, 355);
		contentPane.add(scrollPane);
		
		// Instanciando e retornando a lista de arquivos
		backup = new Backup();
		arquivosBackup = new ArrayList<>();
		arquivosBackup = backup.listarArquivos();
		nomesBackup = arquivosBackup.toArray(new String[arquivosBackup.size()]);
		
		// Setando a lista de arquivos no objeto list que irá para o painel
		JList list = new JList();
		list.setListData(nomesBackup);
		
		scrollPane.setViewportView(list);
		
		
		// ----------------------------------------------
		JButton btnGerarBackup = new JButton("Gerar Backup");
		btnGerarBackup.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(JOptionPane.showConfirmDialog(btnGerarBackup, "Deseja gerar o backup? ") == JOptionPane.YES_NO_OPTION) {
					
					backup.gerarBackup();
					arquivosBackup = backup.listarArquivos();
					
					nomesBackup = arquivosBackup.toArray(new String[arquivosBackup.size()]);
					
					// Todo esse bloco referente ao 'btnGerarBackup', precisa estar em baixo da declaração 'list', para
					// que essa chamade de 'list' funcione;
					// Acho que eu também poderia por como variável global, não testei.
					list.setListData(nomesBackup);
					
					// Para atualizar na tela os arquivos que foram gerados.
					revalidate();
					repaint();
				}
				
			}
		});
		btnGerarBackup.setBounds(33, 418, 160, 25);
		contentPane.add(btnGerarBackup);
		
				
		// ----------------------------------------------
		JButton btnRestaurarBackup = new JButton("Restaurar Backup");
		btnRestaurarBackup.setEnabled(false); // Deixar desabilitado por pardão, para só habilitar quando um item estiver selecionado.
		btnRestaurarBackup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(JOptionPane.showConfirmDialog(btnGerarBackup, "Deseja restaurar o backup? ") == JOptionPane.YES_NO_OPTION) {
					
					// Preciso pegar a linha com o caminho selecionado para passar nessa chamada.
					try {
						backup.restaurarBackup(itemSelecionado);
						
					} catch (FileNotFoundException e) {
						e.printStackTrace();
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		btnRestaurarBackup.setBounds(222, 418, 160, 25);
		contentPane.add(btnRestaurarBackup);
		
		
		// Buscando a linha com o caminho selecionado.
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				
				// Para o valor que 'não' foi ajustado.
				if(!arg0.getValueIsAdjusting()) { 
					
					// Lógica para forçar a seleção do item.
					if(list.getSelectedIndex() == -1) {
						
						// Se for menor que 1, então a selecionar o item já selecionado.
						list.setSelectedIndex(arg0.getFirstIndex());
					}
					
					// Pegar o valor da lista selecionado.
					itemSelecionado = ((JList<String>)arg0.getSource()).getSelectedValue();
					
					// Manter o botão ativo apenas quando um item estiver selecionado.
					if(itemSelecionado != null) {
						btnRestaurarBackup.setEnabled(true);
					}
				}
			}
		});
		
	}
}
