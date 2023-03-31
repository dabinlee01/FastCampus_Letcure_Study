import javax.servlet.http.HttpServletRequest;
import javax.swing.text.View;
import java.util.List;

public class UserController {
    // 초기화, DI
    private UserRepository userRepository;
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    // 이렇게 생성자를 통해 userRepository 객체를 주입받으면 UserController 클래스에서는 userRepository의 메소드를 사용하여 데이터를 조회하고 저장할 수 있게 된다.
    // 객체를 주입받는 것은 객체를 생성하지 않고 다른 코드에서 이미 생성한 객체를 해당 클래스나 메소드에 전달하는 것을 의미.


    // /user/list" 페이지에서 request.getAttribute("userList")로 사용자 정보 리스트를 얻어와 출력하는 메소드
    public String list(HttpServletRequest request) {
        List<User> userList = userRepository.findAll();
        request.setAttribute("userList", userList); // 해당 요청에 대한 데이터 저장
        return ViewResolver.resolve("/user/list");  // /user/list 뷰 페이지 반환
    }

    public String loginForm() { return ViewResolver.resolve("/user/joinForm");}
    public String joinForm() {
        return ViewResolver.resolve("/user/joinForm");
    }

    // 로그인
    public String login(HttpServletRequest request) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(username == null || username.equals("") || password == null || password.equals("")) {
            throw new RuntimeException("username과 password를 다시 확인해주세요");
        }

        User user = userRepository.login(username, password);

        request.setAttribute("List", user);
        return ViewResolver.resolve("/user/joinForm");
    }


    public String join(String username, String password, String email) {
        if(username == null || username.isEmpty()) {
            throw new RuntimeException("헤딩 username 정보가 없습니다");
        }
        if(password == null || password.isEmpty()) {
            throw new RuntimeException("헤딩 password가 없습니다");
        }
        userRepository.join(username, password, email);
        return "/user/joinForm.do";
    }
}
