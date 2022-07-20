package alkemy.challenge.disney_api_rest.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class MovieDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String image;

    @NotNull
    @Size(max = 255)
    private String title;

    @NotNull
    private Long gender;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(final String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public Long getGender() {
        return gender;
    }

    public void setGender(final Long gender) {
        this.gender = gender;
    }

}
