/*
 * Name: Seana Aghili
 * EID: sa49738
 */

// Implement your heap here
// Methods may be added to this file, but don't remove anything
// Include this file in your final submission

import java.util.ArrayList;

public class Heap {
    private ArrayList<City> minHeap;

    public Heap() {
        minHeap = new ArrayList<City>();
    }


    /**
     * buildHeap(ArrayList<City> cities)
     * Given an ArrayList of Cities, build a min-heap keyed on each City's minDist
     * Time Complexity - O(nlog(n)) or O(n)
     *
     * @param cities
     */
    public void buildHeap(ArrayList<City> cities) {
        // TODO: implement this method
//        City placeholder = new City(-1);
//        placeholder.setIndex(-1);
//        placeholder.setMinDist(-1);
//        minHeap.add(placeholder);
        //Add each city into heap
        for(int c = 0; c < cities.size(); c++)
        {
            //base case
            if(minHeap.size() == 0)
            {
                minHeap.add(cities.get(c));
                cities.get(c).setPosition(c);
            }
            else
            {
                minHeap.add(cities.get(c));
                cities.get(c).setPosition(c);
                int indexUpdate = minHeap.size() - 1;
                //Add in Log(n) time to maintain min-heap property

                //                while(minHeap.get(indexUpdate).getMinDist() < minHeap.get((indexUpdate - 1) / 2).getMinDist()){
                while(minHeap.get(indexUpdate).getMinDist() <= minHeap.get((indexUpdate - 1) / 2).getMinDist()){
                    ////////////////////////
                    boolean done = false;
                    if(minHeap.get(indexUpdate).getMinDist() == minHeap.get((indexUpdate - 1) / 2).getMinDist()){
                        if(minHeap.get(indexUpdate).getName() < minHeap.get((indexUpdate - 1) / 2).getName()){
                            minHeap.get(indexUpdate).setPosition((indexUpdate - 1) / 2);
                            minHeap.get((indexUpdate - 1) / 2).setPosition(indexUpdate);

                            //flip city values
                            City fooNode = minHeap.get(indexUpdate);
                            minHeap.set(indexUpdate, minHeap.get((indexUpdate - 1) / 2));
                            minHeap.set((indexUpdate - 1) / 2, fooNode);

                            indexUpdate = (indexUpdate - 1) / 2;
                            done = true;
                        }
                        else{break;}
                    }
                    if(!done){
                        minHeap.get(indexUpdate).setPosition((indexUpdate - 1) / 2);
                        minHeap.get((indexUpdate - 1) / 2).setPosition(indexUpdate);

                        //flip city values
                        City fooNode = minHeap.get(indexUpdate);
                        minHeap.set(indexUpdate, minHeap.get((indexUpdate - 1) / 2));
                        minHeap.set((indexUpdate - 1) / 2, fooNode);

                        indexUpdate = (indexUpdate - 1) / 2;

                    }
                    /////////////////////////
                    //flip indices

                }

            }
        }
    }





