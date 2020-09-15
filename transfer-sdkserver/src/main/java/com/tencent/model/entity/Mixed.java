package com.tencent.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class Mixed implements Serializable {
    List<Map<String ,String>> item;
}
