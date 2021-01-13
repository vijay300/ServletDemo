package com.firstservlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

@WebServlet(
        description = "Login Servlet Testing",
        urlPatterns = {"/LoginServlet"},
        initParams = {
                @WebInitParam(name = "user", value = "Vijay"),
                @WebInitParam(name = "password", value = "Vijay123")
        }
)
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("user");
        String pwd = req.getParameter("pwd");
        String userID = getServletConfig().getInitParameter("user");
        String password = getServletConfig().getInitParameter("password");
        String nameRegex = "^[A-Z]{1}[a-zA-Z\\s]{2,}$";

        if (userID.equals(user) && password.equals(pwd)) {
            req.setAttribute("user", user);
            req.getRequestDispatcher("LoginSuccess.jsp").forward(req, resp);
        } else if (!Pattern.matches(nameRegex, user)) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = resp.getWriter();
            out.println("<font color=red>Please enter username as First letter capital and minimum 3 characters.</font>");
            rd.include(req, resp);
        }
        else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = resp.getWriter();
            out.println("<font color=red>Either user name or password is wrong.</font>");
            rd.include(req, resp);
        }
    }
}
