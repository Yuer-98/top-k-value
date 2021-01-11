import java.util.*;

public class TestTopK {
    public static void main(String[] args) throws Exception {
        /**使用堆实现topK*/
        TopK topK = new TopK(20,false);
        List<int[]> datas = new ArrayList<>();
        for(int i=0;i<10;i++){
            datas.add(generateDatas());
        }
        System.out.println("BEGIN!");
        for(int i=0;i<10;i++){
            //System.out.println("in " + datas[i]);
            for(int j=0;j<1000000;j++){
                topK.addData(datas.get(i)[j]);
            }
        }
        System.out.println(topK.getTopK());


        /**测试批量加入*/
//        List<Integer> datas2 = new ArrayList<>();
//        Random rand = new Random();
//        for(int i=0;i<100000;i++){
//            datas2.add(rand.nextInt(1000000));
//        }
//        TopK topK = new TopK(10);
//        topK.addData(datas2);
//        System.out.println(topK.getTopK());


        /**使用排序的方式获取topK*/
//        TopKSort topKSort = new TopKSort();
//        List<int[]> datas = new ArrayList<>();
//        for(int i=0;i<10;i++){
//            datas.add(generateDatas());
//        }
//        System.out.println("BEGIN!");
//        for(int i=0;i<10;i++){
//            //System.out.println("in " + datas[i]);
//            for(int j=0;j<1000000;j++){
//                topKSort.add(datas.get(i)[j]);
//            }
//        }
//        System.out.println("GET!");
//        System.out.println(topKSort.getTop10());
    }
    public static int[] generateDatas(){
        int[] datas = new int[1000000];
        Random rand = new Random();
        for(int i=0;i<1000000;i++){
            datas[i] = rand.nextInt(10000000);
        }
        return datas;
    }
}

