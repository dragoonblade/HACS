package HACS.Iterators;


import HACS.Iterators.Lists.SolutionList;

public class SolutionIterator extends ListIterator {

    public SolutionIterator(SolutionList list) {
        super(list);
    }

    @Override
    public boolean hasNext() {
        return list.list.size() > currentIndex;
    }

    @Override
    public int getCurrentIndex() { return currentIndex; }

    @Override
    public Object next() {
        Object object = list.list.get(currentIndex);
        currentIndex = currentIndex + 1;
        return object;
    }

    @Override
    public void moveToHead() {
        currentIndex = 0;
    }

    @Override
    public void remove() {
        list.list.remove(currentIndex);
    }
}
