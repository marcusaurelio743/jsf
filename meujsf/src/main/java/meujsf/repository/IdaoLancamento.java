package meujsf.repository;

import java.util.List;

import meujsf.entidade.Lancamento;

public interface IdaoLancamento {
	List<Lancamento> LancamentosUsuarioLogado(Long codUser);

}
