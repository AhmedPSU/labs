package psu.edu.ch06.crud04.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("users")
public record User(
		@Id Integer id,
		String username,
		String password
) {

}
