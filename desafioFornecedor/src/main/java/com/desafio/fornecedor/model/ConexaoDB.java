package com.desafio.fornecedor.model;

import java.util.HashMap;
import java.util.Map;

import com.desafio.fornecedor.entities.Fornecedor;

public class ConexaoDB {
	
	private static Map<Integer, Fornecedor> fornecedores = new HashMap<Integer, Fornecedor>();
	public static Map<Integer,Fornecedor> getFornecedores(){return fornecedores;};
}
