package br.com.anhanguera.cadastro.usuario;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;

import com.google.gson.Gson;

public class Principal {
	
	public static List<Produto> produtos =
			new ArrayList<Produto>();
	
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
				"/usuarios");
		
		servidor.setHandler(servletHandler);
		
		servidor.start();
		servidor.join();

	}
	
	
	static class CadastroUsuarioServlet extends HttpServlet{
		
			@Override
			protected void doGet(HttpServletRequest req, 
					HttpServletResponse resp) 
							throws ServletException, 
							IOException {
				resp.setContentType("application/json");
				resp.setStatus(HttpServletResponse.SC_OK);
				
				resp.getWriter()
					.println(new Gson().toJson(produtos));
			}
			
			@Override
			protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// TODO IMPLEMENTAR A EXCLUSAO
				super.doDelete(req, resp);
				
			}
			
			
			@Override
			protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// TODO IMPLEMENTAR ATUALIZACDAO
				super.doPut(req, resp);
				
			}
			
			@Override
			protected void doPost(HttpServletRequest req, 
					HttpServletResponse resp) 
							throws ServletException, IOException {
			
				StringBuffer jsonBuffer = new StringBuffer();
				String linha = null;
				
				try{
					BufferedReader reader = req.getReader();
					while((linha = reader.readLine()) != null){
						jsonBuffer.append(linha);
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
				
				Produto usuario = new Gson()
						.fromJson(jsonBuffer.toString(), Produto.class);
				usuario.setId(idsProduto++);
				produtos.add(usuario);
				
				resp.setContentType("application/json");
				resp.setStatus(HttpServletResponse.SC_CREATED);
				
				resp.getWriter()
					.println(new Gson().toJson(usuario));
				
			}
		
	}
	
}
