package com.vhbeltramini.dronezeta.model.enums;

public enum TravelStatus {
    SENT {
        @Override
        public boolean isTreveling() {
            return true;
        }
    },
    DELIVERED {
        @Override
        public boolean isDelivered() {
            return true;
        }
    };

    private String description;
    private Integer statusID;

    public boolean isTreveling() {
        return false;
    }

    public boolean isDelivered() {
        return false;
    }


    public String getDescription() {
        return description;
    }

    public Integer getStatusID() {
        return statusID;
    }

    TravelStatus() {
    }
}
