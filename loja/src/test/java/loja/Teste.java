package loja;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import br.unibh.loja.entidades.*;

public class Teste {

	@Test
	public void testeCriacaoObjeto() throws ParseException {
		Categoria cat1 = new Categoria (00001L, "Alimentos");
		assertEquals(cat1.getId(),new Long(00001));
		assertEquals(cat1.getDescricao(),"Alimentos");		
		
		BigDecimal preco = new BigDecimal(0.30);
		Produto prod1 = new Produto (00001L, "Pão", "Alimento", cat1, preco, "Padaria");
		assertEquals(prod1.getId(),new Long(00001));
		assertEquals(prod1.getNome(),"Pão");
		assertEquals(prod1.getDescricao(),"Alimento");
		assertEquals(prod1.getCategoria(),cat1);
		assertEquals(prod1.getPreco(),new BigDecimal(0.30));
		assertEquals(prod1.getFabricante(),"Padaria");
		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date dataN = (Date)formatter.parse("01/01/2001");
		Date dataC = (Date)formatter.parse("11/04/2018");
		
		Cliente cli1 = new Cliente (00001L, "Maria", "Mariazinha", "12345", "Cliente", "0001.0002.0003-04", "31 1234-5678", "mariazinhagatinha@hotmail.com", dataN, dataC);
		assertEquals(cli1.getId(),new Long(00001));
		assertEquals(cli1.getNome(),"Maria");
		assertEquals(cli1.getLogin(),"Mariazinha");
		assertEquals(cli1.getSenha(),"12345");
		assertEquals(cli1.getPerfil(),"Cliente");
		assertEquals(cli1.getCpf(),"0001.0002.0003-04");
		assertEquals(cli1.getTelefone(),"31 1234-5678");
		assertEquals(cli1.getEmail(),"mariazinhagatinha@hotmail.com");
		assertEquals(cli1.getDataNascimento(),dataN);
		assertEquals(cli1.getDataCadastro(),dataC);
	}
		
	@Test
	public void testeComparacaoObjetos() throws ParseException {
		Categoria cat1 = new Categoria (00001L, "Alimentos");
		Categoria cat2 = new Categoria (00002L, "Roupa");
		assertNotEquals(cat1,cat2);	
		
		BigDecimal preco1 = new BigDecimal(0.30);
		Produto prod1 = new Produto (00001L, "Pão", "Alimento", cat1, preco1, "Padaria");
		BigDecimal preco2 = new BigDecimal(0.80);
		Produto prod2 = new Produto (00002L, "Blusa", "Roupa", cat2, preco2, "Adidas");
		assertNotEquals(prod1,prod2);	
		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date dataN1 = (Date)formatter.parse("01/01/1998");
		Date dataC1 = (Date)formatter.parse("11/04/2018");	
		Cliente cli1 = new Cliente (00001L, "Maria", "Mariazinha", "12345", "Cliente", "0001.0002.0003-04", "31 1234-5678", "mariazinhagatinha@hotmail.com", dataN1, dataC1);
		Date dataN2 = (Date)formatter.parse("01/05/1995");
		Date dataC2 = (Date)formatter.parse("11/04/2018");	
		Cliente cli2 = new Cliente (00002L, "João", "Joãozinho", "54321", "Cliente", "0005.0006.0007-08", "31 4321-8765", "joaozinhotop@hotmail.com", dataN2, dataC2);
	}
		
	@Test
	public void testeImpressaoObjetos() throws ParseException {
		Categoria cat1 = new Categoria (00001L, "Alimentos");
		assertEquals(cat1.toString(), "Categoria [id=1, descricao=Alimentos]");
		
		BigDecimal preco1 = new BigDecimal(0.30);
		Produto prod1 = new Produto (00001L, "Pão", "Alimento", cat1, preco1, "Padaria");
		assertEquals(prod1.toString(), "Produto [id=1, nome=Pão, descricao=Alimento, categoria=Categoria [id=1, descricao=Alimentos], preco=0.299999999999999988897769753748434595763683319091796875, fabricante=Padaria]");
		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date dataN1 = (Date)formatter.parse("01/01/1998");
		Date dataC1 = (Date)formatter.parse("11/04/2018");	
		Cliente cli1 = new Cliente (00001L, "Maria", "Mariazinha", "12345", "Cliente", "0001.0002.0003-04", "31 1234-5678", "mariazinhagatinha@hotmail.com", dataN1, dataC1);
		assertEquals(cli1.toString(), "Cliente [id=1, nome=Maria, login=Mariazinha, senha=12345, perfil=Cliente, cpf=0001.0002.0003-04, telefone=31 1234-5678, email=mariazinhagatinha@hotmail.com, dataNascimento=Thu Jan 01 00:00:00 BRST 1998, dataCadastro=Wed Apr 11 00:00:00 BRT 2018]");
		
	}
	
}