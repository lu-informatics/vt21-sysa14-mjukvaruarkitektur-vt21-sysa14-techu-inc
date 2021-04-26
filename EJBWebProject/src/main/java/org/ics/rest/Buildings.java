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
import org.ics.facade.FacadeLocal;

/**
 * Servlet implementation class BuildingServlet
 */
@WebServlet("/Buildings/*")
public class Buildings extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	FacadeLocal facade;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Buildings() {
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
			List<Building> allBuildings = facade.getAllBuildings();
			sendAsJson(response, allBuildings);
			return;
		}
		String[] splits = pathInfo.split("/");
		if(splits.length != 2) {
		 response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		 return;
		}
		String buildingAddress = splits[1];
		Building b = facade.findByAddress(buildingAddress);
		sendAsJson(response, b);
		
		
		
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

			Building b = parseJsonBuilding(reader);

			try {
				b = facade.createBuilding(b);
			}catch(Exception e) {
		
			}
			sendAsJson(response, b);
		}
	}
	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 * Update
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		//Perhaps add "String id = splits[1];" if it doesn't work.
		BufferedReader reader = request.getReader();

		Building b = parseJsonBuilding(reader);
		try {
			b = facade.updateBuilding(b);
		}catch(Exception e) {
			System.out.println("facade Update Error");
		}
		sendAsJson(response, b);
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
		String address = splits[1];
		Building building = facade.findByAddress(address);
		if (building != null) {
			facade.deleteBuilding(address);
		}
		sendAsJson(response, building);

	}
	
	private void sendAsJson(HttpServletResponse response, Building building)
			 throws IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		if (building != null) {
			out.print("{\"address\":");
			out.print("\"" + building.getAddress() + "\"}");
		} else {
			out.print("{ }");
		}
		out.flush();
	}
	
	private void sendAsJson(HttpServletResponse response, List<Building> buildings)
			 throws IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		if (buildings != null) {
			JsonArrayBuilder array = Json.createArrayBuilder();
			for (Building b : buildings) {
				JsonObjectBuilder o = Json.createObjectBuilder();
				o.add("address", b.getAddress());
				array.add(o);
			}
			JsonArray jsonArray = array.build();
			out.print(jsonArray);
		} else {
			out.print("[]");
		}
		out.flush();
	}
	
	private Building parseJsonBuilding(BufferedReader reader) {
		JsonReader jsonReader = null;
		JsonObject jsonRoot = null;
		jsonReader = Json.createReader(reader);
		jsonRoot = jsonReader.readObject();
		Building building = new Building();
		building.setAddress(jsonRoot.getString("address"));

		return building;
	}

}
