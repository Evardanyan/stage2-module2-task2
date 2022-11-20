package com.example.servlet;


import com.example.Users;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object user = req.getSession().getAttribute("user");

        if (user == null) {
            resp.sendRedirect("/login.jsp");
        } else {
            resp.sendRedirect("/user/hello.jsp");
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users usersLst = Users.getInstance();

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login != null && password != null && !login.equals("") && !password.equals("")) {

            boolean rightLogin = usersLst.getUsers().contains(login);
            boolean rightPassword = password != null && !password.trim().isEmpty();

            if (rightLogin && rightPassword) {
                req.getSession().setAttribute("user", login);
                resp.sendRedirect("/user/hello.jsp");
            } else {
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        }

    }
}
