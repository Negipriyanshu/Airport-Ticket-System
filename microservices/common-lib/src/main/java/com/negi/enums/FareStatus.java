package com.negi.enums;

public enum FareStatus {
    ACTIVE, //(available)
    EXPIRED, //(status end)
    DISABLED, //(active, but currently unavailable)
    INACTIVE //(soft-delete)
}
