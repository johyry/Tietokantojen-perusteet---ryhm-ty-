/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.runko.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.Annos;


/**
 *
 * @author stobe
 */
public class AnnosDao implements Dao<Annos, Integer>{
    
    private Database database;

    public AnnosDao(Database database) {
        this.database = database;
    }
    
    public List<Annos> listaaKaikki() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Annos");

        ResultSet rs = stmt.executeQuery();
        List<Annos> annokset = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String nimi = rs.getString("nimi");
            annokset.add(new Annos(id, nimi));
        }

        rs.close();
        stmt.close();
        connection.close();

        return annokset;
    }
    
    public void uusi(Annos annos) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Annos(nimi) VALUES(?)");
        stmt.setString(1, annos.getNimi());
        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }
    
    //SQL-injektiovaara?
    public void poista(String nimi) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM Annos WHERE nimi = '" + nimi + "'");
        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }
    
    public int getId(String nimi) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Annos WHERE nimi = '" + nimi + "'");
        ResultSet rs = stmt.executeQuery();
        int palaute = rs.getInt("id");
        stmt.close();
        rs.close();
        connection.close();
        return palaute;
    }
}
