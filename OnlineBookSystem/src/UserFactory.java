
import java.util.List;
import java.util.Map;

public class UserFactory {

    Map<User, List<Book>> userListMap;
    //To maintain History you can use LinkList or Stack - seperate variable can be created
    UserFactory(Map<User, List<Book>> userListMap) {
        this.userListMap = userListMap;
    }

    public List<Book> getCurrentBookIssued(User user) {
        return userListMap.getOrDefault(user, null);
    }


}
