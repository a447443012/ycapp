package com.ycapp.com.app.bean;

import java.util.List;

public class BetRecord {

    /**
     * code : success
     * data : {"getBettingList":[{"gameId":"JSK3","gameName":"江苏快3","id":1089815223,"playName":"和值","betAmount":1,"betAward":0,"status":1,"betContent":"小","createDate":1556160286000,"awardResult":"未开奖","winStatus":0,"periodEndDate":1556160430000,"isCancel":true,"periodNo":"20190425007","rebate":null,"betType":"digital","billNo":"BT3001490648391","todayBetAmount":null,"todayBetAward":null,"profit":null,"cancelBill":true,"statusStr":"等待开奖"},{"gameId":"JSK3","gameName":"江苏快3","id":1089712052,"playName":"和值","betAmount":1,"betAward":0,"status":0,"betContent":"大","createDate":1556157237000,"awardResult":"未开奖","winStatus":0,"periodEndDate":1556158030000,"isCancel":true,"periodNo":"20190425005","rebate":null,"betType":"digital","billNo":"BT3001490483278","todayBetAmount":null,"todayBetAward":null,"profit":null,"cancelBill":false,"statusStr":"已撤单"}]}
     * msg : null
     */

    private String code;
    private DataBean data;
    private Object msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public static class DataBean {
        private List<GetBettingListBean> getBettingList;

        public List<GetBettingListBean> getGetBettingList() {
            return getBettingList;
        }

        public void setGetBettingList(List<GetBettingListBean> getBettingList) {
            this.getBettingList = getBettingList;
        }

        public static class GetBettingListBean {
            /**
             * gameId : JSK3
             * gameName : 江苏快3
             * id : 1089815223
             * playName : 和值
             * betAmount : 1
             * betAward : 0
             * status : 1
             * betContent : 小
             * createDate : 1556160286000
             * awardResult : 未开奖
             * winStatus : 0
             * periodEndDate : 1556160430000
             * isCancel : true
             * periodNo : 20190425007
             * rebate : null
             * betType : digital
             * billNo : BT3001490648391
             * todayBetAmount : null
             * todayBetAward : null
             * profit : null
             * cancelBill : true
             * statusStr : 等待开奖
             */

            private String gameId;
            private String gameName;
            private int id;
            private String playName;
            private int betAmount;
            private double betAward;
            private int status;
            private String betContent;
            private long createDate;
            private String awardResult;
            private int winStatus;
            private long periodEndDate;
            private boolean isCancel;
            private String periodNo;
            private Object rebate;
            private String betType;
            private String billNo;
            private Object todayBetAmount;
            private Object todayBetAward;
            private Object profit;
            private boolean cancelBill;
            private String statusStr;

            public String getGameId() {
                return gameId;
            }

            public void setGameId(String gameId) {
                this.gameId = gameId;
            }

            public String getGameName() {
                return gameName;
            }

            public void setGameName(String gameName) {
                this.gameName = gameName;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPlayName() {
                return playName;
            }

            public void setPlayName(String playName) {
                this.playName = playName;
            }

            public int getBetAmount() {
                return betAmount;
            }

            public void setBetAmount(int betAmount) {
                this.betAmount = betAmount;
            }

            public double getBetAward() {
                return betAward;
            }



            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getBetContent() {
                return betContent;
            }

            public void setBetContent(String betContent) {
                this.betContent = betContent;
            }

            public long getCreateDate() {
                return createDate;
            }

            public void setCreateDate(long createDate) {
                this.createDate = createDate;
            }

            public String getAwardResult() {
                return awardResult;
            }

            public void setAwardResult(String awardResult) {
                this.awardResult = awardResult;
            }

            public int getWinStatus() {
                return winStatus;
            }

            public void setWinStatus(int winStatus) {
                this.winStatus = winStatus;
            }

            public long getPeriodEndDate() {
                return periodEndDate;
            }

            public void setPeriodEndDate(long periodEndDate) {
                this.periodEndDate = periodEndDate;
            }

            public boolean isIsCancel() {
                return isCancel;
            }

            public void setIsCancel(boolean isCancel) {
                this.isCancel = isCancel;
            }

            public String getPeriodNo() {
                return periodNo;
            }

            public void setPeriodNo(String periodNo) {
                this.periodNo = periodNo;
            }

            public Object getRebate() {
                return rebate;
            }

            public void setRebate(Object rebate) {
                this.rebate = rebate;
            }

            public String getBetType() {
                return betType;
            }

            public void setBetType(String betType) {
                this.betType = betType;
            }

            public String getBillNo() {
                return billNo;
            }

            public void setBillNo(String billNo) {
                this.billNo = billNo;
            }

            public Object getTodayBetAmount() {
                return todayBetAmount;
            }

            public void setTodayBetAmount(Object todayBetAmount) {
                this.todayBetAmount = todayBetAmount;
            }

            public Object getTodayBetAward() {
                return todayBetAward;
            }

            public void setTodayBetAward(Object todayBetAward) {
                this.todayBetAward = todayBetAward;
            }

            public Object getProfit() {
                return profit;
            }

            public void setProfit(Object profit) {
                this.profit = profit;
            }

            public boolean isCancelBill() {
                return cancelBill;
            }

            public void setCancelBill(boolean cancelBill) {
                this.cancelBill = cancelBill;
            }

            public String getStatusStr() {
                return statusStr;
            }

            public void setStatusStr(String statusStr) {
                this.statusStr = statusStr;
            }
        }
    }
}
