package com.taylor.sentinel.config;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class DefaultBlockHandler {

    public static String exHandler(BlockException e) {
        return "DefaultBlockHandler error";
    }

}
