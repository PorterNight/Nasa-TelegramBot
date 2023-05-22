package tgbot.dto.mars;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MarsPhoto {

    @JsonProperty("id")
    private final int id;

    @JsonProperty("sol")
    private final int sol;

    @JsonProperty("camera")
    private final Camera camera;

    @JsonProperty("img_src")
    private final String imgSrc;

    @JsonProperty("earth_date")
    private final String earthDate;

    @JsonProperty("rover")
    private final Rover rover;

    public MarsPhoto(
        @JsonProperty("id") int id,
        @JsonProperty("sol") int sol,
        @JsonProperty("camera") Camera camera,
        @JsonProperty("img_src") String imgSrc,
        @JsonProperty("earth_date") String earthDate,
        @JsonProperty("rover") Rover rover
    ) {
        this.id = id;
        this.sol = sol;
        this.camera = camera;
        this.imgSrc = imgSrc;
        this.earthDate = earthDate;
        this.rover = rover;
    }

    public int getId() {
        return id;
    }

    public int getSol() {
        return sol;
    }

    public Camera getCamera() {
        return camera;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public String getEarthDate() {
        return earthDate;
    }

    public Rover getRover() {
        return rover;
    }

    @Override public String toString() {
        return "MarsPhoto{" +
            "id=" + id +
            ", sol=" + sol +
            ", camera=" + camera +
            ", imgSrc='" + imgSrc + '\'' +
            ", earthDate='" + earthDate + '\'' +
            ", rover=" + rover +
            '}';
    }
}
