package org.comicbookret.repository;

import org.comicbookret.common.DataNotFoundException;
import org.comicbookret.common.NotReturnException;
import org.comicbookret.common.TableName;
import org.comicbookret.dto.CollectionDB;
import org.comicbookret.dto.CommonDto;
import org.comicbookret.dto.RentalDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Model 대여 Repository interface 구현 클래스
 */
public class RentalRepositoryImpl implements CommonRepository<RentalDto>{
    // log 생성
    private final static Logger log = LoggerFactory.getLogger(RentalRepositoryImpl.class);
    private CollectionDB db = CollectionDB.getInstance();

    @Override
    public List<RentalDto> findAll() {
        return db.getTable(TableName.RENTAL);
    }

    @Override
    public RentalDto findById(int id) throws Exception {
        CommonDto dto = db.findById(TableName.RENTAL, id);
        if(dto == null) {
            throw new DataNotFoundException("데이터를 찾지 못했습니다 - ID : " + id);
        }
        return (RentalDto)dto;
    }

    @Override
    public void create(RentalDto parameter){
        try {
            findByRentalCustomerId(parameter.getCustomerId());
            db.<RentalDto>create(parameter, TableName.RENTAL);
        }catch(Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    private void findByRentalCustomerId(int customerId){
        boolean isRental = false;
        List<RentalDto> resultList = db.getTable(TableName.RENTAL);
        // 고객ID로 대여한 건이 있는지 조회
        RentalDto dto = resultList.stream()
                .filter(r -> r.getCustomerId() == customerId)
                .findFirst() // filter조건에 맞는 첫번째 요소를 리턴
                .orElse(null); // 값이 없을 경우 null을 리턴
        // 대여정보가 있으면 삭제한다
        if(dto != null){
            try{
                throw new NotReturnException("반납하지 않은 만확책이 있습니다. 강제로 반납처리 합니다. 대여 만화책-"
                        + dto.getComicBookId());
            }catch(NotReturnException ne){
                log.error(ne.getMessage());
            }
            try {
                this.delete(dto.getId());
            }catch(Exception e){
                log.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(RentalDto parameter) throws Exception {
        RentalDto result = (RentalDto)db.update(
                TableName.RENTAL, parameter.getId());
        if(result != null) {
            result.setRentalDate(parameter.getRentalDate());
            result.setCustomerId(parameter.getCustomerId());
            result.setComicBookId(parameter.getComicBookId());
        }else{
            throw new DataNotFoundException("데이터를 찾지 못했습니다 - ID : " + parameter.getId());
        }
    }

    @Override
    public void delete(int id) throws Exception {
        if(!db.delete(TableName.RENTAL, id)){
            throw new DataNotFoundException("데이터를 찾지 못했습니다 - ID : " + id);
        }
    }
}
