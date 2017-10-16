
package tikape.runko.domain;

public class AnnosRaakaAine {
    
    private Integer jarjestys;
    private Integer maara;
    private String ohje;
    private Integer raaka_aine_id;
    private Integer annos_id;
    
    
    public AnnosRaakaAine() {
        
    }
    public AnnosRaakaAine(Integer jarjestys, Integer maara, String ohje, Integer raaka_aine_id, Integer annos_id) {
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
    public Integer getMaara() {
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

    public void setMaara(Integer maara) {
        this.maara = maara;
    }

    public void setOhje(String ohje) {
        this.ohje = ohje;
    }

    
    
    
    
    
}
