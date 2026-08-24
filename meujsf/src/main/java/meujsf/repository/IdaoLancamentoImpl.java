package meujsf.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import meujsf.entidade.Lancamento;
import meujsf.util.JPAUTIL;

public class IdaoLancamentoImpl implements IdaoLancamento {

	@Override
	public List<Lancamento> LancamentosUsuarioLogado(Long codUser) {
		List<Lancamento> lancamentos = new ArrayList<>();
		
		EntityManager entityManager = JPAUTIL.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		lancamentos = entityManager.createQuery(" from Lancamento where pessoa_id = "+codUser).getResultList();
		
		return lancamentos;
	}

}
