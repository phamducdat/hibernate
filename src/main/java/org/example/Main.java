package org.example;

import org.example.entity.Event;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import static java.lang.System.out;
import static java.time.LocalDateTime.now;

public class Main {
  private static SessionFactory sessionFactory;

  public static void main(String[] args) {
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
        .build();

    try {
      sessionFactory =
          new MetadataSources(registry)
              .addAnnotatedClass(Event.class)
              .buildMetadata()
              .buildSessionFactory();

      // create a couple of events...
      sessionFactory.inTransaction(session -> {
        session.persist(new Event("datpd", now()));
        session.persist(new Event("test", now()));
      });

      // now lets pull events from the database and list them
      sessionFactory.inTransaction(session -> {
        session.createSelectionQuery("from Event", Event.class).getResultList()
            .forEach(event -> out.println("Event (" + event.getDate() + ") : " + event.getTitle()));
      });
    } catch (Exception e) {
      e.printStackTrace();
      StandardServiceRegistryBuilder.destroy(registry);
    }

  }
}