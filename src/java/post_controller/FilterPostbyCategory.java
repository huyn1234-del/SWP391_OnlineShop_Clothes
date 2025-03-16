
package post_controller;

import dal.PostCategoryDAO;
import dal.PostDAO;
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
import model.PostCategory;


@WebServlet(name="FilterPostbyCategory", urlPatterns={"/filterpostbycategory"})
public class FilterPostbyCategory extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        PostDAO pdao = new PostDAO();
        PostCategoryDAO pcdao = new PostCategoryDAO();
        
        String cid = request.getParameter("cid");
        List<Post> pList = pdao.getAllPostByCategoryId(cid);
        List<Post> top6post = select6Post(pList, 0);
        List<PostCategory> pcList = pcdao.getAllPostCategory();
        session.setAttribute("mainpage", "blog");
        session.setAttribute("allpostlist", pList);
        session.setAttribute("top6post", top6post);
        session.setAttribute("postcategorylist", pcList);
        session.setAttribute("cpostpage", 0);
        Reset(session);
        session.setAttribute("sortPostValue", cid);
        response.sendRedirect(request.getContextPath()+"/common/post.jsp");
    } 

    
    public static void Reset(HttpSession session){
        session.setAttribute("pobegin", "");
        session.setAttribute("poend", "");
        session.setAttribute("author", "");
        session.setAttribute("title", "");
        session.setAttribute("sortPostValue", "");
        session.setAttribute("mainpage", "blog");
        session.setAttribute("ploi", "");
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
