package co.boldtec.salesgen.domain;

import org.apache.commons.lang3.StringUtils;

public class Presentation {

    private Long id;
    private String name;

    public Presentation(String name) {
        if (!StringUtils.isEmpty(name)) {
            this.name = name;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }
}
