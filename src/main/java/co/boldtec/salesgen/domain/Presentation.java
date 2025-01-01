package co.boldtec.salesgen.domain;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import lombok.Data;

@Data
public class Presentation {

    private Long id;
    private String name;

    public Presentation(String name) {
        if (!StringUtils.isEmpty(name)) {
            this.name = name;
        }
    }
}

