package com.example.salonmanage.DTO;

public class ChartRequest {
        private String dateStart;
    private String dateEnd;
    private int type;

    public ChartRequest() {
    }

    public ChartRequest(String dateStart, String dateEnd, int type) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.type = type;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
