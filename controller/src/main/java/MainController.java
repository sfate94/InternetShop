import dao.OrderDAO;
import dao.ToolsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller

@Transactional

@EnableWebMvc
public class MainController {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private ToolsDAO productDAO;


    @RequestMapping("/")
    public String home() {
        return "index";
    }
}
