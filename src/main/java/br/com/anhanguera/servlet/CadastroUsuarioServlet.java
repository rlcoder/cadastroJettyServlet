package br.com.anhanguera.servlet;

import br.com.anhanguera.DataObject.Produto;
import br.com.anhanguera.ejb.ProdutoBean;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class CadastroUsuarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException,
            IOException {
        String parametro = req.getParameter("codProduto");
        Long codProduto  = (parametro != null && !parametro.isEmpty())? Long.valueOf(parametro): 0;
        ProdutoBean produto = new ProdutoBean();
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);
        if (codProduto == 0){
            resp.getWriter().println(new Gson().toJson(produto.buscarProduto()));
        }else{
            resp.getWriter().println(new Gson().toJson(produto.buscarByCodProduto(codProduto)));
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parametro = req.getParameter("codProduto");
        Long codProduto  = (parametro != null && !parametro.isEmpty())? Long.valueOf(parametro): 0;
        ProdutoBean produto = new ProdutoBean();
        Boolean isRemovido =  produto.removerProduto(codProduto);
        if (isRemovido){
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println("Pessoa Removida");
        }else{
            resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
            resp.getWriter().println("Codigo Invalido");
        }
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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

        Produto objJsonProduto = new Gson().fromJson(jsonBuffer.toString(), Produto.class);
        ProdutoBean produto = new ProdutoBean();
        boolean isAtualizado = produto.atualizarProduto(objJsonProduto);
        if (isAtualizado){
            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_CREATED);
        }else{
            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
        }
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

        Produto objJsonProduto = new Gson().fromJson(jsonBuffer.toString(), Produto.class);
        ProdutoBean produto = new ProdutoBean();
        boolean isAdicionado = produto.atualizarProduto(objJsonProduto);
        if (isAdicionado){
            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_CREATED);
        }else{
            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
        }

    }
}
