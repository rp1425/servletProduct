package productservlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import productservlet.dao.ProductDao;
import productservlet.dto.Product;

public class Productcontroller extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String brand = req.getParameter("brand");
		String manufacture = req.getParameter("manufacture");
		String state = req.getParameter("state");
		long price = Long.parseLong(req.getParameter("price"));
		
		ServletContext context = getServletContext();
		Double cgst = Double.parseDouble(context.getInitParameter("cgst"));
		
		ServletConfig config =getServletConfig();
		Double kar = Double.parseDouble(config.getInitParameter("KAR"));
		
		Double tn = Double.parseDouble(config.getInitParameter("TN"));
		
		Product product = new Product();
		PrintWriter writer =resp.getWriter();
		
		if(state.equals("KAR")) {
			price += (cgst+kar)*price;
			product.setPrice(price);
			writer.print(price);
		}else if(state.equals("TN")) {
			price += (cgst+tn)*price;
			product.setPrice(price);
			writer.print(price);
			
		}
		
		product.setName(name);
		product.setBrand(brand);
		product.setManufacture(manufacture);
		product.setState(state);
		
		
		ProductDao dao = new ProductDao();
		dao.save(product);
		
	}

}
