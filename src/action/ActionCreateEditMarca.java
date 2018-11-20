package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MarcaDao;
import service.ClienteService;
import model.Marca;


public class ActionCreateEditMarca implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Marca marca = new Marca();
		marca.setNome(request.getParameter("nome"));

		MarcaDao marcaDao = new MarcaDao();
		
		if (request.getParameter("id") != "" && request.getParameter("id") != null) {
			String id = request.getParameter("id");
			marca.setId(Integer.parseInt(id));
			marcaDao.update(marca);
			
			request.setAttribute("mensagem", "Marca alterado com sucesso.");
			
		} else {
			marcaDao.insert(marca);
			request.setAttribute("mensagem", "Marca criado com sucesso");
		}
		
		return "controller?action=ActionListaMarca";
	}

}
