package blog.model;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Post {
	
	int id;
	String post;
	Timestamp date;
	int userId;
}
