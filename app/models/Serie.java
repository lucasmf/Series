package models;

import javax.persistence.*;
import java.util.*;

/**
 * Created by lucas on 12/4/14.
 */
@Entity(name = "Serie")
public class Serie {

    @Id
    @SequenceGenerator(name = "SERIE_SEQUENCE", sequenceName = "SERIE_SEQUENCE", allocationSize = 1, initialValue = 0)
    @GeneratedValue()
    private long id;

    @Column
    private String nome;
    @JoinColumn(name="TEMPORADA")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Temporada> temporadas;

    @Column
    int assistida;

    public Serie() {
    }

    public Serie(String nome) {
        this.nome = nome;
        temporadas = new ArrayList<Temporada>();
        this.assistida = 0;
    }
    public List<Temporada> getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(List<Temporada> temporadas) {
        this.temporadas = temporadas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAssistida() {
        return assistida;
    }

    public void setAssistida(int assistida) {
        this.assistida = assistida;
    }

    public void adicionaTemporada(Temporada temporada) {temporadas.add(temporada);}
}
