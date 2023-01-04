package org.itsci.dao.exam;

import org.itsci.model.exam.Chapter;

import java.util.List;

public interface ChapterDao {

    List<Chapter> getChapters();

    void saveChapter(Chapter obj);

    Chapter getChapter(Long id);

    void deleteChapter(Long id);
}
