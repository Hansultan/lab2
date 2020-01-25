import history.HistoryBean;
import history.HistoryNode;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AreaCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        String x = req.getParameter("X").trim();
        String y = req.getParameter("Y").trim();
        String r = req.getParameter("R").trim();

        if (x == null || y == null || r == null) {
            req.getRequestDispatcher("/main.jsp").forward(req, resp);
            return;
        }

        Object history = req.getSession().getAttribute("history");
        HistoryBean bean;
        if (!(history instanceof HistoryBean)) {
            bean = new HistoryBean();
            req.getSession().setAttribute("history", bean);
        } else bean = (HistoryBean) history;

        PrintWriter writer = resp.getWriter();
        String contextPath = req.getContextPath() + "/";
        Cookie[] cookies = req.getCookies();
        boolean lightTheme = true;

        if (cookies != null)
            for (Cookie c: cookies)
                if (c.getName().equals("lightTheme")) {
                    lightTheme = Boolean.parseBoolean(c.getValue());
                }

        String themePath = contextPath + ( lightTheme ? "light.css" : "dark.css" );

        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>Результаты</title>");
        writer.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + themePath + "\">");
        writer.println("<link rel=\"shortcut icon\" href=\"img/favicon.ico\">");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<div class=\"centered\">");
        writer.println("<h1>Результат</h1>");
        writer.println("<table id=\"result-table\" class=\"centered\">");
        try {
            HistoryNode node = new HistoryNode(x, y, r);
            bean.getNodeList().add(node);
            writer.println("<tr><th>X</th><th>Y</th><th>R</th><th>Попадание</th></tr>");
            writer.println("<tr>" +
                                "<td>" + x + "</td>" +
                                "<td>" + y + "</td>" +
                                "<td>" + r + "</td>" +
                                "<td class=\"hit\" colspan=\"2\">" + ( node.isHit() ? "Попал!" : "Промазал!" ) + "</td>" +
                            "</tr>");
        }
        catch (Exception e) {
            writer.println("<tr><td colspan=\"2\">" + e.getMessage() + "</td></tr>");
        }
        writer.println("</table>");

        writer.println("<input type=\"button\" id=\"button\" value=\"Вернуться на главную страницу\"" +
                " onclick=\"location.href='" + contextPath + "';\">");
        writer.println("</div>");

        writer.println("</body>");
        writer.println("</html>");
    }
}


