package tgbot.dto.mars;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class MarsPhotoResponse {
    @JsonProperty("photos")
    private final List<MarsPhoto> photos;

    public MarsPhotoResponse(@JsonProperty("photos") List<MarsPhoto> photos) {
        this.photos = photos;
    }

    public List<MarsPhoto> getPhotos() {
        return photos;
    }
}
