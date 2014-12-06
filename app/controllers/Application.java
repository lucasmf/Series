package controllers;

import models.Episodio;
import models.Serie;
import models.Temporada;
import models.dao.GenericDAO;
import play.*;
import play.db.jpa.Transactional;
import play.mvc.*;

import views.html.*;

import java.util.ArrayList;
import java.util.List;

public class Application extends Controller {
    static GenericDAO dao = new GenericDAO();

    @Transactional
    public static Result index() {
        List<Serie> series = dao.findAllByClass(Serie.class);
        for(Serie serie:series){
            for(Temporada temporada:serie.getTemporadas()) {
                temporada.reverseEpisodios();
            }
        }
        return ok(index.render(series));
    }

    @Transactional
    public static Result assistirSerie(Long id) {
        Serie serie = dao.findByEntityId(Serie.class, id);
        serie.setAssistida(1);
        dao.merge(serie);
        dao.flush();
        return redirect(routes.Application.index());
    }
    @Transactional
    public static Result assistirEpisodio(Long id) {
        Episodio episodio = dao.findByEntityId(Episodio.class, id);
        episodio.setAssistido(2);
        Episodio next = episodio.getNext();
        if(next != null) {
            if(next.getAssistido() != 2) {
                next.setAssistido(1);
                dao.merge(next);
            }
        }
        dao.merge(episodio);
        dao.flush();
        Temporada temporada = episodio.getTemporada();
        temporada.addEpisodioAssistido();
        dao.merge(temporada);
        dao.flush();
        return redirect(routes.Application.index());
    }
}
