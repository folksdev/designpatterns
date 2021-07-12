package designpatterns.behavioral;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class Iterator {

    interface IIterator{
        boolean hasNext();
        Object next();
    }

    interface Container {
        IIterator getIterator();
    }

    class StringCollection implements Container{
        private List<String> strings = new ArrayList<>();

        void addString(String string){
            strings.add(string);
        }

        @Override
        public IIterator getIterator() {
            return new StringCollectionIterator(strings);
        }
    }

    class StringCollectionIterator implements IIterator{
        private List<String> strings;
        private int pos = 0;
        public StringCollectionIterator(List<String> strings) {
            this.strings = strings;
        }

        @Override
        public boolean hasNext() {
            if(pos >= strings.size() ||strings.get(pos) == null){
                return false;
            }
            return true;
        }

        @Override
        public Object next() {
            String string = strings.get(pos);
            pos = pos + 1;
            return string;
        }
    }

    void iteratorDemo(){
        StringCollection stringCollection = new StringCollection();
        stringCollection.addString("Str 1");
        stringCollection.addString("Str 2");
        stringCollection.addString("Str 3");
        stringCollection.addString("Str 4");
        stringCollection.addString("Str 5");

        IIterator iterator = stringCollection.getIterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    public static void main(String[] args) {
        Iterator iterator = new Iterator();
        iterator.iteratorDemo();
    }


}
