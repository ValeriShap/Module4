package com.shapran.servlets;

import com.shapran.model.Detail;
import com.shapran.service.DetailService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "StatsByDetail", value = "/stats/*")
public class ShowStatsDetail extends HttpServlet {

    @Override
    public void init() {
        System.out.println(getServletName() + " initialized");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        final String id = request.getParameter("id");
        final DetailService detailService = new DetailService();
        if (id != null) {
            Optional<Detail> detailById = detailService.getById(id);
            if (detailById.isPresent()) {
                Detail detail = detailById.get();
                request.setAttribute("detail", detail);
            }
            request.getRequestDispatcher("/showStatsDetail.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        System.out.println(getServletName() + " destroyed");
    }
}