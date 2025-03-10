package controller;

import dal.FeedbackDAO;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ProductFeedback;

@WebServlet(name = "SortFeedbackServlet", urlPatterns = {"/sortFeedback"})
public class SortFeedbackServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchName = request.getParameter("search");
        String orderBy = request.getParameter("orderBy");
        String orderDir = request.getParameter("orderDir");

        // Giá trị mặc định nếu không có thông tin order
        if (orderBy == null) {
            orderBy = "customer_name";
        }
        if (orderDir == null) {
            orderDir = "ASC";
        }

        FeedbackDAO feedbackDAO = new FeedbackDAO();
        List<ProductFeedback> feedbackList;

        // Nếu có từ khóa tìm kiếm thì lọc danh sách
        if (searchName != null && !searchName.trim().isEmpty()) {
            feedbackList = feedbackDAO.getFeedback(searchName);
        } else {
            feedbackList = feedbackDAO.getFeedback("");
        }

        // Sắp xếp danh sách theo tiêu chí orderBy
        Comparator<ProductFeedback> comparator = null;
        switch (orderBy) {
            case "customer_name":
                comparator = Comparator.comparing(ProductFeedback::getCustomer_name, Comparator.nullsLast(String::compareTo));
                break;
            case "product_name":
                comparator = Comparator.comparing(ProductFeedback::getProduct_name, Comparator.nullsLast(String::compareTo));
                break;
            case "rating":
                comparator = Comparator.comparingInt(ProductFeedback::getRating);
                break;
            case "is_active":
                comparator = Comparator.comparingInt(ProductFeedback::getIs_active); 
                break;
        }

        // Nếu orderDir là DESC, đảo ngược danh sách
        if (comparator != null) {
            if ("DESC".equalsIgnoreCase(orderDir)) {
                comparator = comparator.reversed();
            }
            Collections.sort(feedbackList, comparator);
        }

        // Đặt danh sách vào request scope để hiển thị trên JSP
        request.setAttribute("Feedback", feedbackList);
        request.setAttribute("orderBy", orderBy);
        request.setAttribute("orderDir", orderDir);
        request.getRequestDispatcher("ListFeedback.jsp").forward(request, response);
    }
}
