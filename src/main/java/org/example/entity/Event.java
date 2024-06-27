package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Events")
@Data
@NoArgsConstructor
public class Event {

  @Id
  @GeneratedValue
  private Long id;

  private String title;

  @Column(name = "eventDate")
  private LocalDateTime date;

  public Event(String title, LocalDateTime date) {
    this.title = title;
    this.date = date;
  }
}
