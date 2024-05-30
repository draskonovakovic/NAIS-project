package rs.ac.uns.acs.nais.ColumnarDatabaseService.entity;

public class MatchesGroupByCity {
    private String city;
    private int count;

    public MatchesGroupByCity(String city, int count) {
        this.city = city;
        this.count = count;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
