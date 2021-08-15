package xyz.defiancecoding.proxycheck.api.proxycheck.dashboard.results;

public class UsageResults {

    private int burstTokensAvailable;
    private int queriesToday;
    private int dailyLimit;
    private int queriesTotal;
    private String planTier;


    public UsageResults(){

    }

    public UsageResults newInstance(int burstTokensAvailable, int queriesToday, int dailyLimit, int queriesTotal, String planTier){
        UsageResults usageResults = new UsageResults();
        this.burstTokensAvailable = burstTokensAvailable;
        this.queriesToday = queriesToday;
        this.dailyLimit = dailyLimit;
        this.queriesTotal = queriesTotal;
        this.planTier = planTier;
        return usageResults;
    }


    public int getBurstTokensAvailable() {
        return burstTokensAvailable;
    }

    public void setBurstTokensAvailable(int burstTokensAvailable) {
        this.burstTokensAvailable = burstTokensAvailable;
    }

    public int getQueriesToday() {
        return queriesToday;
    }

    public void setQueriesToday(int queriesToday) {
        this.queriesToday = queriesToday;
    }

    public int getDailyLimit() {
        return dailyLimit;
    }

    public void setDailyLimit(int dailyLimit) {
        this.dailyLimit = dailyLimit;
    }

    public int getQueriesTotal() {
        return queriesTotal;
    }

    public void setQueriesTotal(int queriesTotal) {
        this.queriesTotal = queriesTotal;
    }

    public String getPlanTier() {
        return planTier;
    }

    public void setPlanTier(String planTier) {
        this.planTier = planTier;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UsageResults{");
        sb.append("burstTokensAvailable=").append(burstTokensAvailable);
        sb.append(", queriesToday=").append(queriesToday);
        sb.append(", dailyLimit=").append(dailyLimit);
        sb.append(", queriesTotal=").append(queriesTotal);
        sb.append(", planTier='").append(planTier).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
