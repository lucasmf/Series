package models;

import javax.persistence.*;

/**
 * Created by lucas on 12/4/14.
 */
@Entity(name = "Episodio")
public class Episodio {
    @Id
    @SequenceGenerator(name = "EPISODIO_SEQUENCE", sequenceName = "EPISODIO_SEQUENCE", allocationSize = 1, initialValue = 0)
    @GeneratedValue
    private long id;

    @Column
    private String nome;

    @Column
    private int numero;

    @ManyToOne(cascade = CascadeType.ALL)
    Temporada temporada;

    @Column
    int assistido;

    @OneToOne(cascade = CascadeType.ALL)
    Episodio next;

    public Episodio(){}

    public Episodio(Temporada temporada, String nome, int numero) {
        this.temporada = temporada;

        this.nome = nome;
        this.numero = numero;
    }

    public Temporada getTemporada() {
        return temporada;
    }

    public void setTemporada(Temporada temporada) {
        this.temporada = temporada;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getAssistido() {
        return assistido;
    }

    public void setAssistido(int assistido) {
        this.assistido = assistido;
    }

    public Episodio getNext() {
        return next;
    }

    public void setNext(Episodio next) {
        this.next = next;
    }
}
