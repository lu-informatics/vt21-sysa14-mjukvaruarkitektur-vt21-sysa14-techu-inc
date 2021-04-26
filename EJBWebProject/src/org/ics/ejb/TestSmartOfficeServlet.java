package org.ics.ejb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ics.facade.FacadeLocal;


/**
 * Servlet implementation class SoftwareArchitectureServlet
 */
@WebServlet("/SoftwareArchitectureServlet")
public class TestSmartOfficeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private FacadeLocal facade;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestSmartOfficeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				
		
		
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html><html><head>");
		out.println("<title>Technological Unemployment</title>");
		out.println("<meta charset=\"ISO-8859-1\">");
		out.println("</head><body>");
		
		out.println("<h2>Technological unemployment</h2>");
		out.println("<h2>Smart Office 2.0</h2>");
		Building checkBuilding = facade.findByAddress("Trelleborg");
		if (checkBuilding == null) {

			Building b = new Building();
			b.setAddress("Trelleborg");
			facade.createBuilding(b);
			out.print("<h4>Created building.</h4>");
		}
		Building building = facade.findByAddress("Göteborg");
		if(building != null) {
			out.print("<p>" + building.getAddress() + " </p>");
			building.setAddress("Jokkmokk");
			facade.updateBuilding(building);
			out.print("<h4>Updated building.</h4>");
			
			building = facade.findByAddress("Borås");
			out.print("<p>" + building.getAddress() + "</p>");	
			out.print("<h4>Found building.</h4>");


			Office office = new Office();
			office.setBuilding(building);
			office.setTemperatureSetting(23);
			office.setVentilationSetting("V4");
			facade.createOffice(office);
			out.print("<h4>Created office.</h4>");

			
			office = facade.findByOfficeId("O000001");
			office.setTemperatureSetting(20);
			office.setVentilationSetting("V3");
			facade.updateOffice(office);
			out.print("<h4>Updated office.</h4>");

			
			//facade.deleteOffice("O000031");
			//out.print("<h4>Deleted office.</h4>");

			//			out.print("<h2>**** Building Delete ****</h2>");
			//			facade.deleteBuilding("Trelleborg");

			out.println("<p>The building:" + building.getAddress()+" has the following offices:<br>");
			for(Office o1: building.getOffices() ) {
				out.println("<p>Office: "+ o1.getOfficeNumber() + " ventilation setting: "+ o1.getVentilationSetting() + " temperature setting: " + o1.getTemperatureSetting() +  " degrees celsius.</p>");
			}
		} else {
			out.print("<p>" + "Filmen finns inte." + "</p>");
		}
		out.println("</body></html>");
		out.close();
	}

}
