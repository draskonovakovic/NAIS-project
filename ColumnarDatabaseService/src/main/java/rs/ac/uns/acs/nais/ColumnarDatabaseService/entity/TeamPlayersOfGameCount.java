package rs.ac.uns.acs.nais.ColumnarDatabaseService.entity;

public class TeamPlayersOfGameCount {
    private long team_id;
    private int count;

    public TeamPlayersOfGameCount(long team_id, int count) {
        this.team_id = team_id;
        this.count = count;
    }

    public long getTeam_id() {
        return team_id;
    }

    public void setTeam_id(long team_id) {
        this.team_id = team_id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
