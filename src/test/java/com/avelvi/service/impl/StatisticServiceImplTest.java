package com.avelvi.service.impl;

import com.avelvi.data.Data;
import com.avelvi.service.CounterService;
import com.avelvi.service.StatisticService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class StatisticServiceImplTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private StatisticService statisticService;

    private CounterService counterService;


    @Before
    public void setUp() {
        counterService = new LinesCounterService();
        statisticService = new StatisticServiceImpl(counterService);
    }

    @Test
    public void collectData() throws IOException {

        File folder = temporaryFolder.newFolder("folder");
        File file = new File(folder, "test.java");
        String text = "public class Hello {\n}";
        Files.write(file.toPath(), text.getBytes());

        Data folderResult = statisticService.collectData(folder.toPath(), 0).get();
        assertFalse(folder.isFile());
        assertEquals("folder", folderResult.getFileName());
        assertEquals(2, folderResult.getCount());
        assertEquals(0, folderResult.getLevel());
        assertEquals(1, folderResult.getChildren().size());

        Data child = folderResult.getChildren().get(0);
        assertTrue(child.isFile());
        assertEquals("test.java", child.getFileName());
        assertEquals(2, child.getCount());
        assertEquals(1, child.getLevel());
        assertEquals(0, child.getChildren().size());



    }
}
