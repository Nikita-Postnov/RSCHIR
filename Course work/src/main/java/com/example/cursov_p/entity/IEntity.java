package com.example.cursov_p.entity;

import java.io.Serializable;
import java.util.Map;

public interface IEntity extends Serializable {
    Integer getId();

    Map<String, Object> toMap();
}
