package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MarcaDao;
import model.Marca;

public class ActionListaMarca implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Marca> marcas = new MarcaDao().getAll();
		
		request.setAttribute("marcas", marcas);
		
		return "/WEB-INF/jsp/lista-marcas.jsp";
	}

}
