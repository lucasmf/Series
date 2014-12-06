package models;

import javax.persistence.*;
import java.util.*;

/**
 * Created by lucas on 12/4/14.
 */

@Entity(name = "Temporada")
public class Temporada {
    @Id
    @SequenceGenerator(name = "TEMPORADA_SEQUENCE", sequenceName = "TEMPORADA_SEQUENCE", allocationSize = 1, initialValue = 0)
    @GeneratedValue
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Serie serie;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="EPISODIOS")
    private List<Episodio> episodios;

    @Column
    private int numero;

    public Temporada() {
    }

    public Temporada(Serie serie, int numero) {
        this.serie = serie;

        this.numero = numero;
        episodios = new ArrayList<Episodio>();
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Episodio> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(List<Episodio> episodios) {
        this.episodios = episodios;
    }

    public void addEpisodio(Episodio episodio) {
        episodios.add(episodio);
    }

    public void adicionaEpisodio(Episodio episodio) {episodios.add(episodio);}

    public void reverseEpisodios() {
        Collections.reverse(episodios);
    }

}
