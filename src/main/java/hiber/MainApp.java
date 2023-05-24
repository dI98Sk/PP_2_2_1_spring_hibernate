package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Dima", "Skakun", "dimaskak123.com");
      User user2 = new User("Alex", "Sidorov", "dimaskak456.com");
      User user3 = new User("Den", "Skabelkin", "dimaskak897.com");

      user1.setCar(new Car("BMW", 1));
      user2.setCar(new Car("VAZ", 2));
      user3.setCar(new Car("GAZ", 3));

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user);
      }

      User user4 = userService.carList(new Car("Audi",6));
      System.out.println(user4);
      User user5 = userService.carList(new Car("Reno", 6));
      System.out.println(user5);
      context.close();
   }
}
