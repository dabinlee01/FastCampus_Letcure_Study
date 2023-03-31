package org.comicbookret.factory;

import org.comicbookret.common.RepositoryType;
import org.comicbookret.controller.*;
import org.comicbookret.repository.ComicBookRepositoryImpl;
import org.comicbookret.repository.CommonRepository;
import org.comicbookret.repository.CustomerRepositoryImpl;
import org.comicbookret.repository.RentalRepositoryImpl;

/**
 * 인스턴스 Factory Singleton class
 */
public class Factory {

    private static Factory instance;

    /**
     * Singleton 인스턴스 얻기
     * @return
     */
    public static final Factory getInstance() {
        if( instance == null )
            instance = new Factory();
        return instance;
    }

    private Factory(){}


    /**
     * 만화책 컨트롤러 객체 얻기
     * @return
     */
    public final ComicBookController getComicBookController(){
        return new ComicBookControllerImpl();
    }
    /**
     * 고객 컨트롤러 객체 얻기
     * @return
     */
    public final CustomerController getCustomerController(){ return new CustomerControllerImpl(); }
    /**
     * 대여 컨트롤러 객체 얻기
     * @return
     */
    public final RentalController getRentalController() {
        return new RentalControllerImpl();
    }

    /**
     * Repository 구현 인스턴스 리턴
     * @param type Repository type
     * @return
     */
    public final CommonRepository<?> getRepository(RepositoryType type) {
        CommonRepository repository = null;

        if(type == RepositoryType.COMICBOOK) repository = new ComicBookRepositoryImpl();
        if(type == RepositoryType.CUSTOMER) repository = new CustomerRepositoryImpl();
        if(type == RepositoryType.RENTAL) repository = new RentalRepositoryImpl();

        return repository;
    }
}
