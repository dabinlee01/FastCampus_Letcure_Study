package org.comicbookret.repository;

import org.comicbookret.common.DataNotFoundException;
import org.comicbookret.common.TableName;
import org.comicbookret.dto.CollectionDB;
import org.comicbookret.dto.CommonDto;
import org.comicbookret.dto.CustomerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Model 고객 Repository interface 구현 클래스
 */
public class CustomerRepositoryImpl implements CommonRepository<CustomerDto>{

    // log 생성
    private final static Logger log = LoggerFactory.getLogger(CustomerRepositoryImpl.class);
    private CollectionDB db = CollectionDB.getInstance();

    @Override
    public List<CustomerDto> findAll() {
        return db.getTable(TableName.CUSTOMER);
    }

    @Override
    public CustomerDto findById(int id) throws Exception {
        CommonDto dto = db.findById(TableName.CUSTOMER, id);
        if(dto == null) {
            throw new DataNotFoundException("데이터를 찾지 못했습니다 - ID : " + id);
        }
        return (CustomerDto)dto;
    }

    @Override
    public void create(CustomerDto parameter){
        try {
            db.<CustomerDto>create(parameter, TableName.CUSTOMER);
        }catch(Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void update(CustomerDto parameter) throws Exception {
        CustomerDto result = (CustomerDto)db.update(
                TableName.CUSTOMER, parameter.getId());
        if(result != null) {
            result.setCustomerName(parameter.getCustomerName());
        }else{
            throw new DataNotFoundException("데이터를 찾지 못했습니다 - ID : " + parameter.getId());
        }
    }

    @Override
    public void delete(int id) throws Exception {
        if(!db.delete(TableName.CUSTOMER, id)){
            throw new DataNotFoundException("데이터를 찾지 못했습니다 - ID : " + id);
        }
    }
}
