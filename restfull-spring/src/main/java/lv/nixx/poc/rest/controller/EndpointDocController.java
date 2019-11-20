package lv.nixx.poc.rest.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@RestController
@RequestMapping("/")
public class EndpointDocController {

    private final Logger log = LoggerFactory.getLogger(EndpointDocController.class);

    private final RequestMappingHandlerMapping handlerMapping;

    @Autowired
    public EndpointDocController(RequestMappingHandlerMapping handlerMapping) {
        this.handlerMapping = handlerMapping;
    }


    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public void show(HttpServletRequest request, HttpServletResponse response) {

        String baseUrl = String.format("%s://%s:%d", request.getScheme(), request.getServerName(), request.getServerPort());

        response.setContentType("text/html");
        PrintWriter out;
        try {
            out = response.getWriter();
            out.println("<html><head>");
            out.println("<title>REST Requests</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>REST Requests</h1>");
            out.println("<table border=\"1\">");

            for (RequestMappingInfo mapping : handlerMapping.getHandlerMethods().keySet()) {
                PatternsRequestCondition pc = mapping.getPatternsCondition();

                RequestMethodsRequestCondition mc = mapping.getMethodsCondition();

                Set<MediaType> producibleMediaTypes = mapping.getProducesCondition().getProducibleMediaTypes();

                String types = producibleMediaTypes.stream()
                        .map(MediaType::toString)
                        .collect(Collectors.joining(","));

                String m = mc.getMethods().stream()
                        .map(RequestMethod::toString)
                        .collect(Collectors.joining(","));

                for (String p : pc.getPatterns()) {
                    String url = baseUrl + p;
                    out.println("<tr>");
                    out.println("<td><a href=\"" + url + "\">" + url + "<a/></td>");
                    out.println("<td>" + m + "</td>");
                    out.println("<td>" + types + "</td>");
                    out.println("</tr>");
                }
                if (log.isInfoEnabled()) {
                    log.info(mapping.toString());
                }
            }
            out.println("</table>");
            out.println("</body></html>");
            out.close();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}