package org.itsci.dao;

import org.itsci.exam.model.Chapter;

import java.util.List;

public interface ChapterDao {

    List<Chapter> getChapters();

    void saveChapter(Chapter obj);

    Chapter getChapter(Long id);

    void deleteChapter(Long id);
}
