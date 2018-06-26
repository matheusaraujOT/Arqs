package loja;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteCategoria {
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
		assertEquals(cat1.getId(), new Long(00001));
		assertEquals(cat1.getDescricao(), "Alimentos");
	}

	@Test
	public void testeComparacaoObjetos() throws ParseException {
		Categoria cat1 = new Categoria(1L, "Alimentos");
		Categoria cat2 = new Categoria(2L, "Roupa");
		assertNotEquals(cat1, cat2);
	}

	@Test
	public void testeImpressaoObjetos() throws ParseException {
		Categoria cat1 = new Categoria(1L, "Alimentos");
		assertEquals(cat1.toString(), "Categoria [id=1, descricao=Alimentos]");
	}

	@Test
	public void testeValidacaoCategoria1() {
		Categoria cat1 = new Categoria(1L, "Alimentos");
		System.out.println(cat1);

		Set<ConstraintViolation<Categoria>> constraintViolations = validator.validate(cat1);
		for (ConstraintViolation<Categoria> c : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c.getMessage());
		}
		Assert.assertEquals(0, constraintViolations.size());
	}

	@Test
	public void testeValidacaoCategoria2() {
		Categoria cat2 = new Categoria(2L, "Roupa@");
		System.out.println(cat2);

		Set<ConstraintViolation<Categoria>> constraintViolations = validator.validate(cat2);
		for (ConstraintViolation<Categoria> c : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c.getMessage());
		}
		Assert.assertEquals(1, constraintViolations.size());
	}
}