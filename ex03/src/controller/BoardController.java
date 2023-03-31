import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class BoardController {

    // 책임: 클라이언트 요청을 잘 받고(유효성 검사=validation check), 응답(view, data)
    // 클라이언트(x-www-form-url-encoded)-> DispatcherServlet(request body): getParameter-> Controller(title,content) -> Respository(title,content)
    // model에 데이터 넣고, 모델을 넣을 view이름 배정
    // controller -> model -> view


    private BoardRepository boardRepository;

    public BoardController(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public String list(HttpServletRequest request) {
        List<Board> boardList = boardRepository.findAll();
        request.setAttribute("boardList", boardList);
        return ViewResolver.resolve("/board/list");
    }

    public String saveForm() {
        return ViewResolver.resolve("/board/saveForm");
    }

    public String save(String title, String content) {
        boardRepository.save(title, content);
        if (title == null || title.equals("")) {
            throw new NullPointerException("tilte이 없습니다");
        }
        if (content == null || content.equals("")) {
            throw new NullPointerException("content가 없습니다");
        }
        return "/board/list.do";
    }
}
