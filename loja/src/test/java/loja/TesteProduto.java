package loja;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.unibh.loja.entidades.Categoria;
import br.unibh.loja.entidades.Produto;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteProduto {
	private static Validator validator;

	@BeforeClass
	public static void setUp() {
		System.out.println("Inicializando validador...");
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testeCriacaoObjeto() throws ParseException {
		Categoria cat1 = new Categoria(1L, "Alimentos");
		assertEquals(cat1.getId(), new Long(1));
		assertEquals(cat1.getDescricao(), "Alimentos");

		BigDecimal preco = new BigDecimal(0.30);
		Produto prod1 = new Produto(1L, "Pão", "Alimento", cat1, preco, "Padaria");
		assertEquals(prod1.getId(), new Long(1));
		assertEquals(prod1.getNome(), "Pão");
		assertEquals(prod1.getDescricao(), "Alimento");
		assertEquals(prod1.getCategoria(), cat1);
		assertEquals(prod1.getPreco(), new BigDecimal(0.30));
		assertEquals(prod1.getFabricante(), "Padaria");
	}

	@Test
	public void testeComparacaoObjetos() throws ParseException {
		Categoria cat1 = new Categoria(1L, "Alimentos");
		Categoria cat2 = new Categoria(2L, "Roupa");
		assertNotEquals(cat1, cat2);

		BigDecimal preco1 = new BigDecimal(0.30);
		Produto prod1 = new Produto(1L, "Pão", "Alimento", cat1, preco1, "Padaria");
		BigDecimal preco2 = new BigDecimal(0.80);
		Produto prod2 = new Produto(2L, "Blusa", "Roupa", cat2, preco2, "Adidas");
		assertNotEquals(prod1, prod2);
	}

	@Test
	public void testeImpressaoObjetos() throws ParseException {
		Categoria cat1 = new Categoria(1L, "Alimentos");
		assertEquals(cat1.toString(), "Categoria [id=1, descricao=Alimentos]");

		BigDecimal preco1 = new BigDecimal(0.30);
		Produto prod1 = new Produto(1L, "Pão", "Alimento", cat1, preco1, "Padaria");
		assertEquals(prod1.toString(),
				"Produto [id=1, nome=Pão, descricao=Alimento, categoria=Categoria [id=1, descricao=Alimentos], preco=0.299999999999999988897769753748434595763683319091796875, fabricante=Padaria]");
	}

	@Test
	public void testeValidacaoProduto1() {
		Categoria cat1 = new Categoria(1L, "Alimentos");
		BigDecimal preco1 = new BigDecimal(0.30);
		Produto prod1 = new Produto(1L, "Pão", "Alimento", cat1, preco1, "Padaria");
		System.out.println(prod1);

		Set<ConstraintViolation<Produto>> constraintViolations = validator.validate(prod1);
		for (ConstraintViolation<Produto> c : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c.getMessage());
		}
		Assert.assertEquals(0, constraintViolations.size());
	}

	@Test
	public void testeValidacaoProduto2() {
		Categoria cat2 = new Categoria(2L, "Roupa");
		BigDecimal preco2 = new BigDecimal(0.80);
		Produto prod2 = new Produto(2L, "Blusa@", "Roupa", cat2, preco2, "Adidas");
		System.out.println(prod2);

		Set<ConstraintViolation<Produto>> constraintViolations = validator.validate(prod2);
		for (ConstraintViolation<Produto> c : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c.getMessage());
		}
		Assert.assertEquals(1, constraintViolations.size());
	}

}