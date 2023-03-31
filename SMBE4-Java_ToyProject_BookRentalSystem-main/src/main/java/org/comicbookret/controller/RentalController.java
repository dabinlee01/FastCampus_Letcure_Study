package org.comicbookret.controller;

import org.comicbookret.dto.RentalDto;

import java.util.List;

/**
 * 대여 Controller interface
 */
public interface RentalController {
    List<RentalDto> findAll();

    RentalDto findById(int id);

    void create(RentalDto parameter);

    void update(RentalDto parameter);

    void delete(int id);
}
