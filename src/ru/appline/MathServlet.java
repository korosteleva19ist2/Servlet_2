package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
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
    //Model model = Model.getInstance();
    Gson gson=new GsonBuilder().setPrettyPrinting().create(); //GsonBuilder() используют для корректного возращения json

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
                //чтобы мы могли считывать тело нашего запроса
                StringBuffer jb= new StringBuffer();
                //построчно будем записывать наш запрос в стрингбилдинг?
                String line;
                try{
                    //будем читать данные из body (нашего request)
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

                //JsonObject jobj=gson.fromJson(jb.toString(), JsonObject.class);
                //JsonElement jobj=gson.fromJson(String.valueOf(jb), JsonObject.class);
                request.setCharacterEncoding("utf-8");

//        double a= Double.parseDouble(request.getParameter("a"));
//        double b= Double.parseDouble(request.getParameter("b"));
//        String operation=request.getParameter("operation");
                //парсим json на получение данных
                double a=jobj.get("a").getAsDouble();
                double b=jobj.get("b").getAsDouble();
                String operation=jobj.get("operation").getAsString();
                double result=calc(a,b,operation);



                //сервлет должен возвращать данные в формате json
                response.setContentType("application/json;charset=utf-8");
                PrintWriter pw=response.getWriter();
        //jobj.addProperty("result",result);

        Map<String,Double> map=new HashMap<String,Double>();
        map.put("result",result);
        String mape = this.gson.toJson(map);
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
