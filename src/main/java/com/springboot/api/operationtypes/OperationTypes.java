package com.springboot.api.operationtypes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OperationTypes {
    OPERATION_ADD("add"),
    OPERATION_EDIT("edit"),
    OPERATION_DELETE("delete");

    private final String operation;
}
