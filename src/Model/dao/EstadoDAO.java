/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.dao;

import JDBC.Conexao;
import Model.bean.Estado;
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
public class EstadoDAO {
    
    private Connection conecta;
    
    //contrutor
    public EstadoDAO(){
        this.conecta = new Conexao().Conectar();
    }
    
    //create
    
    public void create(Estado obj){
       try {
           //criar string do comando sql
           String sql = "INSERT INTO estados (est_nome, est_uf) VALUES (?, ?)";
           
           //definir parametros que serão passados na execução do comando
           PreparedStatement stmt = conecta.prepareStatement(sql);
           stmt.setString(1, obj.getNome());
           stmt.setString(2, obj.getUf());
           
           //executar o comando
           stmt.execute();
           
           //encerrar a conexao
           stmt.close();
           
       } catch (SQLException e) {
            throw new RuntimeException(e);
       }

    }
    
    public List<Estado> read(){
        
        try {
            List<Estado> lista = new ArrayList<Estado>();
            
            String sql = "SELECT * FROM estados ORDER BY est_nome ASC";
            PreparedStatement stmt = conecta.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Estado e = new Estado();
                e.setId(rs.getInt("est_id"));
                e.setNome(rs.getString("est_nome"));
                e.setUf(rs.getString("est_uf"));
                
                lista.add(e);
            }
            
            return lista;
        
        } catch (Exception e) {
            throw new RuntimeException(e);
        
        }
    
    }
    public List<Estado> search(String termo){
        
        try {
            List<Estado> lista = new ArrayList<Estado>();
            
            String sql = "SELECT * FROM estados WHERE est_nome LIKE '%"+termo+"%' ORDER BY est_nome ASC";
            PreparedStatement stmt = conecta.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Estado e = new Estado();
                e.setId(rs.getInt("est_id"));
                e.setNome(rs.getString("est_nome"));
                e.setUf(rs.getString("est_uf"));
                
                lista.add(e);
            }
            
            return lista;
        
        } catch (Exception e) {
            throw new RuntimeException(e);
        
        }
    
    }
    
    public void update(Estado obj){
       try {
           //criar string do comando sql
           String sql = "UPDATE estados SET est_nome = ? , est_uf = ? WHERE est_id = ?";
           
           //definir parametros que serão passados na execução do comando
           PreparedStatement stmt = conecta.prepareStatement(sql);
           stmt.setString(1, obj.getNome());
           stmt.setString(2, obj.getUf());
           stmt.setInt(3, obj.getId());
           
           //executar o comando
           stmt.execute();
           
           //encerrar a conexao
           stmt.close();
           
       } catch (SQLException e) {
            throw new RuntimeException(e);
       }

    }
    
    public void delete(Estado obj){
       try {
           //criar string do comando sql
           String sql = "DELETE FROM estados WHERE est_id = ?";
           
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
