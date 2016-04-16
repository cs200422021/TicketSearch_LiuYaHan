package com.liuyahan.ticketsearch_liuyahan.entity;

import java.util.List;

public class SearchEntity {
    private boolean ret;
    private dataEntity data;

    public boolean isRet() {
        return ret;
    }

    public void setRet(boolean ret) {
        this.ret = ret;
    }

    public dataEntity getData() {
        return data;
    }

    public void setData(dataEntity data) {
        this.data = data;
    }

    public class dataEntity {
        private List<trainListEntity> trainList;

        public List<trainListEntity> getTrainList() {
            return trainList;
        }

        public void setTrainList(List<trainListEntity> trainList) {
            this.trainList = trainList;
        }

        public class trainListEntity {
            private String trainType;
            private String trainNo;
            private String from;
            private String to;
            private String startTime;
            private String endTime;
            private String duration;
            private List<SeatInfosEntity> seatInfos;

            public String getTrainType() {
                return trainType;
            }

            public void setTrainType(String trainType) {
                this.trainType = trainType;
            }

            public String getTrainNo() {
                return trainNo;
            }

            public void setTrainNo(String trainNo) {
                this.trainNo = trainNo;
            }

            public String getFrom() {
                return from;
            }

            public void setFrom(String from) {
                this.from = from;
            }

            public String getTo() {
                return to;
            }

            public void setTo(String to) {
                this.to = to;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public List<SeatInfosEntity> getSeatInfos() {
                return seatInfos;
            }

            public void setSeatInfos(List<SeatInfosEntity> seatInfos) {
                this.seatInfos = seatInfos;
            }

            public class SeatInfosEntity {
                private String seat;
                private String seatPrice;
                private int remainNum;

                public int getRemainNum() {
                    return remainNum;
                }

                public void setRemainNum(int remainNum) {
                    this.remainNum = remainNum;
                }

                public String getSeat() {
                    return seat;
                }

                public void setSeat(String seat) {
                    this.seat = seat;
                }

                public String getSeatPrice() {
                    return seatPrice;
                }

                public void setSeatPrice(String seatPrice) {
                    this.seatPrice = seatPrice;
                }
            }
        }
    }
}
