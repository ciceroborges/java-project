/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Cícero
 */
public class Conexao {

    public Connection Conectar() {

        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/g2?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
       
        } catch (SQLException e) {
            throw new RuntimeException(e);
        
        }

    }

}
