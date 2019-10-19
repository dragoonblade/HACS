package HACS.Iterators;


import HACS.Iterators.Lists.ArrayList;

public class CourseIterator extends ListIterator {

    public CourseIterator(ArrayList list) {
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
        if (hasNext()) {
            Object object = list.list.get(currentIndex);
            currentIndex = currentIndex + 1;
            return object;
        }
        return null;
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
