package org.comicbookret.dto;

import lombok.*;

/**
 * 고객 DTO 클래스
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper=false)
public class CustomerDto extends CommonDto{
    // 고객명
    private String customerName;

    public CustomerDto(int id, String customerName){
        this.setId(id);
        this.customerName = customerName;
    }

    public void printInfo(){
        System.out.println(
                this.getId() + "\t\t\t" +
                this.customerName
        );
    }
}
