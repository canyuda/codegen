package cn.zy.codegen.qlexpress.test;


import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

public class Example1 {
    @SneakyThrows
    @Test
    void test1() {
        // 官方demo
        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        context.put("税前工资", 10000.111);
        context.put("竞业金", 10);
        context.put("社保补贴", 10);
        context.put("餐补", 10);
        runner.addFunction("IF", new IFOperator("IF"));
        String express = "税前工资 - 竞业金 - 社保补贴 - 餐补 - (税前工资 * IF(税前工资 < 5000, 0.1, IF(税前工资 < 8000, 0.2, 0.3)))";
        Object r = runner.execute(express, context, null, true, false);
        System.out.println(r);
    }
}
