package org.itsci.model.exam;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

@Entity
@Table(name = "subjects")
public class Subject implements Comparable<Subject> {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;
    @Column(length = 50, nullable = false, unique = true)
    private String name;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn (name = "subject_id")
    @OrderBy("name")
    private SortedSet<Chapter> chapters = new TreeSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return id == subject.id && name.equals(subject.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public int compareTo(Subject o) {
        return name.compareTo(o.getName());
    }
}
