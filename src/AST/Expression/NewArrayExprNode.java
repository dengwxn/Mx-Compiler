package AST.Expression;

import AST.Basic.ExprNode;

import java.util.ArrayList;

public class NewArrayExprNode extends ExprNode {
    String base;
    int dim;
    ArrayList<ExprNode> param;

    public int getDim() { return dim; }

    public NewArrayExprNode(String b, int d) {
        base = b;
        dim = d;
        param = new ArrayList<>();
    }

    public void addParam(ExprNode p) {
        param.add(p);
    }

    public String getBase() { return base; }

    @Override
    public boolean isLeftValue() {
        return false;
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.printf("new %s:\n", base);
        for (ExprNode expr : param) {
            if (expr != null)
                expr.dump(indent + 4);
            else {
                format(indent + 4);
                System.out.println("null");
            }
        }
    }
}
