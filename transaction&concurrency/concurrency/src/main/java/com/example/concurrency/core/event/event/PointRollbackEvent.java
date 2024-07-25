package com.example.concurrency.core.event.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PointRollbackEvent {

    private final Long point;

    private final String userId;
}
