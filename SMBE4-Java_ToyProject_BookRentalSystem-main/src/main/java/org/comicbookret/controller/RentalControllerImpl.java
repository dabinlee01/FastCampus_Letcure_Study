package org.comicbookret.controller;

import org.comicbookret.common.RepositoryType;
import org.comicbookret.dto.RentalDto;
import org.comicbookret.factory.Factory;
import org.comicbookret.repository.CommonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 대여 Controller interface 구현 클래스
 */
public class RentalControllerImpl implements RentalController{

    // log 생성
    private final static Logger log = LoggerFactory.getLogger(RentalControllerImpl.class);
    private CommonRepository repository = null;

    public RentalControllerImpl(){
        repository = Factory.getInstance().
                getRepository(RepositoryType.RENTAL);
    }

    @Override
    public List<RentalDto> findAll() {
        return repository.findAll();
    }

    @Override
    public RentalDto findById(int id) {
        RentalDto dto = null;
        try {
            dto = (RentalDto)repository.findById(id);
        }catch(Exception e) {
            log.error(e.getMessage());
        }
        return dto;
    }

    @Override
    public void create(RentalDto parameter) {
        repository.create(parameter);
        System.out.println("대여 정보 등록이 완료되었습니다!");
    }

    @Override
    public void update(RentalDto parameter)  {
        try {
            repository.update(parameter);
            System.out.println("대여 정보가 수정 되었습니다!");
        }catch(Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try {
            repository.delete(id);
            System.out.println("대여 정보가 정상적으로 삭제 되었습니다!");
        }catch(Exception e) {
            log.error(e.getMessage());
        }
    }
}
