package tgbot.dto.mars;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rover {
    @JsonProperty("id")
    private final int id;

    @JsonProperty("name")
    private final String name;

    @JsonProperty("landing_date")
    private final String landingDate;

    @JsonProperty("launch_date")
    private final String launchDate;

    @JsonProperty("status")
    private final String status;

    public Rover(@JsonProperty("id") int id,
        @JsonProperty("name") String name,
        @JsonProperty("landing_date") String landingDate,
        @JsonProperty("launch_date") String launchDate,
        @JsonProperty("status") String status) {
        this.id = id;
        this.name = name;
        this.landingDate = landingDate;
        this.launchDate = launchDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLandingDate() {
        return landingDate;
    }

    public String getLaunchDate() {
        return launchDate;
    }

    public String getStatus() {
        return status;
    }

    @Override public String toString() {
        return "Rover{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", landingDate='" + landingDate + '\'' +
            ", launchDate='" + launchDate + '\'' +
            ", status='" + status + '\'' +
            '}';
    }
}
