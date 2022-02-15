package com.desafio.fornecedor.resource;

import java.util.List;

import com.desafio.fornecedor.entities.Fornecedor;
import com.desafio.fornecedor.repository.FornecedorRepository;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/fornecedor")
public class FornecedorResource {
	FornecedorRepository fornecedorRepository = new FornecedorRepository();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Fornecedor> getFornecedores() {
		return fornecedorRepository.getAll();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addFornecedor(Fornecedor novoFornecedor) {
		try {
			fornecedorRepository.addFornecedor(novoFornecedor);
			return Response.status(Response.Status.CREATED).entity(novoFornecedor).build();
		} catch (Exception ex) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
		}
		
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Fornecedor getFornecedor(@PathParam("id") int id) {
		return fornecedorRepository.getFornecedor(id);
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Fornecedor updateFornecedor(@PathParam("id") int id, Fornecedor updateFornecedor) {
		return fornecedorRepository.updateFornecedor(id, updateFornecedor);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteFornecedor(@PathParam("id") int id) {
		fornecedorRepository.deleteFornecedor(id);
	}

}
