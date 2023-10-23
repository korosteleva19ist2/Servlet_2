package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/math")
public class MathServlet extends HttpServlet {
    Gson gson=new GsonBuilder().setPrettyPrinting().create(); //GsonBuilder() используют для корректного возращения json

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuffer jb= new StringBuffer();
        String line;
        try{
            BufferedReader reader=request.getReader();
            while((line=reader.readLine()) != null)
            {
                jb.append(line);
            }
        }
        catch(Exception e)
        {
            System.out.println("Error");
        }

        JsonObject jobj=gson.fromJson(String.valueOf(jb), JsonObject.class);

        request.setCharacterEncoding("utf-8");

        double a=jobj.get("a").getAsDouble();
        double b=jobj.get("b").getAsDouble();
        String operation=jobj.get("operation").getAsString();
        double result=calc(a,b,operation);

        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw=response.getWriter();

        Map<String,Double> map=new HashMap<String,Double>();
        map.put("result",result);

        pw.print(gson.toJson(map));
    }

    public double calc(double a, double b, String c)
    {
        double result=0;
        switch (c)
        {
            case "+":
                result= a+b;
                break;
            case "-":
                result= a-b;
                break;
            case "*":
                result= a*b;
                break;
            case "/":
                result= a/b;
                break;

        }
        return result;

    }
}
