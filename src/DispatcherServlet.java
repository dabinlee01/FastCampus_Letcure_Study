import mvcapp.controller.BoardController;
import mvcapp.model.BoardRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// GET -> http://localhost:8080/board/list.do
// GET -> http://localhost:8080/board/saveForm.do
// POST -> http://localhost:8080/board/save.do
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 버퍼로 들어오는 모든 값을 UTF-8로 인코딩해서 받기(공통처리)
        // 얘를 안쓰면 한글이 다 깨짐. 모든걸 UTF-8로 파싱해서 받음
        req.setCharacterEncoding("utf-8");

        // 2. path를 파싱. path가 뭐냐고? http://localhost:8080/board/list.do 여기서 board가 path임
        String path = getPath(req);
        System.out.println("path : " + path);

        // 3. action 파싱
        String action = getAction(req);
        System.out.println("action : "+action);


        // 4. 컨트롤러 객체 생성, DI 의존성 주입.
        BoardController boardCon = new BoardController(new BoardRepository());



        // 5. 라우팅하기
        if(path.equals("baord")) {
            switch (action) {

                case "saveForm" :  // saveform 페이지 줘
                   String saveFormView = boardCon.saveForm();
                    // request를 두 번 안하려고. sendRedirect는 여기 못 감. 보안폴더이기때문에
                    req.getRequestDispatcher("/WEB-INF/views/board/list.jsp").forward(req,resp);
                    break;

                    // saveForm이 웹 브라우저에서 열리고 글쓰기 완료 누르면 save 메소드가 실행된다.
                case "save":  // post로 게시글 쓰기 요청(content, title)
                   String method = req.getMethod();
                   if(!method.equals("POST")) {
                       resp.setContentType("text/html; charset=utf-8");
                       resp.getWriter().println("POST로 요청해야 합니다");
                       break;
                   }
                   // POST면 얘가 실행됨
                    // 우리가 직접 파싱해보는거임 getParameter로 받아서
                    String title = req.getParameter("title");
                   String content = req.getParameter("content");
                   String saveRedirect = boardCon.save(title, content);
                    resp.sendRedirect(saveRedirect);  // 게시글 다 쓰면 메인페이지로 가라고. "/board/list.do"
                    // Redirection은 보안폴더(web-inf) 통과하지 못하므로 "/WEB-INF/views/board/list.jsp"로 쓰면 안됨.
                    // 왜 req.getRequestDispatcher("/WEB-INF/views/board/list.jsp").forward(req, resp)를 쓰면 안될까?
                    // request에 list 데이터 저장안해놨음. save에서
                    //  BoardRepository boardRepository = new BoardRepository();
                    //        List<Board> boardList = boardRepository.findAll();     이런거 안해놓음.
                    break;

                case "list":
                    // 필요한 컨트롤러 요청
                    String listView = boardCon.list(req); // boardList가 request에 저장하라고 req넣어줌
                    // 요청받고 응답하는 역할만 하게 됨. MVC에 위임하는 코드임.
                    // req 날라가지 말라고 getRequestDispatcher 사용함. 내부요청
                    // jsp파일에 req전달해줌 -> jsp파일에서 꺼내올 수 있음
                    req.getRequestDispatcher(listView).forward(req, resp);  //"/WEB-INF/views/board/list.jsp"
                    break;
                default:  // 메인페이지
                    resp.sendRedirect("/board/list.do");  // case list로 가라
            }
        }
    }



    // http://localhost:8080/
    private String getAction(HttpServletRequest req) {
        // 문자를 기준으로 분리하여 두 번째 요소를 추출합니다. 이때 .do 확장자가 붙어 있으면 이를 제거한 후 반환
        String action = getUri(req).split("/")[1];  // list
        action = action.replace(".do", "");
        // System.out.println(action);
        return action;
    }
        private String getPath(HttpServletRequest req) {
            // board/list.do
            String path = getUri(req).split("/")[0]; // 문자를 기준으로 분리하여 첫 번째 요소를 반환. board
            return path;
        }
        // http://localhost:8080/board/list.do
        private String getUri(HttpServletRequest req) {
            String uri = req.getRequestURI(); // /board/list.do 호스트 주소 뒤
            uri = uri.substring(1); // board/list.do  앞에 붙어 있는 / 문자를 제거하여 반환
            return uri;
        }
}
