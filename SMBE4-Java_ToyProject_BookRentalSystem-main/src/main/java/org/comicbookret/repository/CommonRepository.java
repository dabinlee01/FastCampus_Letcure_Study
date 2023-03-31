package org.comicbookret.repository;

import java.util.List;

/**
 * Model Common Repository interface
 * @param <T>
 */
public interface CommonRepository<T> {
    /**
     * 전체 데이터 검색
     * @return
     */
    List<T> findAll();

    /**
     * ID를 조건으로 검색
     * @param id
     * @return
     */
    T findById(int id)  throws Exception;

    /**
     * 데이터 생성
     * @param parameter
     */
    void create(T parameter);

    /**
     * 데이터 수정
     * @param parameter
     */
    void update(T parameter) throws Exception;

    /**
     * 데이터 삭제
     * @param id
     */
    void delete(int id) throws Exception;
}
