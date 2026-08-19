package meujsf.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import meujsf.entidade.Pessoa;
import meujsf.util.JPAUTIL;

public class IDaoPessoaImpl implements IDaoPessoa  {

	@Override
	public Pessoa buscaPessoa(String login, String senha) {
		Pessoa usuario = null;
		EntityManager entityManager = JPAUTIL.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		usuario = (Pessoa) entityManager.createQuery("select p from Pessoa p  where  p.login = '"+login+"' and p.senha = '"+senha+"'").getSingleResult();
		
		transaction.commit();
		entityManager.close();
		
		return usuario;
	}

}
