package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      Car car1 = new Car("Moskvich", 3);
      Car car2 = new Car("Lada", 21021);
      Car car3 = new Car("UAZ", 469);
      Car car4 = new Car("ZIL", 106);

      System.out.println("__________________________");
      System.out.println("Add new users: Logging - Вывод пользователей с машиной:");

      userService.add(new User("User11", "Lastname11", "user11@mail.ru", car1));
      userService.add(new User("User12", "Lastname12", "user12@mail.ru", car2));
      userService.add(new User("User13", "Lastname13", "user13@mail.ru", car3));
      userService.add(new User("User14", "Lastname14", "user14@mail.ru", car4));

      System.out.println("_________________________");

      System.out.println("getUserByCarModelAndCarSeries: Logging - Вывод пользователя с машиной:");

      User user = userService.getUserByCarModelAndCarSeries("ZIL",106);

      /*
      if (user == null) {
         System.out.println("getUserByCarModelAndCarSeries: Logging - Пользователь или автомобиль не существует");
      } else {
         System.out.println(user);
      }
       */
      System.out.println(user);
      System.out.println("getUserByCarModelAndCarSeries: Logging - Пользователь и автомобиль успешно найден!");

      context.close();
   }
}
