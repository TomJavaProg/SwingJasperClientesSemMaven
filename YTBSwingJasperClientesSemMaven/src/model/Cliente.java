package model;

public class Cliente {
	protected String id;
	protected String nome;
	protected String cpfcnpj;
	protected String email;
	protected String telefone;
	protected String endereco;

	public Cliente() {
	}

	public Cliente(String id, String nome, String cpfcnpj, String email, String telefone, String endereco) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpfcnpj = cpfcnpj;
		this.email = email;
		this.telefone = telefone;
		this.endereco = endereco;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the cpfcnpj
	 */
	public String getCpfcnpj() {
		return cpfcnpj;
	}

	/**
	 * @param cpfcnpj the cpfcnpj to set
	 */
	public void setCpfcnpj(String cpfcnpj) {
		this.cpfcnpj = cpfcnpj;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the telefone
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * @param telefone the telefone to set
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/**
	 * @return the endereco
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco the endereco to set
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}



}
