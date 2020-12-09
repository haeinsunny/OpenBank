package mypet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@WebServlet("/PetListServ")
public class PetListServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public PetListServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); 
		PetDao dao = new PetDao();
		
		ArrayList<Pet> list = dao.selectAll();
		
		Gson gson = new Gson();
		String res = gson.toJson(list);
		response.getWriter().append(res);
	}

}
