
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
import model.User;

@WebServlet(name="ListPostMarketing", urlPatterns={"/management/listpostmarketing"})
public class ListPostMarketing extends HttpServlet {
   

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        PostDAO pdao = new PostDAO();
        
        User user = (User)session.getAttribute("account");
        if(user==null){
            response.sendRedirect(request.getContextPath()+"/login");
        } else {
        List<Post> pList = pdao.getAllPostMarketing();
        List<Post> top3post = select3Post(pList, 0);
        PostCategoryDAO pcdao = new PostCategoryDAO();
        List<PostCategory> pcList = pcdao.getAllPostCategory();
        Reset(session);
        
        session.setAttribute("listpostcategorymkt", pcList);
        session.setAttribute("listpostmarketing", pList);
        session.setAttribute("top3postmarketing", top3post);
        session.setAttribute("cpostmkt", 0);
        session.setAttribute("authorfiltermkt", null);
        response.sendRedirect(request.getContextPath()+"/management/listpostmarketing.jsp");
    
        } 
    }
    
    public static void Reset(HttpSession session){
        session.setAttribute("begindatemkt", "");
        session.setAttribute("enddatemkt", "");
        session.setAttribute("authormkt", "");
        session.setAttribute("titlemkt", "");
        session.setAttribute("sortValuemkt",null);
        session.setAttribute("pCategorycmk", null);
        session.setAttribute("pcmktName", "");
        session.setAttribute("pmktloi", "");
    }
    
    public static List<Post> select3Post( List<Post> pList, int pageNum){
        List<Post> top6List = new ArrayList<>();
        for(int i = pageNum*3;i<=pageNum*3+2;i++){
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
