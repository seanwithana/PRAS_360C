/*
 * Name: Seana Aghili
 * EID: sa49738
 */

// Implement your algorithms here
// Methods may be added to this file, but don't remove anything
// Include this file in your final submission

import java.util.ArrayList;
import java.util.Collection;

public class Program2 {
    private ArrayList<City> cities;  // this is a list of all Cities, populated by Driver class
    private Heap minHeap;

    // additional constructor fields may be added, but don't delete or modify anything already here
    public Program2(int numCities) {
        minHeap = new Heap();
        cities = new ArrayList<City>();
    }

    /**
     * findMinimumRouteDistance(City start, City dest)
     *
     * @param start - the starting City.
     * @param dest  - the end (destination) City.
     * @return the minimum distance possible to get from start to dest.
     * Assume the given graph is always connected.
     */
    public int findMinimumRouteDistance(City start, City dest) {
        // TODO: implement this function
        Heap minHeap = new Heap();

        //Set first city to have distance of 0
        start.setMinDist(0);
        minHeap.buildHeap(cities);

        ArrayList<City> neighborsInit = start.getNeighbors();
        ArrayList<Integer> weightsInit = start.getWeights();
        minHeap.delete(start.getPosition());
        //Change the min-dists
        for(int i = 0; i < start.getNeighbors().size(); i ++){
            //neighborsInit.get(i).setParent(start);
            minHeap.changeKey(neighborsInit.get(i), weightsInit.get(i));
        }

        while(minHeap.getSize() != 0)
        {
            City city = minHeap.findMin();
            ArrayList<City> neighbors = city.getNeighbors();
            ArrayList<Integer> weights = city.getWeights();
            minHeap.delete(city.getPosition());

            //Change the min-dists
            for(int i = 0; i < city.getNeighbors().size(); i ++){
                //If u.d > v.d + w(v, u)
                if(neighbors.get(i).getMinDist() > weights.get(i) + city.getMinDist()){
                    //neighbors.get(i).setParent(city);
                    minHeap.changeKey(neighbors.get(i), weights.get(i) + city.getMinDist());
                }
            }
        }
        return dest.getMinDist();
    }

    /**
     * findMinimumLength()
     *
     * @return The minimum total optical line length required to connect (span) each city on the given graph.
     * Assume the given graph is always connected.
     */
    public int findMinimumLength() {
        // TODO: implement this function
        if(cities.size() < 2){
            return 0;
        }

        Heap minHeap = new Heap();
        int totalWeight = 0;
        //ArrayList<City> MST = new ArrayList<City>();

        //Set arbitrary first city to have distance of 0
        cities.get(0).setMinDist(0);
        minHeap.buildHeap(cities);

        while(minHeap.getSize() != 0)
        {
            if(minHeap.getSize() == 1)
            {
                City minCity = minHeap.findMin();
                totalWeight += minCity.getMinDist();
                return totalWeight;
            }
            City minCity = minHeap.findMin();
            totalWeight += minCity.getMinDist();
            minCity = minHeap.extractMin();
            //MST.add(minCity);
            ArrayList<City> neighbors = minCity.getNeighbors();
            ArrayList<Integer> weights = minCity.getWeights();

            for(int i = 0; i < neighbors.size(); i++){
                if(neighbors.get(i).getTreeStatus() && neighbors.get(i).getMinDist() > weights.get(i)){
                    minHeap.changeKey(neighbors.get(i), weights.get(i));
                }
            }
        }
        return -1;
    }

    //returns edges and weights in a string.
    public String toString() {
        String o = "";
        for (City v : cities) {
            boolean first = true;
            o += "City ";
            o += v.getName();
            o += " has neighbors ";
            ArrayList<City> ngbr = v.getNeighbors();
            for (City n : ngbr) {
                o += first ? n.getName() : ", " + n.getName();
                first = false;
            }
            first = true;
            o += " with distances ";
            ArrayList<Integer> wght = v.getWeights();
            for (Integer i : wght) {
                o += first ? i : ", " + i;
                first = false;
            }
            o += System.getProperty("line.separator");

        }
        return o;
    }

///////////////////////////////////////////////////////////////////////////////
//                           DANGER ZONE                                     //
//                everything below is used for grading                       //
//                      please do not change :)                              //
///////////////////////////////////////////////////////////////////////////////

    public Heap getHeap() {
        return minHeap;
    }

    public ArrayList<City> getAllCities() {
        return cities;
    }

    // used by Driver class to populate each City with correct neighbors and corresponding weights
    public void setEdge(City curr, City neighbor, Integer weight) {
        curr.setNeighborAndWeight(neighbor, weight);
    }

    // used by Driver.java and sets cities to reference an ArrayList of all RestStops
    public void setAllNodesArray(ArrayList<City> x) {
        cities = x;
    }
}
