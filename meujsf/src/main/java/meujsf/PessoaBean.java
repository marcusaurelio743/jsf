package meujsf;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import meujsf.dao.DaoGeneric;
import meujsf.entidade.Pessoa;
import meujsf.repository.IDaoPessoa;
import meujsf.repository.IDaoPessoaImpl;

@ManagedBean(name = "pessoaBean")
@ViewScoped
public class PessoaBean {
	private Pessoa pessoa = new Pessoa();
	private DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<>();
	private List<Pessoa> pessoas = new ArrayList<>();
	
	private IDaoPessoa daoPessoa = new IDaoPessoaImpl();
	
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
	public String logar() {
		Pessoa usuario = daoPessoa.buscaPessoa(pessoa.getLogin(), pessoa.getSenha());
		
		if(usuario != null) {
			//adicionar o usuario na sessão
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			externalContext.getSessionMap().put("usuarioLogado", usuario);
			return "pagina.jsf";
		}
		return "index.jsf";
	}
	//metodo que permiti ao usuario ter acesso ao sistema
	public boolean permitirAcesso(String acesso) {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Pessoa user = (Pessoa) externalContext.getSessionMap().get("usuarioLogado");
		return user.getUserPerfil().equals(acesso);
	}
	
}
