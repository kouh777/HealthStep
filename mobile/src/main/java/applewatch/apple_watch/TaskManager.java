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

    // manage tasks
    private Vector<Task> FTasks;

    // add task
    public void addList(Task add_task){
        Class clname = add_task.getClass();
        boolean add_flg = true;     // add flag
        int size = FTasks.size();

        // if add_task doesn't exist in FTasks
        for (int i = 0; i < size; i++) {
            if (FTasks.elementAt(i).getClass() == clname) {
                add_flg = false;
                Log.d("TM::addList","double class");
                break;
            }
        }

        if(add_flg) {
            for (int i = 0; i < size; i++) {
                if (FTasks.elementAt(i).GetPriority() <= add_task.GetPriority()) {
                    FTasks.insertElementAt(add_task, i);
                    Log.d("TM::addList(inLoop)",add_task.toString());
                    return;
                }
            }
            FTasks.addElement(add_task);
            Log.d("TM::addList(outsideLoop)",add_task.toString());
        }
    }

    public void Initialize(){

    }

    public void update(){
        int size = FTasks.size();
        for(int i = 0; i < size; i++){
            if( FTasks.elementAt(i) != null) {
                if( !FTasks.elementAt(i).move() ){
                    FTasks.elementAt(i).update();
                }
                if( FTasks.elementAt(i).move() ){
                    Log.d("TM::update(remove)",FTasks.elementAt(i).toString());
                    FTasks.removeElementAt(i);
                    break;
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
        for(int i = 0; i < size; i++){
            if (FTasks.elementAt(i) != null)
                FTasks.elementAt(i).touch(event);
        }
    }

    public void Clear(){

    }

}