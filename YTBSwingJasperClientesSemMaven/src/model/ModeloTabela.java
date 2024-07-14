package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModeloTabela extends AbstractTableModel{

	private static final String[] colunas = {
			"ID", "CPF/CNPJ", "Nome", "E-mail", "Telefone", "Endere\u00E7o"
		};

	private ArrayList<Cliente> clientes;



	public ModeloTabela(ArrayList<Cliente> clientes) {
		super();
		this.clientes = clientes;
	}

	// Para saber a quantidade linhas, vamos pegar a quantidade de clientes.
	@Override
	public int getRowCount() {
		return clientes.size();
	}

	// A quantidade de colunas será o comprimento dela.
	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	// Determina o valor de cada célula.
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// Pegando cada um dos clientes.
		Cliente cliente = clientes.get(rowIndex);

		// Vamos preencher todas as informações desse cliente.
		if(columnIndex == 0) {
			return cliente.getId();

		}else if (columnIndex == 1) {
			return cliente.getCpfcnpj();

		}else if (columnIndex == 2) {
			return cliente.getNome();

		}else if (columnIndex == 3) {
			return cliente.getEmail();

		}else if (columnIndex == 4) {
			return cliente.getTelefone();

		}else if (columnIndex == 5) {
			return cliente.getEndereco();

		}else {
			return null;
		}

	}

	//
	@Override
	public String getColumnName(int column) {
//		return super.getColumnName(column);

		// Pegando pelo index da coluna.
		return colunas[column];
	}

}
