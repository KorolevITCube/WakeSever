package com.korolev.wake.repository;

import com.korolev.wake.database.DataBaseConnector;
import com.korolev.wake.model.MonthTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Component
@Slf4j
public class MonthRepository implements IRepositoryMonth<MonthTrace,Integer>{

    private final String findById = "select * from month_trace where id = ?";
    private final String findByMonthAndYear = "select * from month_trace where yyyy = ? and mm = ?";
    private final String updateById = "update month_trace set mask = ? where id = ?";
    private final String insert = "insert into month_trace (yyyy,mm,mask,maxdays,startwd) values (?,?,?,?,?) returning id";
    private final String findAll = "select * from month_trace";

    @Autowired
    private DataBaseConnector connector;

    @Override
    public ArrayList<MonthTrace> findAll() {
        ArrayList<MonthTrace> month = new ArrayList<>();
        Connection connect = null;
        try{
            connect = connector.getConnection();
            PreparedStatement prepStatement = connect.prepareStatement(findAll);
            ResultSet result = prepStatement.executeQuery();
            while(result.next()){
                MonthTrace curMonth = new MonthTrace();
                curMonth.setId(result.getInt("ID"));
                curMonth.setMonth(result.getString("MM"));
                curMonth.setYear(result.getString("YYYY"));
                curMonth.setMask(result.getString("MASK"));
                curMonth.setMaxDays(result.getInt("MAXDAYS"));
                curMonth.setStartWeekDay(result.getInt("STARTWD"));
                month.add(curMonth);
            }
        }catch(Exception e){
            log.error(e.getMessage());
        }finally{
            connector.closeConnection(connect);
        }
        return month;
    }

    @Override
    public MonthTrace getById(Integer id) {
        MonthTrace month = null;
        Connection connect = null;
        try{
            connect = connector.getConnection();
            PreparedStatement prepStatement = connect.prepareStatement(findById);
            prepStatement.setInt(1,id);
            ResultSet result = prepStatement.executeQuery();
            while(result.next()){
                month = new MonthTrace();
                month.setId(result.getInt("ID"));
                month.setMonth(result.getString("MM"));
                month.setYear(result.getString("YYYY"));
                month.setMask(result.getString("MASK"));
                month.setMaxDays(result.getInt("MAXDAYS"));
                month.setStartWeekDay(result.getInt("STARTWD"));
            }
        }catch(Exception e){
            log.error(e.getMessage());
        }finally{
            connector.closeConnection(connect);
        }
        return month;
    }

    @Override
    public void delete(MonthTrace monthTrace) {

    }

    @Override
    public Integer save(MonthTrace monthTrace) {
        Connection connect = null;
        Integer id = null;
        try{
            connect = connector.getConnection();
            PreparedStatement prepStatement = connect.prepareStatement(insert);
            prepStatement.setString(1,monthTrace.getYear());
            prepStatement.setString(2,monthTrace.getMonth());
            prepStatement.setString(3,monthTrace.getMask());
            prepStatement.setInt(4,monthTrace.getMaxDays());
            prepStatement.setInt(5,monthTrace.getStartWeekDay());
            ResultSet result = prepStatement.executeQuery();
            result.next();
            id = result.getInt("ID");
            log.info("Inserted ");
        }catch(Exception e){
            log.error(e.getMessage());
            id = -1;
        }finally{
            connector.closeConnection(connect);
        }
        return id;
    }

    @Override
    public void update(MonthTrace monthTrace) {
        Connection connect = null;
        try{
            connect = connector.getConnection();
            PreparedStatement prepStatement = connect.prepareStatement(updateById);
            prepStatement.setString(1,monthTrace.getMask());
            prepStatement.setInt(2,monthTrace.getId());
            Integer count = prepStatement.executeUpdate();
            log.info("Updated rows: " + count + " rows id: " + monthTrace.getId());
        }catch(Exception e){
            log.error(e.getMessage());
        }finally{
            connector.closeConnection(connect);
        }
    }

    @Override
    public MonthTrace getByMonthYear(String month, String year) {
        MonthTrace curMonth = null;
        Connection connect = null;
        try{
            connect = connector.getConnection();
            PreparedStatement prepStatement = connect.prepareStatement(findByMonthAndYear);
            prepStatement.setString(1,year);
            prepStatement.setString(2,month);
            ResultSet result = prepStatement.executeQuery();
            while(result.next()){
                curMonth = new MonthTrace();
                curMonth.setId(result.getInt("ID"));
                curMonth.setMonth(result.getString("MM"));
                curMonth.setYear(result.getString("YYYY"));
                curMonth.setMask(result.getString("MASK"));
                curMonth.setMaxDays(result.getInt("MAXDAYS"));
                curMonth.setStartWeekDay(result.getInt("STARTWD"));
            }
        }catch(Exception e){
            log.error(e.getMessage());
        }finally{
            connector.closeConnection(connect);
        }
        return curMonth;
    }
}
