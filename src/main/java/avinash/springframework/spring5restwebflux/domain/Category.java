package avinash.springframework.spring5restwebflux.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Data
@Component
public class Category {
	
	@Id
	private String id;
	private String description;
}
