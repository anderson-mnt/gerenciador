package br.com.alura.gerenciador.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Empresa;

/**
 * Servlet implementation class EmpresaService
 */
@WebServlet("/empresas")
public class EmpresaService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		List<Empresa> empresas = new Banco().getEmpresas();
		String valor = request.getHeader("Accept");//le o cabeçalho da classe ClientWebService
		System.out.println(valor);
		
		if(valor.contains("xml")) {
			//XML
			XStream xstream = new XStream();//objeto da biblioteca xstram-1.4.10.jar
			xstream.aliasType("empresa", Empresa.class);//seta o nome da classe no xml
			String xml = xstream.toXML(empresas);
			
			response.setContentType("application/xml");
			response.getWriter().print(xml); //devolve uma string e imprime na saida o JSON
		} else if(valor.contains("json")) {
			// JSON
			Gson gson = new Gson(); //objeto da biblioteca gson-2.8.5.jar
			String json = gson.toJson(empresas);
			
			response.setContentType("application/json");
			response.getWriter().print(json); //devolve uma string e imprime na saida o JSON
		}
		else {
			response.setContentType("application/json");
			response.getWriter().print("{'message':'no content'}");
		}
	}
}


