package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MarcaDao;
import model.Marca;


public class ActionFormEditMarca implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String strId = request.getParameter("id");
		
		Marca marca = new Marca();
		
		if (strId != null) {
			int id = Integer.parseInt(strId);
			MarcaDao dao = new MarcaDao();
			marca = dao.getByKey(id);
		}
			
		request.setAttribute("marca", marca);
		
		return "/WEB-INF/jsp/adiciona-marca.jsp";
		
		
	}

}
