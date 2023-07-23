package conversor;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/conversor")
public class ConversorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ConversorServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Obtém os parâmetros do formulário
		String tipoConversao = request.getParameter("tipo-conversao");
		 double valor = Double.parseDouble(request.getParameter("valor"));

		    // Realiza a conversão
		    int resultado = 0;
		    if (tipoConversao.equals("fahrenheit-celsius")) {
		        resultado = (int) ((valor - 32) * 5 / 9);
		    } else if (tipoConversao.equals("celsius-fahrenheit")) {
		        resultado = (int) ((valor * 9 / 5) + 32);
		    }
		    
		    
		
		// Gera a resposta em HTML
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Resultado da Conversão</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Resultado da Conversão:</h1>");
		out.println("<p>Valor original: " + valor + "</p>");
		out.println("<p>Valor convertido: " + resultado + "</p>");
		out.println("</body>");
		out.println("</html>");
	}
}
