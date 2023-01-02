package com.example.controllers;

import com.example.entity.Book;
import com.example.entity.Member;
import com.example.utils.HibernateUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class libraryInchargePageController implements Initializable {
    @FXML
    TabPane tabPane;

    //    issue book
    @FXML
    Tab issueTab;
    @FXML
    TextField issuePageBookId;
    @FXML
    TextField issuePageMemberId;
    @FXML
    DatePicker issuePageDateOfIssue;
    @FXML
    DatePicker issuePageDueDate;


    public void issuePageSubmit(ActionEvent event) {
        int bookId = Integer.parseInt(issuePageBookId.getText());
        int memberId = Integer.parseInt(issuePageMemberId.getText());
        LocalDate dateOfIssue = issuePageDateOfIssue.getValue();
        LocalDate dueDate = issuePageDueDate.getValue();

        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();

        Book book = session.get(Book.class, bookId);
        book.setMemberId(memberId);
        book.setIssueDate(dateOfIssue);
        book.setDueDate(dueDate);
        book.setAvailable(false);

        session.beginTransaction();
        session.merge(book);
        session.getTransaction().commit();


        System.out.println("Book Issued...");
        System.out.println(bookId + " : " + memberId + " : " + dateOfIssue + " : " + dueDate);
    }

    public void issuePageReset(ActionEvent event) {
        issuePageBookId.setText("");
        issuePageMemberId.setText("");
        issuePageDateOfIssue.setValue(null);
        issuePageDueDate.setValue(null);
    }


    //    return book
    @FXML
    Tab returnTab;
    @FXML
    TextField returnPageBookId;
    @FXML
    TextField returnPageMemberId;

    public void returnPageSubmit(ActionEvent event) {
        int bookId = Integer.parseInt(returnPageBookId.getText());
        int memberId = Integer.parseInt(returnPageMemberId.getText());

        System.out.println(bookId + " : " + memberId);
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();

        Book book = session.get(Book.class, bookId);
        book.setAvailable(true);
        book.setDueDate(null);
        book.setIssueDate(null);
        book.setMemberId(null);

        session.beginTransaction();
        session.merge(book);
        session.getTransaction().commit();

        System.out.println("Book Returned : " + book);

    }

    public void returnPageReset(ActionEvent event) {
        returnPageBookId.setText("");
        returnPageMemberId.setText("");
    }


    //    add book
    @FXML
    Tab addBookTab;
    @FXML
    TextField addBookPageTitle;
    @FXML
    TextField addBookPageAuthor;
    @FXML
    TextField addBookPagePrice;


    public void addBookPageSubmit(ActionEvent event) {
        String title = addBookPageTitle.getText();
        String author = addBookPageAuthor.getText();
        double price = Double.parseDouble(addBookPagePrice.getText());


        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();

        Book book = new Book(0, title, author, price, null, null, null, true);
        session.beginTransaction();
        session.persist(book);
        session.getTransaction().commit();
        System.out.println("Book Added : " + book);

    }

    public void addBookPageReset(ActionEvent event) {
        addBookPageTitle.setText("");
        addBookPageAuthor.setText("");
        addBookPagePrice.setText("");
    }

    //    add member
    @FXML
    Tab addMemberTab;
    @FXML
    TextField addMemberPageName;
    @FXML
    TextField addMemberPageProfession;

    public void addMemberPageSubmit(ActionEvent event) {
        String memberName = addMemberPageName.getText();
        String memberProfession = addMemberPageProfession.getText();

        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();

        Member member = new Member(0, memberName, memberProfession);
        session.beginTransaction();
        session.persist(member);
        session.getTransaction().commit();
        System.out.println("member added..." + member);
    }

    public void addMemberPageReset(ActionEvent event) {
        addMemberPageName.setText("");
        addMemberPageProfession.setText("");
    }

    //    view books
    @FXML
    Tab viewBooksTab;
    @FXML
    TableView<Book> viewBooksPageTable;
    @FXML
    TableColumn<Book, Integer> viewBooksPageBookIdColumn;
    @FXML
    TableColumn<Book, String> viewBooksPageTitleColumn;
    @FXML
    TableColumn<Book, String> viewBooksPageAuthorColumn;
    @FXML
    TableColumn<Book, Double> viewBooksPagePriceColumn;
    @FXML
    TableColumn<Book, Boolean> viewBooksPageAvailableColumn;

    ObservableList<Book> booksList;

    public void viewBooksPageRefreshButton(ActionEvent event) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("select b.bookId, b.title, b.author,b.price,b.issueDate,b.dueDate,b.memberId, b.available from Book b");
        List<Object[]> list = query.list();
        booksList = FXCollections.observableArrayList();
        Book book;
        for (Object[] item : list) {
            book = new Book((Integer) item[0], (String) item[1], (String) item[2], (Double) item[3], (LocalDate) item[4], (LocalDate) item[5], (Integer) item[6], (Boolean) item[7]);
            booksList.add(book);
            System.out.println(book);
        }
        viewBooksPageTable.setItems(booksList);
    }


    //    issued books
    @FXML
    Tab issuedBooksTab;
    @FXML
    TableView<Book> issuedBooksTable;
    @FXML
    TableColumn<Book, Integer> issuedBooksPageBookIdColumn;
    @FXML
    TableColumn<Book, String> issuedBooksPageTitleColumn;
    @FXML
    TableColumn<Book, String> issuedBooksPageAuthorColumn;
    @FXML
    TableColumn<Book, Integer> issuedBooksPageMemberIdColumn;
    @FXML
    TableColumn<Book, LocalDate> issuedBooksDueDateColumn;

    public void issueBooksPageRefresh(ActionEvent event) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("select b.bookId, b.title, b.author,b.price,b.issueDate,b.dueDate,b.memberId, b.available from Book b");
        List<Object[]> list = query.list();
        ObservableList<Book> issuedBooksList = FXCollections.observableArrayList();
        Book book;
        for (Object[] item : list) {
            book = new Book((Integer) item[0], (String) item[1], (String) item[2], (Double) item[3], (LocalDate) item[4], (LocalDate) item[5], (Integer) item[6], (Boolean) item[7]);
            if (!book.getAvailable()) {
                issuedBooksList.add(book);
            }
            System.out.println(book);
        }
        issuedBooksTable.setItems(issuedBooksList);
    }

    //    members table
    @FXML
    Tab membersTab;
    @FXML
    TableView<Member> membersPageTable;
    @FXML
    TableColumn<Member, Integer> membersPageMemberIdColumn;
    @FXML
    TableColumn<Member, String> membersPageNameColumn;
    @FXML
    TableColumn<Member, String> membersPageProfessionColumn;

    ObservableList<Member> memberList;
    public void membersPageRefresh(ActionEvent event) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("select m.memberId, m.name, m.profession from Member m");
        List<Object[]> list = query.list();
        memberList = FXCollections.observableArrayList();
        Member member;
        for (Object[] item : list) {
            member = new Member((Integer) item[0], (String) item[1], (String) item[2]);
            memberList.add(member);
            System.out.println(member);
        }
        membersPageTable.setItems(memberList);
    }


    //    initialize tables
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        view Books table
        viewBooksPageBookIdColumn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("bookId"));
        viewBooksPageTitleColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        viewBooksPageAuthorColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        viewBooksPagePriceColumn.setCellValueFactory(new PropertyValueFactory<Book, Double>("price"));
        viewBooksPageAvailableColumn.setCellValueFactory(new PropertyValueFactory<Book, Boolean>("available"));

        viewBooksPageRefreshButton(null);
        //        viewBooksPageTable.setItems(booksList);


//        issued books table
        issuedBooksPageBookIdColumn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("bookId"));
        issuedBooksPageTitleColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        issuedBooksPageAuthorColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        issuedBooksPageMemberIdColumn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("memberId"));
        issuedBooksDueDateColumn.setCellValueFactory(new PropertyValueFactory<Book, LocalDate>("dueDate"));
        issueBooksPageRefresh(null);

//        members table
        membersPageMemberIdColumn.setCellValueFactory(new PropertyValueFactory<Member, Integer>("memberId"));
        membersPageNameColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("name"));
        membersPageProfessionColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("profession"));
        membersPageRefresh(null);

    }


}
