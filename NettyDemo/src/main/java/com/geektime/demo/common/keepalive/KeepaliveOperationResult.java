package com.geektime.demo.common.keepalive;

import com.geektime.demo.common.OperationResult;
import lombok.Data;

@Data
public class KeepaliveOperationResult extends OperationResult {

    private final long time;

}