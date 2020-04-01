package com.avelvi.service;

import com.avelvi.data.Data;

import java.nio.file.Path;
import java.util.Optional;

public interface StatisticService {

    Optional<Data> collectData(Path path, int level);
}
