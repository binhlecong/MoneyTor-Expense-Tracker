package com.hcmus.group14.moneytor.data.local.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Insert;
import androidx.lifecycle.LiveData;
import androidx.room.Update;
import java.util.List;
import com.hcmus.group14.moneytor.data.model.SpendGoal;
import com.hcmus.group14.moneytor.data.model.Spending;

import androidx.room.Delete;

@Dao
public interface SpendGoalDao {
    //Data manipulation queries
    @Insert
    void insert(SpendGoal spendGoal);
    @Update
    void update(SpendGoal... spendGoals);
    @Query("delete from spend_goal_table")
    void deleteAllSpendGoals();
    @Query("delete from spend_goal_table where goalID = :id")
    void deleteSpendGoal(int id);
    @Delete
    void deleteSpendGoal(SpendGoal spendGoal);


    //Total data retrieval
    @Query("select * from spend_goal_table order by date desc")
    LiveData<List<SpendGoal>> getAllSpendGoals();
    @Query("select * from spend_goal_table where goalID = :id LIMIT 1")
    LiveData<List<SpendGoal>> getSpendGoalByID(int id);

    //filter
    @Query("SELECT * FROM spend_goal_table WHERE (date BETWEEN :startDate AND :endDate) AND (category IN (:cats))")
    LiveData<List<SpendGoal>> filterByCategoryAndTime(List<String> cats, long startDate, long endDate);
    @Query("SELECT * FROM spend_goal_table WHERE category IN (:cats)")
    LiveData<List<SpendGoal>> filterByCategories(List<String> cats);
    @Query("SELECT * FROM spend_goal_table WHERE date BETWEEN :startDate AND :endDate")
    LiveData<List<SpendGoal>> filterByTime(long startDate, long endDate);

}