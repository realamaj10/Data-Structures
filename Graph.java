package eneaproject;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import javafx.util.Pair;

    public class Graph {
        int vertices;
        LinkedList<Edge>[] adjacencylist;

        Graph(int vertices) {
            this.vertices = vertices;
            adjacencylist = new LinkedList[vertices];
           
            for (int i = 0; i <vertices ; i++) {
                adjacencylist[i] = new LinkedList<>();
            }
        }

        public void addEdge(Loc source, Loc destination, int weight) {
           Edge edge = new Edge(source, destination, weight);
            adjacencylist[source.x].addFirst(edge);

            edge = new Edge(destination, source, weight);
            adjacencylist[destination.x].addFirst(edge); //for undirected graph
        }

        public void dijkstra_GetMinDistances(int sourceVertex){

            boolean[] SPT = new boolean[vertices];
            //distance used to store the distance of vertex from a source
            int [] distance = new int[vertices];

            //Initialize all the distance to infinity
            for (int i = 0; i <vertices ; i++) {
                distance[i] = Integer.MAX_VALUE;
            }
            //Initialize priority queue
            //override the comparator to do the sorting based keys
            PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(vertices, new Comparator<Pair<Integer, Integer>>() {
                @Override
                public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
                    //sort using distance values
                    int key1 = p1.getKey();
                    int key2 = p2.getKey();
                    return key1-key2;
                }
            });
            //create the pair for for the first index, 0 distance 0 index
            distance[0] = 0;
            Pair<Integer, Integer> p0 = new Pair<>(distance[0],0);
            //add it to pq
            pq.offer(p0);

            //while priority queue is not empty
            while(!pq.isEmpty()){
                //extract the min
                Pair<Integer, Integer> extractedPair = pq.poll();

                //extracted vertex
                int extractedVertex = extractedPair.getValue();
                if(SPT[extractedVertex]==false) {
                    SPT[extractedVertex] = true;

                    //iterate through all the adjacent vertices and update the keys
                    LinkedList<Edge> list = adjacencylist[extractedVertex];
                    for (int i = 0; i < list.size(); i++) {
                        Edge edge = list.get(i);
                        Loc destination = edge.destination;
                        //only if edge destination is not present in mst
                        if (SPT[destination.x] == false) {
                            ///check if distance needs an update or not
                            //means check total weight from source to vertex_V is less than
                            //the current distance value, if yes then update the distance
                            int newKey =  distance[extractedVertex] + edge.weight ;
                            int currentKey = distance[destination.x];
                            if(currentKey>newKey){
                                Pair<Integer, Integer> p = new Pair<>(newKey, destination.x);
                                pq.offer(p);
                                distance[destination.x] = newKey;
                            }
                        }
                    }
                }
            }
            //print Shortest Path Tree
            printDijkstra(distance, sourceVertex);
        }

        public void printDijkstra(int[] distance, int sourceVertex){
            System.out.println("Dijkstra Algorithm: (Adjacency List + Priority Queue)");
            for (int i = 0; i <vertices ; i++) {
                System.out.println("Source Vertex: " + sourceVertex + " to vertex " +   + i +
                        " distance: " + distance[i]);
            }
        }
}
