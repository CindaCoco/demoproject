package com.example.demo.enums;

import com.example.demo.model.BedRule;

import java.util.HashMap;
import java.util.Map;

/**
 * @author linyida
 * @version 1.0
 * @created 2023/9/21.
 */
public enum MetaRuleType {
    BED(1, "床型规则", BedRule.class)
    ;

    private final Integer typeCode;
    private final String description;
    private final Class<?> ruleClass;

    private static final Map<Integer ,MetaRuleType> lookup = new HashMap<>();

    static{
        for (MetaRuleType value : MetaRuleType.values()) {
            lookup.put(value.getTypeCode(), value);
        }
    }

    public static MetaRuleType getByCode(Integer code){
        if(code == null){
            return null;
        }
        return lookup.get(code);
    }

    MetaRuleType(Integer typeCode, String description, Class<?> ruleClass) {
        this.typeCode = typeCode;
        this.description = description;
        this.ruleClass = ruleClass;
    }

    public Integer getTypeCode() {
        return typeCode;
    }

    public String getDescription() {
        return description;
    }

    public Class<?> getRuleClass() {
        return ruleClass;
    }
}
