package meujsf.repository;

import meujsf.entidade.Pessoa;

public interface IDaoPessoa {
	public Pessoa buscaPessoa(String login,String senha);
}
