import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.logging.Logger;

/**最多保留2000000个top值*/
class TopK {
    private int size;
    boolean maxFlag;
    private PriorityQueue<Integer> heap;
    private Logger topKLogger = Logger.getLogger("topKLogger");

    /**默认创建取最大10个*/
    public TopK(){
        this.size = 10;
        this.maxFlag = true;
        heap = new PriorityQueue<Integer>(10);
    }

    /**创建取最大size个*/
    public TopK(int size) throws Exception{
        if(size>2000000){
            topKLogger.warning("top值个数过多");
            throw new Exception("top值个数过多");
        }
        else{
            this.size = size;
            this.maxFlag = true;
            heap = new PriorityQueue<Integer>(size);
        }
    }

    /**取size个，最大还是最小可指定*/
    public TopK(int size,boolean maxFlag) throws Exception{
        if(size>2000000){
            topKLogger.warning("top值个数过多");
            throw new Exception("top值个数过多");
        }
        else{
            this.size = size;
            this.maxFlag = maxFlag;
            //最大
            if(maxFlag){
                heap = new PriorityQueue<Integer>(size);
            }
            //最小
            else{
                heap = new PriorityQueue<Integer>(size,new Comparator<Integer>(){ //大顶堆，容量11
                    @Override
                    public int compare(Integer i1,Integer i2){
                        return i2-i1;
                    }
                });
            }
        }
    }
    /**加入数据*/
    public boolean addData(int data){
        if(heap.size()<this.size){
            heap.add(data);
            return true;
        }
        else{
            if((maxFlag&&data<heap.peek())||(!maxFlag&&data>heap.peek())){
                return false;
            }
            else{
                heap.poll();
                heap.add(data);
                return true;
            }
        }
    }
    /**批量加入数据*/
    public boolean addData(List<Integer> datas){
        if(datas.isEmpty()){
            return false;
        }
        int spare = this.size - heap.size();
        for(int i=0;i<spare&&i<datas.size();i++){
            heap.add(datas.get(i));
        }
        for(int i=spare;i<datas.size();i++){
            if((maxFlag&&datas.get(i)<heap.peek())||(!maxFlag&&datas.get(i)>heap.peek())){
                continue;
            }
            else{
                heap.poll();
                heap.add(datas.get(i));
            }
        }
        return true;
    }
    /**取出当前TOPK*/
    public List<Integer> getTopK(){
        List<Integer> ans = new ArrayList<>();
        for(Integer i : heap){
            ans.add(i);
        }
        return ans;
    }

    /**改为获得最大K个值
     * 此操作会清空所有已存在数据
     * 谨慎使用*/
    public boolean changeToMax(){
        topKLogger.warning("此操作会清空所有已存在数据");
        heap.clear();
        if(this.maxFlag==true){
            topKLogger.warning("已经是取最大k个值");
            return false;
        }
        else{
            this.maxFlag = true;
            return true;
        }
    }

    /**改为获得最小K个值
     * 此操作会清空所有已存在数据
     * 谨慎使用*/
    public boolean changeToMin(){
        topKLogger.warning("此操作会清空所有已存在数据");
        heap.clear();
        if(this.maxFlag==false){
            topKLogger.warning("已经是取最小k个值");
            return false;
        }
        else{
            this.maxFlag = false;
            return true;
        }
    }

    /**改变top值个数size
     * 此操作会清空所有已存在数据
     * 谨慎使用*/
    public boolean changeSize(int size){
        if(size>2000000){
            topKLogger.warning("top值个数过多");
            return false;
        }
        else{
            this.size = size;
            heap = new PriorityQueue<Integer>(size);
            return true;
        }
    }
}
