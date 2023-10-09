package com.example.demo.model;

import lombok.Data;

import java.util.Map;

/**
 * @author linyida
 * @version 1.0
 * @created 2023/9/21.
 */
@Data
public class BedRule {
    private Integer canCheckInGuests;
    private Map<Integer, String> bedSizes;
}
