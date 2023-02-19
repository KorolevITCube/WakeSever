package com.korolev.wake.repository;

import com.korolev.wake.database.DataBaseConnector;
import com.korolev.wake.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Service("userDAO")
@Slf4j
public class UserRepository implements IRepository<User,Integer>{

    private final String findAllQuery = "select * from users";
    private final String selectWhereLogin = "select * from users where login = ?";

    @Autowired
    private DataBaseConnector connector;

    @Override
    public ArrayList<User> findAll() {
        ArrayList<User> users = new ArrayList<>();
        try {
            Connection connect = connector.getConnection();
            PreparedStatement prepStatement = connect.prepareStatement(findAllQuery);
            ResultSet result = prepStatement.executeQuery();
            while(result.next()){
                User user = new User();
                user.setLogin(result.getString("login"));
                user.setPassword(result.getString("password"));
                user.setEmail(result.getString("email"));
                users.add(user);
            }
        }catch(Exception e){
            log.error(e.getMessage());
        }
        return users;
    }

    @Override
    public User getById(Integer id) {
        return null;
    }

    public User getByLogin(String login) {
        User user = null;
        try {
            Connection connect = connector.getConnection();
            PreparedStatement prepStatement = connect.prepareStatement(selectWhereLogin);
            prepStatement.setString(1,login);
            ResultSet result = prepStatement.executeQuery();
            while (result.next()) {
                user = new User();
                user.setLogin(result.getString("login"));
                user.setPassword(result.getString("password"));
                user.setEmail(result.getString("email"));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return user;
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void save(User user) {

    }

    @Override
    public void update(User user) {

    }
}
