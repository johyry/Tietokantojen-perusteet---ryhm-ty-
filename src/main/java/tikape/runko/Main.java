package tikape.runko;

import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import static spark.Spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.AnnosDao;
import tikape.runko.database.Database;
import tikape.runko.database.RaakaAineDao;
import tikape.runko.domain.RaakaAine;

public class Main {

    public static void main(String[] args) throws Exception {
        Database database = new Database("jdbc:sqlite:reseptit.db");
        database.init();

        RaakaAineDao raakaainedao = new RaakaAineDao(database);
        AnnosDao annosdao = new AnnosDao(database);
        
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

            return new ModelAndView(map, "annosmain");
        }, new ThymeleafTemplateEngine());
        
        
        // RAAKA-AINE -MAIN SIVUN KOODI
        
         get("/raakaAineMain", (req, res) -> {
             
             
            HashMap map = new HashMap<>();
            // mappi raaka-aineista
            map.put("raakaaineet", raakaainedao.listaaKaikki());

            return new ModelAndView(map, "raaka-aine -main");
        }, new ThymeleafTemplateEngine());
         
         
         
        
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
