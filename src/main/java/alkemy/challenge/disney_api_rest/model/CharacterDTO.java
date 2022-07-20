package alkemy.challenge.disney_api_rest.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class CharacterDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String image;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    @Size(max = 255)
    private String age;

    @NotNull
    @Size(max = 255)
    private String weight;

    @NotNull
    private Long movie;

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

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(final String age) {
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(final String weight) {
        this.weight = weight;
    }

    public Long getMovie() {
        return movie;
    }

    public void setMovie(final Long movie) {
        this.movie = movie;
    }

}
