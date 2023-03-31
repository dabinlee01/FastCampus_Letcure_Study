package org.comicbookret.common;


public enum DataCode {
    // field
    COMIC_BOOK_DATA("ComicBookDto"), RENTAL_DATA("RentalDto"), CUSTOMER_DATA("CustomerDto");
    private final String value;


    // constructor
    DataCode(String value) {this.value = value;}


    // method
    public String value() {return this.value;}
}
