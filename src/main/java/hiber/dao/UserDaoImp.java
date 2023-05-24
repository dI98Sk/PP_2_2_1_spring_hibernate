package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import hiber.model.Car;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User", User.class);
      return query.getResultList();
   }
   @Override
   public User carList(Car car) {
      try {
         return sessionFactory.getCurrentSession()
                 .createQuery("from User user where user.car.model = :model and user.car.series = :series",
                         User.class)
                 .setParameter("model", car.getModel())
                 .setParameter("series", car.getSeries())
                 .uniqueResult();
      } catch (Exception e){
         System.out.println("Не существует владельца или авто");
         return null;
      }
   }
}
