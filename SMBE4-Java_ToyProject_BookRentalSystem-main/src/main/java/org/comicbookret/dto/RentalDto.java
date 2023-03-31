package org.comicbookret.dto;

import lombok.*;
import org.comicbookret.controller.ComicBookController;
import org.comicbookret.controller.CustomerController;
import org.comicbookret.factory.Factory;

import java.time.LocalDate;

/**
 * 만화책 DTO 클래스
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper=false)
public class RentalDto extends CommonDto {
    // 대여일
    private LocalDate rentalDate;
    // 대여 만화책ID
    private int comicBookId;
    // 대여 고객ID
    private int customerId;

    public RentalDto(int id, LocalDate rentalDate, int comicBookId, int customerId ){
        this.setId(id);
        this.rentalDate = rentalDate;
        this.comicBookId = comicBookId;
        this.customerId = customerId;
    }

    public void printInfo(){
        // 대여한 만화책명과 고객명을 같이 노출시켜준다
        CustomerController custCtrl = Factory.getInstance().getCustomerController();
        ComicBookController comicCtrl = Factory.getInstance().getComicBookController();

        System.out.println(
            new StringBuilder()
                    .append(this.getId()).append("\t\t")
                    .append(this.rentalDate).append("\t\t")
                    .append(this.comicBookId).append("\t\t\t")
                    .append(comicCtrl.findById(this.comicBookId).getTitle()).append("\t\t\t")
                    .append(this.customerId).append("\t\t\t")
                    .append(custCtrl.findById(this.customerId).getCustomerName()).toString()
        );
    }
}
