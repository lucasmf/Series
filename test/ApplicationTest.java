import java.util.*;

import com.fasterxml.jackson.databind.JsonNode;
import models.Episodio;
import models.Serie;
import models.Temporada;
import models.dao.GenericDAO;
import org.junit.*;

import play.db.jpa.JPA;
import play.db.jpa.JPAPlugin;
import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;
import scala.*;

import javax.persistence.EntityManager;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {

    private GenericDAO dao = new GenericDAO();
    public EntityManager em;

    @Before
    public void setUp() {
        FakeApplication app = Helpers.fakeApplication();
        Helpers.start(app);
        scala.Option<JPAPlugin> jpaPlugin = app.getWrappedApplication().plugin(JPAPlugin.class);
        em = jpaPlugin.get().em("default");
        JPA.bindForCurrentThread(em);
        em.getTransaction().begin();
    }

    @After
    public void tearDown() {
        em.getTransaction().commit();
        JPA.bindForCurrentThread(null);
        em.close();
    }

    @Test
    public void seriesDevemComecarOrdenado() {
        List<Serie> series = dao.findAllByClass(Serie.class);
        assertThat(series.get(0).getNome()).isEqualTo("South Park");
    }

    @Test
    public void temporadasDevemComcarOrdenadas() {
        List<Serie> series = dao.findAllByClass(Serie.class);
        List<Temporada> temporadas = series.get(0).getTemporadas();
        assertThat(temporadas.get(0).getNumero()).isEqualTo(1);
        assertThat(temporadas.get(1).getNumero()).isEqualTo(2);
    }

    @Test
    public void episodiosDevemComecarOrdenados() {
        List<Serie> series = dao.findAllByClass(Serie.class);
        List<Episodio> episodios = series.get(0).getTemporadas().get(0).getEpisodios();
        assertThat(episodios.get(0).getNome()).isEqualTo("Cartman's Mom is a Dirty Slut");
        assertThat(episodios.get(1).getNome()).isEqualTo("Mecha-Streisand");
    }

    @Test
    public void simpleCheck() {
        int a = 1 + 1;
        assertThat(a).isEqualTo(2);
    }



    /*@Test
    public void renderTemplate() {
        Content html = views.html.index.render("Your new application is ready.");
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("Your new application is ready.");
    }*/



}
