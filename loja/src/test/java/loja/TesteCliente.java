package loja;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import br.unibh.loja.entidades.Cliente;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteCliente {
	private static Validator validator;

	@BeforeClass
	public static void setUp() {
		System.out.println("Inicializando validador...");
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testeCriacaoObjeto() throws ParseException {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date dataN = (Date) formatter.parse("01/01/2001");
		Date dataC = (Date) formatter.parse("11/04/2018");

		Cliente cli1 = new Cliente(1L, "Maria", "Mariazinha", "12345", "Cliente", "001.002.003-04", "31 1234-5678",
				"mariazinhagatinha@hotmail.com", dataN, dataC);
		assertEquals(cli1.getId(), new Long(1));
		assertEquals(cli1.getNome(), "Maria");
		assertEquals(cli1.getLogin(), "Mariazinha");
		assertEquals(cli1.getSenha(), "12345");
		assertEquals(cli1.getPerfil(), "Cliente");
		assertEquals(cli1.getCpf(), "001.002.003-04");
		assertEquals(cli1.getTelefone(), "31 1234-5678");
		assertEquals(cli1.getEmail(), "mariazinhagatinha@hotmail.com");
		assertEquals(cli1.getDataNascimento(), dataN);
		assertEquals(cli1.getDataCadastro(), dataC);
	}

	@Test
	public void testeComparacaoObjetos() throws ParseException {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date dataN1 = (Date) formatter.parse("01/01/1998");
		Date dataC1 = (Date) formatter.parse("11/04/2018");
		Cliente cli1 = new Cliente(1L, "Maria", "Mariazinha", "12345", "Cliente", "001.002.003-04", "31 1234-5678",
				"mariazinhagatinha@hotmail.com", dataN1, dataC1);
		Date dataN2 = (Date) formatter.parse("01/05/1995");
		Date dataC2 = (Date) formatter.parse("11/04/2018");
		Cliente cli2 = new Cliente(2L, "João", "Joãozinho", "54321", "Cliente", "005.006.007-08", "31 4321-8765",
				"joaozinhotop@hotmail.com", dataN2, dataC2);
		assertNotEquals(cli1, cli2);
	}

	@Test
	public void testeImpressaoObjetos() throws ParseException {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date dataN1 = (Date) formatter.parse("01/01/1998");
		Date dataC1 = (Date) formatter.parse("11/04/2018");
		Cliente cli1 = new Cliente(1L, "Maria", "Mariazinha", "12345", "Cliente", "001.002.003-04", "31 1234-5678",
				"mariazinhagatinha@hotmail.com", dataN1, dataC1);
		assertEquals(cli1.toString(),
				"Cliente [id=1, nome=Maria, login=Mariazinha, senha=12345, perfil=Cliente, cpf=001.002.003-04, telefone=31 1234-5678, email=mariazinhagatinha@hotmail.com, dataNascimento=Thu Jan 01 00:00:00 BRST 1998, dataCadastro=Wed Apr 11 00:00:00 BRT 2018]");

	}

	@Test
	public void testeValidacaoCliente1() throws ParseException {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date dataN1 = (Date) formatter.parse("01/01/1998");
		Date dataC1 = (Date) formatter.parse("11/04/2018");
		Cliente cli1 = new Cliente(1L, "Maria", "Mariazinha", "12345", "Cliente", "076.858.366-70", "(31)1234-5678",
				"mariazinhagatinha@hotmail.com", dataN1, dataC1);
		System.out.println(cli1);

		Set<ConstraintViolation<Cliente>> constraintViolations = validator.validate(cli1);
		for (ConstraintViolation<Cliente> c : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c.getMessage());
		}
		Assert.assertEquals(0, constraintViolations.size());
	}

	@Test
	public void testeValidacaoCliente2() throws ParseException {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date dataN2 = (Date) formatter.parse("01/05/1995");
		Date dataC2 = (Date) formatter.parse("11/04/2018");
		Cliente cli2 = new Cliente(2L, "João", "Joaozinho", "54321", "Cliente", "065.845.354-05", "(31)4321-8765",
				"joaozinhotop@hotmail.com", dataN2, dataC2);
		System.out.println(cli2);

		Set<ConstraintViolation<Cliente>> constraintViolations = validator.validate(cli2);
		for (ConstraintViolation<Cliente> c : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c.getMessage());
		}
		Assert.assertEquals(1, constraintViolations.size());
	}

}