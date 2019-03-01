package day11.exec;


import java.util.EventListener;
import java.util.EventObject;

public class Stocks {
    gouper up;
    godowner down;
    public Stocks() {
    }

    public void addGoupListener(gouper up) {
        this.up = up;
    }

    public void addGodownListenter(godowner down) {
        this.down = down;
    }

    public void goup() {
        goupEvent e = new goupEvent(this);
        up.goup(e);
    }

    public void godown() {
        godownEvent e = new godownEvent(this);
        down.godown(e);
    }
    

    public static void main(String[] args) {
        Stocks stocks = new Stocks();
        stocks.addGoupListener(new gouper());
        stocks.goup();
        stocks.addGodownListenter(new godowner());
        stocks.godown();
    }
}


class gouper implements goup {
    public void goup(goupEvent e) {
        // System.out.print(e.getSource().toString());
        // if (e.getSource().toString() == "Stocks") {
        System.out.println("股市升，请买入！");
        // }
    }
}


class godowner implements godown {
    public void godown(godownEvent e) {
        //    if (e.getSource().getClass()) {
        System.out.println("股市跌，请卖出！");
        //   }
    }
}


class goupEvent extends EventObject {
    public goupEvent(Object source) {
        super(source);
    }
}


class godownEvent extends EventObject {
    public godownEvent(Object source) {
        super(source);
    }
}


interface goup extends EventListener {
    void goup(goupEvent e);
}


interface godown extends EventListener {
    void godown(godownEvent e);
}
