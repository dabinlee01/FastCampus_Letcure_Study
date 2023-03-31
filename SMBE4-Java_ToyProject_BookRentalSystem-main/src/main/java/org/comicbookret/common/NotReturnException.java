package org.comicbookret.common;

/**
 * 대여 만화책을 반납하지 않았을때 예외를 throw한다
 */
public class NotReturnException extends Exception{
    public NotReturnException(String message){
        super(message);
    }
}
