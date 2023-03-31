import java.util.ArrayList;
import java.util.List;

    public class DB {
        private static DB instance = new DB();

        private DB() {
        }

        public static DB getInstance() {
            if (instance == null) {
                instance = new DB();
            }
            return instance;
        }

        private static List<Board> boardList = new ArrayList<>();
        private static List<User> userList = new ArrayList<>();

        // static 블록에서 초기값을 설정하므로 DB클래스가 로딩될 때 바로 초기값을 가지게 됨. 걍 바로 메모리에 로딩됨
        // 즉 다른 클래스에서 "DB" 클래스를 사용할 때, 객체를 생성할 필요 없이 static 필드와 메소드를 바로 사용할 수 있음

        static {
            boardList.add(new Board(1, "제목 ", "내용"));
            boardList.add(new Board(2, "제목 ", "내용"));
            userList.add(new User(1, "윤", "0000", "lostonme@gmail.com"));
            userList.add(new User(2, "도", "1111", "idontknowuanymore@gmail.com"));
        }

        public static List<Board> boardSelectAll() {
            return boardList;
        }

        public static List<User> userSelectAll() {
            return userList;
        }

        public static User login(String username, String password) {
           for(int i=0; i<userList.size(); i++) {
               if(userList.get(i).getUsername().equals(username) && userList.get(i).getPassword().equals(password)) return userList.get(i);
           }
            return null;
        }

        public static void join(String username, String password, String email) {
            int id = userList.size() + 1;
            userList.add(new User(id, username, password, email));
        }

        public static void insert(String title, String content) {
            int id = boardList.size()+1;
            boardList.add(new Board(id, title, content));
        }
    }



