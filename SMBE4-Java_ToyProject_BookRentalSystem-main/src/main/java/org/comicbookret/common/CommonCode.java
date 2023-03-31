package org.comicbookret.common;

/**
 * 기존 방법
 *
 * class CommonCode {
 *     public static final int REPOSITORY_COMIC_BOOK = 1;
 *     public static final int REPOSITORY_RENTAL = 2;
 *     public static final int REPOSITORY_CUSTOMER = 3;
 * }
 *
 * 최근 방법
 *
 * enum 사용
 */

public enum CommonCode {
    // field
    REPOSITORY_COMIC_BOOK(1), REPOSITORY_RENTAL(2), REPOSITORY_CUSTOMER(3);
    private final int typeNum;


    // constructor
    CommonCode(int typeNum) {this.typeNum = typeNum;}


    // method
    public int value() {return typeNum;}
}
