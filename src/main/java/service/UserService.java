package service;

import dao.UserDao;
import entity.User;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utill.SessionUtil;

import java.util.List;

public class UserService extends SessionUtil implements UserDao {
    @Override
    public List<User> getAll() {
        openTransactionSession();

        String sql = "select * from user_";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(User.class);
        List<User> addressList = query.list();

        closeTransactionSesstion();

        return addressList;
    }

    @Override
    public User getById(int id) {
        openTransactionSession();

        String sql = "SELECT * FROM user_ WHERE user_id = :id";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(User.class);
        query.setParameter("id", id);

        User user = (User) query.getSingleResult();

        closeTransactionSesstion();

        return user;
    }

    @Override
    public void insert(User user) {
        openTransactionSession();

        Session session = getSession();
        session.save(user);

        closeTransactionSesstion();
    }

    @Override
    public void update(User user) {
        openTransactionSession();

        Session session = getSession();
        session.update(user);

        closeTransactionSesstion();

    }

    @Override
    public void delete(User user) {
        openTransactionSession();

        Session session = getSession();
        session.remove(user);

        closeTransactionSesstion();
    }

//    public List<User> pagination() {
//        int pageSize = 10;
//        String count = "select count(u.id) from User u";
//        openTransactionSession();
//        Session session = getSession();
//        Query countQuery = session.createQuery(count);
//        Long countResults = (Long) countQuery.uniqueResult();
//        int lastPageNumber = (int) (Math.ceil(countResults / pageSize));
//        Query selectQuery = session.createQuery("From User");
//        selectQuery.setFirstResult((lastPageNumber - 1) * pageSize);
//        selectQuery.setMaxResults(pageSize);
//        List<User> lastPage = selectQuery.list();
//        return lastPage;
//    }

    public List<User> searchByPage(int limitPerPage, int page) {
        String sql = "From User";

        openTransactionSession();

        Session session = getSession();

        Query query = session.createQuery(sql)
                .setFirstResult(calculateOffset(page, limitPerPage))
                .setMaxResults(limitPerPage);

        return query.getResultList();
    }

    private int calculateOffset(int page, int limit) {
        return ((limit * page) - limit);
    }

}
