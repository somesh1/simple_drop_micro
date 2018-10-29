package micro_services.learn.application;

import java.util.concurrent.atomic.AtomicLong;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import micro_services.learn.configuration.TellTimeConfiguration;
import micro_services.learn.healthcheck.TemplateHealthCheck;
import micro_services.learn.resource.TellTimeResource;

public class TellTimeApplication extends Application<TellTimeConfiguration> {

    private AtomicLong counter = new AtomicLong();

    public static void main(String [] a) throws Exception{
        new TellTimeApplication().run(a);
    }

    public String getName(){
        return "Time - Teller";
    }

    public void initialize(Bootstrap<TellTimeConfiguration> bootstrap){

    }


    public void run(TellTimeConfiguration tellTimeConfiguration, Environment environment){
        final TellTimeResource resource = new TellTimeResource(
                tellTimeConfiguration.getTemplate(),
                tellTimeConfiguration.getDefaultName(),
                counter
        );
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(tellTimeConfiguration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }
}
