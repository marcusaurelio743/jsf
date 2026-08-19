package meujsf.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import meujsf.entidade.Pessoa;
import meujsf.util.JPAUTIL;

@WebFilter(urlPatterns = {"/*"})
public class FilterAutenticacao implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//captura o request da pagina
		HttpServletRequest req = (HttpServletRequest) request; 
		//capturando a sessão da pagina
		HttpSession session = req.getSession();
		
		//capturando o usuario da sessão
		Pessoa usuario = (Pessoa) session.getAttribute("usuarioLogado");
		
		String url = req.getServletPath();
		//validar usuario logado com a url de acesso
		
		if(!url.equals("index.jsf") && usuario == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsf");
			dispatcher.forward(request, response);
			return;
		}else {
		
			//execulta as ações do request e do response
			chain.doFilter(request, response);
		}
		
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		JPAUTIL.getEntityManager();
	}

	

}
