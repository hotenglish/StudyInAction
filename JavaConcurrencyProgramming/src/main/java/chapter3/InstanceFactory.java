package chapter3;

public class InstanceFactory {
    private static class InstanceHolder {
        private static Instance instance = new Instance();
    }

    public static Instance getInstance() {
        return InstanceHolder.instance; //这里将导致InstanceHolder类被初始化
    }

    static class Instance {

        private int var;

        private Instance() {
            var = 5;
        }

        public int getVar() {
            return var;
        }

    }
}
