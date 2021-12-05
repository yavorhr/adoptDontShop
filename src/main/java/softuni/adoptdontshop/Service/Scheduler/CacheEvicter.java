package softuni.adoptdontshop.Service.Scheduler;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

@Component
public class CacheEvicter {

    @CacheEvict(value = "getAllBreeds", allEntries = true)
    public void evictAllCacheValues() {}
}
