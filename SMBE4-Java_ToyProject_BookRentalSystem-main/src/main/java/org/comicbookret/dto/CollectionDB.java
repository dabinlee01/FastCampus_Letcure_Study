package org.comicbookret.dto;

import org.comicbookret.common.TableName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.*;


/**
 * Collection API를 활용한 Database 클래스
 */
public class CollectionDB {

    // log 생성
    private final static Logger log = LoggerFactory.getLogger(CollectionDB.class);

    // Singleton Pattern CollectionDB instance
    private static CollectionDB instance = new CollectionDB();
    // Singleton Collection Database Object
    private static Map<TableName, ArrayList>  mDatabase = new HashMap<>();

    /**
     * private 생성자
     */
    private CollectionDB(){}

    // static 초기화 블럭
    static{
        // 초기 데이터 셋팅
        mDatabase.put(TableName.COMICBOOK, new ArrayList<ComicBookDto>(
                Arrays.asList(
                        new ComicBookDto(1, "공포의 외인구단", "이현세"),
                        new ComicBookDto(2, "용비풀패", "류기훈"),
                        new ComicBookDto(3, "구르물 버서난 달처럼", "박흥용"),
                        new ComicBookDto(4, "도깨비 감투", "신문수"),
                        new ComicBookDto(5, "바람의 나라", "김진"),
                        new ComicBookDto(6, "귀멸의칼날", "고토게 고요하루"),
                        new ComicBookDto(7, "나루토", "카시모토 마사시"),
                        new ComicBookDto(8, "진격의 거인", "이시야마 하지메"),
                        new ComicBookDto(9, "데스노트", "대원씨아이"),
                        new ComicBookDto(10, "슬램덩크", "이노우에 타케히코")
                ))
        ); // 만확책 테이블

        mDatabase.put(TableName.CUSTOMER, new ArrayList<CustomerDto>(
                Arrays.asList(
                        new CustomerDto(1,"홍길동"),
                        new CustomerDto(2,"김철수"),
                        new CustomerDto(3,"이나영")
                ))
        ); // 고객 테이블

        mDatabase.put(TableName.RENTAL, new ArrayList<RentalDto>(
                Arrays.asList(
                        new RentalDto(1,LocalDate.now(), 2, 1 ),
                        new RentalDto(2,LocalDate.now(), 7, 2 ),
                        new RentalDto(3,LocalDate.now(), 5, 3 )
                ))
        ); // 대여 테이블
    }

    public static final CollectionDB getInstance() {
        if( instance == null )
            instance = new CollectionDB();
        return instance;
    }

    /**
     * 매개변수로 전달된 테이블명의 ArrayList 객체를 리턴
     * @param name
     * @return
     */
    public final ArrayList getTable(TableName name){
        return this.mDatabase.get(name);
    }


    /**
     * Table에 저장
     * @param dto 저장할 dto
     * @param tableName 테이블명
     * @param <T> dto type
     * @throws Exception
     */
    public final <T> void create(T dto, TableName tableName) throws Exception {
        List<T> list = getTable(tableName);
        List<CommonDto> mList = getTable(tableName);

        // id의 max값 구하기 (id 값을 유일한 값으로 유지하기 위해 +1 연산이 필요함)
        CommonDto mDto = mList.stream().
                max(Comparator.comparingInt(CommonDto::getId))
                .orElseThrow(NoSuchElementException::new);
        int maxId = mDto != null ?
                mDto.getId() + 1 :
                list.size() + 1;
        Method m = dto.getClass().getMethod("setId", int.class);
        m.invoke(dto, maxId);
        list.add(dto);
    }

    public final CommonDto update(TableName tableName, int id){
        List<CommonDto>  list = getTable(tableName);

        // Steam API를 이용해서 comicBookId에 해당하는 dto 객체를 얻어온다
        return list.stream()
                .filter(c -> c.getId() == id) // comicBookId의 값을 리턴
                .findFirst() // filter조건에 맞는 첫번째 요소를 리턴
                .orElse(null); // 값이 없을 경우 null을 리턴
    }

    public final boolean delete(TableName tableName, int id){
        boolean isExist = false;
        List<CommonDto> list = getTable(tableName);

        // Steam API를 이용해서 id에 해당하는 dto 객체를 얻어온다
        CommonDto dto = list.stream()
                .filter(c -> c.getId() == id) // id의 값을 리턴
                .findFirst() // filter조건에 맞는 첫번째 요소를 리턴
                .orElse(null); // 값이 없을 경우 null을 리턴

        if(dto != null) {
            isExist = true;
            list.remove(list.indexOf(dto)); // 해당 DTO의 index를 찾아서 remove
        }
        return isExist;
    }


    /**
     * id에 해당하는 DTO 객체를 리턴
     * @param id
     * @return
     */
    public final CommonDto findById(TableName tableName, int id){
        List<CommonDto> list = getTable(tableName);

        // Steam API를 이용해서 comicBookId에 해당하는 dto 객체를 얻어온다
        return list.stream()
                .filter(c -> c.getId() == id) // comicBookId의 값을 리턴
                .findFirst() // filter조건에 맞는 첫번째 요소를 리턴
                .orElse(null); // 값이 없을 경우 null을 리턴
    }

//    /**
//     * id에 해당하는 DTO 객체를 리턴
//     * @param comicBookId
//     * @return
//     */
//    public final ComicBookDto findByComicBookId(int comicBookId){
//        List<ComicBookDto>  list = this.mDatabase.get(TableName.COMICBOOK);
//
//        // Steam API를 이용해서 comicBookId에 해당하는 dto 객체를 얻어온다
//        return list.stream()
//                .filter(c -> c.getId() == comicBookId) // comicBookId의 값을 리턴
//                .findFirst() // filter조건에 맞는 첫번째 요소를 리턴
//                .orElse(null); // 값이 없을 경우 null을 리턴
//    }
//
//    /**
//     * id에 해당하는 DTO 객체를 리턴
//     * @param customerId
//     * @return
//     */
//    public final CustomerDto findByCustomerId(int customerId){
//        List<CustomerDto>  list = this.mDatabase.get(TableName.CUSTOMER);
//
//        // Steam API를 이용해서 comicBookId에 해당하는 dto 객체를 얻어온다
//        return list.stream()
//                .filter(c -> c.getId() == customerId)
//                .findFirst()
//                .orElse(null);
//    }
//
//    /**
//     * id에 해당하는 DTO 객체를 리턴
//     * @param rentalId
//     * @return
//     */
//    public final RentalDto findByRentalId(int rentalId){
//        List<RentalDto>  list = this.mDatabase.get(TableName.RENTAL);
//
//        // Steam API를 이용해서 comicBookId에 해당하는 dto 객체를 얻어온다
//        return list.stream()
//                .filter(c -> c.getId() == rentalId)
//                .findFirst()
//                .orElse(null);
//    }


}
