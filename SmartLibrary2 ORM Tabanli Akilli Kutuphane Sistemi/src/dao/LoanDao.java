package dao;

import entity.Loan;
import org.hibernate.*;
import util.HibernateUtil;
import java.util.List;

public class LoanDao {

    public void save(Loan l) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.save(l);
        tx.commit();
        s.close();
    }

    public void update(Loan l) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.update(l);
        tx.commit();
        s.close();
    }

    public Loan getById(int id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Loan l = s.get(Loan.class, id);
        s.close();
        return l;
    }

    public List<Loan> getAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Loan> list = s.createQuery("from Loan", Loan.class).list();
        s.close();
        return list;
    }
}