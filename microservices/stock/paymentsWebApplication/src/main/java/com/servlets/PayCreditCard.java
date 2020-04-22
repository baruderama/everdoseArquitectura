package com.servlets;

import com.beans.PaymentsEJBBean;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/PayCreditCard")
public class PayCreditCard extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    PaymentsEJBBean bean;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("name");
        String cardNumber=request.getParameter("number");
        String cvv=request.getParameter("cvv");
        String dateThru=request.getParameter("date");
        double value=Double.valueOf(request.getParameter("value"));
        response.getWriter().append(bean.creditPayment(name,  cardNumber, cvv, dateThru, value));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
