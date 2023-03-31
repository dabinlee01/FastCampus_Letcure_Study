package org.comicbookret.common;

/**
 * 자료를 찾지 못했을 때 예외를 throw한다
 */
public class DataNotFoundException extends Exception{
    public DataNotFoundException(String message){
        super(message);
    }
}
