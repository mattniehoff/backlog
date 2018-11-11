package com.mattniehoff.backlog.database;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.mattniehoff.backlog.model.database.AppDatabase;
import com.mattniehoff.backlog.model.database.GameDao;
import com.mattniehoff.backlog.model.database.GameEntry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

// https://developer.android.com/training/data-storage/room/testing-db
@RunWith(AndroidJUnit4.class)
public class SimpleEntityReadWriteTest {
    private GameDao gameEntryDao;
    private AppDatabase mDb;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getContext();
        mDb = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).allowMainThreadQueries().build();
        gameEntryDao = mDb.gameDao();
    }

    @After
    public void closeDb() throws IOException {
        mDb.close();
    }

    @Test
    public void writeGameEntryAndRead() throws Exception {
        GameEntry gameEntry = new GameEntry();
        gameEntry.setId(1);
        gameEntry.setName("Test Name");
        gameEntry.setDateAdded(new Date());
        gameEntryDao.insertGame(gameEntry);
        assertThat(gameEntry.getId(), equalTo(1));

        assertEquals(1, LiveDataTestUtil.getValue(gameEntryDao.getAllGames()).size());
        GameEntry dbEntry = gameEntryDao.getGameById(1).getValue();
        assertEquals(gameEntry.getId(), LiveDataTestUtil.getValue(gameEntryDao.getGameById(1)).getId());
    }
}
