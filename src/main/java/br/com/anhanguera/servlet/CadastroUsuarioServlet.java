package br.com.anhanguera.servlet;

import br.com.anhanguera.DataObject.Produto;
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
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);

 /*       resp.getWriter().println(new Gson().toJson());*/
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

        Produto produto = new Gson()
                .fromJson(jsonBuffer.toString(), Produto.class);
/*        produto.setValor(idsProduto++);
        listaProdutos.add(produto);*/

        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_CREATED);

        resp.getWriter()
                .println(new Gson().toJson(produto));

    }
}