    /**
     * insertNode(City in)
     * Insert a City into the heap.
     * Time Complexity - O(log(n))
     *
     * @param in - the City to insert.
     */
    public void insertNode(City in) {
        // TODO: implement this method
        minHeap.add(in);
        in.setPosition(minHeap.size() - 1);
        in.setParent(-1);
        in.setTreeStatus(true);

        int indexUpdate = minHeap.size() - 1;

        while(minHeap.get(indexUpdate).getMinDist() <= minHeap.get((indexUpdate - 1) / 2).getMinDist()) {
            ////////////////////////
            boolean done = false;
            if (minHeap.get(indexUpdate).getMinDist() == minHeap.get((indexUpdate - 1) / 2).getMinDist()) {
                if (minHeap.get(indexUpdate).getName() < minHeap.get((indexUpdate - 1) / 2).getName()) {
                    minHeap.get(indexUpdate).setPosition((indexUpdate - 1) / 2);
                    minHeap.get((indexUpdate - 1) / 2).setPosition(indexUpdate);

                    //flip city values
                    City fooNode = minHeap.get(indexUpdate);
                    minHeap.set(indexUpdate, minHeap.get((indexUpdate - 1) / 2));
                    minHeap.set((indexUpdate - 1) / 2, fooNode);

                    indexUpdate = (indexUpdate - 1) / 2;
                    done = true;
                } else {
                    break;
                }
            }
            if (!done) {
                minHeap.get(indexUpdate).setPosition((indexUpdate - 1) / 2);
                minHeap.get((indexUpdate - 1) / 2).setPosition(indexUpdate);

                //flip city values
                City fooNode = minHeap.get(indexUpdate);
                minHeap.set(indexUpdate, minHeap.get((indexUpdate - 1) / 2));
                minHeap.set((indexUpdate - 1) / 2, fooNode);

                indexUpdate = (indexUpdate - 1) / 2;

            }
        }
    }

    /**
     * findMin()
     * Time Complexity - O(1)
     *
     * @return the minimum element of the heap.
     */
    public City findMin() {
        // TODO: implement this method
        return minHeap.get(0);
    }

    /**
     * extractMin()
     * Time Complexity - O(log(n))
     *
     * @return the minimum element of the heap, AND removes the element from said heap.
     */
    public City extractMin() {
        // TODO: implement this method
        //base case
        if(minHeap.size() == 0)
        {
            return new City(-9999);
        }
        //base case
        if(minHeap.size() == 1)
        {
            minHeap.get(0).setPosition(-1);
            return minHeap.remove(0);
        }

        //remove root
        City returnCity = minHeap.get(0);
        minHeap.set(0, minHeap.get(minHeap.size() - 1));                ////Might need to set the position here to 0
        minHeap.get(0).setPosition(0);
        minHeap.remove(minHeap.size() - 1);

        heapifyDown(0);

        returnCity.setTreeStatus(false);
        return returnCity;
    }

    /**
     * Basic heapify down function starting from given index
     * @param index
     */
    public void heapifyDown(int index){
        int parent, childOne, childTwo;
        parent = index;
        childOne = index * 2 + 1;
        childTwo = index * 2 + 2;

        boolean flagSmallerFound = false;

        if(childOne < minHeap.size() && minHeap.get(childOne).getMinDist() < minHeap.get(parent).getMinDist()){
            parent = childOne;
            //flagSmallerFound = true;

            minHeap.get(index).setPosition(parent);
            minHeap.get(parent).setPosition(index);

            City fooNode = minHeap.get(index);
            minHeap.set(index, minHeap.get(parent));
            minHeap.set(parent, fooNode);

            heapifyDown(parent);
        }

        if(childTwo < minHeap.size() && minHeap.get(childTwo).getMinDist() < minHeap.get(parent).getMinDist()){
            parent = childTwo;
            //flagSmallerFound = true;

            minHeap.get(index).setPosition(parent);
            minHeap.get(parent).setPosition(index);

            City fooNode = minHeap.get(index);
            minHeap.set(index, minHeap.get(parent));
            minHeap.set(parent, fooNode);

            heapifyDown(parent);
        }
//        if(flagSmallerFound)
//        {
//            //changeNodes(index, parent);
//            minHeap.get(index).setPosition(parent);
//            minHeap.get(parent).setPosition(index);
//
//            City fooNode = minHeap.get(index);
//            minHeap.set(index, minHeap.get(parent));
//            minHeap.set(parent, fooNode);
//
//            heapifyDown(parent);
//        }
    }

    /**
     * delete(int index)
     * Deletes an element in the min-heap given an index to delete at.
     * Time Complexity - O(log(n))
     *
     * @param index - the index of the item to be deleted in the min-heap.
     */
    public void delete(int index) {
        // TODO: implement this method

        if(minHeap.size() == 0){
            return;
        }
        else{
            int position = minHeap.get(index).getPosition();

            minHeap.set(index, minHeap.get(minHeap.size() - 1));
            minHeap.get(index).setPosition(position);
            minHeap.remove(minHeap.size() - 1);
            heapifyDown(index);
        }
    }

