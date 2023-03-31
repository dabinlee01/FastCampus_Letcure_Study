import java.util.List;

public class UserRepository {


    public User login(String username, String password){
        return DB.login(username, password);
    }

    public void join(String username, String password, String email){
        DB.join(username, password, email);
    }

    public List<User> findAll() {
        return DB.userSelectAll();
    }
}
