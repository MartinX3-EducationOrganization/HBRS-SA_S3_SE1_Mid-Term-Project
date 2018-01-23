import org.bonn.se.ws17.midterm.entity.UserStory;
import org.bonn.se.ws17.midterm.exception.ContainerException;
import org.bonn.se.ws17.midterm.model.Container;
import org.bonn.se.ws17.midterm.utility.OutputUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ContainerTest {
    
    private UserStory us;
    
    @Before
    public void setUp() throws Exception {
        us = new UserStory("titel", "b", "d", "a", "e", "e", 1, 1, 1, 1, "actor1");
        
        Container.getContainer().add(us);
    }
    
    @Test
    public void testAnalyze() throws ContainerException {
        OutputUtils.analyze(us.getId());
    }
    
    @After
    public void tearDown() {
        Container.getContainer().clear();
    }
}