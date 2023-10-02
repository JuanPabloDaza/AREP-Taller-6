package edu.escuelaing.arep.app;

import static spark.Spark.*;

import java.io.IOException;

public class RoundRobinServer {

    public static void main(String... args) {
        port(getPort());
        staticFiles.location("/public");
        get("/log", (req, res) -> getLogMessage(req.queryParams("value")));
    }

    
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4568;
    }

    private static String getLogMessage(String value) throws IOException{
        return HttpConnectionExample.remoteLogCall(value);
    }


    public static String getIndex() {
        String response = 
                "\r\n"
                + "<!DOCTYPE html>\n"
                + "<html\n>"
                + "    <head\n>"
                + "        <title>Taller 5</title\n>"
                + "        <meta charset=\"UTF-8\"\n>"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    </head>\n"
                + "    <body>\n"
                + "        <h1>Servidor con Spark</h1>\n"
                + "        <h2>Funciones disponibles:</h2>\n"
                + "        <form action=\"/sin\">\n"
                + "            <label for=\"Sin\">Seno:</label><br>\n"
                + "            <input type=\"text\" id=\"sin\" name=\"seno\" value=\"\"><br><br>\n"
                + "            <input type=\"button\" value=\"Enviar\" onclick=\"loadGetSinMsg()\">\n"
                + "        </form>\n"
                + "        <div id=\"getrespsinmsg\"></div>\n"
                + "      <script>\n"
                + "            function loadGetSinMsg() {\n"
                + "                let nameVar = document.getElementById(\"sin\").value;\n"
                + "                const xhttp = new XMLHttpRequest();\n"
                + "                xhttp.onload = function() {\n"
                + "                    document.getElementById(\"getrespsinmsg\").innerHTML =\n"
                + "                    this.responseText;\n"
                + "                } \n"
                + "                xhttp.open(\"GET\", \"sen?value=\"+nameVar);\n"
                + "                xhttp.send();\n"
                + "            }\n"
                + "      </script>\n"
                + "        <form action=\"/cos\">\n"
                + "            <label for=\"Cos\">Coseno:</label><br>\n"
                + "            <input type=\"text\" id=\"cos\" name=\"cos\" value=\"\"><br><br>\n"
                + "            <input type=\"button\" value=\"Enviar\" onclick=\"loadGetCosMsg()\">\n"
                + "        </form>\n"
                + "        <div id=\"getrespcosmsg\"></div>\n"
                + "      <script>\n"
                + "            function loadGetCosMsg() {\n"
                + "                let nameVar = document.getElementById(\"cos\").value;\n"
                + "                const xhttp = new XMLHttpRequest();\n"
                + "                xhttp.onload = function() {\n"
                + "                    document.getElementById(\"getrespcosmsg\").innerHTML =\n"
                + "                    this.responseText;\n"
                + "                } \n"
                + "                xhttp.open(\"GET\", \"cos?value=\"+nameVar);\n"
                + "                xhttp.send();\n"
                + "            }\n"
                + "      </script>\n"
                + "        <form action=\"/palindromo\">\n"
                + "            <label for=\"Cos\">Palabra palindroma:</label><br>\n"
                + "            <input type=\"text\" id=\"palindromo\" name=\"palindromo\" value=\"\"><br><br>\n"
                + "            <input type=\"button\" value=\"Enviar\" onclick=\"loadGetPalMsg()\">\n"
                + "        </form>\n"
                + "        <div id=\"getresppalmsg\"></div>\n"
                + "      <script>\n"
                + "            function loadGetPalMsg() {\n"
                + "                let nameVar = document.getElementById(\"palindromo\").value;\n"
                + "                const xhttp = new XMLHttpRequest();\n"
                + "                xhttp.onload = function() {\n"
                + "                    document.getElementById(\"getresppalmsg\").innerHTML =\n"
                + "                    this.responseText;\n"
                + "                } \n"
                + "                xhttp.open(\"GET\", \"palindromo?value=\"+nameVar);\n"
                + "                xhttp.send();\n"
                + "            }\n"
                + "      </script>\n"
                + "        <form action=\"/magnitud\">\n"
                + "            <label for=\"Magnitud\">Magnitud de dos dimensiones:</label><br>\n"
                + "            <input type=\"text\" id=\"dimension1\" name=\"dimension1\" value=\"\"><br><br>\n"
                + "            <input type=\"text\" id=\"dimension2\" name=\"dimension2\" value=\"\"><br><br>\n"
                + "            <input type=\"button\" value=\"Enviar\" onclick=\"loadGetMagMsg()\">\n"
                + "        </form>\n"
                + "        <div id=\"getrespmagmsg\"></div>\n"
                + "      <script>\n"
                + "            function loadGetMagMsg() {\n"
                + "                let dim1 = document.getElementById(\"dimension1\").value;\n"
                + "                let dim2 = document.getElementById(\"dimension2\").value;\n"
                + "                const xhttp = new XMLHttpRequest();\n"
                + "                xhttp.onload = function() {\n"
                + "                    document.getElementById(\"getrespmagmsg\").innerHTML =\n"
                + "                    this.responseText;\n"
                + "                } \n"
                + "                xhttp.open(\"GET\", \"magnitud?valueA=\" + dim1 + \"&valueB=\" + dim2);\n"
                + "                xhttp.send();\n"
                + "            }\n"
                + "      </script>\n"
                + "    </body>\n"
                + "</html>\n";
        return response;
    }
}