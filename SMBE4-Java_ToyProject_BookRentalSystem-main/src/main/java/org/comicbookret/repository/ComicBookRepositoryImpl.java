package org.comicbookret.repository;

import org.comicbookret.common.DataNotFoundException;
import org.comicbookret.common.TableName;
import org.comicbookret.dto.CollectionDB;
import org.comicbookret.dto.ComicBookDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Model 만화책 Repository interface 구현 클래스
 */
public class ComicBookRepositoryImpl implements CommonRepository<ComicBookDto>{
    // log 생성
    private final static Logger log = LoggerFactory.getLogger(ComicBookRepositoryImpl.class);

    private CollectionDB db = CollectionDB.getInstance();

    @Override
    public List<ComicBookDto> findAll() {
        return db.getTable(TableName.COMICBOOK);
    }

    @Override
    public ComicBookDto findById(int id) {
        return (ComicBookDto)db.findById(
                TableName.COMICBOOK, id);
    }

    @Override
    public void create(ComicBookDto parameter){
        try {
            db.<ComicBookDto>create(parameter, TableName.COMICBOOK);
        }catch(Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void update(ComicBookDto parameter) throws Exception {
        ComicBookDto result = (ComicBookDto)db.update(
                TableName.COMICBOOK, parameter.getId());
        if(result != null) {
            result.setTitle(parameter.getTitle());
            result.setAristName(parameter.getAristName());
        }else{
            throw new DataNotFoundException("데이터를 찾지 못했습니다 - ID : " + parameter.getId());
        }
    }

    @Override
    public void delete(int id) throws Exception {
        if(!db.delete(TableName.COMICBOOK, id)){
            throw new DataNotFoundException("데이터를 찾지 못했습니다 - ID : " + id);
        }
    }
}
