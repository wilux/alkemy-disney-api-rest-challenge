package alkemy.challenge.disney_api_rest.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class GenderDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

}
