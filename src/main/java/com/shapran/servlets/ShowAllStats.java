package com.shapran.servlets;

import com.shapran.model.StatsDTO;
import com.shapran.service.DetailService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "allStats", value = "/stats")
public class ShowAllStats extends HttpServlet {

    @Override
    public void init() {
        System.out.println(getServletName() + " initialized");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        final StatsDTO statsDTO = new DetailService().getStats();
        request.setAttribute("statistics", statsDTO);
        final List<String> allId = new DetailService().getAllId();
        request.setAttribute("allId", allId);
//        request.setAttribute("allPreparedFuel", statsDTO.getAllPreparedFuel());
//        request.setAttribute("allBrokenMicrochips", statsDTO.getAllBrokenMicrochips());
//        request.setAttribute("allUsedFuel", statsDTO.getAllUsedFuel());
        request.getRequestDispatcher("showAllStats.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        System.out.println(getServletName() + " destroyed");
    }
}
