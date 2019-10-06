package tech.artos.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "task", schema = "to_do_list")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "information")
    private String information;

    @Column(name = "status")
    private boolean status;

    public Task() {
    }

    public Task(String title, String information, LocalDateTime date, boolean status) {
        this.title = title;
        this.information = information;
        this.date = date;
        this.status = status;
    }

    public Task(Long id, String title, String information, LocalDateTime date, boolean status) {
        this.id = id;
        this.title = title;
        this.information = information;
        this.date = date;
        this.status = status;
    }
}
