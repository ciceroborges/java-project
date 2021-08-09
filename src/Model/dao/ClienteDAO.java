/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.dao;

import JDBC.Conexao;
import Model.bean.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Cícero
 */
public class ClienteDAO {
    
    private Connection conecta;
//construtor

    public ClienteDAO() {
        this.conecta = new Conexao().Conectar();
    }
    
    public void create(Cliente obj){
       try {
           //criar string do comando sql
           String sql = "INSERT INTO clientes (cli_nome, cli_data_nasc, cli_endereco, cli_celular, cli_cidade) VALUES (?, ?, ?, ?, ?)";
           
           //definir parametros que serão passados na execução do comando
           PreparedStatement stmt = conecta.prepareStatement(sql);
           stmt.setString(1, obj.getNome());
           stmt.setString(2, obj.getNascimento());
           stmt.setString(3, obj.getEndereco());
           stmt.setString(4, obj.getCelular());
           stmt.setInt(5, obj.getCidade());
           
           //executar o comando
           stmt.execute();
           
           //encerrar a conexao
           stmt.close();
           
       } catch (SQLException e) {
            throw new RuntimeException(e);
       }

    }
    
    public List<Cliente> read(){
        
        try {
            List<Cliente> lista = new ArrayList<Cliente>();
            
            String sql = "SELECT * FROM vw_clientes ORDER BY cli_nome ASC";
            PreparedStatement stmt = conecta.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Cliente e = new Cliente();            
              
                e.setId(rs.getInt("cli_id"));
                e.setNome(rs.getString("cli_nome"));
                e.setNascimento(rs.getString("cli_data_nasc"));
                e.setCelular(rs.getString("cli_celular"));
                e.setEndereco(rs.getString("cli_endereco"));
                e.setCidadeNome(rs.getString("cid_nome"));
                e.setUf(rs.getString("est_uf"));
                
                lista.add(e);
            }
            
            return lista;
        
        } catch (Exception e) {
            throw new RuntimeException(e);
        
        }
    
    }
    public List<Cliente> search(String termo){
        
        try {
            List<Cliente> lista = new ArrayList<Cliente>();
            
            String sql = "SELECT * FROM vw_clientes WHERE cli_nome LIKE '%"+termo+"%' ORDER BY cli_nome ASC";
            PreparedStatement stmt = conecta.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Cliente e = new Cliente();
                e.setId(rs.getInt("cli_id"));
                e.setNome(rs.getString("cli_nome"));
                e.setNascimento(rs.getString("cli_data_nasc"));
                e.setCelular(rs.getString("cli_celular"));
                e.setEndereco(rs.getString("cli_endereco"));
                e.setCidadeNome(rs.getString("cid_nome"));
                e.setUf(rs.getString("est_uf"));
                
                lista.add(e);
            }
            
            return lista;
        
        } catch (Exception e) {
            throw new RuntimeException(e);
        
        }
    
    }
    
    public void update(Cliente obj){
       try {
           //criar string do comando sql
           String sql = "UPDATE clientes SET cli_nome = ? , cli_data_nasc = ?, cli_endereco = ?, cli_celular = ?, cli_cidade = ?  WHERE cli_id = ?";
           
           //definir parametros que serão passados na execução do comando
           PreparedStatement stmt = conecta.prepareStatement(sql);
           
           stmt.setString(1, obj.getNome());
           stmt.setString(2, obj.getNascimento());
           stmt.setString(3, obj.getEndereco());
           stmt.setString(4, obj.getCelular());
           stmt.setInt(5, obj.getCidade());
           stmt.setInt(6, obj.getId());
           
           //executar o comando
           stmt.execute();
           
           //encerrar a conexao
           stmt.close();
           
       } catch (SQLException e) {
            throw new RuntimeException(e);
       }

    }
    
    public void delete(Cliente obj){
       try {
           //criar string do comando sql
           String sql = "DELETE FROM clientes WHERE cli_id = ?";
           
           //definir parametros que serão passados na execução do comando
           PreparedStatement stmt = conecta.prepareStatement(sql);
           stmt.setInt(1, obj.getId());
           
           //executar o comando
           stmt.execute();
           
           //encerrar a conexao
           stmt.close();
           
       } catch (SQLException e) {
            throw new RuntimeException(e);
       }

    }
}
