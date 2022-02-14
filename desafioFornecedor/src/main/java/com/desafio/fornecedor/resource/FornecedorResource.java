package com.desafio.fornecedor.resource;

import java.sql.SQLException;
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
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/fornecedor")
public class FornecedorResource {
	
	@DELETE
    @Path("/excluirFornecedor/{id}")
    public Response excluirFornecedor(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
    	try {
    		FornecedorRepository fornecDAO = new FornecedorRepository();
			fornecDAO.excluir(id);
			return Response.status(Response.Status.OK).build();
		} catch (SQLException e) {
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
    }
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/consultarFornecedores")
	public List<Fornecedor> ListarFornecedores() throws ClassNotFoundException, SQLException{
		
		try {
			FornecedorRepository fornecDAO = new FornecedorRepository();
			return fornecDAO.listAll();
		} catch (SQLException e) {
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	 @GET
	    @Produces(MediaType.APPLICATION_JSON)
	    @Path("/obterFornecedor/{id}")
	    public Fornecedor obterFornecedor(@PathParam("id") int id) throws SQLException, ClassNotFoundException {
	    	try {
	    		FornecedorRepository fornecDAO = new FornecedorRepository();
				return fornecDAO.select(id);
			} catch (SQLException e) {
				throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
			}
	    }
	 
	 @POST
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Path("/inserirFornecedor")
	    public Response inserirFornecedor(Fornecedor fornec) throws ClassNotFoundException, SQLException {
	    	try {
	    		FornecedorRepository fornecDAO = new FornecedorRepository();
	    		fornecDAO.inserir(fornec);
	    		return Response.status(Response.Status.OK).build();
			} catch (SQLException e) {
				throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
			}
	    }
	 
	 @PUT
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Path("/alterarFornecedor")
	    public Response alterarFornecedor(Fornecedor fornec) throws ClassNotFoundException, SQLException {
	    	try {
	    		FornecedorRepository fornecDAO = new FornecedorRepository();
				fornecDAO.alterar(fornec);
				return Response.status(Response.Status.OK).build();
			} catch (SQLException e) {
				throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
			}
	    }


}
