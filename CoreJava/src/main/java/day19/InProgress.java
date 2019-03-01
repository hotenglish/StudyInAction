package day19;
import java.lang.annotation.*;

@Documented
@Inherited
@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD,ElementType.TYPE})
public @interface InProgress {
	
}

@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD})
@interface Author {
	String name();
	String id();
	String groupId();
}








