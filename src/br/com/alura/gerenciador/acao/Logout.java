package br.com.alura.gerenciador.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession sessao = request.getSession();
		
		//sessao.removeAttribute("usuarioLogado"); remove somente essse atributo
		sessao.invalidate();//invalida toda a sessao e destroi o cookie de todos os valores de session removendo da memoria
		return "redirect:entrada?acao=LoginForm";
	}

}
