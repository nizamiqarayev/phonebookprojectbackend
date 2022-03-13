package com.springboot.api.response;

import com.springboot.api.operationtypes.OperationStatus;
import com.springboot.api.operationtypes.OperationTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto {
    private UUID user_id;
    private OperationTypes operation_type;
    private OperationStatus operation_status;
}