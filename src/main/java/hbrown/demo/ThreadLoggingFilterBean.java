package hbrown.demo;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * This is an ordinary Spring {@link GenericFilterBean}. All web requests goes through this filter.
 * If a request parameter named {@literal trace} is found with a value set to: {@literal on} then everything from the
 * {@literal trace} level will be logged for this request.
 */
@Component
public class ThreadLoggingFilterBean extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {

            final boolean logEverythingForThisRequest = "on".equalsIgnoreCase(request.getParameter("trace"));
            ThreadLoggingSupport.logEverything(logEverythingForThisRequest);

            chain.doFilter(request, response);

        } finally {
            ThreadLoggingSupport.cleanup();
        }
    }

}
