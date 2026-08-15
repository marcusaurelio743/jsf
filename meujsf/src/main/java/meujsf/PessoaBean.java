package meujsf;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import meujsf.dao.DaoGeneric;
import meujsf.entidade.Pessoa;

@ManagedBean(name = "pessoaBean")
@ViewScoped
public class PessoaBean {
	private Pessoa pessoa = new Pessoa();
	private DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<>();
	private List<Pessoa> pessoas = new ArrayList<>();
	
	public String salvar() {
		pessoa = daoGeneric.merge(pessoa);
		carregarPessoa();
		return "";
	}
	
	public String novo() {
		pessoa = new Pessoa();
		return "";
	}

	public Pessoa getPessoa() {
		return pessoa;
	}
	public String deletar() {
		daoGeneric.delete(pessoa);
		novo();
		carregarPessoa();
		return "";
	}
	@PostConstruct
	public void carregarPessoa() {
		pessoas = daoGeneric.findAll(Pessoa.class);
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public DaoGeneric<Pessoa> getDaoGeneric() {
		return daoGeneric;
	}

	public void setDaoGeneric(DaoGeneric<Pessoa> daoGeneric) {
		this.daoGeneric = daoGeneric;
	}
	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	
}
