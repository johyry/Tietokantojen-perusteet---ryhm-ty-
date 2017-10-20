package tikape.runko;

import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import spark.Spark;
import static spark.Spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.AnnosDao;
import tikape.runko.database.AnnosRaakaAineDao;
import tikape.runko.database.Database;
import tikape.runko.database.RaakaAineDao;
import tikape.runko.domain.Annos;
import tikape.runko.domain.AnnosRaakaAine;
import tikape.runko.domain.RaakaAine;

public class Main {

    public static void main(String[] args) throws Exception {
        Database database = new Database("jdbc:sqlite:reseptit.db");
        database.init();

        RaakaAineDao raakaainedao = new RaakaAineDao(database);
        AnnosDao annosdao = new AnnosDao(database);
        AnnosRaakaAineDao annosraakaainedao = new AnnosRaakaAineDao(database);

        int i = 0;

        List<RaakaAine> lista = raakaainedao.listaaKaikki();

        // PÄÄSIVUN KOODI ALLA
        get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("viesti", "random shit");

            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

        // ANNOS-MAIN SIVUN KOODI
        get("/annosMain", (req, res) -> {

            HashMap map = new HashMap<>();
            map.put("annokset", annosdao.listaaKaikki());
            map.put("rat", raakaainedao.listaaKaikki());

            return new ModelAndView(map, "annosmain");
        }, new ThymeleafTemplateEngine());

        // Annossivun lomakkeen käsittely
//        Spark.post("/annosMain", (req, res) -> {
//            String nimi = req.queryParams("nimi");
//            annosdao.uusi(new Annos(-1, nimi));
//            res.redirect("/annosMain");
//            return "";
//        });
        //Liitostaulun lomakkeen käsittely 
        Spark.post("/annosMain", (req, res) -> {
            if (req.queryParams("valikko").equals("1")) {
                String nimi = req.queryParams("nimi");
                annosdao.uusi(new Annos(-1, nimi));
                res.redirect("/annosMain");
                return "";
            } 
            if(req.queryParams("valikko").equals("2")) {
                System.out.println("2-valikossa");
                String maara = req.queryParams("maara");
                String ohje = req.queryParams("ohje");
                String annos = req.queryParams("annosValikko");
                System.out.println("blaa" + annos + "blaa");
                String raakaaine = "vesi";
                annosraakaainedao.uusi(new AnnosRaakaAine(-1, maara, ohje, annosdao.getId(annos), raakaainedao.getId(raakaaine)));
                res.redirect("/annosMain");
                return "";
            }
            return "";
        }
    );
        
        // ANNOKSEN OMA SIVU
        
        

        // RAAKA-AINE -MAIN SIVUN KOODI
    get( 
        "/raakaAineMain", (req, res) -> {

            HashMap map = new HashMap<>();
        map.put("raakaaineet", raakaainedao.listaaKaikki());

        return new ModelAndView(map, "raaka-aine -main");
    }
     ,

    new ThymeleafTemplateEngine()

    );

        //POISTOOnnistus
    get( 
        "/raakaaineet/:id/poista", (req, res) -> {

            int poistoId = Integer.parseInt(req.params("id"));
        raakaainedao.poistaId(poistoId);
        HashMap map = new HashMap<>();
        map.put("raakaaineet", raakaainedao.listaaKaikki());
        return new ModelAndView(map, "raaka-aine -main");
    }
     ,

    new ThymeleafTemplateEngine()

    );

        // Raaka-ainesivun lomakkeen käsittely
    Spark.post ( 
        "/raakaAineMain", (req, res) -> {
            String nimi = req.queryParams("nimi");
        raakaainedao.uusi(new RaakaAine(-1, nimi));
        res.redirect("/raakaAineMain");
        return "";
    }

);

//
//        get("/opiskelijat", (req, res) -> {
//            HashMap map = new HashMap<>();
//            map.put("opiskelijat", raakaainedao.listaaKaikki());
//
//            return new ModelAndView(map, "opiskelijat");
//        }, new ThymeleafTemplateEngine());
//        get("/opiskelijat/:id", (req, res) -> {
//            HashMap map = new HashMap<>();
//            map.put("opiskelija", raakaainedao.listaaKaikki(Integer.parseInt(req.params("id"))));
//
//            return new ModelAndView(map, "opiskelija");
//        }, new ThymeleafTemplateEngine());
    }
}
