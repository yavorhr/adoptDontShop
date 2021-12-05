package softuni.adoptdontshop.Service.Scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RemoveCacheScheduler {

    private final CacheEvicter cacheEvicter;
    private static final Logger LOGGER = LoggerFactory.getLogger(RemoveCacheScheduler.class);

    public RemoveCacheScheduler(CacheEvicter cacheEvicter) {
        this.cacheEvicter = cacheEvicter;
    }

    @Scheduled(cron = "23 00 * * * *") //
    public void removeAllBreedsOnceAday() {
        this.cacheEvicter.evictAllCacheValues();
        LOGGER.info("Hello from scheduler");
    }


}
