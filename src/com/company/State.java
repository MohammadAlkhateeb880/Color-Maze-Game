package com.company;

import java.util.*;


public class State  {
    int n=8;
    int m=8;
    State parent;
    Stack<State> children=new Stack();
    Cell [][]map =new Cell[n][m];
    int xball;
    int yball;
    int cost;
    Queue<String> queue=new LinkedList<>();
    int f;




      State copy(State another){

              this.n=another.n;
              this.m=another.m;
              this.xball=another.xball;
              this.yball= another.yball;
                this.cost=another.cost;
              for ( int i=0;i<another.n;i++ )
                  for (int j=0;j<another.m;j++)
                  {
                    //  System.out.println(another.map[i][j].s);

                      this.map[i][j].s=another.map[i][j].s;
                      this.map[i][j].x=another.map[i][j].x;
                      this.map[i][j].y=another.map[i][j].y;

                  }
       /* for (int i=0 ;i<another.children.size();i++) {
            //System.out.println(another.children.size());
            this.children.push(another.children.get(i));
        }*/

        for(String j: another.queue)
        {
            this.queue.add(j);
        }
           this.parent=another.parent;
           return another;
}

    public State() {

    }

    void initial_state(){
        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++)
            {
                map[i][j]=new Cell();
                map[i][j].x=i;
                map[i][j].y=j;
                map[i][j].s="#";
            }

        map[1][1].s="+";
        map[1][2].s="+";
        map[1][3].s="+";
        map[1][4].s="+";
        map[1][5].s="+";
        map[1][6].s="+";
        map[2][1].s="+";
        map[3][1].s="+";
        map[3][2].s="+";
        map[2][6].s="+";
        map[3][6].s="+";
        map[4][6].s="+";
        map[5][1].s="+";
        map[5][6].s="+";
        map[6][1].s="+";
        map[6][2].s="+";
        map[6][3].s="+";
        map[6][4].s="+";
        map[6][5].s="+";
        map[6][6].s="+";
        map[xball][yball].s="@";

    }
    State(int xball,int yball){
        this.xball=xball;
        this.yball=yball;
    }
    void print(){

        for(int i=0;i<n;i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(map[i][j].s+"  ");
            }
            System.out.println();
        }


        for (String p: next_state()) {
            System.out.println( "You Can Move To : "+p+"  ");

        }
    }

    List<String> next_state(){
        List<String> p=new ArrayList<String>();
        int x1=xball;
        int y1=yball;


        if(!map[x1][y1+1].s.equals("#"))
        {
            p.add("Right");

        }
        if(!map[x1][y1-1].s.equals("#")){
            p.add("Left");

        }
        if(!map[x1+1][y1].s.equals("#")){

            p.add("Down");

        }
        if(!map[x1-1][y1].s.equals("#")){
            p.add("Up");


        }
        return p;
    }

