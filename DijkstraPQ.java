
package eneaproject;


public class DijkstraPQ {
  
        public static void main(String[] args) {
            int vertices = 4;
            Graph graph = new Graph(vertices);
            
            Loc l1 = new Loc("Dajti", 0);
            Loc l2 = new Loc("Mon Cheri", 1);
            Loc l3 = new Loc("Era", 2);
            Loc l4 = new Loc("Anisa", 3);
          
            
            graph.addEdge(l1, l2, 4);
            graph.addEdge(l1, l3, 3);
            graph.addEdge(l2, l3, 1);
            graph.addEdge(l2, l4, 2);
    
            graph.dijkstra_GetMinDistances(0);
        }

    }
