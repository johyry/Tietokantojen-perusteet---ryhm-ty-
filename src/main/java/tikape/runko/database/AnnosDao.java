package tikape.runko.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.Annos;


public class AnnosDao implements Dao<Annos, Integer>{
    
    private Database database;

    public AnnosDao(Database database) {
        this.database = database;
    }
    
//    metodin joka palauttaa Annos-olion, raaka-aine -oliot ja niiden määrän
    public Annos findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Annos WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        String nimi = rs.getString("nimi");

        Annos o = new Annos(id, nimi);

        rs.close();
        stmt.close();
        connection.close();

        return o;
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
    
    public void poistaId(int id) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM Annos WHERE id = '" + id + "'");
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
