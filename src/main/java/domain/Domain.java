package domain;

import entity.User;
import service.UserService;
import service.UserServiceCriteria;

import static utill.Util.viewData;

public class Domain {
    public static void main(String[] args) {
        UserService userService = new UserService();
        userService.getAll();

        UserServiceCriteria userServiceCriteria = new UserServiceCriteria();
        viewData(userServiceCriteria.getAll());
        System.out.println(userServiceCriteria.getById(78).toString());

        User user = new User();
        user.setId(80);
        user.setFirstName("Gena");
        user.setLastName("Gorin");
        user.setEmail("gorin@mail.ru");
        user.setPhoneNumber("89345677634");

        userService.insert(user);

        userService.delete(user);

        User userFromDateBase = userService.getById(4);
        System.out.println(userFromDateBase);

//        userService.pagination();
//        viewData(userService.pagination());


        viewData(userService.searchByPage(10, 1));

        viewData(userService.searchByPage(10, 2));

        viewData(userService.searchByPage(10, 3));
    }
}
