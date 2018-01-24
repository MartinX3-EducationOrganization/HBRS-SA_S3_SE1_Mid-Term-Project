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
        us = new UserStory("titel", "basdasd, asdasd, ,, , , , ,asdas .", "dasdasdad asdas a sdas d", "aasdasd dsad asa ds", "easdasd asd asd ", "easdasd", 1, 1, 1, 1, "Student");
        Container.getContainer().addActor("Student");
        Container.getContainer().addUS(us);
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