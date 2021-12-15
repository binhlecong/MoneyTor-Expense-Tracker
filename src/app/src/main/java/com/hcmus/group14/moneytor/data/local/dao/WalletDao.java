package com.hcmus.group14.moneytor.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.hcmus.group14.moneytor.data.model.Wallet;

import java.util.List;

public interface WalletDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWallet(Wallet wallet);

    @Delete
    void deleteWallet(Wallet wallet);

    @Update
    void updateWallet(Wallet wallet);

    @Query("SELECT * FROM wallet_table")
    LiveData<List<Wallet>> getAllWallets();

    @Query("SELECT * FROM wallet_table WHERE provider LIKE :prov")
    Wallet[] getWalletByProvider(String prov);
}
