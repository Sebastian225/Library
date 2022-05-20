package com.example.library.repository;

import com.example.library.enums.Language;
import com.example.library.model.Author;
import com.example.library.model.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthorRepository {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    public List<Author> getAuthors() {
        String sql = "select * from author";
        List<Author> list = new ArrayList<Author>();

        try (Connection con = DriverManager.getConnection(url, username, password)) {
            var statement = con.prepareStatement(sql);
            ResultSet res = statement.executeQuery();

            while (res.next()) {
                Author entity = new Author();

                entity.setId(res.getInt("id"));
                entity.setFirstName(res.getString("first_name"));
                entity.setLastName(res.getString("last_name"));
                entity.setCountry(res.getString("country"));

                ArrayList<Book> books = new ArrayList<Book>();

                String booksByAuthorSql = "select * from book where author_id = " + res.getInt("id");

                try (Connection bookCon = DriverManager.getConnection(url, username, password)) {
                    var bookStatement = bookCon.prepareStatement(booksByAuthorSql);
                    ResultSet bookRes = bookStatement.executeQuery();

                    while(bookRes.next()) {
                        Book book = new Book();

                        book.setId(bookRes.getInt("id"));
                        book.setName(bookRes.getString("name"));
                        book.setLanguage(Language.values()[bookRes.getInt("language")]);
                        book.setYear(bookRes.getInt("year"));
                        book.setPublisher(bookRes.getString("publisher"));

                        books.add(book);
                    }

                    entity.setBooks(books);

                } catch (SQLException e){
                    e.printStackTrace();
                }

                list.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public Author getAuthorById(int id){
        String sql = "select * from author where id = " + id;
        Author entity = new Author();

        try(Connection con = DriverManager.getConnection(url, username, password)) {
            var statement = con.prepareStatement(sql);
            ResultSet res = statement.executeQuery();

            res.next();

            entity.setId(id);
            entity.setFirstName(res.getString("first_name"));
            entity.setLastName(res.getString("last_name"));
            entity.setCountry(res.getString("country"));

            ArrayList<Book> books = new ArrayList<Book>();

            String booksByAuthorSql = "select * from book where author_id = " + res.getInt("id");

            try (Connection bookCon = DriverManager.getConnection(url, username, password)) {
                var bookStatement = bookCon.prepareStatement(booksByAuthorSql);
                ResultSet bookRes = bookStatement.executeQuery();

                while(bookRes.next()) {
                    Book book = new Book();

                    book.setId(bookRes.getInt("id"));
                    book.setName(bookRes.getString("name"));
                    book.setLanguage(Language.values()[bookRes.getInt("language")]);
                    book.setYear(bookRes.getInt("year"));
                    book.setPublisher(bookRes.getString("publisher"));

                    books.add(book);
                }

                entity.setBooks(books);

            } catch (SQLException e){
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

    public void addAuthor(Author entity){
        String sql = "insert into author values (NULL, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url, username, password)) {

            var statement = con.prepareStatement(sql);
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setString(3, entity.getCountry());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAuthor(int id, Author entity){
        String sql = "update author set first_name = ?, last_name = ?, country = ? where id = " + id;

        try (Connection con = DriverManager.getConnection(url, username, password)) {

            var statement = con.prepareStatement(sql);
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setString(3, entity.getCountry());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAuthor(int id){
        String sql = "delete from author where id = " + id;

        try(Connection con = DriverManager.getConnection(url, username, password)) {

            var statement = con.prepareStatement(sql);
            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
