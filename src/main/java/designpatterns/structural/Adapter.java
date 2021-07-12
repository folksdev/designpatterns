package designpatterns.structural;

/*
    The Adapter pattern is used for connecting two incompatible interfaces
    that otherwise cannot be connected directly. An Adapter wraps an existing
    class with a new interface so that it becomes compatible with the interface needed.

    http://www.vincehuston.org/dp/adapter.html
 */
public class Adapter {

    class LegacyUser{
        int id;
        String username;
        String name;
        String lastname;
    }

    interface LegacyUserService{
        LegacyUser getUser(int id);
    }

    class User{
        int id;
        String email;
        String username;
        String name;
        String lastname;
        String location;
        Boolean isActive;
    }

    interface UserRepository{
        User getUser(String email);
    }

    interface UserService {
        User getUser(String email);
    }

    class UserServiceAdapter implements UserService {

        UserRepository userRepository;
        LegacyUserService legacyUserService;

        @Override
        public User getUser(String email) {
            User user = userRepository.getUser(email);
            LegacyUser legacyUser = legacyUserService.getUser(user.id);
            user.username = legacyUser.username;
            user.lastname = legacyUser.lastname;
            return user;
        }
    }

    public void adapterDemo(){
        UserService userService = new UserServiceAdapter();
        User user = userService.getUser("email@email.com");
        System.out.println(user);
    }

    public static void main(String[] args) {
        Adapter adapter = new Adapter();
        adapter.adapterDemo();
    }

}
