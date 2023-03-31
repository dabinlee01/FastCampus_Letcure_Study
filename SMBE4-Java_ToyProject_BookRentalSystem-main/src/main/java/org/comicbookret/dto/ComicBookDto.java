package org.comicbookret.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 만화책 DTO 클래스
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper=false)
public class ComicBookDto extends CommonDto {
    // 만화책명
    private String title;
    // 작가명
    private String aristName;

    public ComicBookDto(int id, String title, String aristName){
        this.setId(id);
        this.title = title;
        this.aristName = aristName;
    }
    public void printInfo(){
        System.out.println(
            new StringBuilder()
                    .append(this.getId()).append("\t\t\t")
                    .append(this.title).append("\t\t\t")
                    .append(this.aristName).toString()
        );
    }
}
