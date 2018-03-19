package br.com.anhanguera.DAO;

import br.com.anhanguera.DataObject.Produto;
import br.com.anhanguera.fabrica.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutoDAO {

    private Connection con;

    public ProdutoDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

        public boolean adicionaProduto (Produto produto) {
        String sql = "insert into loja.tb_produto " +
                "(nome,descricao,quantidade, valor)" +
                " values (?,?,?,?)";

        try {
            // prepared statement para inserção
            PreparedStatement ps = con.prepareStatement(sql);

            // seta os valores
            ps.setString(1,produto.getNome());
            ps.setString(2,produto.getDescricao());
            ps.setInt(3,produto.getQuantidade());
            ps.setBigDecimal(4, produto.getValor());


            // executa
            return ps.execute();
        } catch (Exception e){
            throw  new RuntimeException(e);
        }finally {
            try {
                con.close();
            }catch (Exception e){
                throw new RuntimeException();
            }

        }
    }

    public Produto consultaById(long codProduto){

        ResultSet rs = null;
        String sql = "SELECT  * FROM  loja.tb_produto WHERE  cod_produto = ?";

        try {
            // prepared statement para inserção
            PreparedStatement ps = con.prepareStatement(sql);

            // seta os valores
            ps.setLong(1, codProduto);

            // executa
            rs = ps.executeQuery();
            Produto produto = new Produto();
            while (rs.next()){
                produto.setCodProduto(rs.getLong("cod_produto"));
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setValor(rs.getBigDecimal("valor"));
                produto.setQuantidade(rs.getInt("quantidade"));
            }
            return produto;
        } catch (Exception e){
            throw  new RuntimeException(e);
        }finally {
            try {
                con.close();
            }catch (Exception e){
                throw new RuntimeException();
            }

        }

    }

    public ArrayList<Produto> listaProdutos(){

        ResultSet rs = null;
        String sql = "SELECT  * FROM  loja.tb_produto";

        try {
            // prepared statement para inserção
            PreparedStatement ps = con.prepareStatement(sql);
            // executa
            rs = ps.executeQuery();
           ArrayList<Produto> listaProduto = new ArrayList<>();
            while (rs.next()){
                Produto produto = new Produto();
                produto.setCodProduto(rs.getLong("cod_produto"));
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setValor(rs.getBigDecimal("valor"));
                produto.setQuantidade(rs.getInt("quantidade"));
                listaProduto.add(produto);
            }
            return listaProduto;
        } catch (Exception e){
            throw  new RuntimeException(e);
        }finally {
            try {
                con.close();
            }catch (Exception e){
                throw new RuntimeException();
            }

        }

    }

    public boolean atualizarProduto(Produto produto){

        ResultSet rs = null;
        String sql = "UPDATE loja.tb_produto SET nome= ?, descricao = ?, quantidade = ?, valor = ? WHERE  cod_produto = ?";

        try {
            // prepared statement para inserção
            PreparedStatement ps = con.prepareStatement(sql);

            // seta os valores
            ps.setString(1,produto.getNome());
            ps.setString(2,produto.getDescricao());
            ps.setInt(3,produto.getQuantidade());
            ps.setBigDecimal(4, produto.getValor());
            ps.setLong(5, produto.getCodProduto());

            // executa
            return ps.execute();
        } catch (Exception e){
            throw  new RuntimeException(e);
        } finally {
            try {
                con.close();
            }catch (Exception e){
                throw new RuntimeException();
            }

        }

    }


    public boolean removerProduto(long codProduto){

        ResultSet rs = null;
        String sql = "DELETE FROM  loja.tb_produto WHERE cod_produto = ?";

        try {
            // prepared statement para inserção
            PreparedStatement ps = con.prepareStatement(sql);

            // seta os valores
            ps.setLong(1, codProduto);

            // executa
             return ps.execute();
        } catch (Exception e){
            throw  new RuntimeException(e);
        }finally {
            try {
                con.close();
            }catch (Exception e){
                throw new RuntimeException();
            }

        }

    }

}
