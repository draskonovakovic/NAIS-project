package rs.ac.uns.acs.nais.ColumnarDatabaseService.entity;

public class WinnerStats {
    private long winner_id;
    private double avgScore;
    private double avgRebounds;
    private double avgTurnovers;

    public WinnerStats(long winner_id, double avgScore, double avgRebounds, double avgTurnovers) {
        this.winner_id = winner_id;
        this.avgScore = avgScore;
        this.avgRebounds = avgRebounds;
        this.avgTurnovers = avgTurnovers;
    }

    public long getWinner_id() {
        return winner_id;
    }

    public void setWinner_id(long winner_id) {
        this.winner_id = winner_id;
    }

    public double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(double avgScore) {
        this.avgScore = avgScore;
    }

    public double getAvgRebounds() {
        return avgRebounds;
    }

    public void setAvgRebounds(double avgRebounds) {
        this.avgRebounds = avgRebounds;
    }

    public double getAvgTurnovers() {
        return avgTurnovers;
    }

    public void setAvgTurnovers(double avgTurnovers) {
        this.avgTurnovers = avgTurnovers;
    }
}
