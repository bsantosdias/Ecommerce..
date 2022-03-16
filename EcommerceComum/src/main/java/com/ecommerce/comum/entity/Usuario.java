package com.ecommerce.comum.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")

public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 130, nullable = false, unique = true)
	private String email;

	@Column(length = 64, nullable = false)
	private String senha;

	@Column(name = "nome", length = 45, nullable = false)
	private String nome;

	@Column(name = "nascimento")
	private String nascimento;

	@Column(name = "telefone")
	private String telefone;

	@Column(name = "cpf", nullable = false)
	private String cpf;

	private boolean ativado;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuarios_cargos", joinColumns = @JoinColumn(name = " usuario_id"), inverseJoinColumns = @JoinColumn(name = "cargo_id"))

	private Set<Cargo> cargos = new HashSet<>();

	public Usuario() {

	}

	public Usuario(String email, String senha, String nome, String nascimento, String telefone, String cpf) {
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.nascimento = nascimento;
		this.telefone = telefone;
		this.cpf = cpf;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNascimento() {
		return nascimento;
	}

	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public boolean isAtivado() {
		return ativado;
	}

	public void setAtivado(boolean ativado) {
		this.ativado = ativado;
	}

	public Set<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(Set<Cargo> cargos) {
		this.cargos = cargos;
	}

	public void adicionarCargo(Cargo cargo) {
		this.cargos.add(cargo);
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", email=" + email + ", nome=" + nome + ", nascimento=" + nascimento
				+ ", telefone=" + telefone + ", cpf=" + cpf + ", cargos=" + cargos + "]";
	}

}
