package mvcapp.model;

import mvcapp.config.DB;

import java.util.List;
// 책임: 컨트롤러가 데이터베이스에 접근
public class BoardRepository {

    public List<Board> findAll(){
        return DB.selectAll();
    }
  // SELECT * FROM board


    // 클라이언트(x-www-form-url-encoded)-> DispatcherServlet(request body): getParameter-> Controller(title,content) -> Respository(title,content)
    // 클라이언트가 title,content를 잘 보냈는지 체크하는 역할은 controller or DS가 함. 체크는 필수! controller에서 유효성 검사 필수!
    public void save(String title, String content){
        DB.insert(title, content);
// INSERT INTO board(title, content) VALUES('제목1', '내용')
    }

}