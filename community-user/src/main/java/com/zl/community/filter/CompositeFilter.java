package com.zl.community.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Author : ZL
 */
public class CompositeFilter implements Filter {
    private final MyCharsetFilter charsetFilter = new MyCharsetFilter();
    private final MyCorsFilter corsFilter = new MyCorsFilter();


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        charsetFilter.init(filterConfig);
        corsFilter.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        charsetFilter.doFilter(request, response, chain);
        corsFilter.doFilter(request, response, chain);
    }

    @Override
    public void destroy() {
        charsetFilter.destroy();
        corsFilter.destroy();
    }
}
