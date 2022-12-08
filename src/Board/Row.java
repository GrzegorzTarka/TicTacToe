package Board;

import java.util.ArrayList;
import java.util.List;

public class Row {

        List<Sign> fields;

        public Row() {
            this.fields = new ArrayList<>();
        }

        public void initialize(int size) {

            for (int i=0; i<size; i++) {
                fields.add(new Sign("-"));
            }
        }

        public void show() {
            for (int i = 0; i < fields.size(); i++) {
                if (i == (fields.size()-1))  System.out.println(fields.get(i).getSign() + " "); // if printing last sign, print new line
                else System.out.print(fields.get(i).getSign() + " ");  // print sign in one line
            }
        }
}



