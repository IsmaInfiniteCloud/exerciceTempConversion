package control;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TemperatureConverterServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // Get the value of the temperature and unit choice from the request
            String temperatureString = request.getParameter("temperature");
            String unit = request.getParameter("unit");

            double temperature = Double.parseDouble(temperatureString);
            double convertedTemperature = 0;

            // Convert the temperature based on the chosen unit
            if (unit.equals("Celsius")) {
                convertedTemperature = (temperature * 9/5) + 32;
            } else if (unit.equals("Fahrenheit")) {
                convertedTemperature = (temperature - 32) * 5/9;
            }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TraitementCasesEtRadio</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Resume de ce qui a ete saisie</h1>");
            out.println("<h2> nom : " + convertedTemperature + "</h2>");          
            out.println("</body>");
            out.println("</html>");
            // Store the result of the conversion in the request object
            request.setAttribute("convertedTemperature", convertedTemperature);

            // Redirect to the JSP for display of the result
            request.getRequestDispatcher("result.html").forward(request, response);
        } catch (NumberFormatException ex) {
            // Handle invalid temperature input
            ex.printStackTrace();
            request.setAttribute("errorMessage", "Invalid temperature input");
            request.getRequestDispatcher("index.html").forward(request, response);
        }
    }
    
    
    
    
          @Override
                 protected void doPost(HttpServletRequest request, HttpServletResponse response)
                 throws ServletException, IOException {
             processRequest(request, response);
            }
  }
