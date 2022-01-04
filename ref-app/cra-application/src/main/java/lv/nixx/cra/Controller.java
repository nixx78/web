package lv.nixx.cra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class Controller {

    @Autowired
    public ProjectDao projectDao;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World:" + System.currentTimeMillis();
    }

    @GetMapping("/project/{name}")
    public Project addProject(@PathVariable String name) {
        Project p = new Project();
        p.setName(name);
        return projectDao.save(p);
    }

    @GetMapping("/project")
    public Collection<Project> getAll() {
        Iterable<Project> iterable = () -> projectDao.findAll().iterator();
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

}
