package com.javarush.task.task33.task3309;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringWriter;

/*
Комментарий внутри xml
*/
public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) throws JAXBException {
        StringWriter xml = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(obj, xml);

        String commentFormat = String.format("<!--%s-->", comment);
        String tag = String.format("<%s>", tagName);
        if (xml.toString().contains(tag)){
            String newXml = xml.toString().replaceAll(tag, commentFormat+"\n\t"+tag);
            xml = new StringWriter();
            xml.write(newXml);
        }

        return xml.toString();
    }

    public static void main(String[] args) throws JAXBException {
//        Cat cat = new Cat("Murka", 10);
//        System.out.println(toXmlWithComment(cat, "name", "this is a comment"));
    }

    /*
    @XmlRootElement
    private static class Cat{
        public String name;
        public int age;

        public Cat(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Cat() {
        }
    }
    */
}
