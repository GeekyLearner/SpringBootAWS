package bookapp.controller;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("bookapp.controller")
@EnableJpaRepositories("bookapp.controller")
@EnableAutoConfiguration
@SpringBootApplication
public class Application {

  /*  public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx){

        return args -> {
          System.out.println("Inspect Beans being Created");
          String[] beanNames = ctx.getBeanDefinitionNames();
          for (String beanName: beanNames){
              System.out.println(beanName);
          }
        };
    }*/

    private final AmazonS3Client amazonS3Client;

    public Application(AmazonS3Client amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @EventListener(classes = ApplicationReadyEvent.class)
    public void onApplicationReadyEvent(ApplicationReadyEvent event) {
        for (Bucket availableBuckets : amazonS3Client.listBuckets()) {
            System.out.println(availableBuckets.getName());
        }
    }

}
