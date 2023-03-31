package org.comicbookret;

import org.comicbookret.controller.ComicBookController;
import org.comicbookret.controller.CustomerController;
import org.comicbookret.controller.RentalController;
import org.comicbookret.dto.ComicBookDto;
import org.comicbookret.dto.CustomerDto;
import org.comicbookret.dto.RentalDto;
import org.comicbookret.factory.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * 만화책 대여 애플리케이션(토이 프로젝트)
 */

public class ComicBookRentalApp {

    // log 생성
    private final static Logger log = LoggerFactory.getLogger(ComicBookRentalApp.class);
    private Scanner input = new Scanner(System.in);

    private String lines = "***************";

    public static void main(String[] args) {
        ComicBookRentalApp app = new ComicBookRentalApp();
        app.mainMenu();
    }

    private void mainMenu(){
        String bSel = "";
        while(!bSel.equals("0")) {
            System.out.println("<메뉴 선택>");
            System.out.println("1.만화책 조회/등록/수정/삭제");
            System.out.println("2.만화책 대여/반납");
            System.out.println("3.고객 조회/등록/수정/삭제");
            System.out.println("0.프로그램 종료");
            bSel = input.next();

            switch (bSel) {
                case "1":
                    System.out.println("1.만화책 조회/등록/수정/삭제 메뉴");
                    comicBookMenu();
                    break;
                case "2":
                    System.out.println("2.만화책 대여 메뉴");
                    rentalMenu();
                    break;
                case "3":
                    System.out.println("4.고객 조회/등록/수정/삭제 메뉴");
                    customerMenu();
                    break;
                case "0":
                    System.out.println("@@@@@ 프로그램이 종료되었습니다 @@@@@");
                    break;
                default:
                    System.out.println("메뉴 입력이 잘 못 되었습니다!\n재입력 해주세요!");
            }
        }
    }

    /**
     * 만화책 메뉴
     */
    private void comicBookMenu() {
        ComicBookController controller = Factory.getInstance().getComicBookController();

        String bSel = "";
        do {
            System.out.println("##### 만화책 메뉴 #####");
            System.out.println("1.만화책 등록");
            System.out.println("2.만화책 수정");
            System.out.println("3.만화책 삭제");
            System.out.println("4.만화책 조회");
            System.out.println("5.만화책 전체 조회");
            System.out.println("0.이전 메뉴로 이동");
            bSel = input.next();

            int comicBookId = 0;
            String title = null;
            String aristName = null;

            switch (bSel) {
                case "1":
                    System.out.println(lines);
                    System.out.println("등록할 만화책 정보를 입력해주세요.");
                    System.out.print("만화책명:");
                    title = input.next();
                    System.out.print("작가명:");
                    aristName = input.next();
                    System.out.println(lines);
                    controller.create(new ComicBookDto(0,title, aristName));
                    break;
                case "2":
                    System.out.println(lines);
                    System.out.println("수정할 만화책 번호와 수정 정보를 입력해주세요.");
                    System.out.print("만화책 ID:");
                    comicBookId = input.nextInt();
                    System.out.print("만화책명:");
                    title = input.next();
                    System.out.print("작가명:");
                    aristName = input.next();
                    System.out.println(lines);
                    controller.update(new ComicBookDto(comicBookId, title, aristName));
                    break;
                case "3":
                    System.out.println(lines);
                    System.out.println("삭제할 만화책 번호를 입력해주세요.");
                    System.out.print("만화책 ID:");
                    comicBookId = input.nextInt();
                    System.out.println(lines);
                    controller.delete(comicBookId);
                    break;
                case "4":
                    System.out.println(lines);
                    System.out.println("조회할 만화책 번호를 입력해주세요.");
                    System.out.print("만화책 ID:");
                    comicBookId = input.nextInt();
                    System.out.println(lines);
                    ComicBookDto dto = controller.findById(comicBookId);
                    if(dto != null) {
                        System.out.println("만화책ID\t\t만화책명\t\t\t\t작가");
                        System.out.println("-----------------------------------------");
                        dto.printInfo();
                    } else {
                        System.out.println("만화책 정보가 없습니다!");
                    }
                    break;
                case "5":
                    List<ComicBookDto> resultList = controller.findAll();
                    if(resultList.size() > 0) {
                        System.out.println("만화책ID\t\t만화책명\t\t\t\t작가");
                        System.out.println("-----------------------------------------");
                        resultList.stream().forEach(c -> c.printInfo());
                    } else {
                        System.out.println("만화책 정보가 없습니다!");
                    }
                    break;
                case "0":
                    break;
                default:
                    System.out.println("메뉴 입력이 잘 못 되었습니다!\n재입력 해주세요!");
            }

        } while (!bSel.equals("0"));
    }

