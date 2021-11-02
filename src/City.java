/*
 * Name: Seana Aghili
 * EID: sa49738
 */

// Methods may be added to this file, but don't remove anything
// Include this file in your final submission

import java.util.*;

public class City {
    private int minDist;
    private int name;
    private ArrayList<City> neighbors;
    private ArrayList<Integer> weights;
    private int parentCity;
    private int position;
    private boolean inTree;

    public City(int x) {
        name = x;
        minDist = Integer.MAX_VALUE;
        neighbors = new ArrayList<City>();
        weights = new ArrayList<Integer>();
        position = -1;
        parentCity = -1;
        inTree = true;
    }

    public void setPosition(int ind){position = ind; }
    public int getPosition(){ return this.position;
    }

    public void setTreeStatus(boolean inTree)
    {
        this.inTree = inTree;
    }
    public boolean getTreeStatus(){return this.inTree;}
    public void setParent(int c){parentCity = c; }

    public void setNeighborAndWeight(City n, Integer w) {
        neighbors.add(n);
        weights.add(w);
    }

    public ArrayList<City> getNeighbors() {
        return neighbors;
    }

    public ArrayList<Integer> getWeights() {
        return weights;
    }

    public int getMinDist() { return minDist; }

    public void setMinDist(int x) {
        minDist = x;
    }

    public void resetMinDist() {
        minDist = Integer.MAX_VALUE;
    }

    public int getName() {
        return name;
    }
}
