package br.unibh.loja.negocio;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.unibh.loja.entidades.Cliente;

@Stateless
@LocalBean
public class ServicoCliente implements DAO<Cliente, Long> {
	@Inject
	EntityManager em;
	@Inject
	private Logger log;

	public Cliente insert(Cliente t) throws Exception {
		if (t.getPerfil().equals("Standard")) {
			log.info("Persistindo " + t);
			em.persist(t);
			return t;
		} else {
			throw new Exception("Só é permitido inserir cliente com perfil Standard");
		}
	}

	public Cliente update(Cliente t) throws Exception {
		Calendar premiumCalendar = Calendar.getInstance();
		Calendar goldCalendar = Calendar.getInstance();

		premiumCalendar.add(Calendar.YEAR, -1);
		goldCalendar.add(Calendar.YEAR, -5);

		Date premiumDate = premiumCalendar.getTime();
		Date goldDate = goldCalendar.getTime();

		switch (t.getPerfil()) {
		case "Gold":
			if (t.getDataCadastro().before(goldDate)) {
				log.info("Atualizando " + t);
				return em.merge(t);
			} else {
				throw new Exception("So é permitido os perfis Standard e Premium entre 1 e 5 anos");
			}
		case "Premium":
			if (t.getDataCadastro().before(premiumDate)) {
				log.info("Atualizando " + t);
				return em.merge(t);
			} else {
				throw new Exception("So é permitido os perfis Standard com menos de 1 ano");
			}
		case "Standard":
			log.info("Atualizando " + t);
			return em.merge(t);

		default:
			throw new Exception("So é permitido clientes com os perfis Standard, Premium ou Gold");
		}
	}

	public void delete(Cliente t) throws Exception {
		log.info("Removendo " + t);
		Object c = em.merge(t);
		em.remove(c);
	}

	public Cliente find(Long k) throws Exception {
		log.info("Encontrando pela chave " + k);
		return em.find(Cliente.class, k);
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> findAll() throws Exception {
		log.info("Encontrando todos os objetos");
		return em.createQuery("from Cliente").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> findByLogin(String login) throws Exception {
		log.info("Encontrando o " + login);
		return em.createNamedQuery("Cliente.findByLogin").setParameter("login", "%" + login + "%").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> findByPerfil(String perfil) throws Exception {
		log.info("Encontrando todos com perfil " + perfil);
		return em.createNamedQuery("Cliente.findByPerfil").setParameter("perfil", "%" + perfil + "%").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> findByName(String nome) throws Exception {
		log.info("Encontrando o " + nome);
		return em.createNamedQuery("Cliente.findByName").setParameter("nome", "%" + nome + "%").getResultList();
	}
}