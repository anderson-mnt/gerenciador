package br.com.alura.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

//@WebFilter("/entrada") ja esta configurado no web.xml
public class MonitoramentoFilter implements Filter {
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("MonitoramentoFilter");
		
		long antes = System.currentTimeMillis();//calcula o tempo antes da acao
		
		String acao = request.getParameter("acao");
		
		//executa acao
		chain.doFilter(request, response);//sem esse metodo o a requisicao fica parada e nao segue adiante
		
		long depois = System.currentTimeMillis();//calcula o tempo depois da acao
		System.out.println("Tempo de execução:" + acao + " -> " + (depois - antes));//faz o calculo de tempo de execucao
		
	}

}
