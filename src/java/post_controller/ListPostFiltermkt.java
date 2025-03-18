

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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import model.Post;
import model.PostCategories;
import model.PostCategory;


@WebServlet(name="ListPostFiltermkt", urlPatterns={"/listpostfiltermkt"})
public class ListPostFiltermkt extends HttpServlet {
   

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        PostDAO pdao = new PostDAO();
        PostCategoryDAO pcdao = new PostCategoryDAO();
        
        String begindate = request.getParameter("begindate");
        String enddate = request.getParameter("enddate");
        String author = request.getParameter("author");
        String title = request.getParameter("title");
        String category = request.getParameter("cid");
        
        
        
        if(begindate==null) {
            if(session.getAttribute("begindatemkt")!=null)
            begindate= session.getAttribute("begindatemkt")+"";
            else begindate="";
        } 
        
        if(enddate==null) {
            if(session.getAttribute("enddatemkt")!=null) 
            enddate=session.getAttribute("enddatemkt")+"";
            else enddate="";
        }
           
        
        
        if(author==null) {
           if(session.getAttribute("authormkt")!=null)
            author=session.getAttribute("authormkt")+"";   
           else author="";
        }
            
        
        if(title==null) {
            if(session.getAttribute("titlemkt")!=null)
                title=session.getAttribute("titlemkt")+"";
            else title="";
        }
            
        
        
        if(category==null) category=session.getAttribute("pCategorycmk")+"";
        
        if(begindate.length()==0) session.setAttribute("begindatemkt", "");
        if(enddate.length()==0) session.setAttribute("enddatemkt", "");
        if(author.length()==0) session.setAttribute("authormkt", "");
        if(title.length()==0) session.setAttribute("titlemkt", "");
        
        
        String sql = "select p.*, (u.first_name+' '+u.last_name) as authorname  ,DATEDIFF(DAY, modified_at ,GETDATE()) from Posts as p, Users as u\n" +
                    "where p.author_id = u.user_id and u.is_banned=0 and p.is_active=1 ";
        
        
        String loi = "";
        
        //Khai bao date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        
        
        //TH1: chi nhap begim, ko nhap end:
        if((begindate.length()!=0 && enddate.length()==0) || (begindate.length()==0 && enddate.length()!=0)){
            loi = "Please input both From and To";
            session.setAttribute("pmktloi", loi);
            
        } else if(begindate.length()!=0 && enddate.length()!=0){
            session.setAttribute("begindatemkt", begindate);
            session.setAttribute("enddatemkt", enddate);
             LocalDate beginDate = LocalDate.parse(begindate,formatter);
            LocalDate endDate = LocalDate.parse(enddate,formatter);
            
            long diff = ChronoUnit.DAYS.between(beginDate, endDate);
            if(diff<0){
                loi = "To Date must be >= From Date";
                session.setAttribute("pmktloi", loi);
                
            } else {
                sql += "and modified_at >= '"+beginDate+"' and modified_at<='"+endDate+"' ";
            }

        }
        
        if(author.length()>0){
            session.setAttribute("authormkt", author);
            sql+="and (u.first_name+' '+u.last_name) like '%"+author+"%' ";
        }
        if(title.length()>0){
            session.setAttribute("titlemkt", title);
            sql+="and title like '%"+title+"%' ";
        }
        
        if(category.equals("null")==false){
            session.setAttribute("pCategorycmk", category);
            PostCategory pc = pcdao.getPostCategories(category);
            String pcname = pc.getPost_category_name();
            session.setAttribute("pcmktName", pcname);
            
            sql += "and p.post_category_id = "+category+" ";
        }
        String authorfiltermkt = session.getAttribute("authorfiltermkt")+"";
        if(authorfiltermkt.equals("null")) authorfiltermkt="";
        if(authorfiltermkt.length()>0 && authorfiltermkt.equals("all")==false){
            sql += "and p.author_id = "+authorfiltermkt+" ";
        }
               
        List<Post> pList = pdao.getAllPostByFilterMkt(sql);
        List<Post> top3post = select3Post(pList, 0);
        
        session.setAttribute("listpostmarketing", pList);
        session.setAttribute("top3postmarketing", top3post);
        session.setAttribute("cpostmkt", 0);
        session.setAttribute("postsql", sql);
        if(loi.length()==0) session.setAttribute("pmktloi", "");
        response.sendRedirect(request.getContextPath()+"/management/listpostmarketing.jsp");
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
