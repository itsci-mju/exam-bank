package org.itsci.exam.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.SortedSet;
import java.util.TreeSet;

@Entity
@Table(name = "subjects")
public class Subject {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;
    @Column(length = 50, nullable = false, unique = true)
    private String name;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn (name = "subject_id")
    @OrderBy("name")
    private SortedSet<Chapter> chapters = new TreeSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SortedSet<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(SortedSet<Chapter> chapters) {
        this.chapters = chapters;
    }
}
