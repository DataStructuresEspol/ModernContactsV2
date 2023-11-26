
package dsa.contacts.util;

import java.util.List;


public class ViewDisplay<E> {
    private int view = 0;
    private List<E> list;
    
    public ViewDisplay(List<E> list){this.list = list;}
    
    public E current(){
        return list.get(view);
    }
    public E next(){
        view = (view+1)%list.size();
        return current();
    }
    
    public E prev(){
        if (view > 0){view--;}
        else{view = list.size()-1;}
        return current();
    }
    
}
