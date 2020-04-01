package com.avelvi.service.impl;

import com.avelvi.service.CounterService;

import java.util.Arrays;

public class LinesCounterService implements CounterService {

    private static final String NEW_LINE_REGEX = "\\n";
    private static final String BLOCK_COMMENT_REGEX = "/\\*(\\n|.)*?\\*/";
    private static final String INLINE_COMMENT_REGEX = "//";

    @Override
    public long count(String text) {
        return Arrays.stream(text.replaceAll(BLOCK_COMMENT_REGEX, "").split(NEW_LINE_REGEX))
                .filter(
                        line -> {
                            String trimmedLine = line.trim();
                            return trimmedLine.length() > 0 && !trimmedLine.startsWith(INLINE_COMMENT_REGEX);
                        }
                ).count();
    }

}
