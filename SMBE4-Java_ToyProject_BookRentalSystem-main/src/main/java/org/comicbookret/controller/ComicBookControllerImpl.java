package org.comicbookret.controller;

import org.comicbookret.common.RepositoryType;
import org.comicbookret.dto.ComicBookDto;
import org.comicbookret.factory.Factory;
import org.comicbookret.repository.CommonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 만화책 Controller interface 구현 클래스
 */
public class ComicBookControllerImpl implements ComicBookController{

    // log 생성
    private final static Logger log = LoggerFactory.getLogger(ComicBookControllerImpl.class);
    private CommonRepository repository = null;

    public ComicBookControllerImpl(){
        repository = Factory.getInstance().
                getRepository(RepositoryType.COMICBOOK);
    }

    @Override
    public List<ComicBookDto> findAll() {
        return repository.findAll();
    }

    @Override
    public ComicBookDto findById(int id) {
        ComicBookDto dto = null;
        try {
            dto = (ComicBookDto)repository.findById(id);
        }catch(Exception e) {
            log.error(e.getMessage());
        }
        return dto;
    }

    @Override
    public void create(ComicBookDto parameter) {
        repository.create(parameter);
        System.out.println("만화책 정보 등록이 완료되었습니다!");
    }

    @Override
    public void update(ComicBookDto parameter)  {
        try {
            repository.update(parameter);
            System.out.println("만화책 정보가 수정 되었습니다!");
        }catch(Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try {
            repository.delete(id);
            System.out.println("만화책 정보가 정상적으로 삭제 되었습니다!");
        }catch(Exception e) {
            log.error(e.getMessage());
        }
    }
}
