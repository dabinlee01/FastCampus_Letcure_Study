package org.comicbookret.controller;

import org.comicbookret.common.RepositoryType;
import org.comicbookret.dto.CustomerDto;
import org.comicbookret.factory.Factory;
import org.comicbookret.repository.CommonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 고객 Controller interface 구현 클래스
 */
public class CustomerControllerImpl implements CustomerController{

    // log 생성
    private final static Logger log = LoggerFactory.getLogger(CustomerControllerImpl.class);
    private CommonRepository repository = null;

    public CustomerControllerImpl(){
        repository = Factory.getInstance().
                getRepository(RepositoryType.CUSTOMER);
    }

    @Override
    public List<CustomerDto> findAll() {
        return repository.findAll();
    }

    @Override
    public CustomerDto findById(int id) {
        CustomerDto dto = null;
        try {
            dto = (CustomerDto)repository.findById(id);
        }catch(Exception e) {
            log.error(e.getMessage());
        }
        return dto;
    }

    @Override
    public void create(CustomerDto parameter) {
        repository.create(parameter);
        System.out.println("고객 정보 등록이 완료되었습니다!");
    }

    @Override
    public void update(CustomerDto parameter) {
        try {
            repository.update(parameter);
            System.out.println("고객 정보가 수정 되었습니다!");
        }catch(Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void delete(int id){
        try {
            repository.delete(id);
            System.out.println("고객정보가 정상적으로 삭제 되었습니다!");
        }catch(Exception e) {
            log.error(e.getMessage());
        }
    }
}
