package org.ics.ejb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response) doGet for header operations to redirect to other pages or to
	 *      find an object.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation = request.getParameter("operation");
		ServletContext sc = this.getServletContext();

		// Find building
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
			Office o = facade.findByOfficeNumber(officeNumber);

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

		} else if ("viewBuildings".equals(operation)) {
			List<Building> buildings = facade.getAllBuildings();

			request.setAttribute("buildings", buildings);

			RequestDispatcher rd = sc.getRequestDispatcher("/SmartOfficeHome.jsp");
			rd.forward(request, response);
		} else if (operation == null || "viewOffices".equals(operation)) {
			List<Office> offices = facade.getAllOffices();
			List<Building> buildings = facade.getAllBuildings();

			request.setAttribute("offices", offices);
			request.setAttribute("buildings", buildings);

			RequestDispatcher rd = sc.getRequestDispatcher("/Smart_Offices.jsp");
			rd.forward(request, response);
		} else if ("findOfficesForBuilding".equals(operation)) {
			// View associated office entities for a building.
			String buildingAddress = request.getParameter("buildingAddress");
			Building b = facade.findByAddress(buildingAddress);
			Set<Office> offices = new HashSet<Office>();
			// Note to self - Set<Office> might need to be cast to a List<Office> object
			// instead.

			if (b != null) {
				offices = b.getOffices();
			}

			request.setAttribute("offices", offices);

			RequestDispatcher rd = sc.getRequestDispatcher("/SmartOfficeHome.jsp");
			rd.forward(request, response);
		} else if ("viewTest".equals(operation)) {
			RequestDispatcher rd = sc.getRequestDispatcher("/SmartOfficeTest.jsp");
			rd.forward(request, response);
		} // TO-DO:
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response) All other methods.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation = request.getParameter("operation");
		// AddOffice finished!
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

				o = facade.createOffice(o);
				String parsedOfficeNumber = parseOfficeNumber(o.getOfficeNumber());

				JsonObject value = Json.createObjectBuilder()
						.add("officeNumber", parsedOfficeNumber)
						.add("ventilationSetting", o.getVentilationSetting())
						.add("temperatureSetting", o.getTemperatureSetting())
						.add("buildingAddress", o.getBuilding().getAddress()).build();

				response.setContentType("application/json");

				PrintWriter out = response.getWriter();
				
				out.print(value.toString());
				out.flush();

			} else if (operation.equals("DeleteOffice")) { // Finished (ish)
				String officeNumber = request.getParameter("officeNumber");
				facade.deleteOffice(officeNumber);

				JsonObject value = Json.createObjectBuilder().add("index", request.getParameter("index")).build();

				response.setContentType("application/json");

				PrintWriter out = response.getWriter();

				out.print(value.toString());
				out.flush();
			} else if (operation.equals("updateOffice")) { // Update office (kind of finished)

				Office o = facade.findByOfficeNumber(request.getParameter("officeNumber"));
				o.setBuilding(facade.findByAddress(request.getParameter("buildingAddress")));
				o.setTemperatureSetting(Integer.parseInt(request.getParameter("temperatureSetting")));
				o.setVentilationSetting(request.getParameter("ventilationSetting"));

				facade.updateOffice(o);

				// Todo fixa s? att sidan inte laddas om vid update
				JsonObject value = Json.createObjectBuilder().add("officeNumber", o.getOfficeNumber())
						.add("temperatureSetting", o.getTemperatureSetting())
						.add("ventilationSetting", o.getVentilationSetting())
						.add("building", o.getBuilding().getAddress()).build();

				response.setContentType("application/json");

				PrintWriter out = response.getWriter();

				out.print(value.toString());
				out.flush();

			}
		} catch (Exception e) {
			// Do something
		}
	}

	private String parseOfficeNumber(String officeIDNumberAsString) {
		//O000535
		int officeIDNumber = Integer.parseInt(officeIDNumberAsString);
		String officeNumber = "O";
		if (officeIDNumber<10) {
			officeNumber+="00000"  + officeIDNumber;
		} else if (officeIDNumber<100) {
			officeNumber+= "0000"  + officeIDNumber;
		}
		else if (officeIDNumber<1000) {
			officeNumber+="000"  + officeIDNumber;
		}
		else if (officeIDNumber<10000) {
			officeNumber+="00"  + officeIDNumber;
		}
		else if (officeIDNumber<100000) {
			officeNumber+="0"  + officeIDNumber;
		}
		else if (officeIDNumber<1000000) {
			officeNumber+=officeNumber;
		}
		return officeNumber;
	}
}
