package main;

import static spark.Spark.*;
import static spark.SparkBase.port;
import controllers.HomeController;
import controllers.PerfilUsuarioController;
import controllers.RecetasController;
import spark.template.handlebars.HandlebarsTemplateEngine;


public class Routes {

  public static void main(String[] args) {
    System.out.println("Iniciando servidor");

    HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
    HomeController home = new HomeController();
    RecetasController recetas = new RecetasController();
    PerfilUsuarioController perfilUsuario = new PerfilUsuarioController(); 

    port(8080);

    staticFileLocation("/public");

    get("/", home::mostrar, engine);
    get("/index.html", (request, response) -> {
      response.redirect("/");
      return null;
    });
    
    get("/recetas", recetas::mostrar, engine);
    post("/recetas", recetas::accederAlDetalle);
    get("/recetas/receta/:id",recetas::mostrarDetalle,engine);
    post("/recetas/receta",recetas::modificarReceta);
    post("/recetas/receta/",recetas::modificarReceta);
    get("/perfil",perfilUsuario::mostrar,engine);
    post("/perfil",perfilUsuario::modificarPerfil);
  }

}
