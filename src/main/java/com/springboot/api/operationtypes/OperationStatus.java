package com.springboot.api.operationtypes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OperationStatus {
    FAIL("fail"),
    SUCCESS("success");

    private final String status;
}
