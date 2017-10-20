/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.runko.domain;

import java.sql.SQLException;
import tikape.runko.database.AnnosDao;
import tikape.runko.database.AnnosRaakaAineDao;
import tikape.runko.database.RaakaAineDao;

/**
 *
 * @author stobe
 */
public class RaakaAineHtml {
    private Integer jarjestys;
    private String maara;
    private String ohje;
    private String nimi2;
    private String annos;
    private String nimi;

    public RaakaAineHtml(AnnosRaakaAine ara, RaakaAineDao dao, AnnosDao dao2) throws SQLException {
        this.jarjestys = 0;
        this.maara = ara.getMaara();
        this.ohje = ara.getOhje();
        this.nimi2 = dao.findOne(ara.getRaaka_aine_id()).getNimi();
        this.annos = dao2.findOne(ara.getAnnos_id()).getNimi();
        this.nimi = nimi2 + " " + maara + " " + ohje;
    }

    public Integer getJarjestys() {
        return jarjestys;
    }

    public void setJarjestys(Integer jarjestys) {
        this.jarjestys = jarjestys;
    }

    public String getMaara() {
        return maara;
    }

    public void setMaara(String maara) {
        this.maara = maara;
    }

    public String getOhje() {
        return ohje;
    }

    public void setOhje(String ohje) {
        this.ohje = ohje;
    }

    public String getNimi2() {
        return nimi2;
    }

    public void setNimi2(String nimi2) {
        this.nimi2 = nimi2;
    }

    public String getAnnos() {
        return annos;
    }

    public void setAnnos(String annos) {
        this.annos = annos;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
}