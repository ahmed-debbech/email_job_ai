package com.debbech.emailai.logic;

import com.debbech.emailai.model.WriteRequest;

public interface IQueueProcessor {

    void add(WriteRequest writeRequest);
    void process();

}
