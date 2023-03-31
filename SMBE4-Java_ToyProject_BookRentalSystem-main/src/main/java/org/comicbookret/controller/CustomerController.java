package org.comicbookret.controller;

import org.comicbookret.dto.CustomerDto;

import java.util.List;

/**
 * 고객 Controller interface
 */
public interface CustomerController {

    /**
     * 고객 전체 불러오기
     * @return
     */
    List<CustomerDto> findAll();

    /**
     * 고객 ID로 고객 dto 찾기
     * @param id 고객 ID
     * @return
     */
    CustomerDto findById(int id);

    /**
     * 고객 정보 저장
     * @param dto 고객 정보
     */
    void create(CustomerDto dto);

    /**
     * 고객 정보 수정
     * @param dto 고객 정보
     */
    void update(CustomerDto dto);

    /**
     * 고객 정보 삭제
     * @param id 고객 ID
     */
    void delete(int id);

}
