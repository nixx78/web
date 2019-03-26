package lv.nixx.poc.rest;

import java.io.*;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AuditFilter implements Filter {

	private Logger log = LoggerFactory.getLogger("AUDIT_LOG");

	@Override public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
//		String requestString = request.getReader().lines().collect(Collectors.joining());
//		log.debug("Request [{}]", requestString);
		
//		final CopyPrintWriter writer = new CopyPrintWriter(response.getWriter());
//		
//		 chain.doFilter(request, new HttpServletResponseWrapper((HttpServletResponse) response) {
//		        @Override public PrintWriter getWriter() {
//		            return writer;
//		        }
//		    });
//		 
//	
//		 log.debug("Response [{}]", writer.getCopy());
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) {
	}
	
	class CopyPrintWriter extends PrintWriter {

	    private final  StringBuilder copy = new StringBuilder();

	    public CopyPrintWriter(Writer writer) {
	        super(writer);
	    }

	    @Override
	    public void write(int c) {
	        copy.append((char) c); // It is actually a char, not an int.
	        super.write(c);
	    }

	    @Override
	    public void write(char[] chars, int offset, int length) {
	        copy.append(chars, offset, length);
	        super.write(chars, offset, length);
	    }

	    @Override
	    public void write(String string, int offset, int length) {
	        copy.append(string, offset, length);
	        super.write(string, offset, length);
	    }

	    public String getCopy() {
	        return copy.toString();
	    }

	}

}