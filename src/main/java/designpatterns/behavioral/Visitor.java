package designpatterns.behavioral;

import java.util.ArrayList;
import java.util.List;

public class Visitor {
    interface Node{
        void display();
    }

    class City implements Node {
        @Override
        public void display() {
            System.out.println("This is a city");
        }
    }

    class IndustrialZone implements Node {
        @Override
        public void display() {
            System.out.println("This is an industrial zone");
        }
    }

    class Stadium implements Node {
        @Override
        public void display() {
            System.out.println("This is an stadium");
        }
    }

    class Graph implements Node {
        List<Node> nodes = new ArrayList<>();

        public List<Node> getNodes() {
            return nodes;
        }

        void addNode(Node node){
            nodes.add(node);
        }

        @Override
        public void display(){
            for(Node node: nodes){
                node.display();
            }
        }
    }

    interface ExporterVisitor{
        void exportGraph(Graph graph);
        void exportCity(City city);
        void exportIndustrialZone(IndustrialZone industrialZone);
        void exportStadium(Stadium stadium);
    }

    class XmlExporter implements ExporterVisitor {

        @Override
        public void exportGraph(Graph graph){
            List<Node> nodes = graph.getNodes();
            for (Node node: nodes) {
                if(node instanceof City){
                    exportCity((City)node);
                }else if(node instanceof IndustrialZone){
                    exportIndustrialZone((IndustrialZone)node);
                }else if(node instanceof Stadium){
                    exportStadium((Stadium)node);
                }else {
                    System.out.println("Unknown node type");
                }

            }
        }

        @Override
        public void exportCity(City city) {
            System.out.println("Exporting city in xml format");
            city.display();
        }

        @Override
        public void exportIndustrialZone(IndustrialZone industrialZone) {
            System.out.println("Exporting industrial zone in xml format");
            industrialZone.display();
        }

        @Override
        public void exportStadium(Stadium stadium) {
            System.out.println("Exporting stadium in xml format");
            stadium.display();
        }
    }

    void visitorDemo(){
        Node city = new City();
        Node industrialZone = new IndustrialZone();
        Node stadium = new Stadium();

        Graph graph = new Graph();
        graph.addNode(city);
        graph.addNode(industrialZone);
        graph.addNode(stadium);

        ExporterVisitor exporterVisitor = new XmlExporter();
        exporterVisitor.exportGraph(graph);

    }

    public static void main(String[] args) {
        Visitor visitor = new Visitor();
        visitor.visitorDemo();
    }
}
