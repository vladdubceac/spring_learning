package md.vladdubceac.hibernate_learning;

import md.vladdubceac.hibernate_learning.model.Actor;
import md.vladdubceac.hibernate_learning.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManyToManyMapping {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Movie.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try(sessionFactory){
            Session session = sessionFactory.getCurrentSession();
             session.beginTransaction();

            Movie movie = new Movie("Pulp fiction",1994);
            Actor actor1 = new Actor("Harvey Keitel",81);
            Actor actor2 = new Actor("Samuel L. Jackson",72);

//            addMovieAndActors(session, movie, actor1, actor2);

//            addMovieForExistingActor(session, new Movie("Reservoir Dogs",1992), 1);

            deleteMoviesForActor(session, 2, 0);

            session.getTransaction().commit();
        }
    }

    private static void deleteMoviesForActor(Session session, int actorId, int movieIndex) {
        Actor actor = session.get(Actor.class, actorId);
        System.out.println(actor);

        Movie movieToRemove = actor.getMovies().get(movieIndex);

        actor.getMovies().remove(movieIndex);
        movieToRemove.getActors().remove(actor);
    }

    private static void addMovieForExistingActor(Session session, Movie movie, int actorId) {
        Actor actor = session.get(Actor.class, actorId);
        movie.setActors(new ArrayList<>(Collections.singletonList(actor)));
        actor.getMovies().add(movie);

        session.save(movie);
    }

    private static void addMovieAndActors(Session session, Movie movie, Actor actor1, Actor actor2) {
        movie.setActors(new ArrayList<>(List.of(actor1, actor2)));

        actor1.setMovies(new ArrayList<>(Collections.singletonList(movie)));
        actor2.setMovies(new ArrayList<>(Collections.singletonList(movie)));

        session.save(movie);
        session.save(actor1);
        session.save(actor2);
    }
}
