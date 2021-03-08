package br.com.alura.gerenciador.acao;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Empresa;

public class MostraEmpresa {

	public void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paramId = request.getParameter("id");
		Integer id = Integer.valueOf(paramId);
		
		Banco banco = new Banco();
		Empresa empresa = banco.GetEmpresaByID(id);
		
		System.out.print(empresa.getNome());
		
		RequestDispatcher rd = request.getRequestDispatcher("/FormAlteraEmpresa.jsp");
		request.setAttribute("empresa", empresa);
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		rd.forward(request, response);
		
	}
}
