import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");

        String path = getPath(req);  // board, save, list

        String action = getAction(req);  // loginForm, joinForm, login


        BoardRepository boardRepository = new BoardRepository();
        UserRepository userRepository = new UserRepository();
        BoardController boardCon = new BoardController(boardRepository);
        UserController userCon = new UserController(userRepository);


        String method = req.getMethod();

        // GET -> http://localhost:8080/board/list.do
        // GET -> http://localhost:8080/board/saveForm.do
        // POST -> http://localhost:8080/board/save.do
        if (path.equals("board")) {
            switch (action) {
                case "saveForm": // 게시글 저장을 위한 폼을 보여주는 뷰 뜸
                    String saveFormView = boardCon.saveForm();
                    req.getRequestDispatcher(saveFormView).forward(req, resp);  // 보안폴더니까. // 뷰를 클라이언트에게 전송
                    break;

                case "save": // 저장
                    method = req.getMethod();
                    if (!method.equals("POST")) {
                        resp.setContentType("text/html; charset=utf-8");
                        resp.getWriter().println("POST로 요청해야 합니다");
                        break;
                    }

                    String title = req.getParameter("title");
                    String content = req.getParameter("content");
                    String saveRedirect = boardCon.save(title, content);  // 새로운 게시글 저장
                    resp.sendRedirect(saveRedirect);  // 게시글 다 쓰면 메인페이지로 가라고. "/board/list.do"
                    break;

                case "list": // 게시글 리스트 조회하는 뷰 생성
                    String listView = boardCon.list(req);  // 출력
                    req.getRequestDispatcher(listView).forward(req, resp);  // 클라이언트에게 해당 뷰를 전송 /WEB-INF/views/board/list.jsp
            }
        }
        // GET --> http://localhost:10000/user/loginForm.do
        // GET --> http://localhost:10000/user/joinForm.do
        // POST
        if (path.equals("user")) {
            switch (action) {  // GET
                case "loginForm":
                    String loginFormView = userCon.loginForm();
                    req.getRequestDispatcher(loginFormView).forward(req, resp);
                    break;

                case "joinForm":  // GET
                    String joinFormView = userCon.joinForm();
                    req.getRequestDispatcher(joinFormView).forward(req, resp); // view는 다 보안이구나
                    break;

                case "login": // POST
                    method = req.getMethod();

                    String username = req.getParameter("username");
                    String password = req.getParameter("password");
                    String email = req.getParameter("email");

                    String saveRedirect = userCon.join(username, password, email);
                    resp.sendRedirect(saveRedirect);
                    break;

                case "list":
                    String listView = userCon.list(req);
                    req.getRequestDispatcher(listView).forward(req, resp);
                    break;
                default:
                    resp.sendRedirect("/user/list.do");
            }
        }
        }
    // http://localhost:8080/board/list.do
    private String getUri(HttpServletRequest req) {
        String uri = req.getRequestURI(); // /board/list.do
        uri = uri.substring(1); // board/list.do  앞에 붙어 있는 / 문자를 제거하여 반환. 호스트 주소를 제외한 경로와 파일명을 추출
        return uri;
    }
    private String getPath(HttpServletRequest req) {
        //     /board/list.do
        String path = getUri(req).split("/")[0]; // 문자를 기준으로 분리하여 첫 번째 요소를 반환. board
        return path;
    }
    // http://localhost:8080/board/list.do
    private String getAction(HttpServletRequest req) {
        // 두 번째 요소를 추출
        String action = getUri(req).split("/")[1];
        action = action.replace(".do", "");
        System.out.println(action); // list
        return action;
    }
    }


