package org.ics.ejb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ics.facade.FacadeLocal;

/**
 * Servlet implementation class SmartOfficeServlet
 */
@WebServlet("/SmartOfficeServlet")
public class SmartOfficeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private FacadeLocal facade;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SmartOfficeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * doGet for header operations to redirect to other pages or to find an object.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operation");
		ServletContext sc = this.getServletContext();
		
		//Find building
		if ("findBuilding".equals(operation)) {

			String buildingAddress = request.getParameter("buildingAddress");
			Building b = facade.findByAddress(buildingAddress);

			if (b != null) {
				JsonObject value = Json.createObjectBuilder().add("building", true).build();

				response.setContentType("application/json");

				PrintWriter out = response.getWriter();

				out.print(value.toString());
				out.flush();
			} else {
				JsonObject value = Json.createObjectBuilder().add("building", false).build();

				response.setContentType("application/json");

				PrintWriter out = response.getWriter();

				out.print(value.toString());
				out.flush();
			}
		} else if ("findOffice".equals(operation)) {
			
			String officeNumber = request.getParameter("officeNumber");
			Office o = facade.findByOfficeId(officeNumber);

			if (o != null) {
				JsonObject value = Json.createObjectBuilder().add("office", true).build();

				response.setContentType("application/json");

				PrintWriter out = response.getWriter();

				out.print(value.toString());
				out.flush();
			} else {
				JsonObject value = Json.createObjectBuilder().add("office", false).build();

				response.setContentType("application/json");

				PrintWriter out = response.getWriter();

				out.print(value.toString());
				out.flush();
			}
		} else if ("viewAbout".equals(operation)) {

			RequestDispatcher rd = sc.getRequestDispatcher("/SmartOfficeAbout.jsp");
			rd.forward(request, response);

		} else if (operation == null || "viewHome".equals(operation)) {
			List<Building> buildings = facade.getAllBuildings();

			request.setAttribute("buildings", buildings);

			RequestDispatcher rd = sc.getRequestDispatcher("/SmartOfficeHome.jsp");
			rd.forward(request, response);
		} else if ("viewOffices".equals(operation)) {
			List<Office> offices = facade.getAllOffices();

			request.setAttribute("offices", offices);
			
			RequestDispatcher rd = sc.getRequestDispatcher("/Smart_Offices.jsp");
			rd.forward(request, response);

		} else if ("findOfficesForBuilding".equals(operation)) {
			//View associated office entities for a building.
			String buildingAddress = request.getParameter("buildingAddress");
			Building b = facade.findByAddress(buildingAddress);
			Set<Office> offices = new HashSet<Office>();
			
			if(b != null) {
				offices = b.getOffices();
			}

			request.setAttribute("offices", offices);

			RequestDispatcher rd = sc.getRequestDispatcher("/SmartOfficeHome.jsp");
			rd.forward(request, response);
		} else if ("viewTest".equals(operation)) {
			RequestDispatcher rd = sc.getRequestDispatcher("/SmartOfficeTest.jsp");
			rd.forward(request, response);
		} //TO-DO: 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * All other methods.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operation");
		//AddOffice finished!
		try {
			if (operation.equals("AddOffice")) {
				String ventilationSetting = request.getParameter("ventilationSetting");
				int temperatureSetting = Integer.parseInt(request.getParameter("temperatureSetting"));
				String buildingAddress = request.getParameter("buildingAddress");
				Building b = facade.findByAddress(buildingAddress);

				Office o = new Office();
				o.setVentilationSetting(ventilationSetting);
				o.setTemperatureSetting(temperatureSetting);
				o.setBuilding(b);

				facade.createOffice(o);

				JsonObject value = Json.createObjectBuilder()
						.add("officeNumber", o.getOfficeNumber())
						.add("ventilationSetting", o.getVentilationSetting())
						.add("temperatureSetting", o.getTemperatureSetting())
						.add("building", o.getBuilding().getAddress())
						.build();

				response.setContentType("application/json");

				PrintWriter out = response.getWriter();

				out.print(value.toString());
				out.flush();

			} else if (operation.equals("DeleteOffice")) { //Finished (ish)
				String officeNumber = request.getParameter("officeNumber");
				facade.deleteOffice(officeNumber);

				JsonObject value = Json.createObjectBuilder().add("index", request.getParameter("index")).build();

				response.setContentType("application/json");

				PrintWriter out = response.getWriter();

				out.print(value.toString());
				out.flush();
			} else if (operation.equals("updateOffice")) { //Update office (kind of finished)

				Office o = facade.findByOfficeId(request.getParameter("officeNumber"));
				o.setTemperatureSetting(Integer.parseInt(request.getParameter("temperatureSetting")));
				o.setVentilationSetting(request.getParameter("ventilationSetting"));

				facade.updateOffice(o);

				// Todo fixa så att sidan inte laddas om vid update
				JsonObject value = Json.createObjectBuilder()
						.add("officeNumber", o.getOfficeNumber())
						.add("temperatureSetting", o.getTemperatureSetting())
						.add("ventilationSetting", o.getVentilationSetting())
						.add("building", o.getBuilding().getAddress())
						.build();

				response.setContentType("application/json");

				PrintWriter out = response.getWriter();

				out.print(value.toString());
				out.flush();

			}
		}
		catch (Exception e) {
			//Do something
		}
	}
}


