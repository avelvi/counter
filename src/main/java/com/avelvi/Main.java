package com.avelvi;

import com.avelvi.data.Data;
import com.avelvi.service.StatisticService;
import com.avelvi.service.impl.LinesCounterService;
import com.avelvi.service.impl.StatisticServiceImpl;

import java.io.File;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        validateInput(args);
        StatisticService statisticService = new StatisticServiceImpl(new LinesCounterService());

        Optional<Data> result = statisticService.collectData(new File(args[0]).toPath(), 0);
        result.ifPresent(Data::displayData);

    }

    private static void validateInput(String[] args) {
        if (args.length != 1) {
            System.out.println("Wrong number of args. Required path to '*.java' file or directory");
            System.exit(1);
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            System.out.println(String.format("File [%s] not found", file.getAbsolutePath()));
            System.exit(1);
        }
        if (file.isFile() && !file.getPath().endsWith(".java")) {
            System.out.println("Please provide path to '*.java' file or directory");
            System.exit(1);
        }

    }
}
