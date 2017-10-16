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
import tikape.runko.domain.RaakaAine;

/**
 *
 * @author stobe
 */
public class RaakaAineDao implements Dao<RaakaAine, Integer> {
    private Database database;

    public RaakaAineDao(Database database) {
        this.database = database;
    }

    public List<RaakaAine> listaaKaikki() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM RaakaAine");

        ResultSet rs = stmt.executeQuery();
        List<RaakaAine> raineet = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String nimi = rs.getString("nimi");
            raineet.add(new RaakaAine(id, nimi));
        }

        rs.close();
        stmt.close();
        connection.close();

        return raineet;
    }
    
    public void uusi(String nimi) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO RaakaAine(nimi) VALUES(?)");
        stmt.setString(1, nimi);
        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }
    
    //SQL-injektiovaara?
    public void poista(String nimi) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM RaakaAine WHERE = '" + nimi + "'");
        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }
}
