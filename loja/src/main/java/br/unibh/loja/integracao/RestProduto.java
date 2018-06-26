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

import br.unibh.loja.entidades.Produto;
import br.unibh.loja.negocio.ServicoProduto;
import io.swagger.annotations.Api;

@Api
@Path("produto")
public class RestProduto {
	@Inject
	private ServicoProduto sc;

	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Produto> listarProdutos() throws Exception {
		return sc.findAll();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Produto buscarProduto(@PathParam("id") final Long id) throws Exception {
		return sc.find(id);
	}	

	@GET
	@Path("categoria/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Produto> listarProdutosCategoria(@PathParam("id") final Long id) throws Exception {
		return sc.findByCategoria(id);
	}
	
	@POST
	@Path("new")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Produto inserirProduto(Produto produto) throws Exception {
		return sc.insert(produto);
	}
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Produto atualizarProduto(@PathParam("id") final Long id, Produto produto) throws Exception {
		return sc.update(produto);
	}
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deletarProduto(@PathParam("id") final Long id) throws Exception {
		sc.delete(sc.find(id));
	}
}