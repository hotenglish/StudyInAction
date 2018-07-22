package io.github.viscent.mtpattern.ch3.immutableobject;

/**
 * 与运维中心（Operation and Maintenance Center）对接的类
 * 模式角色：ImmutableObject.Manipulator
 */
public class OMCAgent extends Thread {

    public static volatile boolean isTableModificationMsg=false;

    public static volatile String updatedTableName=null;

    public static volatile int count=0;

    @Override
    public void run() {
        System.out.println(this.getName()+" Thread is processing");
        while(true){
            testCaseSelect();
            System.out.println("isTableModificationMsg="+isTableModificationMsg+" updatedTableName="+updatedTableName);
            //省略其它代码
            /*
             * 从与OMC连接的Socket中读取消息并进行解析,
             * 解析到数据表更新消息后,重置MMSCRouter实例。
             */
            if(isTableModificationMsg){
                if("MMSCInfo".equals(updatedTableName)){
                    MMSCRouter.setInstance(new MMSCRouter());
                    count++;
                    System.out.println("exeucte count++ and current count value="+ count);
                }
            }
            //省略其它代码
        }
    }

    //模拟选择场景
    private void testCaseSelect(){
        int x=1+(int)(Math.random()*4);
        if(x==1){
            isTableModificationMsg=true;
            updatedTableName="MMSCInfo";
        }else if(x==2){
            isTableModificationMsg=true;
            updatedTableName="NoneMMSCInfo";
        }else if(x==3){
            isTableModificationMsg=false;
            updatedTableName="MMSCInfo";
        }else if(x==4){
            isTableModificationMsg=false;
            updatedTableName="NoneMMSCInfo";
        }else{
            System.out.println("error random result X="+x);
        }
    }

}
