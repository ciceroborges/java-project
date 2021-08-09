/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.dao;

import JDBC.Conexao;
import Model.bean.Cidade;
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
public class CidadeDAO {

    private Connection conecta;
//construtor

    public CidadeDAO() {
        this.conecta = new Conexao().Conectar();
    }
    
    public void create(Cidade obj){
       try {
           //criar string do comando sql
           String sql = "INSERT INTO cidades (cid_nome, cid_estado) VALUES (?, ?)";
           
           //definir parametros que serão passados na execução do comando
           PreparedStatement stmt = conecta.prepareStatement(sql);
           stmt.setString(1, obj.getNome());
           stmt.setInt(2, obj.getEstado());
           
           //executar o comando
           stmt.execute();
           
           //encerrar a conexao
           stmt.close();
           
       } catch (SQLException e) {
            throw new RuntimeException(e);
       }

    }
    
    public List<Cidade> read(){
        
        try {
            List<Cidade> lista = new ArrayList<Cidade>();
            
            String sql = "SELECT * FROM vw_cidades ORDER BY cid_nome ASC";
            PreparedStatement stmt = conecta.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Cidade e = new Cidade();
                e.setId(rs.getInt("cid_id"));
                e.setNome(rs.getString("cid_nome"));
                e.setEstadoNome(rs.getString("est_nome"));
                
                lista.add(e);
            }
            
            return lista;
        
        } catch (Exception e) {
            throw new RuntimeException(e);
        
        }
    
    }
    public List<Cidade> search(String termo){
        
        try {
            List<Cidade> lista = new ArrayList<Cidade>();
            
            String sql = "SELECT * FROM vw_cidades WHERE cid_nome LIKE '%"+termo+"%' ORDER BY cid_nome ASC";
            PreparedStatement stmt = conecta.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Cidade e = new Cidade();
                e.setId(rs.getInt("cid_id"));
                e.setNome(rs.getString("cid_nome"));
                e.setEstadoNome(rs.getString("est_nome"));
                
                lista.add(e);
            }
            
            return lista;
        
        } catch (Exception e) {
            throw new RuntimeException(e);
        
        }
    
    }
    
    public void update(Cidade obj){
       try {
           //criar string do comando sql
           String sql = "UPDATE cidades SET cid_nome = ? , cid_estado = ? WHERE cid_id = ?";
           
           //definir parametros que serão passados na execução do comando
           PreparedStatement stmt = conecta.prepareStatement(sql);
           stmt.setString(1, obj.getNome());
           stmt.setInt(2, obj.getEstado());
           stmt.setInt(3, obj.getId());
           
           //executar o comando
           stmt.execute();
           
           //encerrar a conexao
           stmt.close();
           
       } catch (SQLException e) {
            throw new RuntimeException(e);
       }

    }
    
    public void delete(Cidade obj){
       try {
           //criar string do comando sql
           String sql = "DELETE FROM cidades WHERE cid_id = ?";
           
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
