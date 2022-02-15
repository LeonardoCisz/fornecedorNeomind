package com.desafio.fornecedor.repository;

import java.util.ArrayList;
import java.util.Map;

import com.desafio.fornecedor.entities.Fornecedor;
import com.desafio.fornecedor.model.ConexaoDB;

public class FornecedorRepository {

	private Map<Integer, Fornecedor> fornecedores = ConexaoDB.getFornecedores();

	public ArrayList<Fornecedor> getAll() {
		return new ArrayList<Fornecedor>(fornecedores.values());
	}

	public Fornecedor getFornecedor(int id) {
		return fornecedores.get(id);
	}

	public Fornecedor addFornecedor(Fornecedor novoFornecedor) {
		if (fornecedores.containsKey(novoFornecedor.getId()) || buscaCnpj(novoFornecedor)) {
			return null;
		} else {
			fornecedores.put(novoFornecedor.getId(), novoFornecedor);
			return novoFornecedor;
		}
	}

	public void deleteFornecedor(int id) {
		fornecedores.remove(id);
	}

	public boolean buscaCnpj(Fornecedor fornecedor) {
		ArrayList<Fornecedor> fornecedoresArray = new ArrayList<Fornecedor>(fornecedores.values());
		boolean valor = false;
		for (Fornecedor fornece : fornecedoresArray) {
			if (fornece.getCnpj().equals(fornecedor.getCnpj()) && !(fornece.getId() == fornecedor.getId())) {
				valor = true;
			}
		}
		return valor;
	}

	public Fornecedor updateFornecedor(int id, Fornecedor updateFornecedor) {
		if (fornecedores.containsKey(id) && !buscaCnpj(updateFornecedor)) {
			fornecedores.put(id, updateFornecedor);
			return updateFornecedor;
		}
		return null;
	}

}
