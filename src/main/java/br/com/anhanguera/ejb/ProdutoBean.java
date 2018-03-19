package br.com.anhanguera.ejb;

import br.com.anhanguera.DAO.ProdutoDAO;
import br.com.anhanguera.DataObject.Produto;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ProdutoBean {


    public ArrayList <Produto> buscarProduto(){
        ProdutoDAO dao = new ProdutoDAO();
        return dao.listaProdutos();
    }

    public Produto buscarByCodProduto(Long codProduto){
        ProdutoDAO dao = new ProdutoDAO();
        return dao.consultaById(codProduto);
    }


    public boolean removerProduto(Long codProduto){
        ProdutoDAO dao = new ProdutoDAO();
        return dao.removerProduto(codProduto);
    }

    public boolean atualizarProduto(Produto produto){
        ProdutoDAO dao = new ProdutoDAO();
        return dao.atualizarProduto(produto);
    }

    public boolean adicionarProduto(Produto produto){
        ProdutoDAO dao = new ProdutoDAO();
        return dao.adicionaProduto(produto);
    }

}