    /**
     * 고객 메뉴
     */
    private void customerMenu() {
        CustomerController controller = Factory.getInstance().getCustomerController();

        String bSel = "";
        do {
            System.out.println("##### 고객 메뉴 #####");
            System.out.println("1.고객 등록");
            System.out.println("2.고객 수정");
            System.out.println("3.고객 삭제");
            System.out.println("4.고객 조회");
            System.out.println("5.고객 전체 조회");
            System.out.println("0.이전 메뉴로 이동");
            bSel = input.next();

            int id = 0;
            String customerName = null;

            switch (bSel) {
                case "1":
                    System.out.println(lines);
                    System.out.println("등록할 고객 정보를 입력해주세요.");
                    System.out.print("고객명:");
                    customerName = input.next();
                    System.out.println(lines);
                    controller.create(new CustomerDto(0,customerName));
                    break;
                case "2":
                    System.out.println(lines);
                    System.out.println("수정할 고객 번호와 수정 정보를 입력해주세요.");
                    System.out.print("고객 ID:");
                    id = input.nextInt();
                    System.out.print("고객명:");
                    customerName = input.next();
                    System.out.println(lines);
                    controller.update(new CustomerDto(id,customerName));
                    break;
                case "3":
                    System.out.println(lines);
                    System.out.println("삭제할 고객 번호를 입력해주세요.");
                    System.out.print("고객 ID:");
                    id = input.nextInt();
                    System.out.println(lines);
                    controller.delete(id);
                    break;
                case "4":
                    System.out.println(lines);
                    System.out.println("조회할 고객ID를 입력해주세요.");
                    System.out.print("고객 ID:");
                    id = input.nextInt();
                    System.out.println(lines);
                    System.out.println(lines);
                    CustomerDto dto = controller.findById(id);
                    if(dto != null) {
                        System.out.println("고객ID\t\t고객명");
                        System.out.println("-----------------------------------------");
                        dto.printInfo();
                    } else {
                        System.out.println("고객 정보가 없습니다!");
                    }
                    break;
                case "5":
                    List<CustomerDto> resultList = controller.findAll();
                    if(resultList.size() > 0) {
                        System.out.println("고객ID\t\t고객명");
                        System.out.println("-----------------------------------------");
                        resultList.stream().forEach(c -> c.printInfo());
                    } else {
                        System.out.println("고객 정보가 없습니다!");
                    }
                    break;
                case "0":
                    break;
                default:
                    System.out.println("메뉴 입력이 잘 못 되었습니다!\n재입력 해주세요!");
            }

        } while (!bSel.equals("0"));
    }

    /**
     * 대여/반납 메뉴
     */
    private void rentalMenu() {
        RentalController controller = Factory.getInstance().getRentalController();
        CustomerController custController = Factory.getInstance().getCustomerController();

        String bSel = "";

        do {
            System.out.println("##### 대여/반납 메뉴 #####");
            System.out.println("1.대여 조회");
            System.out.println("2.만화책 대여");
            System.out.println("3.만화책 반납");
            System.out.println("0.이전 메뉴로 이동");
            bSel = input.next();

            int id = 0;
            int customerId = 0;

            switch (bSel) {
                case "1":
                    List<RentalDto> resultList = controller.findAll();
                    if(resultList.size() > 0) {
                        System.out.println("대여ID\t\t대여일\t\t대여만화책ID\t대여만화책명\t\t대여고객ID\t대여고객명");
                        System.out.println("--------------------------------------------------------------------------------");
                        resultList.stream().forEach(c -> c.printInfo());
                    } else {
                        System.out.println("대여 정보가 없습니다!");
                    }
                    break;
                case "2":
                    System.out.println(lines);
                    System.out.println("대여할 만화책 번호를 입력해주세요.");
                    System.out.print("만화책 번호:");
                    id = input.nextInt();
                    System.out.print("고객ID:");
                    customerId = input.nextInt();
                    System.out.println(lines);

                    // 해당 고객정보가 있는지 먼저 확인
                    CustomerDto dto = custController.findById(customerId);
                    if(dto == null) {
                        System.out.println("고객 정보가 없습니다!\n고객등록을 먼저 해주세요");
                        bSel = "0";
                    } else {
                        // 고객정보가 있는 경우 대여건이 있는지 확인하여 있으면 반납처리후 삭제
                        controller.create(new RentalDto(0, LocalDate.now(), id, customerId));
                    }
                    break;
                case "3":
                    System.out.println(lines);
                    System.out.println("반납할 대여 번호를 입력해주세요.");
                    System.out.print("대여 번호:");
                    id = input.nextInt();
                    System.out.println(lines);
                    System.out.println(lines);
                    controller.delete(id);
                    break;
                case "0":
                    break;
                default:
                    System.out.println("메뉴 입력이 잘 못 되었습니다!\n재입력 해주세요!");
            }

        } while (!bSel.equals("0"));
    }

    /**
     * 종료하기
     */
    private void exitApp() {
        System.out.println("### 프로그램이 종료되었습니다 ###");
    }

}