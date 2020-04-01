package com.avelvi.service.impl;

import com.avelvi.service.CounterService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinesCounterServiceTest {

    private CounterService counterService;

    @Before
    public void setUp() {
        counterService = new LinesCounterService();
    }

    @Test
    public void countTest() {
        String text1 = "// This file contains 4 lines of code\n" +
                "\n" +
                "public interface Dave {\n" +
                "    /**\n" +
                "     * count the number of lines in a file\n" +
                "     */\n" +
                "     int countLines(File inFile); // not the real signature!\n" +
                "}";

        String text2 = "    /*****\n" +
                "    * This is a test program with 5 lines of code\n" +
                "    *  \\/* no nesting allowed!\n" +
                "    //*****//***/// Slightly pathological comment ending...\n" +
                "\n" +
                "public class Hello {\n" +
                "    public static final void main(String [] args) { // gotta love Java\n" +
                "        // Say hello\n" +
                "        System./*wait*/out./*for*/println/*it*/(\"Hello/*\");\n" +
                "    }\n" +
                "\n" +
                "}";

        assertEquals(3, counterService.count(text1));
        assertEquals(5, counterService.count(text2));
    }
}
