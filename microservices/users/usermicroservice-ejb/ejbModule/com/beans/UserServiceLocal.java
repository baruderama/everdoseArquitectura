package com.beans;

import java.util.List;

import javax.ejb.Local;

import com.entities.Product;

import model.Usuario;

@Local
public interface StockServiceLocal {
	public List<Usuario> findUsuario(String userName, String password);
	 public String addUsuario();
	 public List<Usuario> getAllUsuarios();
}
