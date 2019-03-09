package AST.Expression;

import AST.Basic.ExprNode;

import java.util.ArrayList;

public class FuncCallExprNode extends ExprNode {
    String func;
    ArrayList<ExprNode> params;

    public void addParam(ExprNode p) {
        params.add(p);
    }

    public FuncCallExprNode(String f) {
        func = f;
        params = new ArrayList<>();
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.printf("%s():\n", func);
        params.forEach(p -> p.dump(indent + 4));
    }
}
