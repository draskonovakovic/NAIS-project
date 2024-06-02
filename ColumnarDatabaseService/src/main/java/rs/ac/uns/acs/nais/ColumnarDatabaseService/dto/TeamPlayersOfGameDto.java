package rs.ac.uns.acs.nais.ColumnarDatabaseService.dto;

public class TeamPlayersOfGameDto {

    private String name;
    private String country;
    private String city;
    private int countPlayerOfGame;

    public TeamPlayersOfGameDto(String name, String country, String city, int playersOfGameCount) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.countPlayerOfGame = playersOfGameCount;
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

    public int getCountPlayerOfGame() {
        return countPlayerOfGame;
    }

    public void setCountPlayerOfGame(int countPlayerOfGame) {
        this.countPlayerOfGame = countPlayerOfGame;
    }
}
