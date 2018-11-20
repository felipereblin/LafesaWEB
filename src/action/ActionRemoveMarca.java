package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MarcaDao;
import model.Marca;

public class ActionRemoveMarca implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		
		MarcaDao dao = new MarcaDao();
		dao.delete(id);
		
		System.out.println("Excluindo marca ... ");
		
		request.setAttribute("mensagem", "Marca removido com sucesso.");
		
		return "controller?action=ActionListaMarca";
	}

}
