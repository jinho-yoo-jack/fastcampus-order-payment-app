package sw.sustainable.springlabs.core.config;

import org.springframework.context.ApplicationContext;

public class BeanUtils {
    public static Object getBean(Class<?> classType) {
        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
        return applicationContext.getBean(classType);
    }
}
