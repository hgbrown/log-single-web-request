package hbrown.demo;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.spi.FilterReply;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * This component implements the details of the specific logging implementation being used, in this case: Logback.
 * We register a global log filter (a TurboFilter) that knows when to:
 * <ul>
 *     <li>Force the logging of a specific logging event, or</li>
 *     <li>Ignore the specific logging event</li>
 * </ul>
 * <p>
 *     All logging events go through this <a href="http://logback.qos.ch/manual/filters.html">filter</a> – so it is
 *     important to avoid putting expensive code here. Here we simply check the state and use that to decide whether
 *     the current logging event should be logged or ignored. Ignoring it here means that this filter wont interfere
 *     in the decision.
 * </p>
 */
@Component
public class ThreadLoggingInitializer {

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(ThreadLoggingInitializer.class);

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        final LoggerContext loggerContext = ((Logger) LoggerFactory.getLogger("")).getLoggerContext();

        loggerContext.addTurboFilter(new TurboFilter() {
            @Override
            public FilterReply decide(Marker marker, Logger logger, Level level, String format, Object[] params, Throwable t) {
                return (ThreadLoggingSupport.shouldLogEverything()) ? FilterReply.ACCEPT : FilterReply.NEUTRAL;
            }
        });

        LOG.info("ThreadLogging support initialized");
    }
}
