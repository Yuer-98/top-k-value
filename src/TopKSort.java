import java.util.*;

/**这是使用排序的方法获取topK*/
class TopKSort {
    List<Integer> list;
    public TopKSort(){
        this.list = new ArrayList<>(10000000);
    }
    public void add(int data){
        this.list.add(data);
    }
    public List<Integer> getTop10(){
        List<Integer> ans = new ArrayList<>();
        Collections.sort(list);
        for(int i=0;i<10;i++){
            ans.add(list.get(i));
        }
        return ans;
    }
}
