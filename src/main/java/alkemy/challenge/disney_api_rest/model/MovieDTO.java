package alkemy.challenge.disney_api_rest.model;

import java.time.OffsetDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonView;

import alkemy.challenge.disney_api_rest.view.View;

public class MovieDTO {

    private Long id;

    @JsonView(View.UserView.External.class)
    @NotNull
    @Size(max = 255)
    private String image;

    @JsonView(View.UserView.External.class)
    @NotNull
    @Size(max = 255)
    private String title;

    @JsonView(View.UserView.External.class)
    @NotNull
    @Size(max = 255)
    private OffsetDateTime dateCreated;

    @NotNull
    private Long gender;

    @JsonView(View.UserView.External.class)
    private List<CharacterDTO> charactersList;

    public List<CharacterDTO> getCharactersList() {
        return this.charactersList;
    }

    public void setCharactersList(List<CharacterDTO> characterDTO) {
        this.charactersList = characterDTO;
    }

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

    public OffsetDateTime getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(OffsetDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

}
