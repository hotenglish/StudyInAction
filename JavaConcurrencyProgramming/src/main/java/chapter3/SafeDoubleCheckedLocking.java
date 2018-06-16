package chapter3;

public class SafeDoubleCheckedLocking {
    private volatile static Instance instance;

    public static Instance getInstance() {
        if (instance == null) {
            synchronized (SafeDoubleCheckedLocking.class) {
                if (instance == null)
                    instance = new Instance(); //instance为volatile，现在没问题了
            }
        }
        return instance;
    }

    static class Instance {

        private int var;

        private Instance() {
            var = 6;
        }

        public int getVar() {
            return var;
        }
    }
}
