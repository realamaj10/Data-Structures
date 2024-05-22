package eneaproject;

public class Edge {
     
        Loc source;
        Loc destination;
        int weight;

        public Edge(Loc source, Loc destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }
