package ru.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@ToString
public class Topic {

    private Long id;

    private List<String> tags;

    private String text;
}
