package rs.ac.uns.acs.nais.ColumnarDatabaseService.dto;

public class WinnerStatsDto {

    private String name;
    private String country;
    private String city;
    private double avgScore;
    private double avgRebounds;
    private double avgTurnovers;

    public WinnerStatsDto(String name, String country, String city, double avgScore, double avgRebounds, double avgTurnovers) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.avgScore = avgScore;
        this.avgRebounds = avgRebounds;
        this.avgTurnovers = avgTurnovers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
