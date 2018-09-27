package com.epam.htsa.entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "eventRating")
@XmlEnum
public enum EventRating {

    LOW,

    MID,

    HIGH;

    public String value() {
        return name();
    }

    public static EventRating fromValue(String v) {
        return valueOf(v);
    }


}
