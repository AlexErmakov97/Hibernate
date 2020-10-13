package service;

import dao.UserDao;
import entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import utill.SessionUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserServiceCriteria extends SessionUtil implements UserDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> getAll() {
        List<User> users = null;

        openTransactionSession();

        Session session = getSession();

        Criteria criteria = session.createCriteria(User.class);

        users = criteria.list();

        closeTransactionSesstion();
        return users;
    }

    @Override
    public User getById(int id) {
        openTransactionSession();
        Session session = getSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("id", id));
        User user = (User) criteria.uniqueResult();
        closeTransactionSesstion();
        return user;
    }

    @Override
    public void insert(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }

    public void phoneNumber(String oldNumber, Double newNumber) {

        CriteriaBuilder cb = this.em.getCriteriaBuilder();

        CriteriaUpdate<User> update = cb.
                createCriteriaUpdate(User.class);

        Root root = update.from(User.class);

        update.set("phone_number", newNumber);
        update.where(cb.greaterThanOrEqualTo(root.get("phone_number"), oldNumber));

        this.em.createQuery(update).executeUpdate();
    }
}
