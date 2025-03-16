
package post_controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Post;


@WebServlet(name="PostPagination", urlPatterns={"/postpagination"})
public class PostPagination extends HttpServlet {
   

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        List<Post> allpostlist = (List<Post>)session.getAttribute("allpostlist");
        int cpage = Integer.parseInt(request.getParameter("cpage"));
        List<Post> top6post = select6Post(allpostlist, cpage);
        
        
        
        session.setAttribute("top6post", top6post);
        session.setAttribute("cpostpage", cpage);
        
        
        response.sendRedirect(request.getContextPath()+"/common/post.jsp");
    } 
    
    public static List<Post> select6Post( List<Post> pList, int pageNum){
        List<Post> top6List = new ArrayList<>();
        for(int i = pageNum*6;i<=pageNum*6+5;i++){
            if(i>=pList.size()) {
                break;
            } else {
                top6List.add(pList.get(i));
            }
        }
        
        return top6List;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
