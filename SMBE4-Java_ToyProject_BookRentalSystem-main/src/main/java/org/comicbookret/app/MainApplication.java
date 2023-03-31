package org.comicbookret.app;




import org.comicbookret.controller.ComicBookControllerImpl;
import org.comicbookret.controller.CustomerControllerImpl;
import org.comicbookret.controller.RentalControllerImpl;
import org.comicbookret.dto.ComicBookDto;
import org.comicbookret.dto.CustomerDto;
import org.comicbookret.dto.RentalDto;
import org.comicbookret.factory.Factory;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class MainApplication {
    ComicBookControllerImpl comicBookController = Factory.getInstance().createComicBookController();
    RentalControllerImpl rentalController = Factory.getInstance().createRentalController();
    CustomerControllerImpl customerController = Factory.getInstance().createCustomerController();
    Scanner scanner = new Scanner(System.in);


    public void init() {
        comicBookController.save(ComicBookDto.builder().bookNo(1111).title("스프링의 정석").author("남궁성").build());
        comicBookController.save(ComicBookDto.builder().bookNo(2222).title("자바의 정석").author("남궁성").build());
        comicBookController.save(ComicBookDto.builder().bookNo(3333).title("깃허브의 정석").author("광개토대왕").build());
        customerController.save(CustomerDto.builder().customerNo(1).Name("송재근").build());
        customerController.save(CustomerDto.builder().customerNo(2).Name("전승준").build());
        customerController.save(CustomerDto.builder().customerNo(3).Name("김록진").build());
    }


    public void start() throws InterruptedException {
        while (true) {
            System.out.println("<메뉴 선택>" +
                    "\n1.만화책 조회/등록/수정/삭제" +
                    "\n2.만화책 대여" +
                    "\n3.만화책 반납" +
                    "\n4.고객 조회/등록/수정/삭제" +
                    "\n0.프로그램 종료" +
                    "\n=======================");
            System.out.print("메뉴입력 : ");
            int inputMenuType = validationInputType();


            switch (inputMenuType) {
                case 1://TODO : 만화책 조회/등록/수정/삭제 Map<String,List> 식으로 저장
                    menuOne();
                    break;
                case 2://TODO : 만화책 대여
                    menuTwo();
                    break;
                case 3://TODO : 만화책 반납
                    menuThree();
                    break;
                case 4://TODO :고객 조회/등록/수정/삭제
                    menuFour();
                    break;
                case 0:
                    System.out.println("프로그램을 종료합니다0.");
                    scanner.close();
                    System.exit(0);// 0 정상종료, 1 : 비정상종료
                default:
                    System.out.println(":::::::반드시 0~4의 값을 입력해주세요:::::::");
                    break;
            }
        }
    }


    private void menuOne() throws InterruptedException{//TODO : 만화책 조회/등록/수정/삭제 Map<String,List> 식으로 저장
        while(true) {
            System.out.println("<메뉴 선택>" +
                    "\n1.만화책 전체 조회" +
                    "\n2.만화책 조회" +
                    "\n3.만화책 등록" +
                    "\n4.만화책 수정" +
                    "\n5.만화책 삭제" +
                    "\n0.이전 메뉴" +
                    "\n=======================");
            System.out.print("메뉴입력 ");
            int inputMenuType = scanner.nextInt();


            switch (inputMenuType) {
                case 1:
                    List<ComicBookDto> list = comicBookController.findAll();
                    System.out.println("=================================");
                    for (ComicBookDto dto : list) {
                        System.out.println("일련번호 : " + dto.getBookNo());
                        System.out.println("제목 : " + dto.getTitle());
                        System.out.println("작가 : " + dto.getAuthor());
                    }
                    System.out.println("=================================");
                    break;
                case 2:
                    System.out.print("조회할 만화책 제목을 입력하세요. : ");
                    String hidden = scanner.nextLine();
                    String input = scanner.nextLine();
                    try {
                        System.out.println("=================================");
                        ComicBookDto result = comicBookController.findByTitle(input);
                        System.out.println("일련번호 : " + result.getBookNo());
                        System.out.println("제목 : " + result.getTitle());
                        System.out.println("작가 : " + result.getAuthor());
                        System.out.println("=================================");
                    } catch (Exception e) {
                        System.out.println("해당 만화책이 목록에 없습니다.");
                    }
                    break;
                case 3:
                    System.out.print("일련번호 :");
                    int num = scanner.nextInt();
                    String hidden1 = scanner.nextLine();
                    System.out.print("만화책 제목 : ");
                    String title = scanner.nextLine();
                    System.out.print("작가명 : ");
                    String author = scanner.nextLine();
                    ComicBookDto newComicBook = ComicBookDto.builder()
                            .bookNo(num).title(title).author(author).build();
                    String result = comicBookController.save(newComicBook);
                    System.out.println(result);
                    break;
                case 4:
                    System.out.print("수정할 일련번호를 입력하세요. : ");
                    int inputForUpdate = validationInputType();
                    List<ComicBookDto> forUpdateList = comicBookController.findAll();
                    int temp = 0;
                    for (ComicBookDto d : forUpdateList) {
                        if (d.getBookNo() == inputForUpdate) {
                            temp = inputForUpdate;
                        }
                    }
                    if (temp != 0) {
                        System.out.print("변경할 제목을 입력하세요. : ");
                        String hidden02 = scanner.nextLine();
                        String updateTitle = scanner.nextLine();
                        System.out.print("변경할 작가를 입력하세요. : ");
                        String updateAuthor = scanner.nextLine();
                        ComicBookDto updateComicBook = ComicBookDto.builder().title(updateTitle).author(updateAuthor).build();
                        try {
                            String resultUpdateComicBook = comicBookController.update(updateComicBook);
                            System.out.println(resultUpdateComicBook);
                        } catch (NullPointerException e) {
                            System.out.println("찾으려는 만화책은 존재하지 않습니다.");
                        }
                    } else {
                        System.out.println("해당 만화책은 목록에 없습니다.");
                    }
                    break;
                case 5:
                    System.out.print("삭제할 만화책 제목을 입력하세요");
                    String deleteTitle = scanner.nextLine();
                    try {
                        ComicBookDto findDto = comicBookController.findByTitle(deleteTitle);
                        comicBookController.delete(findDto);
                        System.out.println(deleteTitle + "이 삭제되었스빈다.");
                    } catch (Exception e) {
                        System.out.println("해당 제목이 목록에 없습니다.");
                    }
                    break;
                case 0:
                    System.out.println("이전 메뉴로 돌아갑니다.");
                    break;
            }
            if (inputMenuType == 0) break;
        }
    }


    private void menuTwo() throws InterruptedException{//TODO : 만화책 대여
        System.out.print("대여할 만화책 제목을 입력해주세요. : ");
        String searchTitle = scanner.nextLine();
        if (comicBookController.findByTitle(searchTitle) == null ) System.out.println("해당 만화책은 목록에 존재하지 않습니다.");
        else if (rentalController.findByTitle(searchTitle) != null) System.out.println("해당 만화책은 대여가 불가능 합니다.");
        else {
            RentalDto rental = RentalDto.builder().bookTitle(searchTitle).build();
            String resultForRental = rentalController.rental(rental);
            System.out.println(resultForRental);
        }
        start();
    }


    private void menuThree() throws InterruptedException{//TODO : 만화책 반납
        System.out.print("반납할 만화책 제목을 입력해주세요. : ");
        String returnComicBookTitle = scanner.nextLine();
        if (rentalController.findByTitle(returnComicBookTitle) == null) System.out.println("해당 만화책은 대여중이지 않습니다.");
        else {
            RentalDto rental = rentalController.findByTitle(returnComicBookTitle);
            String resultForToReturn = rentalController.toReturn(rental);
            System.out.println(resultForToReturn);
        }
        start();
    }


    private void menuFour() throws InterruptedException{//TODO :고객 조회/등록/수정/삭제
        while (true) {
            System.out.println("<메뉴 선택>" +
                    "\n1.고객 목록 조회" +
                    "\n2.고객 조회"+
                    "\n3.고객 등록"+
                    "\n4.고객 수정"+
                    "\n5.고객 삭제"+
                    "\n0:이전 메뉴"+
                    "\n=======================");
            System.out.print("메뉴입력 ");
            int inputMenuType = validationInputType();


            switch (inputMenuType) {
                //TODO:고객 전체 조회
                case 1:
                    List<CustomerDto> list = customerController.findAll();
                    System.out.println("===============================");
                    for (CustomerDto dto : list) {
                        System.out.println("일련번호 : "+dto.getCustomerNo());
                        System.out.println("이름 : "+dto.getName());
                    }
                    System.out.println("===============================");
                    break;
                case 2 :
                    //TODO:고객 조회
                    System.out.print("조회할 고객 이름을 입력해주세요.");
                    try {
                        CustomerDto customer = customerController.findById(scanner.nextInt());
                        System.out.println("고객 일련번호 : "+customer.getCustomerNo());
                        System.out.println("고객 이름 : "+customer.getName());
                    } catch (Exception e) {
                        System.out.println("해당 고객은 목록에 없습니다.");
                    }
                    break;
                case 3 :
                    //TODO:고객 등록
                    System.out.print("고객 일련번호 : ");
                    int newCustomerNo = scanner.nextInt();
                    System.out.print("고객 이름 : ");
                    String newCustomerName = scanner.nextLine();
                    CustomerDto newCustomer = CustomerDto.builder().customerNo(newCustomerNo).Name(newCustomerName).build();
                    String result = customerController.save(newCustomer);
                    System.out.println(result);
                    break;
                case 4:
                    //TODO:고객 수정
                    int inputForUpdate = validationInputType();
                    System.out.print("수정할 일련번호를 입력하세요. : "+inputForUpdate);
                    List<CustomerDto> forUpdateList = customerController.findAll();
                    int temp = 0;
                    for (CustomerDto d : forUpdateList) {
                        if (d.getCustomerNo() == inputForUpdate) {
                            temp = inputForUpdate;
                        }
                    }
                    if (temp != 0) {
                        System.out.print("변경할 이름를 입력하세요. : ");
                        String updateName = scanner.nextLine();
                        CustomerDto updatedCustomer = CustomerDto.builder().Name(updateName).build();
                        String resultForUpdate = customerController.update(updatedCustomer);
                        System.out.println(resultForUpdate);
                    } else {
                        System.out.println("해당 고객은 목록에 없습니다.");
                    }
                    break;
                case 5:
                    //TODO: 고객 삭제
                    System.out.print("삭제할 고객 일련번호를 입력해주세요. : ");
                    int input = validationInputType();
                    try {
                        CustomerDto customerDTO = customerController.findById(input);
                        String resultForDelete = customerController.delete(customerDTO);
                        System.out.println(resultForDelete);
                    } catch (Exception e) {
                        System.out.println("해당 고객은 목록에 없습니다.");
                    }
                    break;
                case 0 :
                    System.out.println("이전 메뉴로 돌아갑니다.");
                    break;
                default:
                    System.out.println("번호를 다시 확인해주세요");
                    break;
            }
            if (inputMenuType == 0) {
                break;
            }
            }
        }


    public int validationInputType() throws InterruptedException {
        int inputMenuType = 0;

        try {
            return inputMenuType = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("숫자만 입력해주세요");
            String hidden = scanner.nextLine();
            start();
            return inputMenuType;
        } catch (Exception e) {
            System.out.println("숫자만 입력해주세요.");
            start();
            return inputMenuType;
        }
    }
}
