package models;

import models.dao.GenericDAO;
import play.Play;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lucas on 12/4/14.
 */

public class AuxGlobal {
    public static void read() {
        String csvFile = Play.application().getFile("/app/models/File.csv").getAbsolutePath();
        BufferedReader reader = null;
        String line = "";
        String splitBy = ",";
        List<String[]> lines = new LinkedList<String[]>();
        try {
            reader = new BufferedReader(new FileReader(csvFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            populateDatabase(reader, splitBy, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void populateDatabase(BufferedReader reader, String splitBy, List<String[]> lines) throws IOException {
        GenericDAO dao = new GenericDAO();
        String line = reader.readLine();
        String[] args = line.split(splitBy);
        String tvShowName = args[0];
        int seasonNumber = Integer.parseInt(args[1]);
        int episodeNumber = Integer.parseInt(args[2]);
        String episodeName = args[3];
        Serie show = new Serie(tvShowName);
        Temporada season = new Temporada(show, seasonNumber);
        Episodio episode = new Episodio(season, episodeName,episodeNumber);
        season.adicionaEpisodio(episode);
        show.adicionaTemporada(season);
        while ((line = reader.readLine()) != null) {
            args = line.split(splitBy);
            tvShowName = args[0];
            seasonNumber = Integer.parseInt(args[1]);
            episodeNumber = Integer.parseInt(args[2]);
            if (args.length > 3) {
                episodeName = args[3];
            } else {
                episodeName = "";
            }
            if(tvShowName.equals(show.getNome())) {
                if(seasonNumber == season.getNumero()) {
                    episode.setNext(new Episodio(season, episodeName,episodeNumber));
                    episode = episode.getNext();
                    season.adicionaEpisodio(episode);
                } else {
                    season.reverseEpisodios();
                    season = new Temporada(show, seasonNumber);
                    episode = new Episodio(season, episodeName,episodeNumber);
                    season.adicionaEpisodio(episode);
                    show.adicionaTemporada(season);
                }
            } else {
                dao.persist(show);
                dao.flush();
                show = new Serie(tvShowName);
                season = new Temporada(show, seasonNumber);
                episode = new Episodio(season, episodeName,episodeNumber);
                season.adicionaEpisodio(episode);
                show.adicionaTemporada(season);
            }
        }
    }
}
