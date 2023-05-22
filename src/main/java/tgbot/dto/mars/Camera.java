package tgbot.dto.mars;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Camera {
    @JsonProperty("id")
    private final int id;

    @JsonProperty("name")
    private final String name;

    @JsonProperty("rover_id")
    private final int roverId;

    @JsonProperty("full_name")
    private final String fullName;

    public Camera(@JsonProperty("id") int id,
        @JsonProperty("name") String name,
        @JsonProperty("rover_id") int roverId,
        @JsonProperty("full_name") String fullName) {
        this.id = id;
        this.name = name;
        this.roverId = roverId;
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRoverId() {
        return roverId;
    }

    public String getFullName() {
        return fullName;
    }

    @Override public String toString() {
        return "Camera{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", roverId=" + roverId +
            ", fullName='" + fullName + '\'' +
            '}';
    }
}
