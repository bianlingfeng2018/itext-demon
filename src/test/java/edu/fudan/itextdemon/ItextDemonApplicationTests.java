package edu.fudan.itextdemon;

import edu.fudan.itextdemon.repository.AccidentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ItextDemonApplicationTests {
    @Autowired
    AccidentRepository accidentRepository;

    @Test
    public void contextLoads() {
    }
    // es
    @Test
    public void deleteIndex(){
        accidentRepository.deleteById("81e26be3-cdfe-4e7c-9062-0dc76bfd4352");
    }
}
