package com.avelvi.service.impl;

import com.avelvi.data.Data;
import com.avelvi.service.CounterService;
import com.avelvi.service.StatisticService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class StatisticServiceImpl implements StatisticService {

    private CounterService counterService;

    public StatisticServiceImpl(CounterService counterService) {
        this.counterService = counterService;
    }

    @Override
    public Optional<Data> collectData(Path path, int level) {
        Data result = null;
        List<Data> children = new LinkedList<>();

        if (Files.isRegularFile(path) && path.toString().endsWith(".java")) {
            String text = "";
            try {
                text = new String(Files.readAllBytes(path));
            } catch (IOException e) {
                System.out.println("Error: Something went wrong with this file - " + path);
            }
            result = new Data(path.getFileName().toString(), counterService.count(text), level, true);
        }

        if (Files.isDirectory(path)) {
            try {
                result = new Data(path.getFileName().toString(), 0, level, false);
                Files
                        .list(path)
                        .filter(p -> Files.isDirectory(p) || p.toString().endsWith(".java"))
                        .forEach(p -> {
                            Optional<Data> child = collectData(p, level + 1);
                            child.ifPresent(children::add);
                        });
            } catch (IOException e) {
                System.out.println("Error: Something went wrong with this file - " + path);
            }
        }

        if (Objects.nonNull(result)) {
            result.setChildren(children);
            if(!result.isFile()) {
                result.setCount(children.stream().mapToLong(Data::getCount).sum());
            }
        }
        return Optional.ofNullable(result);
    }


}
