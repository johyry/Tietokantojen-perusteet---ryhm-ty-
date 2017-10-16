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
import tikape.runko.domain.AnnosRaakaAine;
import tikape.runko.domain.RaakaAine;

/**
 *
 * @author stobe
 */
public class AnnosRaakaAineDao implements Dao<AnnosRaakaAine, Integer> {
    private Database database;

    public AnnosRaakaAineDao(Database database) {
        this.database = database;
    }

    
    public List<AnnosRaakaAine> listaaKaikki() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM RaakaAine");

        ResultSet rs = stmt.executeQuery();
        List<AnnosRaakaAine> raineet = new ArrayList<>();
        while (rs.next()) {
            Integer jarjestys = rs.getInt("jarjestys");
            String maara = rs.getString("maara");
            String ohje = rs.getString("ohje");
            Integer raaka_aine_id = rs.getInt("raaka_aine_id");
            Integer annos_id = rs.getInt("annos_id");
            raineet.add(new AnnosRaakaAine(jarjestys, maara, ohje, raaka_aine_id, annos_id));
        }

        rs.close();
        stmt.close();
        connection.close();

        return raineet;
    }


}
