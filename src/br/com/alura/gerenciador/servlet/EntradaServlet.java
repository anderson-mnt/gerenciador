package br.com.alura.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.acao.Acao;

/**
 * Servlet implementation class EntradaServlet
 */
@WebServlet("/entrada")
public class EntradaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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


