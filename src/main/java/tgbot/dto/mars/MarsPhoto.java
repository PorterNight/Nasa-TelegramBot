package tgbot.dto.mars;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MarsPhoto {

    @JsonProperty("id")
    private final int id;

    @JsonProperty("sol")
    private final int sol;

    @JsonProperty("img_src")
    private final String imgSrc;

    @JsonProperty("earth_date")
    private final String earthDate;

    public MarsPhoto(
        @JsonProperty("id") int id,
        @JsonProperty("sol") int sol,
        @JsonProperty("img_src") String imgSrc,
        @JsonProperty("earth_date") String earthDate
    ) {
        this.id = id;
        this.sol = sol;
        this.imgSrc = imgSrc;
        this.earthDate = earthDate;
    }

    public int getId() {
        return id;
    }

    public int getSol() {
        return sol;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public String getEarthDate() {
        return earthDate;
    }


    @Override public String toString() {
        return "MarsPhoto{" +
            "id=" + id +
            ", sol=" + sol +
            ", imgSrc='" + imgSrc + '\'' +
            ", earthDate='" + earthDate + '\'' +
            '}';
    }
}
