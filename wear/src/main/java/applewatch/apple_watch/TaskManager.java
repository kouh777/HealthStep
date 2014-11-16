package applewatch.apple_watch;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import java.util.Vector;

/**
 * Created by KOUHO on 2014/11/13.
 */

public class TaskManager {

    // singleton
    private static TaskManager instance = new TaskManager();
    private TaskManager(){ FTasks = new Vector<Task>(); }
    public static TaskManager getInstance(){
        return instance;
    }

    // manage tasks with vector
    private Vector<Task> FTasks;

    // add task
    public void addList(Task add_task){
        int size = FTasks.size();
        boolean add_flg = true;

        for (int i = 0; i < size; i++) {
            if (FTasks.elementAt(i).GetPriority() <= add_task.GetPriority()) {
                FTasks.insertElementAt(add_task, i);
                Log.d("TM::addList(inLoop)",add_task.toString());
                add_flg = false;
                break;
            }
        }
        if( add_flg ) {
            FTasks.addElement(add_task);
            Log.d("TM::addList(outsideLoop)",add_task.toString());
        }
    }

    public void Initialize(){

    }

    public void update(){
        for(int i = 0; i < FTasks.size(); i++){
            if( FTasks.elementAt(i) != null) {
                if( !FTasks.elementAt(i).move() ){
                    FTasks.elementAt(i).update();
                }
                if( FTasks.elementAt(i).move() ){
                    Log.d("TM::update(remove)", FTasks.elementAt(i).toString());
                    FTasks.removeElementAt(i--);
                }
            }
        }
    }

    public void draw(Canvas c){
        int size = FTasks.size();
        for(int i = 0; i < size; i++){
            if (FTasks.elementAt(i) != null)
                FTasks.elementAt(i).draw(c);
        }
    }

    public void touch(MotionEvent event){
        int size = FTasks.size();
        for (int i = 0; i < size; i++) {
            if (FTasks.elementAt(i) != null) {
//                Log.d("TM::touch_before",FTasks.elementAt(i).toString());
                FTasks.elementAt(i).touch(event);
//                Log.d("TM::touch_after",FTasks.elementAt(i).toString());
            }
        }
    }

    public void Clear(){

    }

}