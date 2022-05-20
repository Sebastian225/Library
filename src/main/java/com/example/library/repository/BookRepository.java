package com.example.library.repository;

import com.example.library.enums.Language;
import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.model.Section;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    public List<Book> getBooks() {
        String sql = "SELECT b.id, b.name, b.language, b.year, b.publisher, b.author_id, a.first_name, a.last_name, a.country, b.section_id, s.name, s.is_fiction FROM book b LEFT JOIN author a ON b.author_id = a.id LEFT JOIN section s ON b.section_id = s.id;";
        List<Book> list = new ArrayList<Book>();

        try (Connection con = DriverManager.getConnection(url, username, password)) {
            var statement = con.prepareStatement(sql);
            ResultSet res = statement.executeQuery();

            while (res.next()) {
                Book entity = new Book();

                entity.setId(res.getInt("id"));
                entity.setName(res.getString("b.name"));
                entity.setLanguage(Language.values()[res.getInt("language")]);
                entity.setYear(res.getInt("year"));
                entity.setPublisher(res.getString("publisher"));

                Author author = new Author();

                author.setId((res.getInt("author_id")));
                author.setFirstName(res.getString("first_name"));
                author.setLastName(res.getString("last_name"));
                author.setCountry(res.getString("country"));
                entity.setAuthor(author);

                Section section = new Section();

                section.setId(res.getInt("section_id"));
                section.setName(res.getString("s.name"));
                section.setIsFiction(res.getBoolean("is_fiction"));
                entity.setSection(section);

                list.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public Book getBookById(int id){
        String sql = "SELECT b.id, b.name, b.language, b.year, b.publisher, b.author_id, a.first_name, a.last_name, a.country, b.section_id, s.name, s.is_fiction FROM book b LEFT JOIN author a ON b.author_id = a.id LEFT JOIN section s ON b.section_id = s.id where b.id = " + id;
        Book entity = new Book();

        try(Connection con = DriverManager.getConnection(url, username, password)) {
            var statement = con.prepareStatement(sql);
            ResultSet res = statement.executeQuery();

            res.next();
            //TODO: problema daca dai request pe cv ce nu exista, ar trebui sa pun niste erori cv vad eu
            entity.setId(id);
            entity.setName(res.getString("name"));
            entity.setLanguage(Language.values()[res.getInt("language")]);
            entity.setYear(res.getInt("year"));
            entity.setPublisher(res.getString("publisher"));

            Author author = new Author();

            author.setId((res.getInt("author_id")));
            author.setFirstName(res.getString("first_name"));
            author.setLastName(res.getString("last_name"));
            author.setCountry(res.getString("country"));
            entity.setAuthor(author);

            Section section = new Section();

            section.setId(res.getInt("section_id"));
            section.setName(res.getString("s.name"));
            section.setIsFiction(res.getBoolean("is_fiction"));
            entity.setSection(section);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

    public void addBook(Book entity){
        String sql = "insert into book values (NULL, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url, username, password)) {

            var statement = con.prepareStatement(sql);
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getAuthor().getId());
            statement.setInt(3, entity.getSection().getId());
            statement.setInt(4, entity.getLanguage().ordinal());
            statement.setInt(5, entity.getYear());
            statement.setString(6, entity.getPublisher());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBook(int id, Book entity){
        String sql = "update book set name = ?, author_id = ?, section_id = ?, language = ?, year = ?, publisher = ? where id = " + id;

        try (Connection con = DriverManager.getConnection(url, username, password)) {

            var statement = con.prepareStatement(sql);
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getAuthor().getId());
            statement.setInt(3, entity.getSection().getId());
            statement.setInt(4, entity.getLanguage().ordinal());
            statement.setInt(5, entity.getYear());
            statement.setString(6, entity.getPublisher());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(int id){
        String sql = "delete from book where id = " + id;

        try(Connection con = DriverManager.getConnection(url, username, password)) {

            var statement = con.prepareStatement(sql);
            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
