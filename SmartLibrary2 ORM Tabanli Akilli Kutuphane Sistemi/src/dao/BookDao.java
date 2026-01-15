package dao;

import entity.Book;
import org.hibernate.*;
import util.HibernateUtil;
import java.util.List;

public class BookDao {

    public void save(Book book) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.save(book);
        tx.commit();
        s.close();
    }

    public void update(Book book) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.update(book);
        tx.commit();
        s.close();
    }

    public Book getById(int id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Book b = s.get(Book.class, id);
        s.close();
        return b;
    }

    public List<Book> getAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Book> list = s.createQuery("from Book", Book.class).list();
        s.close();
        return list;
    }
}