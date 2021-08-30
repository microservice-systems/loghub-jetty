/*
 * Copyright (C) 2020 Microservice Systems, Inc.
 * All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package systems.microservice.loghub.jetty;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import systems.microservice.loghub.sdk.http.HttpException;

import java.io.IOException;

/**
 * @author Dmitry Kotlyarov
 * @since 1.0
 */
public class ServiceHandler extends AbstractHandler {
    public ServiceHandler() {
    }

    public int get(String target, String contentType, String acceptType, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        return HttpServletResponse.SC_NOT_IMPLEMENTED;
    }

    public int post(String target, String contentType, String acceptType, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        return HttpServletResponse.SC_NOT_IMPLEMENTED;
    }

    public int put(String target, String contentType, String acceptType, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        return HttpServletResponse.SC_NOT_IMPLEMENTED;
    }

    public int patch(String target, String contentType, String acceptType, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        return HttpServletResponse.SC_NOT_IMPLEMENTED;
    }

    public int delete(String target, String contentType, String acceptType, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        return HttpServletResponse.SC_NOT_IMPLEMENTED;
    }

    public int head(String target, String contentType, String acceptType, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        return HttpServletResponse.SC_NOT_IMPLEMENTED;
    }

    public int options(String target, String contentType, String acceptType, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        return HttpServletResponse.SC_NOT_IMPLEMENTED;
    }

    public int trace(String target, String contentType, String acceptType, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        return HttpServletResponse.SC_NOT_IMPLEMENTED;
    }

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        baseRequest.setHandled(true);
        try {
            int sc = HttpServletResponse.SC_NOT_IMPLEMENTED;
            String m = request.getMethod();
            if (m.equals("GET")) {
                sc = get(target, request.getHeader("Content-Type"), request.getHeader("Accept"), request, response);
            } else if (m.equals("POST")) {
                sc = post(target, request.getHeader("Content-Type"), request.getHeader("Accept"), request, response);
            } else if (m.equals("PUT")) {
                sc = put(target, request.getHeader("Content-Type"), request.getHeader("Accept"), request, response);
            } else if (m.equals("PATCH")) {
                sc = patch(target, request.getHeader("Content-Type"), request.getHeader("Accept"), request, response);
            } else if (m.equals("DELETE")) {
                sc = delete(target, request.getHeader("Content-Type"), request.getHeader("Accept"), request, response);
            } else if (m.equals("HEAD")) {
                sc = head(target, request.getHeader("Content-Type"), request.getHeader("Accept"), request, response);
            } else if (m.equals("OPTIONS")) {
                sc = options(target, request.getHeader("Content-Type"), request.getHeader("Accept"), request, response);
            } else if (m.equals("TRACE")) {
                sc = trace(target, request.getHeader("Content-Type"), request.getHeader("Accept"), request, response);
            }
            response.setStatus(sc);
        } catch (HttpException e) {
            response.sendError(e.getStatus(), e.getMessage());
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
