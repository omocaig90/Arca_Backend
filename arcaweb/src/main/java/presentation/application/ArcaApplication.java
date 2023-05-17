package presentation.application;

import java.util.HashSet;
import java.util.Set;
import presentation.rest.Hello;
import presentation.rest.ArcaWeb;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("rest")
public class ArcaApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> s = new HashSet<>();
		s.add(Hello.class);
		s.add(ArcaWeb.class);
		return s;
	}
}