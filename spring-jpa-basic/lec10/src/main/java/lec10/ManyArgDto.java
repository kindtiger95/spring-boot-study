package lec10;

public class ManyArgDto {
    private String username;
    private String city;

    public ManyArgDto(String username, String city) {
        this.username = username;
        this.city = city;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
