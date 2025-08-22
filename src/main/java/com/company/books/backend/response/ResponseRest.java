package com.company.books.backend.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResponseRest {

    private final List<HashMap<String, String>> metadata = new ArrayList<>();

    public List<HashMap<String, String>> getMetadata() {
        return metadata;
    }

    /**
     * Adds a new metadata entry with automatic timestamp.
     *
     * @param type    Type of the response ("OK", "ERROR", etc.)
     * @param code    Custom code ("00", "-1", etc.)
     * @param message Message describing the response
     */
    public void addMetadata(String type, String code, String message) {
        HashMap<String, String> map = new HashMap<>();
        map.put("type", type);
        map.put("code", code);
        map.put("message", message);
        map.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        this.metadata.add(map);
    }
}
