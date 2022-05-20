package com.example.library.repository;

import com.example.library.model.Reader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReaderRepository {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    public List<Reader> getReaders() {
        String sql = "select * from reader";
        List<Reader> list = new ArrayList<Reader>();

        try (Connection con = DriverManager.getConnection(url, username, password)) {
            var statement = con.prepareStatement(sql);
            ResultSet res = statement.executeQuery();

            while (res.next()) {
                Reader entity = new Reader();

                entity.setId(res.getInt("id"));
                entity.setFirstName(res.getString("first_name"));
                entity.setLastName(res.getString("last_name"));
                entity.setEmail(res.getString("email"));
                entity.setTelephoneNumber(res.getString("telephone_number"));

                list.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public Reader getReaderById(int id){
        String sql = "select * from reader where id = " + id;
        Reader entity = new Reader();

        try(Connection con = DriverManager.getConnection(url, username, password)) {
            var statement = con.prepareStatement(sql);
            ResultSet res = statement.executeQuery();

            res.next();
            //TODO: problema daca dai request pe cv ce nu exista, ar trebui sa pun niste erori cv vad eu
            entity.setId(id);
            entity.setFirstName(res.getString("first_name"));
            entity.setLastName(res.getString("last_name"));
            entity.setEmail(res.getString("email"));
            entity.setTelephoneNumber(res.getString("telephone_number"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

    public void addReader(Reader entity){
        String sql = "insert into reader values (NULL, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url, username, password)) {

            var statement = con.prepareStatement(sql);
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setString(3, entity.getEmail());
            statement.setString(4, entity.getTelephoneNumber());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateReader(int id, Reader entity){
        String sql = "update reader set first_name = ?, last_name = ?, email = ?, telephone_number = ? where id = " + id;

        try (Connection con = DriverManager.getConnection(url, username, password)) {

            var statement = con.prepareStatement(sql);
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setString(3, entity.getEmail());
            statement.setString(4, entity.getTelephoneNumber());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteReader(int id){
        String sql = "delete from reader where id = " + id;

        try(Connection con = DriverManager.getConnection(url, username, password)) {

            var statement = con.prepareStatement(sql);
            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
