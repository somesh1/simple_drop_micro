package micro_services.learn.resource;

import java.time.Clock;
import java.time.LocalDate;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import com.codahale.metrics.annotation.Timed;
import micro_services.learn.api.Saying;
import org.joda.time.LocalTime;

@Path("/time")
@Produces(MediaType.APPLICATION_JSON)
public class TellTimeResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public TellTimeResource(String template, String defaultName, AtomicLong counter) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = counter;
    }

    @GET
    @Timed
    public Saying tellTime(@QueryParam("name") Optional<String> name, @Context HttpServletRequest requestContext){
        final String value = String.format(template, requestContext.getRemoteAddr(), LocalDate.now() + " " + LocalTime.now());
        return new Saying(counter.incrementAndGet(), value);
    }
}
