package edu.school21.students.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Mark {

    FAIL(2),
    ACCEPTABLE(3),
    GOOD(4),
    EXCELLENT(5);

    private final int value;

    public static Mark getNameByValue(int value) {
        for (Mark mark : values()) {
            if (mark.value == value) {
                return mark;
            }
        }
        return null;
    }
}