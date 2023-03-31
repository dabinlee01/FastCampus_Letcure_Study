package org.comicbookret.common;

/**
 * Collection객체 Table name enum
 */
public enum TableName {
    COMICBOOK("ComicBook"),
    CUSTOMER("Customer"),
    RENTAL("Rental");

    private final String name;
    private TableName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
}