    /**
     * changeKey(City r, int newDist)
     * Changes minDist of City s to newDist and updates the heap.
     * Time Complexity - O(log(n))
     *
     * @param r       - the City in the heap that needs to be updated.
     * @param newDist - the new cost of City r in the heap (note that the heap is keyed on the values of minDist)
     */
    public void changeKey(City r, int newDist) {
        // TODO: implement this method
        if(minHeap.size() == 1)
        {
            minHeap.get(r.getPosition()).setMinDist(newDist);
        }
        else{
            int oldDist = r.getMinDist();
            minHeap.get(r.getPosition()).setMinDist(newDist);
            int indexUpdate = r.getPosition();

            if(oldDist > newDist){
                while(minHeap.get(indexUpdate).getMinDist() < minHeap.get((indexUpdate - 1) / 2).getMinDist()){
                    //flip indices
                    minHeap.get(indexUpdate).setPosition((indexUpdate - 1) / 2);
                    minHeap.get((indexUpdate - 1) / 2).setPosition(indexUpdate);

                    //flip cities
                    City fooNode = minHeap.get(indexUpdate);
                    minHeap.set(indexUpdate, minHeap.get((indexUpdate - 1) / 2));
                    minHeap.set((indexUpdate - 1) / 2, fooNode);

                    indexUpdate = (indexUpdate - 1) / 2;
                }
            }

            if(oldDist < newDist){
                while((indexUpdate * 2) + 2 <= minHeap.size() && (minHeap.get(indexUpdate).getMinDist() > minHeap.get((indexUpdate * 2) + 1).getMinDist() ||
                        minHeap.get(indexUpdate).getMinDist() > minHeap.get((indexUpdate * 2) + 2).getMinDist() )
                        ){
                    if(minHeap.get(indexUpdate).getMinDist() > minHeap.get((indexUpdate * 2) + 1).getMinDist()){
                        //flip indices
                        minHeap.get(indexUpdate).setPosition((indexUpdate * 2) + 1);
                        minHeap.get((indexUpdate * 2) + 1).setPosition(indexUpdate);

                        //flip cities
                        City fooNode = minHeap.get(indexUpdate);
                        minHeap.set(indexUpdate, minHeap.get((indexUpdate * 2) + 1));
                        minHeap.set((indexUpdate * 2) + 1, fooNode);

                        indexUpdate = (indexUpdate * 2) + 1;
                        continue;
                    }
                    if(minHeap.get(indexUpdate).getMinDist() > minHeap.get((indexUpdate * 2) + 2).getMinDist()){
                        //flip indices
                        minHeap.get(indexUpdate).setPosition((indexUpdate * 2) + 2);
                        minHeap.get((indexUpdate * 2) + 2).setPosition(indexUpdate);

                        //flip cities
                        City fooNode = minHeap.get(indexUpdate);
                        minHeap.set(indexUpdate, minHeap.get((indexUpdate * 2) + 2));
                        minHeap.set((indexUpdate * 2) + 2, fooNode);

                        indexUpdate = (indexUpdate * 2) + 2;
                        continue;
                    }
                }
            }



        }

    }

    public int getSize() { return minHeap.size();};

    public String toString() {
        String output = "";
        for (int i = 0; i < minHeap.size(); i++) {
            output += minHeap.get(i).getName() + " ";
        }
        return output;
    }

///////////////////////////////////////////////////////////////////////////////
//                           DANGER ZONE                                     //
//                everything below is used for grading                       //
//                      please do not change :)                              //
///////////////////////////////////////////////////////////////////////////////

    public ArrayList<City> toArrayList() {
        return minHeap;
    }
}
