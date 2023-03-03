package cn.zy.codegen.qlexpress.test;

import com.ql.util.express.Operator;

public class IFOperator extends Operator {


    public IFOperator(String name) {
        this.name = name;
        this.isPrecise = true;
    }

    @Override
    public Object executeInner(Object[] list) {
        boolean bool = (boolean) list[0];
        if (bool) {
            return list[1];
        } else {
            return list[2];
        }
    }
}
