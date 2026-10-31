package org.jmaniquet.contrib.openfeign.springdata.bug;

import feign.Param;

public class DummyRequestNoLombok {

    private String fizzBuzz;

    @Param("foo-bar")
    private String fooBar;
    
    public String getFizzBuzz() {
        return fizzBuzz;
    }
    public void setFizzBuzz(String fizzBuzz) {
        this.fizzBuzz = fizzBuzz;
    }

    public String getFooBar() {
        return fooBar;
    }
    public void setFooBar(String fooBar) {
        this.fooBar = fooBar;
    }


}
