package com.company;
import java.util.*;
public class Game {

    LinkedList<State> states=new LinkedList<>();
    State s=new State(1,4);
    //Stack<Integer> a=new Stack<>();
    Game(){

    }
     void start_game()  {
        s.initial_state();
         s.print();
        State n=new State();
        n.initial_state();
               n = ASTAR(s);
        System.out.println(n.cost);
        n.print();

        System.out.println("__________PATH_________________________________");
        State.print_path(n);

    }


    State Dfs(State node){

        LinkedList<State> visited=new LinkedList<>();
        Stack<State> open = new Stack<>() ;
        open.push(node);
        while (!open.isEmpty()){
            //open.peek().print();
            State a=open.pop();
            visited.add(a);
            a.print();

            if(a.target())
            {
                System.out.println("goooooooooooooooooool");
                return a;
            }

                Stack<State> children = a.translate();
                for (State q:children) {
                    if (q.target()){
                        return q;
                    }
                    if (!visited.contains(q))
                        open.push(q);
                }
        }

    return  null;
    }
    State Bfs(State node){

        LinkedList<State> visited=new LinkedList<>();
        LinkedList<State> open = new LinkedList<>();
        open.push(node);
        while (!open.isEmpty()){
            State a=open.poll();
            visited.add(a);
            a.print();
            if(a.target())
            {
                return a;
            }

            Stack<State> children = a.translate();
            for (State q:children) {
                if (q.target()){
                    return q;
                }
               if (!visited.contains(q))
                    open.add(q);
            }
        }
        return  null;
    }


    State UNIFORM(State node){
        LinkedList<State> visited=new LinkedList<>();
        PriorityQueue<State> p=new PriorityQueue<>(new CoropreterState());
        p.add(node);

        while (!p.isEmpty()){
            State a=p.peek();
            p.poll();
            visited.add(a);
            System.out.println("node.cost ___ : "+node.cost+  "____ a.cost : "+a.cost );
            if(a.target())
            {
               System.out.println("gooooooooooooooooool");
                a.print();
                return a;

            }
            else{
            Stack<State> children = a.translate();

            for (State q:children) {
                if(!visited.contains(q))
                    p.add(q);
                else{

                    for (State z:visited) {
                        if (z.cost>q.cost){
                            visited.remove(z);
                            visited.add(q);
                            p.add(q);
                        }
                    }

                }


            }
            }
           // System.out.println("sssizeee : "+visited.size());
        }

        return null;
    }
    State ASTAR(State node){
        LinkedList<State> visited=new LinkedList<>();
        PriorityQueue<State> p=new PriorityQueue<>(new CoropreterState());
        p.add(node);

        while (!p.isEmpty()){
            State a=p.peek();
            p.poll();
            visited.add(a);
            System.out.println("node.cost ___ : "+node.cost+  "____ a.cost : "+a.cost );
            if(a.target())
            {
                System.out.println("gooooooooooooooooool");
                a.print();
                return a;
            }
            else{
                Stack<State> children = a.translate();
                for (State q:children) {
                    if(!visited.contains(q))
                        p.add(q);
                    else{

                        for (State z:visited) {
                            if (z.f>q.f){
                                visited.remove(z);
                                visited.add(q);
                                p.add(q);
                            }
                        }

                    }


                }
            }
        }

        return null;
    }

}
