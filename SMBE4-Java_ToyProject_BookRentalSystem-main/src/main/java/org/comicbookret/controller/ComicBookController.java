package org.comicbookret.controller;

import org.comicbookret.dto.ComicBookDto;

import java.util.List;

/**
 * 만화책 Controller interface
 */
public interface ComicBookController {
    List<ComicBookDto> findAll();

    ComicBookDto findById(int id);

    void create(ComicBookDto parameter);

    void update(ComicBookDto parameter);

    void delete(int id);
}
