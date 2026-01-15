package app;

import dao.*;
import entity.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BookDao bookDao = new BookDao();
        StudentDao studentDao = new StudentDao();
        LoanDao loanDao = new LoanDao();

        while (true) {
            System.out.println("\n1-Kitap Ekle 2-Kitaplar 3-Öğrenci Ekle 4-Öğrenciler 5-Ödünç Ver 6-Ödünçler 7-Teslim 0-Çıkış");
            int secim = sc.nextInt();
            sc.nextLine();

            if (secim == 0) break;

            switch (secim) {
                case 1 -> {
                    Book b = new Book();
                    System.out.print("Başlık: ");
                    b.setTitle(sc.nextLine());
                    System.out.print("Yazar: ");
                    b.setAuthor(sc.nextLine());
                    System.out.print("Yıl: ");
                    b.setYear(sc.nextInt());
                    b.setStatus(Book.Status.AVAILABLE);
                    bookDao.save(b);
                }
                case 2 -> bookDao.getAll().forEach(b ->
                        System.out.println(b.getId()+" "+b.getTitle()+" "+b.getStatus()));
                case 3 -> {
                    Student s = new Student();
                    System.out.print("Ad: ");
                    s.setName(sc.nextLine());
                    System.out.print("Bölüm: ");
                    s.setDepartment(sc.nextLine());
                    studentDao.save(s);
                }
                case 4 -> studentDao.getAll().forEach(s ->
                        System.out.println(s.getId()+" "+s.getName()));
                case 5 -> {
                    System.out.print("Öğrenci ID: ");
                    int sid = sc.nextInt();
                    System.out.print("Kitap ID: ");
                    int bid = sc.nextInt();
                    Student st = studentDao.getById(sid);
                    Book bk = bookDao.getById(bid);
                    if (bk.getStatus() == Book.Status.BORROWED) break;
                    Loan l = new Loan();
                    l.setStudent(st);
                    l.setBook(bk);
                    l.setBorrowDate(LocalDate.now());
                    bk.setStatus(Book.Status.BORROWED);
                    loanDao.save(l);
                    bookDao.update(bk);
                }
                case 6 -> loanDao.getAll().forEach(l ->
                        System.out.println(l.getStudent().getName()+" "+l.getBook().getTitle()));
                case 7 -> {
                    System.out.print("Loan ID: ");
                    int lid = sc.nextInt();
                    Loan l = loanDao.getById(lid);
                    l.setReturnDate(LocalDate.now());
                    Book bk = l.getBook();
                    bk.setStatus(Book.Status.AVAILABLE);
                    loanDao.update(l);
                    bookDao.update(bk);
                }
            }
        }
    }
}