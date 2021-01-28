package com.example.aplicacion;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class Dades {

    static ArrayList recordList = MainActivity.record;

    public static void Dades(File records){

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element eRaiz = doc.createElement("Records");
            doc.appendChild(eRaiz);

            // definimos el nodo que contendrá los elementos
            Element eCoche = doc.createElement("Usuario");
            eRaiz.appendChild(eCoche);

            // definimos cada uno de los elementos y le asignamos un valor
            Element eMarca = doc.createElement("Name");


            Element eModelo = doc.createElement("Attempts");

            Element eCilindrada = doc.createElement("Time");

            // clases necesarias finalizar la creación del archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("ejercicio3.xml"));

            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
