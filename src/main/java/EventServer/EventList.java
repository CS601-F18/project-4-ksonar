package EventServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import Logger.LogData;
import db.DBManager;
/*
 * Get all events listing from db
 * @author ksonar
 */
public class EventList extends HttpServlet{
	private ArrayList<JSONObject> obj = new ArrayList<>();
	private String table = "events";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		LogData.log.info("GET: " + request.getPathInfo());
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_OK);
		DBManager db = DBManager.getInstance();
		
		obj = db.getSelectAllResult(table);
		System.out.println(obj.toString());
		if((obj.size() == 1) && (obj.get(0).containsKey("error"))) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		LogData.log.info("RESPONSE STATUS : " + response.getStatus());
		out.println(obj.toString());
	}
}
