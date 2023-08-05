package stc.assessments.storagestc;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class StorageStcApplication {

	public static void main(String[] args) {
		SpringApplication.run(StorageStcApplication.class, args);
	}

}
