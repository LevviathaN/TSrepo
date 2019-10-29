package cucumber;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReusableRunner {

    public void runReusables(){

        ReusableHelper reusableHelper = new ReusableHelper();

        ApplicationContext context = new FileSystemXmlApplicationContext("TestDoablesBeans.xml");

        // Run through the Beans xml
        ReusableClass mainDoable = (ReusableClass) context.getBean("ExecuteDoable");

        ExecutorService executor = Executors.newSingleThreadExecutor();

//        Runnable runnable = new ReusableClass(reusableHelper.);
//        executor.execute(runnable);
    }
}