/*State Up() {

    State w= new State();
    w.initial_state();
    w.copy(this);
        w.parent = copy(this);
        while (!w.map[w.xball-1][w.yball].s.equals("#")){
            int i=w.xball;
            w.map[i][w.yball].s="*";
            i=w.xball--;
        }

        w.map[w.xball][w.yball].s="@";


        return w;



}*/
/*
State Down(){
    try {
        this.parent = (State) this.clone();
        State w=new State();
        w.initial_state();
        w.copy(this);
        while (!w.map[w.xball+1][w.yball].s.equals("#")){

            int i=w.xball;
            w.map[i][w.yball].s="*";
            i=w.xball++;
        }
        w.map[w.xball][w.yball].s="@";

        return w;
    } catch (CloneNotSupportedException e) {
        e.printStackTrace();
    }
   return null;
}
State Left(){

    try {
        State w=new State();
        w.initial_state();
        w.copy(this);
        this.parent = (State) this.clone();
        while (!w.map[w.xball][w.yball-1].s.equals("#")){

            int i=w.yball;
            w.map[w.xball][i].s="*";
            i=w.yball--;
        }
        w.map[w.xball][w.yball].s="@";

        return w;

    } catch (CloneNotSupportedException e) {
        e.printStackTrace();
    }
    return null;
}
State Right(){
    try {
        State w= new State();
        w.initial_state();
        w.copy(this);
        this.parent = (State) this.clone();
        while (!w.map[w.xball][w.yball+1].s.equals("#")){

            int i=w.yball;
            w.map[w.xball][i].s="*";
            i=w.yball++;
        }
        System.out.println("XRight : "+w.xball+"YRight  ;  "+yball);
        w.map[w.xball][w.yball].s="@";

        return w;
    } catch (CloneNotSupportedException e) {
        e.printStackTrace();
    }
return null;
}*/
Stack<State> translate(){
    List<String> l=next_state();

    for(String h : l)
    {
        switch (h)
        {
            case "Up":
            {
                queue.add("Up");
                State w= new State();
                w.initial_state();
                w.copy(this);
                w.parent=this;
                while (!w.map[w.xball-1][w.yball].s.equals("#")){
                    int i=w.xball;
                    w.map[i][w.yball].s="*";
                    i=w.xball--;
                    w.cost++;
                }

                w.map[w.xball][w.yball].s="@";
                w.f=w.cost+w.cellsEmpty();
                children.push(w);

                break;
            }
            case "Down":
            {
                queue.add("Down");
                System.out.println("D  D  D  D ");
                State w=new State();
                w.initial_state();
                w.copy(this);
                w.parent=this;
                while (!w.map[w.xball+1][w.yball].s.equals("#")){

                    int i=w.xball;
                    w.map[i][w.yball].s="*";
                    i=w.xball++;
                    w.cost++;
                }
                w.map[w.xball][w.yball].s="@";
                w.f=w.cost+w.cellsEmpty();
                children.push(w);
                break;
            }
            case "Left":
            {
                queue.add("Left");
                System.out.println("L  L  L  L");
                State w=new State();
                w.initial_state();
                w.copy(this);
                w.parent=this;
                while (!w.map[w.xball][w.yball-1].s.equals("#")){

                    int i=w.yball;
                    w.map[w.xball][i].s="*";
                    i=w.yball--;
                    w.cost++;
                }
                w.map[w.xball][w.yball].s="@";
                w.f=w.cost+w.cellsEmpty();
                children.push(w);

                break;
            }
            case "Right":
            {
                queue.add("Right");
                System.out.println("R  R  R  R");
                State w= new State();
                w.initial_state();
                w.copy(this);
                w.parent=this;
                while (!w.map[w.xball][w.yball+1].s.equals("#")){
                    int i=w.yball;
                    w.map[w.xball][i].s="*";
                    i=w.yball++;
                    w.cost++;
                }
                w.map[w.xball][w.yball].s="@";
                w.f=w.cost+w.cellsEmpty();
                children.push(w);
                break;
            }

        }

    }
    return this.children;
}
   /* State move() throws CloneNotSupportedException {
        Scanner sc=new Scanner(System.in);
        String m=sc.nextLine();
        String movement=new String();
       // translate();
        switch (m){
            case "w":
            {
                if(next_state().contains("Up"))
                {
                    movement="Up";
                    /*queue.add(movement);
                    children.add(this.Up());*/
    /*  this.parent = (State) this.clone();
                    while (!map[xball-1][yball].s.equals("#")){
                        int i=xball;
                        map[i][yball].s="*";
                        i=xball--;
                    }

                    map[xball][yball].s="@";*/
                /*    break;

                }
                else
                    System.out.println("You Can't Move To This Position !!");
                return this;

            }
            case "s":
            {
                if(next_state().contains("Down"))
                {
                    movement="Down";
                    queue.add(movement);
                    children.add(this.Down());
                   /* this.parent = (State) this.clone();
                    while (!map[xball+1][yball].s.equals("#")){

                        int i=xball;
                        map[i][yball].s="*";
                        i=xball++;
                    }
                    map[xball][yball].s="@";*/
                  /*  break;
                }
                else
                    System.out.println("You Can't Move To This Position !!");
                return this;
            }
            case "a":
            {
                if(next_state().contains("Left"))
                {
                    movement="Left";
                    queue.add(movement);
                    this.parent = (State) this.clone();
                    /*while (!map[xball][yball-1].s.equals("#")){

                        int i=yball;
                        map[xball][i].s="*";
                        i=yball--;
                    }
                    map[xball][yball].s="@";*/
                   /* break;
                }
                else
                    System.out.println("You Can't Move To This Position !!");
                return this;
            }
            case "d":
            {
                if(next_state().contains("Right"))
                {
                    movement="Right";
                    queue.add(movement);
                    this.parent = (State) this.clone();
                   /* while (!map[xball][yball+1].s.equals("#")){

                        int i=yball;
                        map[xball][i].s="*";
                        i=yball++;
                    }
                    map[xball][yball].s="@";*/
                    /*break;
                }
                else
                    System.out.println("You Can't Move To This Position !!");
                return this;
            }
            default:
                System.out.println("Your Choice Is Error !!");

        }

        return this;
    }

*/
   static void print_path(State m){
        LinkedList<State> l=new LinkedList<>();
        State q=m;
        while (q!=null){
            l.addFirst(q);

            q=q.parent;

        }
        for (State a:l) {
            System.out.println("__________  Cost : "+a.cost);
            a.print();
        }

    }

    boolean target(){
        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++)
                if(map[i][j].s.equals("+"))
                    return false;
        System.out.println("You solve It (:");
        return true;
    }

    int cellsEmpty(){
       int p=0;
        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++)
                if(this.map[i][j].s=="+")
                    p++;
                return p;
    }
@Override
  public   boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj== null || this.getClass() != obj.getClass())
            return  false;
        State q= (State)obj;
        for (int i=0;i<n;i++)
            for(int j=0;j<m;j++)
                if(!this.map[i][j].s.equals( q.map[i][j].s))
                    return false;
               //return this.cost==((State) obj).cost;
return true;
    }
}
