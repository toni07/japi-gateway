package com.cyranis.japi;

import com.cyranis.japi.dao.WebServiceDAO;
import com.cyranis.japi.model.Webservice;
import com.cyranis.japi.util.Configuration;
import com.cyranis.japi.util.FileHelper;
import com.cyranis.japi.util.JsonTransformer;
import com.cyranis.japi.util.ShellHelper;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static spark.Spark.*;

/**
 * @author toni07 on 22/06/2017
 * http://www.restapitutorial.com/lessons/httpmethods.html
 */
public class Launcher {

	/**
	 * **************************************************************************************
	 * attributes
	 * **************************************************************************************
	 */

	/**
	 * **************************************************************************************
	 * constructors
	 * **************************************************************************************
	 */

	/**
	 * **************************************************************************************
	 * methods
	 * **************************************************************************************
	 */
	public static void main(String[] args) {

		port(Integer.valueOf(args[0]));
		externalStaticFileLocation("src/main/resources/public/");

		final WebServiceDAO webServiceDAO = new WebServiceDAO();
		/**
		 *
		 */
		path("/admin", () -> {
			before("/*", (q, a) -> System.out.println("in Admin GET method"));
			path("/ws", () -> {
				get("/list",    (request, res) ->
				{
					res.header("Content-Type", "application/json; charset=UTF-8");
					final Map<String, Object> objectMap = new HashMap<>(2);
					objectMap.put("result", true);
					objectMap.put("msg", "Ok!");
					objectMap.put("objects", webServiceDAO.list());
					System.out.println("objectMap: " + objectMap);
					return objectMap;
				}, new JsonTransformer());
				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				post("/",    (req, res) ->
				{
					req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
					res.header("Content-Type", "application/json; charset=UTF-8");
					final String label = req.queryParams("label");
					final String urlFrom = req.queryParams("url_from");
					final String urlTo = req.queryParams("url_to");
					final String cacheTimeInMsStr = req.queryParams("cache_time_in_ms");
					final Part jarFile = req.raw().getPart("jar_file");
					final int cacheTimeInMs = (0 != cacheTimeInMsStr.length()) ? Integer.valueOf(cacheTimeInMsStr) : 0;
					final String jarFilePath = Configuration.PATH_WS_JAR + "/" + jarFile.getSubmittedFileName();
					try (InputStream is = jarFile.getInputStream()) {
						FileHelper.move(is, Paths.get(jarFilePath));
					}
					System.out.println("in Admin add WS method: " + label);
					final Webservice webservice = new Webservice(0, label, urlFrom, urlTo, cacheTimeInMs, jarFilePath);
					webServiceDAO.add(webservice);
					return "{result: true}";
				});
				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				put("/",  (request, res) ->   {System.out.println("in Admin update WS method");return ""; });
				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				put("/restart",  (req, res) ->
				{
					res.header("Content-Type", "application/json; charset=UTF-8");
					final String idWsStr = req.queryParams("id_ws");
					final int idWs = (0 != idWsStr.length()) ? Integer.valueOf(idWsStr) : 0;
					System.out.println("in Admin restart method: " + idWs);
					final Webservice webservice = webServiceDAO.getById(idWs);
					ShellHelper.executeCommand("cd /appli/webservices && nohup "+ Configuration.PATH_JAVA_EXE +" -classpath myfile.jar com.cyranis.webservice.toolbox.App 19080 &");
					return "{result: true}";
				});
				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				delete("/",  (req, res) ->   {
					res.header("Content-Type", "application/json; charset=UTF-8");
					final String idWsStr = req.queryParams("id_ws");
					final int idWs = (0 != idWsStr.length()) ? Integer.valueOf(idWsStr) : 0;
					System.out.println("in Admin delete method: " + idWs);
					webServiceDAO.delete(idWs);
					return "{result: true}";
				});
			});
			path("/username", () -> {
				post("/add",       (request, res) ->   {System.out.println("in Admin ADD method");return ""; });
				put("/change",     (request, res) ->   {System.out.println("in Admin ADD method");return ""; });
				delete("/remove",  (request, res) ->   {System.out.println("in Admin ADD method");return ""; });
			});
		});

		/**
		 *
		 */
		get("*", (request, res) ->
		{
			System.out.println("in GET method");
			final String uri = request.uri();// the uri, e.g. "/foo/toto"
			final String[] urlSplit = uri.split("/");
			System.out.println("URL urlSplit[1]: " + urlSplit[1]);
			if("admin".equals(urlSplit[1])){
				System.out.println("Admin URL, skipping...");
			}
			else{
				final Map<String, Object> webService = webServiceDAO.getByUri(uri);
				if(null != webService){
					System.out.println("found corresponding WS!");
					final String requestMethod = request.requestMethod();// The HTTP method (GET, ..etc)
					final Map<String, String> cookies = request.cookies();// request cookies sent by the client
					final Set<String> headers = request.headers();// the HTTP header list
					final String header1 = request.headers("User-Agent");// value of BAR header
					final Map<String, String> params = request.params();// map with all parameters
				}
				else{
					System.err.println("no corresponding WS found for `url_from` " + uri);
				}
			}

			return "API Management";
		});

		exception(Exception.class, (exception, request, response) ->
		{
			System.err.println("error occured");
			exception.printStackTrace();
		});

	}

	/**
	 * **************************************************************************************
	 * getters & setters
	 * **************************************************************************************
	 */
}
