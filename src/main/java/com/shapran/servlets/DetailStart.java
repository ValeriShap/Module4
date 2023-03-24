package com.shapran.servlets;

import com.shapran.model.Detail;
import com.shapran.service.DetailService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CreateDetails", value = "/start")
public class DetailStart extends HttpServlet {

    @Override
    public void init() {
        System.out.println(getServletName() + " initialized");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        request.getRequestDispatcher("create.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            DetailService detailService = new DetailService();
            final Detail detail = detailService.createDetail();

            response.setContentType("text/html");
            PrintWriter responseBody = response.getWriter();

            responseBody.println("<html>");
            responseBody.println("<head><title>Detail created</title></head>");
            responseBody.println("<body>");
            responseBody.println("<h1 align=\"center\"> Created detail: </h1>");
            responseBody.println("<h3>" + detail + "</h3>");
            responseBody.println("<form method='get' action='http://localhost:8080/Module4'>");
            responseBody.println("<input type='submit' value='Main page'>");
            responseBody.println("</form>");
            responseBody.println("</body>");
            responseBody.println("</html>");
        } catch (InterruptedException e) {
            throw new ServletException("Error creating detail", e);
        }
    }

    @Override
    public void destroy() {
        System.out.println(getServletName() + " destroyed");
    }
}
