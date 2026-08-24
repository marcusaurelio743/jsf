package meujsf;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import meujsf.dao.DaoGeneric;
import meujsf.entidade.Lancamento;
import meujsf.entidade.Pessoa;
import meujsf.repository.IdaoLancamento;
import meujsf.repository.IdaoLancamentoImpl;

@ViewScoped
@ManagedBean(name = "lancamentoBean")
public class LancamentoBean {
	private Lancamento lancamento = new Lancamento();
	private DaoGeneric<Lancamento> daoGeneric = new DaoGeneric<>();
	private List<Lancamento> lancamentos = new ArrayList<>();
	private IdaoLancamento idaoLancamento = new IdaoLancamentoImpl();
	
	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Pessoa user = (Pessoa) externalContext.getSessionMap().get("usuarioLogado");
		lancamento.setPessoa(user);
		
		daoGeneric.merge(lancamento);
		novo();
		carregarLancamento();
		return "";
	}
	
	@PostConstruct
	private void carregarLancamento() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Pessoa user = (Pessoa) externalContext.getSessionMap().get("usuarioLogado");
		 lancamentos = idaoLancamento.LancamentosUsuarioLogado(user.getId());
		
	}
	public String deletar() {
		daoGeneric.delete(lancamento);
		novo();
		carregarLancamento();
		return "";
	}
	
	public String novo() {
		lancamento = new Lancamento();
		return "";
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	public DaoGeneric<Lancamento> getDaoGeneric() {
		return daoGeneric;
	}

	public void setDaoGeneric(DaoGeneric<Lancamento> daoGeneric) {
		this.daoGeneric = daoGeneric;
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

}
