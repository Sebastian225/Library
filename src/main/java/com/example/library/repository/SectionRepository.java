package com.example.library.repository;

import com.example.library.enums.Language;
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
public class SectionRepository {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    public List<Section> getSections() {
        String sql = "select * from section";
        List<Section> list = new ArrayList<Section>();

        try (Connection con = DriverManager.getConnection(url, username, password)) {
            var statement = con.prepareStatement(sql);
            ResultSet res = statement.executeQuery();

            while (res.next()) {
                Section entity = new Section();

                entity.setId(res.getInt("id"));
                entity.setName(res.getString("name"));
                entity.setIsFiction(res.getBoolean("is_fiction"));

                ArrayList<Book> books = new ArrayList<>();

                String booksBySectionSql = "select * from book where section_id = " + res.getInt("id");

                try (Connection bookCon = DriverManager.getConnection(url, username, password)) {
                    var bookStatement = bookCon.prepareStatement(booksBySectionSql);
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

    public Section getSectionById(int id){
        String sql = "select * from section where id = " + id;
        Section entity = new Section();

        try(Connection con = DriverManager.getConnection(url, username, password)) {
            var statement = con.prepareStatement(sql);
            ResultSet res = statement.executeQuery();

            res.next();

            entity.setId(id);
            entity.setName(res.getString("name"));
            entity.setIsFiction(res.getBoolean("is_fiction"));

            ArrayList<Book> books = new ArrayList<>();

            String booksBySectionSql = "select * from book where section_id = " + res.getInt("id");

            try (Connection bookCon = DriverManager.getConnection(url, username, password)) {
                var bookStatement = bookCon.prepareStatement(booksBySectionSql);
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

    public void addSection(Section entity){
        String sql = "insert into section values (NULL, ?, ?)";

        try (Connection con = DriverManager.getConnection(url, username, password)) {

            var statement = con.prepareStatement(sql);
            statement.setString(1, entity.getName());
            statement.setBoolean(2, entity.getIsFiction());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSection(int id, Section entity){
        String sql = "update section set name = ?, is_fiction = ? where id = " + id;

        try (Connection con = DriverManager.getConnection(url, username, password)) {

            var statement = con.prepareStatement(sql);
            statement.setString(1, entity.getName());
            statement.setBoolean(2, entity.getIsFiction());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSection(int id){
        String sql = "delete from section where id = " + id;

        try(Connection con = DriverManager.getConnection(url, username, password)) {

            var statement = con.prepareStatement(sql);
            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
