package br.com.anhanguera.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.anhanguera.DataObject.Produto;
import br.com.anhanguera.servlet.CadastroUsuarioServlet;
import com.google.gson.Gson;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Principal {
	
	public static List<Produto> listaProdutos = new ArrayList<Produto>();
	
	public static long idsProduto = 0;

	public static void main(String[] args) throws Exception {
		
		Server servidor = new Server(8080);
		
		ServletContextHandler servletHandler = 
				new ServletContextHandler(ServletContextHandler.SESSIONS);
		servletHandler.setContextPath("/");
		servletHandler.setBaseResource(Resource.newResource(
				Principal.class.getClassLoader().getResource("html/").toURI()));
		servletHandler.setWelcomeFiles(new String[]{"index.html"});
		
		ServletHolder holderPadrao = new ServletHolder("padrao", 
				DefaultServlet.class);
		holderPadrao.setInitParameter("dirAllowed", "true");
		
		servletHandler.addServlet(holderPadrao, "/");
		servletHandler.addServlet(
				new ServletHolder(new CadastroUsuarioServlet()),
				"/produto");
		
		servidor.setHandler(servletHandler);
		
		servidor.start();
		servidor.join();

	}
}
