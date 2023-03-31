import javax.servlet.http.HttpServletRequest;
import java.util.List;


public class BoardRepository {
    private BoardRepository boardRepository;

    public BoardRepository(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public BoardRepository() {}

    public String list(HttpServletRequest request) {
        List<Board> boardList = boardRepository.findAll();
        request.setAttribute("boardList", boardList);
        return ViewResolver.resolve("/board/list");
    }
    public List<Board> findAll() {
        return DB.boardSelectAll();
    }
    public void save(String title, String content) {
        DB.insert(title, content);
    }

}
