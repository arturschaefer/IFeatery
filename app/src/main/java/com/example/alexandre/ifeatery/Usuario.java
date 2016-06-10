package com.example.alexandre.ifeatery;

public class Usuario {
	private String email,nome;
	private double saldo;
	
	public Usuario(){
		
	}
	
	public Usuario(String email, String nome, double saldo) {
		super();
		this.email = email;
		this.nome = nome;
		this.saldo = saldo;
	}
	
	
	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public double getSaldo() {
		return saldo;
	}



	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	
}
