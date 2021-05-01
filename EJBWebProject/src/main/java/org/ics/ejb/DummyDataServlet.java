package org.ics.ejb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ics.facade.FacadeLocal;

/**
 * Servlet implementation class DummyDataServlet
 */
@WebServlet("/DummyDataServlet")
public class DummyDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	FacadeLocal facade;
	
	public int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DummyDataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html><html><head>");
		out.println("<title>FlightTracker Lite</title>");
		out.println("<meta charset=\"ISO-8859-1\">");
		out.println("</head><body>");
		
		out.println("<h2>Technological unemployment</h2>");
		out.println("<h2>Generating dummy data...</h2>");
		
		List<Building> buildings = facade.getAllBuildings();
		out.println("<h3>Generating 40 offices per building, " + buildings.size() + " buildings.</h3>");
		for(Building building : buildings) {
			out.println("<h4>" + building.getAddress() + "</h4>");
			for (int i = 0; i<= 40; i++) {
				int temperatureSetting = this.getRandomNumber(16, 25);
				String ventilationSetting = "V" + this.getRandomNumber(1, 5);
				Office office = new Office(ventilationSetting, temperatureSetting, building);
				facade.createOffice(office);
				out.println("<p>Office " + office.getOfficeNumber() + ", " 
						+ office.getVentilationSetting() + ", " 
						+ office.getTemperatureSetting() + " degrees C.</p>");
			}
		}
	}

}
