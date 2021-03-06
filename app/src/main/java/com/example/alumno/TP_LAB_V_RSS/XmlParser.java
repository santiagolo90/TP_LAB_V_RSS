package com.example.alumno.TP_LAB_V_RSS;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XmlParser {

    public static List<Noticia> obtenerNoticias(String xml,String fuente){
        List<Noticia> noticias = new ArrayList<>();

        XmlPullParser xmlPullParser = Xml.newPullParser();
        try {
            xmlPullParser.setInput(new StringReader(xml));

            int event = xmlPullParser.getEventType();
            Noticia n = null;
            while (event != XmlPullParser.END_DOCUMENT){

                if (event == XmlPullParser.START_TAG){

                    if ("item".equals(xmlPullParser.getName())){
                        n = new Noticia();
                    }

                    if ("title".equals(xmlPullParser.getName()) && n != null){
                        n.setTitulo(xmlPullParser.nextText());
                    }

                    if ("link".equals(xmlPullParser.getName())&& n != null){
                        n.setLink(xmlPullParser.nextText());
                    }
                    if ("description".equals(xmlPullParser.getName())&& n != null){
                        n.setDescripcion(xmlPullParser.nextText());
                    }

                    if ("enclosure".equals(xmlPullParser.getName())&& n != null){
                        n.setFoto(xmlPullParser.getAttributeValue(null,"url"));
                    }

                    if ("pubDate".equals(xmlPullParser.getName())&& n != null){
                        n.setFecha(xmlPullParser.nextText());

                    }

                    if (n != null){
                        n.setFuente(fuente);
                    }
                    /*
                    if ("creator".equals(xmlPullParser.getName())&& n != null){
                        n.setFuente(xmlPullParser.nextText());
                        n.setFuente(fuente);
                    }
                    */
                }
                if (event == XmlPullParser.END_TAG){
                    if ("item".equals(xmlPullParser.getName()) && n != null) {
                        noticias.add(n);
                        n = null;
                    }
                }
                event = xmlPullParser.next();
            }

            return noticias;

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

}
