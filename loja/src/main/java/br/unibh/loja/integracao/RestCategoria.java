package br.unibh.loja.integracao;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.unibh.loja.entidades.Categoria;
import br.unibh.loja.negocio.ServicoCategoria;
import io.swagger.annotations.Api;

@Api
@Path("categoria")
public class RestCategoria {
	@Inject
	private ServicoCategoria sc;

	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Categoria> listarCategorias() throws Exception {
		return sc.findAll();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Categoria buscarCategoria(@PathParam("id") final Long id) throws Exception {
		return sc.find(id);
	}
	
	@POST
	@Path("new")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Categoria inserirCategoria(Categoria categoria) throws Exception {
		return sc.insert(categoria);
	}
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Categoria atualizarCategoria(@PathParam("id") final Long id, Categoria categoria) throws Exception {
		return sc.update(categoria);
	}
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deletarCategoria(@PathParam("id") final Long id) throws Exception {
		sc.delete(sc.find(id));
	}
}