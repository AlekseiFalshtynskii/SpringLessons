package ru.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class ModerationResult {

    private boolean allowed;

    private RequestPublish rp;
}
