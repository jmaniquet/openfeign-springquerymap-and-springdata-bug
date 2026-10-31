package org.jmaniquet.contrib.openfeign.springdata.bug;

import feign.Param;
import lombok.Data;

@Data
public class DummyRequestWithLombok {

    private String fizzBuzz;

    @Param("foo-bar")
    private String fooBar;

}
