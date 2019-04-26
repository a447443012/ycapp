package com.ycapp.com.app.http.requestparam;

import java.util.List;

public class BetParam {

    /**
     * accountId : 550845877
     * clientTime : 1556110627675
     * gameId : JSK3
     * issue : 20190424038
     * item : ["{\"methodid\":\"K3002001001\",\"nums\":1,\"rebate\":\"0.00\",\"times\":1,\"money\":1,\"playId\":[\"K3002001010\"],\"mode\":1,\"issueNo\":\"20190424038\",\"codes\":\"大\"}"]
     */

    private int accountId;
    private long clientTime;
    private String gameId;
    private String issue;
    private List<String> item;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public long getClientTime() {
        return clientTime;
    }

    public void setClientTime(long clientTime) {
        this.clientTime = clientTime;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public List<String> getItem() {
        return item;
    }

    public void setItem(List<String> item) {
        this.item = item;
    }
}
