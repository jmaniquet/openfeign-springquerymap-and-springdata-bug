package org.jmaniquet.contrib.openfeign.springdata.bug;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "dummy-api", configuration = DummyClientConfig.class)
public interface DummyClient {

    @GetMapping(path = "/dummy")
    List<DummyResult> findByCriteria(@SpringQueryMap DummyRequestWithLombok request);

    @GetMapping(path = "/dummy")
    List<DummyResult> findByCriteria(@SpringQueryMap DummyRequestNoLombok request);
}
