package com.progresssoft.warehouse.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Web Util used to handle  some utility on http request.
 *
 * @author Bader Alnajjar
 * @version 1.0
 */
public final class WebUtils {

    /**
     * Get current request url.
     *
     * @return header value
     */
    public static String getCurrentRequestUrl() {

        final AtomicReference<String> requestUrl = new AtomicReference<String>();

        Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .ifPresent(requestAttributes -> {
                    requestUrl.set(((ServletRequestAttributes) requestAttributes)
                            .getRequest()
                            .getRequestURL().toString());
                });

        return requestUrl.get();
    } //getCurrentRequestUrl

} //class
