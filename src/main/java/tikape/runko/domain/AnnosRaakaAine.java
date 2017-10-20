
package tikape.runko.domain;

import java.sql.SQLException;
import tikape.runko.database.AnnosDao;
import tikape.runko.database.RaakaAineDao;

public class AnnosRaakaAine {
    
    private Integer jarjestys;
    private String maara;
    private String ohje;
    private Integer raaka_aine_id;
    private Integer annos_id;
    
    public AnnosRaakaAine(Integer jarjestys, String maara, String ohje, int raaka_aine_id, int annos_id) throws SQLException {
        this.jarjestys = jarjestys;
        this.maara = maara;
        this.ohje = ohje;
        this.raaka_aine_id = raaka_aine_id;
        this.annos_id = annos_id;
    }
    

    public Integer getRaaka_aine_id() {
        return raaka_aine_id;
    }

    public Integer getAnnos_id() {
        return annos_id;
    }
    public String getMaara() {
        return maara;
    }

    public String getOhje() {
        return ohje;
    }

    public void setRaaka_aine_id(Integer raaka_aine_id) {
        this.raaka_aine_id = raaka_aine_id;
    }

    public void setAnnos_id(Integer annos_id) {
        this.annos_id = annos_id;
    }

    public void setMaara(String maara) {
        this.maara = maara;
    }

    public void setOhje(String ohje) {
        this.ohje = ohje;
    }

    
    
    
    
    
}
