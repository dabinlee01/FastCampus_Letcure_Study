package mvcapp.controller;

import mvcapp.config.ViewResolver;
import mvcapp.model.Board;
import mvcapp.model.BoardRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
// 책임: 클라이언트 요청을 잘 받고(유효성 검사=validation check), 응답(view, data)
public class BoardController {

    // controller -> model -> view
    public String list(HttpServletRequest request) {
        // model에 위임함
        BoardRepository boardRepository = new BoardRepository();
        List<Board> boardList = boardRepository.findAll();  // 이 boardList를 request에 저장해주면 view에서 쓸 수 있음
        request.setAttribute("boardList", boardList); // DispatcherServlet의 case: list -> listView로 이동
        // 받아서 request에 저장
        return ViewResolver.resolve("/board/list");
    } // 근데 이 코드는 매번 new하게 만들어서 별로임. -> 주입받을 거임


    private BoardRepository boardRepository;

    //  결국 controller가 repository를 필요로 함. = controller는 repository에 의존적이다.
    // 의존적인 객체를 dispathcerServlet로부터 주입받을 것(생성자로 주입받음)

    // 주입받는 코드임. 초기화. 생성자 코드
    public BoardController(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }


    /*public String list(HttpServletRequest request) {
        System.out.println("list : 요청됨");
        // controller -> model -> view
        model한테 위임. DB에 접근해서 데이터 좀 가져와하고 받아서 request에 저장 후 돌아감
       안 좋은 코드. 매번 new해야 되니까
       // BoardRepository boardRepository = new BoardRepository();
        List<Board> boardList = boardRepository.findAll(); //list니까 findAll
        request.setAttribute("boardList",boardList);  // DispatcherServlet의 case: list로 이동함
        return ViewResolver.resolve("/board/list");
                // "/WEB-INF/views/board/list.jsp";
    } */

    public String saveForm(){
        // controller -> view.
        System.out.println("saveForm : 요청됨");
        return ViewResolver.resolve("/board/saveForm");
                // "/WEB-INF/views/board/list.jsp";
    }

    // 스프링은 컨트롤러에 매개변수를 적기만 하면(DS의 "save"에서 getParameter로 받은거) formUrlEncoded 데이터를 DispatcherServlet으로부터 전달받음
    // 여기엔 formUrlEncoded 스타일만 들어올 수 있음. 디폴트라.
    public String save(String title, String content) {
        // 들어오면 검증해야함. http method 4가지 중에 post와 put일 때 검증 받아야 함. 왜? resource를 클라이언트로부터 데이터를 전달 받으니까 신뢰할 수 있는지 확인해야함.
        // get, delect는 body 데이터가 없으니까 안해도 됨.

        if(title == null || title.equals("")) {
            throw new NullPointerException("tilte이 없습니다");
        }
        if(content == null || content.equals("")) {
            throw new NullPointerException("content가 없습니다");
        }
        System.out.println("save : 요청됨");
        boardRepository.save(title, content);
        return "/board/list.do";  // 이걸 알아서 찾아줌.
    }  // 얘는 viewresolver를 쓸 필요가 없는게 다시 맨 첫번째의 list를 호출하는거라
}