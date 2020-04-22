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
        String token =request.getParameter("stripeToken");
        response.getWriter().append(bean.creditPayment(token));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
