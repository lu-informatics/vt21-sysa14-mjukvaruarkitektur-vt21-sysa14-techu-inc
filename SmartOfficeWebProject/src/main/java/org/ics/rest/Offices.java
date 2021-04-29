package org.ics.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ics.ejb.Building;
import org.ics.ejb.Office;
import org.ics.facade.FacadeLocal;

/**
 * Servlet implementation class OfficeServlet
 */
@WebServlet("/Offices/*")
public class Offices extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	FacadeLocal facade;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Offices() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Read
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String pathInfo = request.getPathInfo();
		if(pathInfo == null || pathInfo.equals("/")){
			List<Office> allOffices = facade.getAllOffices();
			sendAsJson(response, allOffices);
			return;
		}
		String[] splits = pathInfo.split("/");
		if(splits.length != 2) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		String officeNumber = splits[1];
		Office o = facade.findByOfficeNumber(officeNumber);
		sendAsJson(response, o);



	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * Create
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pathInfo = request.getPathInfo();
		if(pathInfo == null || pathInfo.equals("/")){
			BufferedReader reader = request.getReader();//Läs data Json

			Office o = parseJsonOfficeCreate(reader);

			try {
				o = facade.createOffice(o);
			}catch(Exception e) {

			}
			sendAsJson(response, o);
		}
	}
	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 * Update
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		
		if(pathInfo == null || pathInfo.equals("/")){
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		String[] splits = pathInfo.split("/");
		if(splits.length != 2) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		String id = splits[1];
		BufferedReader reader = request.getReader();

		Office m = parseJsonOfficeUpdate(reader);
		//Uppdatera i db
		try {
			if(facade.findByOfficeNumber(id) != null) {
			m = facade.updateOffice(m);
			System.out.println("Update from facade.");
			}else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}

		}catch(Exception e) {
			System.out.println("facade Update Error");
		}
		sendAsJson(response, m);
	}


	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String pathInfo = request.getPathInfo();
		if(pathInfo == null || pathInfo.equals("/")){
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		String[] splits = pathInfo.split("/");
		if(splits.length != 2) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		String officeNumber = splits[1];
		Office office = facade.findByOfficeNumber(officeNumber);
		if (office != null) {
			facade.deleteOffice(officeNumber);
		}
		sendAsJson(response, office);

	}

	private void sendAsJson(HttpServletResponse response, Office office)
			throws IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		if (office != null) {
			out.print("{\"buildingAddress\":");
			out.print("\"" + office.getBuilding().getAddress() + "\"");
			out.print(",\"officeNumber\":");
			out.print("\"" + office.getOfficeNumber() + "\"");
			out.print(",\"temperatureSetting\":");
			out.print("\"" +office.getTemperatureSetting()+"\"");
			out.print(",\"ventilationSetting\":");
			out.print("\"" +office.getVentilationSetting()+"\"}");

		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		out.flush();
	}

	private void sendAsJson(HttpServletResponse response, List<Office> offices)
			throws IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		if (offices != null) {
			JsonArrayBuilder array = Json.createArrayBuilder();
			for (Office office : offices) {
				JsonObjectBuilder o = Json.createObjectBuilder();
				o.add("buildingAddress", office.getBuilding().getAddress());
				o.add("officeNumber", office.getOfficeNumber());
				o.add("temperatureSetting", office.getTemperatureSetting());
				o.add("ventilationSetting", office.getVentilationSetting());
				array.add(o);
			}
			JsonArray jsonArray = array.build();
			out.print(jsonArray);
		} else {
			out.print("[]");
		}
		out.flush();
	}

	private Office parseJsonOfficeCreate(BufferedReader reader) {
		JsonReader jsonReader = null;
		JsonObject jsonRoot = null;
		jsonReader = Json.createReader(reader);
		jsonRoot = jsonReader.readObject();
		Office office = new Office();
		office.setBuilding(facade.findByAddress(jsonRoot.getString("buildingAddress")));
		office.setTemperatureSetting(Integer.parseInt(jsonRoot.getString("temperatureSetting")));
		office.setVentilationSetting(jsonRoot.getString("ventilationSetting"));

		return office;
	}
	private Office parseJsonOfficeUpdate(BufferedReader br) {
		JsonReader jsonReader = null;
		 JsonObject jsonRoot = null;
		 jsonReader = Json.createReader(br);
		 jsonRoot = jsonReader.readObject();
		 System.out.println("JsonRoot: "+jsonRoot);
		 Office office = new Office();
		 office.setOfficeNumber(jsonRoot.getString("officeNumber"));
		 office.setBuilding(facade.findByAddress(jsonRoot.getString("buildingAddress")));
		 office.setTemperatureSetting(Integer.parseInt(jsonRoot.getString("temperatureSetting")));
		 office.setVentilationSetting(jsonRoot.getString("ventilationSetting"));
		 return office;
	}
	// office: {"buildingAddress": "Örebro", "officeNumber": "O00021", "temperatureSetting": "24"
	//, "ventilationSetting": "V3"}
}
