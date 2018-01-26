import org.bonn.se.ws17.midterm.entity.UserStory;
import org.bonn.se.ws17.midterm.model.Container;
import org.bonn.se.ws17.midterm.utility.AnalyzeUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ContainerTest {
    
    private final UserStory us = new UserStory();
    
    @Before
    public void setUp() throws Exception {
        us.setTitel("titel");
        us.setEpic("epic");
        us.setAkzeptanz("akzeptanz");
        us.setBeschreibung("beschreibung");
        us.setDetails("Details");
        us.setMehrwert("mehrwert");
        us.setAufwand(13);
        us.setMwert(2);
        us.setRisiko(3);
        us.setStrafe(4);
        us.setActor("Student");
        us.setDone(false);
    
        Container.getContainer().addActor(us.getActor());
        Container.getContainer().addUS(us);
    }
    
    @Test
    public void testAnalyze() {
        AnalyzeUtils.analyze(String.format("%s", us.getId()).split(" "));
    }
    
    @After
    public void tearDown() {
        Container.getContainer().clearUS();
    }
}