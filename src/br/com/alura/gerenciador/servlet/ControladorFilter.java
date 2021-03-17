package br.com.alura.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.alura.gerenciador.acao.Acao;

/**
 * Servlet Filter implementation class AutorizacaoFilter
 */
//@WebFilter("/entrada") ja esta configurado no web.xml
public class ControladorFilter implements Filter {

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		
		System.out.println("AutorizacaoFilter");
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		String paramAcao = request.getParameter("acao");
		String nomeClasse = "br.com.alura.gerenciador.acao." + paramAcao;
		String nome;
		
		try {
			Class classe = Class.forName(nomeClasse);//carrega a classe com o nome
			Acao acao = (Acao) classe.newInstance();//precisa que a classe esteja implementando a interface
			nome = acao.executa(request,response);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			throw new ServletException(e);
		}
		
		String[] tipoEndereco = nome.split(":");
		if(tipoEndereco[0].equals("forward")) {
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/" + tipoEndereco[1]);
		rd.forward(request, response);
		}
		else {
			response.sendRedirect(tipoEndereco[1]);
		}
	}
}

