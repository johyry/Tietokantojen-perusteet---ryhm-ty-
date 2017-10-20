package tikape.runko.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private String databaseAddress;

    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }

    public void init() {
        List<String> lauseet = sqliteLauseet();

        // "try with resources" sulkee resurssin automaattisesti lopuksi
        try (Connection conn = getConnection()) {
            Statement st = conn.createStatement();

            // suoritetaan komennot
            for (String lause : lauseet) {
                System.out.println("Running command >> " + lause);
                st.executeUpdate(lause);
            }

        } catch (Throwable t) {
            // jos tietokantataulu on jo olemassa, ei komentoja suoriteta
            System.out.println("Error >> " + t.getMessage());
        }
    }

    private List<String> sqliteLauseet() {
        ArrayList<String> lista = new ArrayList<>();

        // TESTIDATA
        lista.add("CREATE TABLE Annos (id integer PRIMARY KEY, nimi varchar(255));");
        lista.add("INSERT INTO Annos (nimi) VALUES ('voileipä');"); //1
        lista.add("INSERT INTO Annos (nimi) VALUES ('hernerokka');"); //2
        lista.add("INSERT INTO Annos (nimi) VALUES ('vesikeitto');"); //3
        lista.add("INSERT INTO Annos (nimi) VALUES ('kaurapuuro');"); //4

        lista.add("CREATE TABLE RaakaAine (id integer PRIMARY KEY, nimi varchar(255));");
        lista.add("INSERT INTO RaakaAine (nimi) VALUES ('vesi');"); //1
        lista.add("INSERT INTO RaakaAine (nimi) VALUES ('voi');");//2
        lista.add("INSERT INTO RaakaAine (nimi) VALUES ('kaurahiutaleita');");//3
        lista.add("INSERT INTO RaakaAine (nimi) VALUES ('leipä');");//4
        lista.add("INSERT INTO RaakaAine (nimi) VALUES ('hernekeitto');");//5

        lista.add("CREATE TABLE AnnosRaakaAine "
                + "(raaka_aine_id integer,"
                + " annos_id integer,"
                + " jarjestys integer,"
                + " maara varchar(100),"
                + " ohje varchar(300),"
                + " FOREIGN KEY (raaka_aine_id) REFERENCES RaakaAine(id),"
                + " FOREIGN KEY (annos_id) REFERENCES Annos(id));");

        lista.add("INSERT INTO AnnosRaakaAine (raaka_aine_id, annos_id, jarjestys, maara, ohje)"
                + "VALUES (1, 3, 1, 'sopivasti', 'keitä vesi lämpimäksi')");

        return lista;
    }
}
